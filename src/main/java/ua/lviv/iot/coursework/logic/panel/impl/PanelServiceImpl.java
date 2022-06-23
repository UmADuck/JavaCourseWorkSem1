package ua.lviv.iot.coursework.logic.panel.impl;

import org.springframework.stereotype.Service;
import ua.lviv.iot.coursework.csvmanagers.PanelCSVManager;
import ua.lviv.iot.coursework.logic.panel.PanelService;
import ua.lviv.iot.coursework.models.SolarPanel;

import java.util.LinkedList;
import java.util.List;

@Service("PanelServiceImpl")
public class PanelServiceImpl implements PanelService {


    private final PanelCSVManager manager = new PanelCSVManager();

    @Override
    public void create(SolarPanel solarPanel) {

        var checkIfEmpty = new LinkedList<SolarPanel>(manager.getAllHash());
        if (checkIfEmpty.isEmpty()) {
            manager.addDataToHashFromCSVFile();
        }
        manager.putToHash(solarPanel);
    }

    @Override
    public List<SolarPanel> readALL() {
        var checkIfEmpty = new LinkedList<SolarPanel>(manager.getAllHash());
        if (checkIfEmpty.isEmpty()) {
            manager.addDataToHashFromCSVFile();
        }
        return new LinkedList<SolarPanel>(manager.getAllHash());
    }

    @Override
    public SolarPanel read(int id) {

        var checkIfEmpty = new LinkedList<SolarPanel>(manager.getAllHash());
        if (checkIfEmpty.isEmpty()) {
            manager.addDataToHashFromCSVFile();
        }
        return manager.readHash(id);
    }

    @Override
    public boolean update(int id, SolarPanel solarPanel) {

        var checkIfEmpty = new LinkedList<SolarPanel>(manager.getAllHash());
        if (checkIfEmpty.isEmpty()) {
            manager.addDataToHashFromCSVFile();
        }
        return manager.updateHash(id, solarPanel);
    }

    @Override
    public boolean delete(int id) {

        return manager.removeFromHash(id);
    }
}
