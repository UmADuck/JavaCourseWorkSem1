package ua.lviv.iot.coursework.csvmanagers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.coursework.models.PanelOwner;
import ua.lviv.iot.coursework.models.Sensor;
import ua.lviv.iot.coursework.models.templates.PanelOwnerTemplates;

import java.util.LinkedList;

class PanelOwnerCSVManagerTest {

    @Test
    void addDataToHashFromCSVFile() {
        var panelOwnerCSVManager = new PanelOwnerCSVManager();
        var testList = new LinkedList<PanelOwner>();
        panelOwnerCSVManager.addDataToHashFromCSVFile();
        testList.add(panelOwnerCSVManager.readHash(1));
        Assertions.assertEquals(1, testList.get(0).getUserId());
    }

    @Test
    void addStartingValuesToHash() throws  NullPointerException{
        var  panelOwnerTemplates = new PanelOwnerTemplates();
        var panelOwnerCSVManager = new PanelOwnerCSVManager();
        panelOwnerCSVManager.addStartingValuesToHash(panelOwnerTemplates.getIdList());
        Assertions.assertNotNull(panelOwnerCSVManager.getAllHash());
    }

    @Test
    void readHash() throws  NullPointerException{
        var panelOwnerCSVManager = new PanelOwnerCSVManager();
        var isEmptyHash = panelOwnerCSVManager.readHash(1);
        Assertions.assertNull(isEmptyHash);
    }

    @Test
    void putToHash() {
        var panelOwnerCSVManager = new PanelOwnerCSVManager();
        var objToHash = new PanelOwner(1, "Pavlo Pavlenko", "Ukraine/Lviv");
        var testList = new LinkedList<PanelOwner>();
        panelOwnerCSVManager.putToHash(objToHash);
        testList.add(panelOwnerCSVManager.readHash(1));
        Assertions.assertEquals(objToHash.getUserId(), testList.get(0).getUserId());
    }

    @Test
    void updateHash() {
        var panelOwnerCSVManager = new PanelOwnerCSVManager();
        var objToHash = new PanelOwner(1, "Pavlo Pavlenko", "Ukraine/Lviv");
        var panelOwner = new PanelOwner(2, "Mykola Zinchenko", "Ukraine/Kyiv");
        panelOwnerCSVManager.putToHash(objToHash);
        panelOwnerCSVManager.updateHash(1, panelOwner);
        var testList = new LinkedList<PanelOwner>();
        testList.add(panelOwnerCSVManager.readHash(1));
        Assertions.assertEquals(1, panelOwner.getUserId());
        Assertions.assertEquals(testList.getFirst().getFullName(), panelOwner.getFullName());
    }

    @Test
    void getAllHash() {
        var panelOwnerCSVManager = new PanelOwnerCSVManager();
        var objToHash = new PanelOwner(1, "Pavlo Pavlenko", "Ukraine/Lviv");
        var owner2 = new PanelOwner(2, "Mykola Zinchenko", "Ukraine/Kyiv");
        var testList = new LinkedList<PanelOwner>();
        testList.add(objToHash);
        testList.add(owner2);
        panelOwnerCSVManager.putToHash(objToHash);
        panelOwnerCSVManager.putToHash(owner2);
        var testMethodList = new LinkedList<PanelOwner>(panelOwnerCSVManager.getAllHash());
        Assertions.assertEquals(testList.getFirst().getUserId(), testMethodList.getFirst().getUserId());
        Assertions.assertEquals(testList.getLast().getUserId(), testMethodList.getLast().getUserId());

    }

    @Test
    void removeFromHash() {
        var panelOwnerCSVManager = new PanelOwnerCSVManager();
        var objToHash = new PanelOwner(1, "Pavlo Pavlenko", "Ukraine/Lviv");
        var testList = new LinkedList<PanelOwner>();
        testList.add(objToHash);
        panelOwnerCSVManager.putToHash(objToHash);
        panelOwnerCSVManager.removeFromHash(1);
        Assertions.assertNotEquals(panelOwnerCSVManager.getAllHash(), testList);
    }

    @Test
    void addDataToHashFromCSVFileTest(){
        var panelOwnerCSVManager = new PanelOwnerCSVManager();
        panelOwnerCSVManager.addDataToHashFromCSVFile();
        var objToHash = new PanelOwner(1, "Pavlo Pavlenko", "Ukraine/Lviv");
        var testList = new LinkedList<PanelOwner>();
        testList.add(panelOwnerCSVManager.readHash(1));
        Assertions.assertEquals(testList.get(0).getUserId(), objToHash.getUserId());
    }
}