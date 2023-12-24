package com.nthokar.spring2023.userauth.app.services;

import com.nthokar.spring2023.userauth.app.UserDetails;
import com.nthokar.spring2023.userauth.app.entities.*;
import com.nthokar.spring2023.userauth.app.repos.InterestRepository;
import com.nthokar.spring2023.userauth.app.repos.UserRepository;
import com.nthokar.spring2023.userauth.infrastructure.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private InterestService interestService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User with email = "+email+" not exist!"));
        return new UserDetails(user);
    }

    public void register(User user) {
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
        }
    }

    public void update(User userOld, User user) {
        if (userOld instanceof Respondent && user instanceof Respondent
                || userOld instanceof Manager && user instanceof Manager) {
            user.setId(userOld.getId());
            userRepo.save(user);
        }
    }
}