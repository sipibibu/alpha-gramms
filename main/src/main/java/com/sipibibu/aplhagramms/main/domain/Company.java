package com.sipibibu.aplhagramms.main.domain;

import com.google.api.services.script.Script;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor
public class Company {
    @Getter
    private Long id;
    @Getter
    private String name;
    @Setter
    @Getter
    private String description;
    private List<Manager> managers;

    public void setName(String newName){
        if(newName.replaceAll("\\W","").length()==newName.length())
            name=newName;
        throw new RuntimeException("Incorrect name");
    }
    public List<Manager> getManagers(){
        return List.copyOf(managers);
    }
    public void setManagers(List<Manager> managers) throws RuntimeException{
        if(managers.stream().noneMatch(Objects::isNull))
            this.managers=managers;
        else throw new RuntimeException("Some  of managers is Null");
    }
    public void addManager(@NonNull Manager manager){
        this.managers.add(manager);
    }
    public void addManagers(@NonNull List<Manager> managers){
        for(var manager :managers){
            if(manager != null)
                addManager(manager);
            else throw new RuntimeException("One of the managers is null");
        }
    }

}
