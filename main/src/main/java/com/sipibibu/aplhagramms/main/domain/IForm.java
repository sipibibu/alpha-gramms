package com.sipibibu.aplhagramms.main.domain;



import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IForm {

     void setTitle( String newTitle) throws  RuntimeException;
     void addQuestion(@NonNull IQuestion q);
     void removeQuestion(Long qId);
     void setUpdatedAt(@NonNull LocalDateTime upd);
     void setDate(LocalDateTime start,LocalDateTime end);
     void deleteQuestion(Long questId);
     void setDescription(String shortDesc,String fullDesc);
}
