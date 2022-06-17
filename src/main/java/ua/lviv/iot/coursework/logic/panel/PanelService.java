package ua.lviv.iot.coursework.logic.panel;

import org.springframework.stereotype.Service;
import ua.lviv.iot.coursework.csvmanagers.PanelCSVManager;
import ua.lviv.iot.coursework.models.SolarPanel;
import ua.lviv.iot.coursework.logic.panel.impl.PanelServiceImpl;

import java.util.LinkedList;
import java.util.List;

@Service("PanelService")
public class PanelService implements PanelServiceImpl {

    PanelCSVManager manager = new PanelCSVManager();

    @Override
    public void create(SolarPanel solarPanel) {

        manager.putToHash(solarPanel);
    }

    @Override
    public List<SolarPanel> readALL() {

        return new LinkedList<SolarPanel>(manager.getAllHash());
    }

    @Override
    public SolarPanel read(int id) {

        return manager.readHash(id);
    }

    @Override
    public boolean update(SolarPanel solarPanel, int id) {

        return manager.updateHash(id, solarPanel);
    }

    @Override
    public boolean delete(int id) {

        return manager.removeFromHash(id);
    }
}
