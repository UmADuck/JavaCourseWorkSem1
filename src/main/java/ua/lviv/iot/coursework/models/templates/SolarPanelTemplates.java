package ua.lviv.iot.coursework.models.templates;

import ua.lviv.iot.coursework.models.PanelTypes;
import ua.lviv.iot.coursework.models.SolarPanel;

import java.util.ArrayList;
import java.util.List;

public class SolarPanelTemplates {

    SolarPanel panel1 = new SolarPanel(1, PanelTypes.MONOCRYSTALLINE, 500,
            5000, "Ukraine, Lviv");
    SolarPanel panel2 = new SolarPanel(2, PanelTypes.POLYCRYSTALLINE, 300,
            3000, "Ukraine, Lviv");
    SolarPanel panel3 = new SolarPanel(3, PanelTypes.THIN_FILM, 400,
            4000, "Ukraine, Lviv");

    public List<SolarPanel> getTemplateList(){
        var tempList = new ArrayList<SolarPanel>();
        tempList.add(panel1);
        tempList.add(panel2);
        tempList.add(panel3);
        return tempList;
    }

    public List<Integer> getIdList(){
        var tempList = new ArrayList<Integer>();
        tempList.add(panel1.getPanelId());
        tempList.add(panel2.getPanelId());
        tempList.add(panel3.getPanelId());
        return tempList;
    }
}
