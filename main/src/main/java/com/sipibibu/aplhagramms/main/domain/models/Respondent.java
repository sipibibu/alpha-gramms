package com.sipibibu.aplhagramms.main.domain.models;

import com.sipibibu.aplhagramms.main.domain.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;


@NoArgsConstructor
public class Respondent extends  User{
    @Getter
    private Long id;
    @Getter
    private String name;
    private List<String> skills;
    @Getter
    private Integer age;
    @Getter
    @Setter
    private String graduation;
    @Setter
    @Getter
    private String description;
    @Setter
    @Getter
    private Gender gender;
    private List<Form> completedForms;
    private List<Form> subscribedForms;
    @Getter
    @Setter
    private Double balance;

    public void setName(String newName){
        if(newName.replaceAll("\\W","").length()==newName.length())
            name=newName;
        throw new RuntimeException("Incorrect name");
    }
    public List<String> getSkills(){
        return List.copyOf(skills);
    }
    public void addSkill(String skill){
        skills.add(skill);
    }
    public void addSkills(List<String> skills){
        this.skills.addAll(skills);
    }
    public boolean removeSkill(String skill){
        return this.skills.remove(skill);
    }
    public void setSkills(List<String> skills) throws RuntimeException{
        if(skills.stream().anyMatch(x-> x.replaceAll("\\W", "").equals(x)))
            this.skills=skills;
        else throw new RuntimeException("Incorrect skills");
    }
    public void setAge(Integer age) throws RuntimeException{
        if (age > 0)
            this.age = age;
        else throw new RuntimeException("Incorrect age");
    }
    public List<Form> getCompletedForms(){
        return List.copyOf(completedForms);
    }
    public void addCompletedForm(Form form){
        completedForms.add(form);
    }
    public void addCompletedForms(List<Form> forms){
        this.completedForms.addAll(forms);
    }
    public boolean removeCompletedForms(Form form){
        return this.completedForms.remove(form);
    }
    public void setCompletedForms(List<Form> completedForms)throws RuntimeException {
        if (completedForms.stream().noneMatch(Objects::isNull))
            this.completedForms = completedForms;
        else throw new RuntimeException("Some of forms is null");
    }
    public List<Form> getSubscribedForms(){
        return List.copyOf(subscribedForms);
    }
    public void addSubscribedForm(Form form){
        subscribedForms.add(form);
    }
    public void addSubscribedForms(List<Form> forms){
        this.subscribedForms.addAll(forms);
    }
    public boolean removeSubscribedForms(Form form){
        return this.subscribedForms.remove(form);
    }
    public void setSubscribedForms(List<Form> subscribedForms) throws RuntimeException{
        if(subscribedForms.stream().noneMatch(Objects::isNull))
            this.subscribedForms=subscribedForms;
        else  throw new RuntimeException("Some of forms is null");
    }
}
