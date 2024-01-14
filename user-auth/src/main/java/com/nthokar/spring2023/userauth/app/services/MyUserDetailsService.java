package com.nthokar.spring2023.userauth.app.services;

import com.nthokar.spring2023.userauth.app.UserDetails;
import com.nthokar.spring2023.userauth.app.entities.*;
import com.nthokar.spring2023.userauth.app.repos.*;
import com.nthokar.spring2023.userauth.infrastructure.UserController;
import com.nthokar.spring2023.userauth.infrastructure.clients.FormClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private InterestService interestService;
    @Autowired
    FormSubscribeRepository formSubscribeRepo;
    @Autowired
    RespondentRepository respondentRepository;
    @Autowired
    FormClient formClient;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User with email = "+email+" not exist!"));
        return new UserDetails(user);
    }

    public void register(User user) {
        if (userRepo.findByEmail(user.getEmail()).isPresent())
            throw new RuntimeException("user with that email already exists");
        if (user instanceof Manager) {
            var company = companyService.create(String.format("%s's company", user.getEmail()), (Manager) user);
            ((Manager) user).setCompany(company);
            userRepo.save(user);
        }
        userRepo.save(user);
    }

    public User getUser(String email) {
        var user = userRepo.findByEmail(email);
        if (user.isPresent()) return user.get();
        throw new RuntimeException("no user");
    }

    public Company getCompany(String email) {
        var user = userRepo.findByEmail(email);
        if (user.isPresent() && user.get() instanceof Manager manager) {
            return manager.getCompany();
        }
        else if (user.isPresent() && user.get() instanceof Respondent) {
            throw new RuntimeException("Respondent can't be in company");
        }
        else {
            throw new RuntimeException("no user");
        }
    }

    public void setImage(User user,String image) {
        user.setImage(image);
        userRepo.save(user);
    }

    public void setAge(User user,Integer age) {
        user.setAge(age);
        userRepo.save(user);
    }


    public void setEducation(User user,String education) {
        user.setEducation(education);
        userRepo.save(user);
    }


    public void setInterests(User user,List<String> interestsNames) {
        if (!(user instanceof Respondent respondent)) throw new RuntimeException();
        respondent.setInterest(new ArrayList<>());
        for (var interestName:interestsNames) {
            if (interestService.isExist(interestName))
            {
                var interest = interestService.get(interestName);
                interest.ifPresent(respondent::addInterest);
            }
            else {
                throw new RuntimeException("This interest dose not exist");
            }
        }
        userRepo.save(user);

    }

    public void subscribe(String username, Long formId) {
        var user = getUser(username);
        if (!(user instanceof Respondent respondent)) {
            throw new RuntimeException("this user cant subscribe to forms");
        }
        if (respondent.getUpcoming().stream().anyMatch(x -> x.getId().equals(formId)))
        if (formSubscribeRepo.existsById(formId)) {
            var formSubscribe = formSubscribeRepo.findById(formId).get();
            respondent.subscribe(formSubscribe);
            userRepo.save(respondent);
        }
        else {
            var formSubscribe = new FormSubscribe(formId, false);
            formSubscribeRepo.save(formSubscribe);
            respondent.subscribe(formSubscribe);
            userRepo.save(respondent);
        }
    }

    public void unsubscribe(String username, Long formId) {
        var user = getUser(username);
        if (!(user instanceof Respondent respondent)) {
            throw new RuntimeException("this user cant subscribe to forms");
        }
        if (respondent.getUpcoming().stream().anyMatch(x -> Objects.equals(x.getId(), formId))) {
            respondent.unsubscribe(formId);
            userRepo.save(respondent);
        }
    }

    public void update(User userOld, User user) {
        if (userOld instanceof Respondent && user instanceof Respondent
                || userOld instanceof Manager && user instanceof Manager) {
            user.setId(userOld.getId());
            userRepo.save(user);
        }
    }

    public void updateStatus() {
        var iter = formSubscribeRepo.findAll();
        List<FormSubscribe> subscribes = StreamSupport.stream(iter.spliterator(), false)
                .toList();
        var forms = subscribes.stream().map(x -> x.getId()).toList();
        var ids = formClient.get(forms).getBody();
        for (var id:ids) {
            var form = formSubscribeRepo.findById(id);
            if (form.isPresent()) {
                form.get().isAvailable = true;
                formSubscribeRepo.save(form.get());
                var usersToNotify = respondentRepository.findByUpcomingContaining(form.get());
                sendNotification(usersToNotify, "ASdkklakjs;dlka;lsdk;aklsd");
            }
        }
    }

    public void sendNotification(List<Respondent> users,String notify) {

    }
}