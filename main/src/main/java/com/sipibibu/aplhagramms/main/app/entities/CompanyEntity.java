package com.sipibibu.aplhagramms.main.app.entities;

import com.sipibibu.aplhagramms.main.domain.ICompany;
import com.sipibibu.aplhagramms.main.domain.IManager;
import com.sipibibu.aplhagramms.main.domain.models.Manager;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Table(name="companies")
public class CompanyEntity implements ICompany {
    @Id
    @Getter
    @GeneratedValue
    private Long id;
    @Getter
    @Column(name="name")
    private String name;
    @Setter
    @Getter
    @Column(name="desc")
    private String description;
    @OneToMany
    @JoinColumn(name = "managers")
    private List<ManagerEntity> managers;

    @Override
    public void setName(String newName){
        if(newName.replaceAll("\\W","").length()==newName.length())
            name=newName;
        throw new RuntimeException("Incorrect name");
    }
    @Override
    public List<IManager> getManagers(){
        return List.copyOf(managers);
    }
    @Override
    public void setManagers(@NonNull List<IManager> managers) throws RuntimeException{
        if(managers.stream().noneMatch(Objects::isNull))
            /*
            /DERMO
            */
            this.managers=managers.stream().map(x->(ManagerEntity)x).toList();
        else throw new RuntimeException("Some  of managers is Null");
    }
    @Override
    public void addManager(@NonNull IManager manager){
        /*
        * DERMO
        */
        this.managers.add((ManagerEntity)manager);
    }
    @Override
    public void addManagers(@NonNull List<IManager> managers){
        for(var manager :managers){
            if(manager != null)
                addManager(manager);
            else throw new RuntimeException("One of the managers is null");
        }
    }
}
