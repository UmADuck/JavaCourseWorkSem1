package ua.lviv.iot.coursework.models.templates;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.coursework.models.PanelOwner;
import ua.lviv.iot.coursework.models.PanelTypes;
import ua.lviv.iot.coursework.models.SolarPanel;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PanelOwnerTemplatesTest {

    PanelOwnerTemplates panelOwnerTemplates = new PanelOwnerTemplates();
    @Test
    void getTemplateList() {
        var tempList = new ArrayList<PanelOwner>(panelOwnerTemplates.getTemplateList());
        var tempList1 = new ArrayList<PanelOwner>();
        var owner1 = new PanelOwner(1, "Pavlo/Pavlenko", "Ukraine/Lviv");
        var owner2 = new PanelOwner(2, "Mykola/Zinchenko", "Ukraine/Kyiv");

        tempList1.add(owner1);
        tempList1.add(owner2);
        Assertions.assertEquals(tempList.get(0).getUserId(), tempList1.get(0).getUserId());
        Assertions.assertEquals(tempList.get(1).getUserId(), tempList1.get(1).getUserId());
    }

    @Test
    void getIdList() {
        var tempIdList = new ArrayList<Integer>();
        var tempMethodIdList = new ArrayList<Integer>(panelOwnerTemplates.getIdList());
        tempIdList.add(1);
        tempIdList.add(2);
        Assertions.assertEquals(tempIdList.get(0), tempMethodIdList.get(0));
        Assertions.assertEquals(tempIdList.get(1), tempMethodIdList.get(1));
    }
}