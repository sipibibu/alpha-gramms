package com.sipibibu.aplhagramms.main;

import com.sipibibu.aplhagramms.main.app.dto.FormDTO;
import com.sipibibu.aplhagramms.main.app.dto.QuestionDTO;
import com.sipibibu.aplhagramms.main.app.entities.AnswerEntity;
import com.sipibibu.aplhagramms.main.app.entities.FormEntity;
import com.sipibibu.aplhagramms.main.app.entities.QuestionEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class Assembler {

    public QuestionEntity makeQuestion(QuestionDTO dto){
            QuestionEntity quest=new QuestionEntity(dto.questionText(),dto.isReq(),dto.type());
            List<AnswerEntity> ans=new ArrayList<>();
            for(var a:dto.ansVar())
                ans.add(new AnswerEntity(a.text(),quest));
            quest.addAnswers(ans);
            return  quest;
    }
    public FormEntity makeForm(FormDTO dto){
        FormEntity form=new FormEntity(dto.title(), "",
                dto.fullDescription(), dto.companyId(), LocalDateTime.now(),dto.start(),
                dto.end(),new ArrayList<>());
        for(var q:dto.questions()){
            form.addQuestion(makeQuestion(q));
        }
        return form;
    }

}
