package ua.lviv.iot.coursework.logic.panelowner.impl;

import org.springframework.stereotype.Service;
import ua.lviv.iot.coursework.csvmanagers.PanelOwnerCSVManager;
import ua.lviv.iot.coursework.logic.panelowner.PanelOwnerService;
import ua.lviv.iot.coursework.models.PanelOwner;

import java.util.LinkedList;
import java.util.List;

@Service("PanelOwnerServiceImpl")
public class PanelOwnerServiceImpl implements PanelOwnerService {

    private final PanelOwnerCSVManager manager = new PanelOwnerCSVManager();

    @Override
    public void create(PanelOwner panelOwner) {

        var checkIfEmpty = new LinkedList<PanelOwner>(manager.getAllHash());
        if (checkIfEmpty.isEmpty()) {
            manager.addDataToHashFromCSVFile();
        }
        manager.putToHash(panelOwner);
    }

    @Override
    public List<PanelOwner> readALL() {

        var checkIfEmpty = new LinkedList<PanelOwner>(manager.getAllHash());
        if (checkIfEmpty.isEmpty()) {
            manager.addDataToHashFromCSVFile();
        }
        return new LinkedList<PanelOwner>(manager.getAllHash());
    }

    @Override
    public PanelOwner read(int id) {

        var checkIfEmpty = new LinkedList<PanelOwner>(manager.getAllHash());
        if (checkIfEmpty.isEmpty()) {
            manager.addDataToHashFromCSVFile();
        }
        return manager.readHash(id);
    }

    @Override
    public boolean update(int id, PanelOwner panelOwner) {

        var checkIfEmpty = new LinkedList<PanelOwner>(manager.getAllHash());
        if (checkIfEmpty.isEmpty()) {
            manager.addDataToHashFromCSVFile();
            manager.updateHash(id, panelOwner);
        }
        manager.addNewObjectDataToCSV(panelOwner);
        return manager.updateHash(id, panelOwner);

    }

    @Override
    public boolean delete(int id) {

        return manager.removeFromHash(id);
    }
}
