package com.sipibibu.aplhagramms.main.app.entities;

import com.sipibibu.aplhagramms.main.domain.Company;
import com.sipibibu.aplhagramms.main.domain.Manager;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@Table(name="companies")
public class CompanyEntity {
    @Id
    @GeneratedValue
    private Long id;
/*    @Column(name="name")
    private String name;*/


/*    public void setName(String newName){
        if(newName.replaceAll("\\W","").length()==newName.length())
            name=newName;
        throw new RuntimeException("Incorrect name");
    }*/
 /*   public List<ManagerEntity> getManagers(){
        return List.copyOf(managers);
    }
    public void setManagers(List<ManagerEntity> managers) throws RuntimeException{
        if(managers.stream().noneMatch(Objects::isNull))
            this.managers=managers;
        else throw new RuntimeException("Some  of managers is Null");
    }
    public void addManager(@NonNull ManagerEntity manager){
        this.managers.add(manager);
    }
    public void addManagers(@NonNull List<ManagerEntity> managers){
        for(var manager :managers){
            if(manager != null)
                addManager(manager);
            else throw new RuntimeException("One of the managers is null");
        }
    }*/
}
