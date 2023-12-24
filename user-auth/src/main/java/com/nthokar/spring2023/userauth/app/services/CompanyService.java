package com.nthokar.spring2023.userauth.app.services;

import com.nthokar.spring2023.userauth.app.repos.CompanyRepository;
import com.nthokar.spring2023.userauth.app.repos.FormRepository;
import com.nthokar.spring2023.userauth.app.repos.UserRepository;
import com.nthokar.spring2023.userauth.app.entities.Company;
import com.nthokar.spring2023.userauth.app.entities.Form;
import com.nthokar.spring2023.userauth.app.entities.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    UserRepository userRepo;
    @Autowired
    CompanyRepository companyRepo;
    @Autowired
    FormRepository formRepo;
    public Company create(String title, Manager manager) {
        if (companyRepo.findByTitle(title).isPresent())
            throw new RuntimeException("name is zanyato");
        var company = new Company(title);
        companyRepo.save(company);
        return company;
    }
    public void addForm(Company company, Form form) {
        formRepo.save(form);
        company.addForm(form);
        companyRepo.save(company);
    }

    public void setDescription(Company company, String description) {
        company.setDescription(description);
        companyRepo.save(company);
    }

    public void setImage(Company company, String image) {
        company.setImage(image);
        companyRepo.save(company);
    }

    public void setTitle(Company company, String title) {
        if (get(title).isPresent())
            throw new RuntimeException("name is unvailable");
        companyRepo.save(company);
        company.setTitle(title);
    }
    public Optional<Company> get(Long id) {
        return companyRepo.findById(id);
    }

    public Optional<Company> get(String title) {
        return companyRepo.findByTitle(title);
    }
}
