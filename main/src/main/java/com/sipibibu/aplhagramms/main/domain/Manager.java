package com.sipibibu.aplhagramms.main.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.Objects;


@NoArgsConstructor
public class Manager extends User{
    @Getter
    private Long id;
    @Getter
    private String name;
    @Getter
    private Company company;
    @Getter
    private Integer age;
    @Getter
    @Setter
    private String graduation;
    @Getter
    @Setter
    private Double balance;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private Gender gender;
    private List<String> skills;
    private List<Form> createdFroms;
    private List<Form> acceptedForms;

    public void setName(@NonNull String newName){
        if(newName.replaceAll("\\W","").length()==newName.length())
            name=newName;
        throw new RuntimeException("Incorrect name");
    }
    public void setCompany(@NonNull Company company){
            this.company=company;
    }
    public void setAge(Integer age) throws RuntimeException{
        if (age > 0)
            this.age = age;
        else throw new RuntimeException("Incorrect age");
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

    public List<Form> getCreatedFroms(){
        return List.copyOf(createdFroms);
    }
    public void addCreatedFrom(Form form){
        createdFroms.add(form);
    }
    public void addCreatedFroms(List<Form> forms){
        this.createdFroms.addAll(forms);
    }
    public boolean removeCompletedForms(Form form){
        return this.createdFroms.remove(form);
    }
    public void setCompletedForms(List<Form> createdFroms)throws RuntimeException {
        if (createdFroms.stream().noneMatch(Objects::isNull))
            this.createdFroms = createdFroms;
        else throw new RuntimeException("Some of forms is null");
    }
    public List<Form> getAcceptedForms(){
        return List.copyOf(acceptedForms);
    }
    public void addAcceptedForm(Form form){
        acceptedForms.add(form);
    }
    public void addAcceptedForms(List<Form> forms){
        this.acceptedForms.addAll(forms);
    }
    public boolean removeAcceptedForms(Form form){
        return this.acceptedForms.remove(form);
    }
    public void setAcceptedForms(List<Form> subscribedForms) throws RuntimeException{
        if(subscribedForms.stream().noneMatch(Objects::isNull))
            this.acceptedForms=subscribedForms;
        else  throw new RuntimeException("Some of forms is null");
    }

}
