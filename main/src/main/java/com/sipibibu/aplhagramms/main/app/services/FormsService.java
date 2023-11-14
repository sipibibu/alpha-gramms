package com.sipibibu.aplhagramms.main.app.services;

import com.sipibibu.aplhagramms.main.app.entities.CompanyEntity;
import com.sipibibu.aplhagramms.main.app.entities.FormEntity;
import com.sipibibu.aplhagramms.main.app.repositories.CompanyRepository;
import com.sipibibu.aplhagramms.main.app.repositories.FormRepository;
import com.sipibibu.aplhagramms.main.domain.Form;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FormsService {

    @Autowired
    private FormRepository formRepository;
    @Autowired
    private CompanyRepository companyRepository;
    public FormEntity create(String title, Long companyId, String shortDisc, String fullDisc,
                             LocalDateTime createTime,LocalDateTime start,LocalDateTime end){
        /*CompanyEntity company=companyRepository.findById(companyId)
                .orElseThrow(()->new RuntimeException("No such company with id: "+companyId));*/
        CompanyEntity company=new CompanyEntity();
        companyRepository.save(company);
        FormEntity form=new FormEntity(title,shortDisc,fullDisc,company,start,end);
        formRepository.save(form);
        return form;
    }
}
