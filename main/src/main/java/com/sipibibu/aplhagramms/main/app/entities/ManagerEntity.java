package com.sipibibu.aplhagramms.main.app.entities;

import com.sipibibu.aplhagramms.main.domain.ICompany;
import com.sipibibu.aplhagramms.main.domain.IManager;
import com.sipibibu.aplhagramms.main.domain.models.Form;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Entity
@Getter
@Table(name = "managers_gg")/**/
public class ManagerEntity implements IManager {
    @Id
    @GeneratedValue
    private Long id;

    @Override
    public void setName(@NonNull String newName) {

    }

    @Override
    public void setCompany(ICompany company) {

    }

    @Override
    public List<String> getSkills() {
        return null;
    }

    @Override
    public void addSkill(String skill) {

    }

    @Override
    public void addSkills(List<String> skills) {

    }

    @Override
    public boolean removeSkill(String skill) {
        return false;
    }

    @Override
    public void setSkills(List<String> skills) throws RuntimeException {

    }

    @Override
    public List<Form> getCreatedFroms() {
        return null;
    }

    @Override
    public void addCreatedFrom(Form form) {

    }

    @Override
    public void addCreatedFroms(List<Form> forms) {

    }

    @Override
    public boolean removeCompletedForms(Form form) {
        return false;
    }

    @Override
    public void setCompletedForms(List<Form> createdFroms) throws RuntimeException {

    }

    @Override
    public List<Form> getAcceptedForms() {
        return null;
    }

    @Override
    public void addAcceptedForm(Form form) {

    }

    @Override
    public void addAcceptedForms(List<Form> forms) {

    }

    @Override
    public boolean removeAcceptedForms(Form form) {
        return false;
    }

    @Override
    public void setAcceptedForms(List<Form> subscribedForms) throws RuntimeException {

    }
}
