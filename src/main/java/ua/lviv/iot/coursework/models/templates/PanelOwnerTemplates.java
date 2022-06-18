package ua.lviv.iot.coursework.models.templates;

import ua.lviv.iot.coursework.models.PanelOwner;

import java.util.ArrayList;
import java.util.List;

public class PanelOwnerTemplates {

    PanelOwner owner1 = new PanelOwner(1, "Pavlo/Pavlenko", "Ukraine/Lviv");
    PanelOwner owner2 = new PanelOwner(2, "Mykola/Zinchenko", "Ukraine/Kyiv");

    public List<PanelOwner> getTemplateList(){
        var tempList = new ArrayList<PanelOwner>();
        tempList.add(owner1);
        tempList.add(owner2);

        return tempList;
    }

    public List<Integer> getIdList(){
        var tempList = new ArrayList<Integer>();
        tempList.add(owner1.getUserId());
        tempList.add(owner2.getUserId());
        return tempList;
    }
}
