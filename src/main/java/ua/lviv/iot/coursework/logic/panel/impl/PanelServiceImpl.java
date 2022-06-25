package ua.lviv.iot.coursework.logic.panel.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.lviv.iot.coursework.csvmanagers.PanelCSVManager;
import ua.lviv.iot.coursework.logic.panel.PanelService;
import ua.lviv.iot.coursework.models.PanelTypes;
import ua.lviv.iot.coursework.models.SolarPanel;

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
public class PanelServiceImpl implements PanelService {

    static private final String wayToCSVFiles = "src/main/resources/csvcontainer/panelcsvholder/";
    private final Map<Integer, SolarPanel> panelMap = new HashMap<>();
    @Autowired
    private PanelCSVManager panelCSVManager;

    @BeforeCompletion
    @PostConstruct
    public void addStartingObjectsToCash() {
        SolarPanel panel1 = new SolarPanel(1, PanelTypes.MONOCRYSTALLINE, 500,
                5000, "Ukraine/Lviv", 1);
        SolarPanel panel2 = new SolarPanel(2, PanelTypes.POLYCRYSTALLINE, 300,
                3000, "Ukraine/Lviv", 1);
        SolarPanel panel3 = new SolarPanel(3, PanelTypes.THIN_FILM, 400,
                4000, "Ukraine/Kyiv", 2);
        create(panel1);
        create(panel2);
        create(panel3);
    }

    @PreDestroy
    public void createOnlyObjectCSV() throws IOException {
        panelCSVManager.creatingOnlyObjectDataCSV(readALL(), wayToCSVFiles);
    }

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24)
    public void scheduledCSVWriter() throws IOException {
        panelCSVManager.creatingCSVEachDay(readALL(), wayToCSVFiles);
    }

    @Override
    public void create(SolarPanel solarPanel) {
        if (!panelMap.containsKey(solarPanel.getPanelId())) {
            panelMap.put(solarPanel.getPanelId(), solarPanel);
        }
    }

    @Override
    public List<SolarPanel> readALL() {
        var tempList = new LinkedList<SolarPanel>();
        for (Map.Entry<Integer, SolarPanel> entry : panelMap.entrySet()) {
            tempList.add(entry.getValue());
        }
        return tempList;
    }

    @Override
    public SolarPanel read(int id) {
        return panelMap.get(id);
    }

    @Override
    public boolean update(int id, SolarPanel solarPanel) {
        if (panelMap.containsKey(id)) {
            solarPanel.setPanelId(id);
            panelMap.replace(id, solarPanel);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        if (panelMap.containsKey(id)) {
            panelMap.remove(id);
            return true;
        } else {
            return false;
        }
    }
}
