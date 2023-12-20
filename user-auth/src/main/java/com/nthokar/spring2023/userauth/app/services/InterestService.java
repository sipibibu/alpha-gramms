package com.nthokar.spring2023.userauth.app.services;

import com.nthokar.spring2023.userauth.app.entities.Interest;
import com.nthokar.spring2023.userauth.app.repos.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterestService {
    @Autowired
    InterestRepository interestRepo;

    public void registerInterest(String name) {
        if (interestRepo.existsByName(name)) throw new RuntimeException("already known");
        interestRepo.save(new Interest(name));
    }

    public List<Interest> getAllInterests() {
        return (List<Interest>) interestRepo.findAll();
    }

    public Optional<Interest> get(String name) {
        return interestRepo.findByName(name);
    }

    public boolean isExist(String name) {
        return interestRepo.existsByName(name);
    }
}
