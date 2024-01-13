package com.nthokar.spring2023.userauth.app.services;

import com.nthokar.spring2023.userauth.app.repos.CompanyRepository;
import com.nthokar.spring2023.userauth.app.repos.FormRepository;
import com.nthokar.spring2023.userauth.app.repos.UserRepository;
import com.nthokar.spring2023.userauth.app.entities.Company;
import com.nthokar.spring2023.userauth.app.entities.Form;
import com.nthokar.spring2023.userauth.app.entities.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
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
        company.setTitle(title);
        companyRepo.save(company);
    }
    public  CompanyDTO getByForm(Long formId){
        var company=companyRepo.findByFormsIsContaining(new Form(formId))
                .orElseThrow(()->new RuntimeException("No company containing form with id: "+formId));
        return new CompanyDTO(company.getId(),company.getTitle());
    }
    public  HashMap<Long,CompanyDTO> getByForms(List<Long> formId){
        HashMap<Long,CompanyDTO> res = new HashMap<>();
        for(var x:formId){
            var comp=companyRepo.findByFormsIsContaining(new Form(x)).orElse(null);
            if(Objects.nonNull(comp))
                res.put(x,new CompanyDTO(comp.getId(),comp.getTitle()));
            else
                res.put(x,null);
        }
        return res;
    }
    public Optional<Company> get(Long id) {
        return companyRepo.findById(id);
    }

    public Optional<Company> get(String title) {
        return companyRepo.findByTitle(title);
    }
    public record CompanyDTO(Long id, String name){
    }

}
