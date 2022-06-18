package ua.lviv.iot.coursework.models.templates;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.coursework.csvmanagers.PanelCSVManager;
import ua.lviv.iot.coursework.csvmanagers.SensorCSVManager;
import ua.lviv.iot.coursework.models.PanelTypes;
import ua.lviv.iot.coursework.models.Sensor;
import ua.lviv.iot.coursework.models.SolarPanel;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SolarPanelTemplatesTest {

    SolarPanelTemplates solarPanelTemplates = new SolarPanelTemplates();
    @Test
    void getTemplateList() {
        var tempList = new ArrayList<SolarPanel>(solarPanelTemplates.getTemplateList());
        var tempList1 = new ArrayList<SolarPanel>();
        var panel1 = new SolarPanel(1, PanelTypes.MONOCRYSTALLINE, 500,
                5000, "Ukraine/Lviv");
        var panel2 = new SolarPanel(2, PanelTypes.POLYCRYSTALLINE, 300,
                3000, "Ukraine/Lviv");
        var panel3 = new SolarPanel(3, PanelTypes.THIN_FILM, 400,
                4000, "Ukraine/Lviv");
        tempList1.add(panel1);
        tempList1.add(panel2);
        tempList1.add(panel3);
        Assertions.assertEquals(tempList.get(0).getPanelId(), tempList1.get(0).getPanelId());
        Assertions.assertEquals(tempList.get(1).getPanelId(), tempList1.get(1).getPanelId());
        Assertions.assertEquals(tempList.get(2).getPanelId(), tempList1.get(2).getPanelId());
    }

    @Test
    void getIdList() {
        var tempIdList = new ArrayList<Integer>();
        var tempMethodIdList = new ArrayList<Integer>(solarPanelTemplates.getIdList());
        tempIdList.add(1);
        tempIdList.add(2);
        tempIdList.add(3);
        Assertions.assertEquals(tempIdList.get(0), tempMethodIdList.get(0));
        Assertions.assertEquals(tempIdList.get(1), tempMethodIdList.get(1));
        Assertions.assertEquals(tempIdList.get(2), tempMethodIdList.get(2));
    }
}