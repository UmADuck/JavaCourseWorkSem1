package ua.lviv.iot.coursework.logic.panel.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.coursework.csvmanagers.PanelCSVManager;
import ua.lviv.iot.coursework.models.PanelTypes;
import ua.lviv.iot.coursework.models.SolarPanel;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PanelServiceImplTest {

    private final PanelServiceImpl service = new PanelServiceImpl();
    private final List<SolarPanel> objList = new LinkedList<>();


    @BeforeEach
    void setUp() {
        SolarPanel panel1 = new SolarPanel(1, PanelTypes.MONOCRYSTALLINE, 500,
                5000, "Ukraine/Lviv", 1);
        SolarPanel panel2 = new SolarPanel(2, PanelTypes.POLYCRYSTALLINE, 300,
                3000, "Ukraine/Lviv", 1);
        SolarPanel panel3 = new SolarPanel(3, PanelTypes.THIN_FILM, 400,
                4000, "Ukraine/Kyiv", 2);
        objList.add(panel1);
        objList.add(panel2);
        objList.add(panel3);
        service.addStartingObjectsToCash();
    }

    @Test
    void addStartingObjectsToCash() {
        var tempService = new PanelServiceImpl();
        tempService.addStartingObjectsToCash();
        var tempList = new LinkedList<SolarPanel>(service.readALL());
        Assertions.assertEquals(tempList.size(), objList.size());
        Assertions.assertEquals(objList.get(0).getPanelId(), tempList.get(0).getPanelId());
        Assertions.assertEquals(objList.get(0).getPanelType(), tempList.get(0).getPanelType());
        Assertions.assertEquals(objList.get(0).getPanelPower(), tempList.get(0).getPanelPower());
        Assertions.assertEquals(objList.get(0).getBatteryCapacity(), tempList.get(0).getBatteryCapacity());
        Assertions.assertEquals(objList.get(0).getPanelAddress(), tempList.get(0).getPanelAddress());
        Assertions.assertEquals(objList.get(0).getOwnerId(), tempList.get(0).getOwnerId());
    }

    @Test
    void create() {
        var tempService = new PanelServiceImpl();
        var tempList1 = new LinkedList<SolarPanel>();
        SolarPanel panel1 = new SolarPanel(1, PanelTypes.MONOCRYSTALLINE, 500,
                5000, "Ukraine/Lviv", 1);
        tempList1.add(panel1);
        tempService.create(panel1);
        var tempList2 = new LinkedList<SolarPanel>(tempService.readALL());
        Assertions.assertEquals(tempList1.size(), tempList2.size());
        Assertions.assertEquals(tempList1.get(0).getPanelId(), tempList2.get(0).getPanelId());
        Assertions.assertEquals(tempList1.get(0).getPanelType(), tempList2.get(0).getPanelType());
        Assertions.assertEquals(tempList1.get(0).getPanelPower(), tempList2.get(0).getPanelPower());
        Assertions.assertEquals(tempList1.get(0).getBatteryCapacity(), tempList2.get(0).getBatteryCapacity());
        Assertions.assertEquals(tempList1.get(0).getPanelAddress(), tempList2.get(0).getPanelAddress());
        Assertions.assertEquals(tempList1.get(0).getOwnerId(), tempList2.get(0).getOwnerId());
    }

    @Test
    void readALL() {
        var tempList = new LinkedList<SolarPanel>(service.readALL());
        Assertions.assertEquals(tempList.size(), objList.size());
        Assertions.assertEquals(objList.get(0).getPanelId(), tempList.get(0).getPanelId());
        Assertions.assertEquals(objList.get(0).getPanelType(), tempList.get(0).getPanelType());
        Assertions.assertEquals(objList.get(0).getPanelPower(), tempList.get(0).getPanelPower());
        Assertions.assertEquals(objList.get(0).getBatteryCapacity(), tempList.get(0).getBatteryCapacity());
        Assertions.assertEquals(objList.get(0).getPanelAddress(), tempList.get(0).getPanelAddress());
        Assertions.assertEquals(objList.get(0).getOwnerId(), tempList.get(0).getOwnerId());
    }

    @Test
    void read() {
        SolarPanel panel1 = new SolarPanel(1, PanelTypes.MONOCRYSTALLINE, 500,
                5000, "Ukraine/Lviv", 1);
        var tempList = new LinkedList<SolarPanel>();
        tempList.add(service.read(1));
        Assertions.assertEquals(objList.get(0).getPanelId(), tempList.get(0).getPanelId());
        Assertions.assertEquals(objList.get(0).getPanelType(), tempList.get(0).getPanelType());
        Assertions.assertEquals(objList.get(0).getPanelPower(), tempList.get(0).getPanelPower());
        Assertions.assertEquals(objList.get(0).getBatteryCapacity(), tempList.get(0).getBatteryCapacity());
        Assertions.assertEquals(objList.get(0).getPanelAddress(), tempList.get(0).getPanelAddress());
        Assertions.assertEquals(objList.get(0).getOwnerId(), tempList.get(0).getOwnerId());
    }

    @Test
    void update() {
        var tempService = new PanelServiceImpl();
        var tempList1 = new LinkedList<SolarPanel>();
        var tempList2 = new LinkedList<SolarPanel>();
        SolarPanel panel1 = new SolarPanel(1, PanelTypes.MONOCRYSTALLINE, 500,
                5000, "Ukraine/Lviv", 1);
        SolarPanel panel2 = new SolarPanel(2, PanelTypes.POLYCRYSTALLINE, 300,
                3000, "Ukraine/Lviv", 1);
        tempService.create(panel1);
        tempService.update(1, panel2);
        tempList1.add(panel2);
        tempList2.add(tempService.read(1));
        Assertions.assertEquals(tempList1.get(0).getPanelId(), tempList2.get(0).getPanelId());
        Assertions.assertEquals(tempList1.get(0).getPanelType(), tempList2.get(0).getPanelType());
        Assertions.assertEquals(tempList1.get(0).getPanelPower(), tempList2.get(0).getPanelPower());
        Assertions.assertEquals(tempList1.get(0).getBatteryCapacity(), tempList2.get(0).getBatteryCapacity());
        Assertions.assertEquals(tempList1.get(0).getPanelAddress(), tempList2.get(0).getPanelAddress());
        Assertions.assertEquals(tempList1.get(0).getOwnerId(), tempList2.get(0).getOwnerId());
    }

    @Test
    void delete() {
        var tempService = new PanelServiceImpl();
        var tempList1 = new LinkedList<SolarPanel>();
        SolarPanel panel1 = new SolarPanel(1, PanelTypes.MONOCRYSTALLINE, 500,
                5000, "Ukraine/Lviv", 1);
        tempService.create(panel1);
        tempService.delete(1);
        tempList1.add(tempService.read(1));
        Assertions.assertNull(tempList1.get(0));

    }
}