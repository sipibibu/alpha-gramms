package com.sipibibu.aplhagramms.main.domain;


import java.util.List;

public interface ICompany {
    public void setName(String newName);

    public List<IManager> getManagers();

    public void setManagers(List<IManager> managers) throws RuntimeException;

    public void addManager(IManager manager);

    public void addManagers(List<IManager> managers);
}
