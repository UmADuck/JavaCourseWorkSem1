package ua.lviv.iot.coursework.csvmanagers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.coursework.models.PanelTypes;

import ua.lviv.iot.coursework.models.SolarPanel;
import ua.lviv.iot.coursework.models.templates.SolarPanelTemplates;

import java.util.LinkedList;


class PanelCSVManagerTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void addDataToHashFromCSVFile() {
        var panelCSVManager = new PanelCSVManager();
        var testList = new LinkedList<SolarPanel>();
        panelCSVManager.addDataToHashFromCSVFile();
        testList.add(panelCSVManager.readHash(1));
        Assertions.assertEquals(1, testList.get(0).getPanelId());
    }

    @Test
    void addStartingValuesToHash() throws NullPointerException {
        var panelTemplate = new SolarPanelTemplates();
        var panelCSVManager = new PanelCSVManager();
        panelCSVManager.addStartingValuesToHash(panelTemplate.getIdList());
        Assertions.assertNotNull(panelCSVManager.getAllHash());
    }

    @Test
    void readHash() throws NullPointerException {
        var panelCSVManager = new PanelCSVManager();
        var isEmptyHash = panelCSVManager.readHash(1);
        Assertions.assertNull(isEmptyHash);
    }

    @Test
    void putToHash() {
        var panelCSVManager = new PanelCSVManager();
        var objToHash = new SolarPanel(1, PanelTypes.MONOCRYSTALLINE, 500,
                5000, "Ukraine, Lviv");
        var testList = new LinkedList<SolarPanel>();
        panelCSVManager.putToHash(objToHash);
        testList.add(panelCSVManager.readHash(1));
        Assertions.assertEquals(objToHash.getPanelId(), testList.get(0).getPanelId());
    }

    @Test
    void updateHash() {
        var panelCSVManager = new PanelCSVManager();
        var objToHash = new SolarPanel(1, PanelTypes.MONOCRYSTALLINE, 500,
                5000, "Ukraine, Lviv");
        var panel2 = new SolarPanel(2, PanelTypes.POLYCRYSTALLINE, 300,
                3000, "Ukraine, Lviv");
        panelCSVManager.putToHash(objToHash);
        panelCSVManager.updateHash(1, panel2);
        var testList = new LinkedList<SolarPanel>();
        testList.add(panelCSVManager.readHash(1));
        Assertions.assertEquals(1, panel2.getPanelId());
        Assertions.assertEquals(testList.getFirst().getPanelPower(), panel2.getPanelPower());
    }

    @Test
    void getAllHash() {
        var panelCSVManager = new PanelCSVManager();
        var objToHash = new SolarPanel(1, PanelTypes.MONOCRYSTALLINE, 500,
                5000, "Ukraine, Lviv");
        var panel2 = new SolarPanel(2, PanelTypes.POLYCRYSTALLINE, 300,
                3000, "Ukraine, Lviv");
        var testList = new LinkedList<SolarPanel>();
        testList.add(objToHash);
        testList.add(panel2);
        panelCSVManager.putToHash(objToHash);
        panelCSVManager.putToHash(panel2);
        var testMethodList = new LinkedList<SolarPanel>(panelCSVManager.getAllHash());
        Assertions.assertEquals(testList.getFirst().getPanelId(), testMethodList.getFirst().getPanelId());
        Assertions.assertEquals(testList.getLast().getPanelId(), testMethodList.getLast().getPanelId());
    }

    @Test
    void removeFromHash() {
        var panelCSVManager = new PanelCSVManager();
        var objToHash = new SolarPanel(1, PanelTypes.MONOCRYSTALLINE, 500,
                5000, "Ukraine, Lviv");
        var testList = new LinkedList<SolarPanel>();
        testList.add(objToHash);
        panelCSVManager.putToHash(objToHash);
        panelCSVManager.removeFromHash(1);
        Assertions.assertNotEquals(panelCSVManager.getAllHash(), testList);
    }
}