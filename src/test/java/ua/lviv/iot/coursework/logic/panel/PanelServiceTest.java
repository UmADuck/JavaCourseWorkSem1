package ua.lviv.iot.coursework.logic.panel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.coursework.csvmanagers.PanelCSVManager;
import ua.lviv.iot.coursework.models.PanelTypes;
import ua.lviv.iot.coursework.models.SolarPanel;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class PanelServiceTest {

    @Test
    void create() {
        var panelService = new PanelService();
        var objToHash = new SolarPanel(1, PanelTypes.MONOCRYSTALLINE, 500,
                5000, "Ukraine, Lviv");
        var testList = new LinkedList<SolarPanel>();
        panelService.create(objToHash);
        testList.add(panelService.read(1));
        assertEquals(objToHash.getPanelId(), testList.get(0).getPanelId());
    }

    @Test
    void readALL() {
        var panelService = new PanelService();
        var objToHash = new SolarPanel(1, PanelTypes.MONOCRYSTALLINE, 500,
                5000, "Ukraine, Lviv");
        var panel2 = new SolarPanel(2, PanelTypes.POLYCRYSTALLINE, 300,
                3000, "Ukraine, Lviv");
        var testList = new LinkedList<SolarPanel>();
        testList.add(objToHash);
        testList.add(panel2);
        panelService.create(objToHash);
        panelService.create(panel2);
        var testMethodList = new LinkedList<SolarPanel>(panelService.readALL());
        Assertions.assertEquals(testList.getFirst().getPanelId(), testMethodList.getFirst().getPanelId());
        Assertions.assertEquals(testList.getLast().getPanelId(), testMethodList.getLast().getPanelId());
    }

    @Test
    void read() {
        var panelService = new PanelService();
        var isEmptyHash = panelService.read(1);
        Assertions.assertNull(isEmptyHash);
    }

    @Test
    void update() {
        var panelService = new PanelService();
        var objToHash = new SolarPanel(1, PanelTypes.MONOCRYSTALLINE, 500,
                5000, "Ukraine, Lviv");
        var panel2 = new SolarPanel(2, PanelTypes.POLYCRYSTALLINE, 300,
                3000, "Ukraine, Lviv");
        panelService.create(objToHash);
        panelService.update(1, panel2);
        var testList = new LinkedList<SolarPanel>();
        testList.add(panelService.read(1));
        Assertions.assertEquals(1, panel2.getPanelId());
        Assertions.assertEquals(testList.getFirst().getPanelPower(), panel2.getPanelPower());
    }

    @Test
    void delete() {
        var panelService = new PanelService();
        var objToHash = new SolarPanel(1, PanelTypes.MONOCRYSTALLINE, 500,
                5000, "Ukraine, Lviv");
        var testList = new LinkedList<SolarPanel>();
        testList.add(objToHash);
        panelService.create(objToHash);
        panelService.delete(1);
        Assertions.assertNotEquals(panelService.readALL(), testList);
    }
}