package ua.lviv.iot.coursework.logic.panelowner;

import ua.lviv.iot.coursework.models.PanelOwner;


import java.util.List;

public interface PanelOwnerService {

    void create(PanelOwner panelOwner);

    List<PanelOwner> readALL();

    PanelOwner read(int id);

    boolean update(int id, PanelOwner panelOwner);

    boolean delete(int id);
}
