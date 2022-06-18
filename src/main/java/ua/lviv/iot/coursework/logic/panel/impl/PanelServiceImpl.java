package ua.lviv.iot.coursework.logic.panel.impl;

import ua.lviv.iot.coursework.models.SolarPanel;

import java.util.List;

public interface PanelServiceImpl {

    public void create(SolarPanel solarPanel);
    public List<SolarPanel> readALL();
    public SolarPanel read(int id);
    public boolean update(int id, SolarPanel solarPanel);
    public boolean delete(int id);
}
