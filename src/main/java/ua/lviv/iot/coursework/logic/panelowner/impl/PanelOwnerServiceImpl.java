package ua.lviv.iot.coursework.logic.panelowner.impl;

import ua.lviv.iot.coursework.models.PanelOwner;


import java.util.List;

public interface PanelOwnerServiceImpl {

    public void create(PanelOwner panelOwner);
    public List<PanelOwner> readALL();
    public PanelOwner read(int id);
    public boolean update(PanelOwner panelOwner, int id);
    public boolean delete(int id);
}
