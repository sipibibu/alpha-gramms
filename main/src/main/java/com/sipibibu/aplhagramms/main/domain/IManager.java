package com.sipibibu.aplhagramms.main.domain;

import com.sipibibu.aplhagramms.main.domain.models.Form;
import lombok.NonNull;

import java.util.List;

public interface IManager extends IUser {
    public void setName(@NonNull String newName);
    public void setCompany(ICompany company);
    public List<String> getSkills();
    public void addSkill(String skill);
    public void addSkills(List<String> skills);
    public boolean removeSkill(String skill);
    public void setSkills(List<String> skills) throws RuntimeException;

    public List<Form> getCreatedFroms();
    public void addCreatedFrom(Form form);
    public void addCreatedFroms(List<Form> forms);
    public boolean removeCompletedForms(Form form);
    public void setCompletedForms(List<Form> createdFroms)throws RuntimeException;
    public List<Form> getAcceptedForms();
    public void addAcceptedForm(Form form);
    public void addAcceptedForms(List<Form> forms);
    public boolean removeAcceptedForms(Form form);
    public void setAcceptedForms(List<Form> subscribedForms) throws RuntimeException;
}
