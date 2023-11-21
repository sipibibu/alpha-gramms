package com.sipibibu.aplhagramms.main.domain;



import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IForm {

    public void setTitle( String newTitle) throws  RuntimeException;
    public void addQuestion(@NonNull IQuestion q);
    public void removeQuestion(Long qId);
    public void setUpdatedAt(@NonNull LocalDateTime upd);
}
