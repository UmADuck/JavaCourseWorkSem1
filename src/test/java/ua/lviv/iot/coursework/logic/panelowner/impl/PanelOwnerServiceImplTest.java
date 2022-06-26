package ua.lviv.iot.coursework.logic.panelowner.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.coursework.logic.panel.impl.PanelServiceImpl;
import ua.lviv.iot.coursework.models.PanelOwner;
import ua.lviv.iot.coursework.models.PanelTypes;
import ua.lviv.iot.coursework.models.SolarPanel;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PanelOwnerServiceImplTest {

    private final PanelOwnerServiceImpl service = new PanelOwnerServiceImpl();
    private final List<PanelOwner> objList = new LinkedList<>();


    @BeforeEach
    void setUp() {
        PanelOwner owner1 = new PanelOwner(1, "Pavlo/Pavlenko", "Ukraine/Lviv");
        PanelOwner owner2 = new PanelOwner(2, "Mykola/Zinchenko", "Ukraine/Kyiv");
        objList.add(owner1);
        objList.add(owner2);
        service.addStartingObjectsToCash();
    }

    @Test
    void addStartingObjectsToCash() {
        var tempService = new PanelOwnerServiceImpl();
        tempService.addStartingObjectsToCash();
        var tempList = new LinkedList<PanelOwner>(service.readALL());
        Assertions.assertEquals(tempList.size(), objList.size());
        Assertions.assertEquals(objList.get(0).getOwnerId(), tempList.get(0).getOwnerId());
        Assertions.assertEquals(objList.get(0).getFullName(), tempList.get(0).getFullName());
        Assertions.assertEquals(objList.get(0).getOwnAddress(), tempList.get(0).getOwnAddress());
    }

    @Test
    void create() {
        var tempService = new PanelOwnerServiceImpl();
        var tempList1 = new LinkedList<PanelOwner>();
        PanelOwner owner1 = new PanelOwner(1, "Pavlo/Pavlenko", "Ukraine/Lviv");
        tempList1.add(owner1);
        tempService.create(owner1);
        var tempList2 = new LinkedList<PanelOwner>(tempService.readALL());
        Assertions.assertEquals(tempList1.size(), tempList2.size());
        Assertions.assertEquals(tempList1.get(0).getOwnerId(), tempList2.get(0).getOwnerId());
        Assertions.assertEquals(tempList1.get(0).getFullName(), tempList2.get(0).getFullName());
        Assertions.assertEquals(tempList1.get(0).getOwnAddress(), tempList2.get(0).getOwnAddress());
    }

    @Test
    void readALL() {
        var tempList = new LinkedList<PanelOwner>(service.readALL());
        Assertions.assertEquals(tempList.size(), objList.size());
        Assertions.assertEquals(objList.get(0).getOwnerId(), tempList.get(0).getOwnerId());
        Assertions.assertEquals(objList.get(0).getFullName(), tempList.get(0).getFullName());
        Assertions.assertEquals(objList.get(0).getOwnAddress(), tempList.get(0).getOwnAddress());
    }

    @Test
    void read() {
        PanelOwner owner1 = new PanelOwner(1, "Pavlo/Pavlenko", "Ukraine/Lviv");
        var tempList = new LinkedList<PanelOwner>();
        tempList.add(service.read(1));
        Assertions.assertEquals(objList.get(0).getOwnerId(), tempList.get(0).getOwnerId());
        Assertions.assertEquals(objList.get(0).getFullName(), tempList.get(0).getFullName());
        Assertions.assertEquals(objList.get(0).getOwnAddress(), tempList.get(0).getOwnAddress());
    }

    @Test
    void update() {
        var tempService = new PanelOwnerServiceImpl();
        var tempList1 = new LinkedList<PanelOwner>();
        var tempList2 = new LinkedList<PanelOwner>();
        PanelOwner owner1 = new PanelOwner(1, "Pavlo/Pavlenko", "Ukraine/Lviv");
        PanelOwner owner2 = new PanelOwner(2, "Mykola/Zinchenko", "Ukraine/Kyiv");
        tempService.create(owner1);
        tempService.update(1, owner2);
        tempList1.add(owner2);
        tempList2.add(tempService.read(1));
        Assertions.assertEquals(tempList1.size(), tempList2.size());
        Assertions.assertEquals(tempList1.get(0).getOwnerId(), tempList2.get(0).getOwnerId());
        Assertions.assertEquals(tempList1.get(0).getFullName(), tempList2.get(0).getFullName());
        Assertions.assertEquals(tempList1.get(0).getOwnAddress(), tempList2.get(0).getOwnAddress());
    }

    @Test
    void delete() {
        var tempService = new PanelOwnerServiceImpl();
        var tempList1 = new LinkedList<PanelOwner>();
        PanelOwner owner1 = new PanelOwner(1, "Pavlo/Pavlenko", "Ukraine/Lviv");
        tempService.create(owner1);
        tempService.delete(1);
        tempList1.add(tempService.read(1));
        Assertions.assertNull(tempList1.get(0));

    }
}