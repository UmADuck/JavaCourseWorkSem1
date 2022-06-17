package ua.lviv.iot.coursework.models.templates;

import ua.lviv.iot.coursework.models.PanelOwner;

import java.util.ArrayList;
import java.util.List;

public class PanelOwnerTemplates {

    PanelOwner owner1 = new PanelOwner(1, "Pavlo Pavlenko", "Ukraine, Lviv");

    public List<PanelOwner> getTemplateList(){
        var tempList = new ArrayList<PanelOwner>();
        tempList.add(owner1);
        return tempList;
    }

    public List<Integer> getIdList(){
        var tempList = new ArrayList<Integer>();
        tempList.add(owner1.getUserId());
        return tempList;
    }
}
