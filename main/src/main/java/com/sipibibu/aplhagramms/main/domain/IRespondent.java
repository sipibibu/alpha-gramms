package com.sipibibu.aplhagramms.main.domain;



import java.util.List;

public interface IRespondent extends IUser {
    public void setName(String newName);
    public List<String> getSkills();
    public void addSkill(String skill);
    public void addSkills(List<String> skills);
    public boolean removeSkill(String skill);
    public void setSkills(List<String> skills) throws RuntimeException;
    public void setAge(Integer age) throws RuntimeException;
    public List<IForm> getCompletedForms();
    public void addCompletedForm(IForm IForm);
    public void addCompletedForms(List<IForm> forms);
    public boolean removeCompletedForms(IForm form);
    public void setCompletedForms(List<IForm> completedForms)throws RuntimeException;
    public List<IForm> getSubscribedForms();
    public void addSubscribedForm(IForm IForm);
    public void addSubscribedForms(List<IForm> forms);
    public boolean removeSubscribedForms(IForm form);
    public void setSubscribedForms(List<IForm> subscribedForms) throws RuntimeException;
}
