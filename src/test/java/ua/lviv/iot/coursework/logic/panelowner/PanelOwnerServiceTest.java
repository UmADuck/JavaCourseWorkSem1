package ua.lviv.iot.coursework.logic.panelowner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.coursework.csvmanagers.PanelCSVManager;
import ua.lviv.iot.coursework.csvmanagers.PanelOwnerCSVManager;
import ua.lviv.iot.coursework.models.PanelOwner;
import ua.lviv.iot.coursework.models.PanelTypes;
import ua.lviv.iot.coursework.models.SolarPanel;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class PanelOwnerServiceTest {

    @Test
    void create() {
        var panelOwnerService = new PanelOwnerService();
        var objToHash = new PanelOwner(1, "Pavlo Pavlenko", "Ukraine/Lviv");
        var testList = new LinkedList<PanelOwner>();
        panelOwnerService.create(objToHash);
        testList.add(panelOwnerService.read(1));
        Assertions.assertEquals(objToHash.getUserId(), testList.get(0).getUserId());
    }

    @Test
    void readALL() {
        var panelOwnerService = new PanelOwnerService();
        var objToHash = new PanelOwner(1, "Pavlo Pavlenko", "Ukraine/Lviv");
        var owner2 = new PanelOwner(2, "Mykola Zinchenko", "Ukraine/Kyiv");
        var testList = new LinkedList<PanelOwner>();
        testList.add(objToHash);
        testList.add(owner2);
        panelOwnerService.create(objToHash);
        panelOwnerService.create(owner2);
        var testMethodList = new LinkedList<PanelOwner>(panelOwnerService.readALL());
        Assertions.assertEquals(testList.getFirst().getUserId(), testMethodList.getFirst().getUserId());
        Assertions.assertEquals(testList.getLast().getUserId(), testMethodList.getLast().getUserId());
    }

    @Test
    void read() {
        var panelOwnerService = new PanelOwnerService();
        var isEmptyHash = panelOwnerService.read(1);
        Assertions.assertNull(isEmptyHash);
    }

    @Test
    void update() {
        var panelOwnerService = new PanelOwnerService();
        var objToHash = new PanelOwner(1, "Pavlo Pavlenko", "Ukraine/Lviv");
        var panelOwner = new PanelOwner(2, "Mykola Zinchenko", "Ukraine/Kyiv");
        panelOwnerService.create(objToHash);
        panelOwnerService.update(1, panelOwner);
        var testList = new LinkedList<PanelOwner>();
        testList.add(panelOwnerService.read(1));
        Assertions.assertEquals(1, panelOwner.getUserId());
        Assertions.assertEquals(testList.getFirst().getFullName(), panelOwner.getFullName());
    }

    @Test
    void delete() {
        var panelOwnerService = new PanelOwnerService();
        var objToHash = new PanelOwner(1, "Pavlo Pavlenko", "Ukraine/Lviv");
        var testList = new LinkedList<PanelOwner>();
        testList.add(objToHash);
        panelOwnerService.create(objToHash);
        panelOwnerService.delete(1);
        Assertions.assertNotEquals(panelOwnerService.readALL(), testList);
    }
}