package com.sipibibu.aplhagramms.main.app.services;

import com.sipibibu.aplhagramms.main.Assembler;
import com.sipibibu.aplhagramms.main.app.dto.FormAnswerDTO;
import com.sipibibu.aplhagramms.main.app.entities.FormEntity;
import com.sipibibu.aplhagramms.main.app.entities.formanswers.FormAnswerEntity;
import com.sipibibu.aplhagramms.main.app.entities.formanswers.QuestionAnswerEntity;
import com.sipibibu.aplhagramms.main.app.repositories.FormAnswersRepository;
import com.sipibibu.aplhagramms.main.app.repositories.FormRepository;
import com.sipibibu.aplhagramms.main.app.repositories.QuestionAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormAnswersService {

    @Autowired
    private Assembler assembler;
    @Autowired
    private FormAnswersRepository formAnswersRepository;
    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;
    @Autowired
    private FormRepository formRepository;

    public void create(Long userId, FormAnswerDTO dto){
        FormAnswerEntity formAnswer=assembler.makeFormAnswer(userId,dto);
        questionAnswerRepository.saveAll(formAnswer.getQuesions());
        formAnswersRepository.save(formAnswer);
    }

    public List<FormAnswerEntity> get(Long id){
        FormEntity form=formRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No form with id: "+id));
        return formAnswersRepository.findAllByForm(form);

    }

}
