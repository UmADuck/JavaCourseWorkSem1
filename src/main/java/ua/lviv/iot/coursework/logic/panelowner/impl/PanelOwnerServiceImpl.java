package ua.lviv.iot.coursework.logic.panelowner.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.lviv.iot.coursework.csvmanagers.PanelOwnerCSVManager;
import ua.lviv.iot.coursework.logic.panelowner.PanelOwnerService;
import ua.lviv.iot.coursework.models.PanelOwner;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.BeforeCompletion;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@EnableScheduling
public class PanelOwnerServiceImpl implements PanelOwnerService {

    @Autowired
    private PanelOwnerCSVManager panelOwnerCSVManager;
    private final Map<Integer, PanelOwner> panelOwnerMap = new HashMap<>();
    static private final String wayToCSVFiles = "src/main/resources/csvcontainer/panelownercsvholder/";

    @BeforeCompletion
    @PostConstruct
    public void addStartingObjectsToCash() {
        PanelOwner owner1 = new PanelOwner(1, "Pavlo/Pavlenko", "Ukraine/Lviv");
        PanelOwner owner2 = new PanelOwner(2, "Mykola/Zinchenko", "Ukraine/Kyiv");
        create(owner1);
        create(owner2);
    }

    @PreDestroy
    public void createOnlyObjectCSV() throws IOException {
        panelOwnerCSVManager.creatingOnlyObjectDataCSV(readALL(), wayToCSVFiles);
    }

    @Scheduled(fixedDelay = 1000*60*60*24)
    public void scheduledCSVWriter() throws IOException {
        panelOwnerCSVManager.creatingCSVEachDay(readALL(), wayToCSVFiles);
    }

    @Override
    public void create(PanelOwner panelOwner) {
        if (!panelOwnerMap.containsKey(panelOwner.getOwnerId())) {
            panelOwnerMap.put(panelOwner.getOwnerId(), panelOwner);
        }
    }

    @Override
    public List<PanelOwner> readALL() {
        var tempList = new LinkedList<PanelOwner>();
        for (Map.Entry<Integer, PanelOwner> entry : panelOwnerMap.entrySet()) {
            tempList.add(entry.getValue());
        }
        return tempList;
    }

    @Override
    public PanelOwner read(int id) {
        return panelOwnerMap.get(id);
    }

    @Override
    public boolean update(int id, PanelOwner panelOwner) {
        if (panelOwnerMap.containsKey(id)) {
            panelOwner.setOwnerId(id);
            panelOwnerMap.replace(panelOwner.getOwnerId(), panelOwner);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        if (panelOwnerMap.containsKey(id)) {
            panelOwnerMap.remove(id);
            return true;
        } else {
            return false;
        }
    }
}
