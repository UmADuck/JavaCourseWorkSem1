package ua.lviv.iot.coursework.logic.panel;

import ua.lviv.iot.coursework.models.SolarPanel;

import java.util.List;

public interface PanelService {

    void create(SolarPanel solarPanel);

    List<SolarPanel> readALL();

    SolarPanel read(int id);

    boolean update(int id, SolarPanel solarPanel);

    boolean delete(int id);
}
