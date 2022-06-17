package ua.lviv.iot.coursework.csvmanagers;

import org.springframework.stereotype.Component;
import ua.lviv.iot.coursework.models.SolarPanel;
import ua.lviv.iot.coursework.models.templates.SolarPanelTemplates;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class PanelCSVManager {

    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String strDate = dateFormat.format(date);

    Map<Integer, SolarPanel> panelMap = new HashMap<>();
    SolarPanelTemplates templates = new SolarPanelTemplates();

    public void addStartingValuesToHash(List<Integer> list){
        var tempList = templates.getTemplateList();
        for(int i = 0; i<list.size(); i++){
            panelMap.put(list.get(i), tempList.get(i));
        }
    }

    public SolarPanel readHash(int id) {

        return panelMap.get(id);
    }

    public void putToHash(SolarPanel solarPanel) {
        if(!panelMap.containsKey(solarPanel.getPanelId())) {
            panelMap.put(solarPanel.getPanelId(), solarPanel);
        }
    }

    public boolean updateHash(int id, SolarPanel solarPanel) {

        if(panelMap.containsKey(id)){
            panelMap.replace(id, solarPanel);
            solarPanel.setPanelId(id);
            return true;
        }
        else return false;
    }

    public LinkedList<SolarPanel> getAllHash(){
        var tempList = new LinkedList<SolarPanel>();
        for (Map.Entry<Integer, SolarPanel> entry : panelMap.entrySet()) {
            tempList.add(entry.getValue());
        }
        return tempList;
    }

    public boolean removeFromHash(int id){
        if(panelMap.containsKey(id)){
            panelMap.remove(id);
            return true;
        }
        else return false;
    }

    public void creatingCSVEachDay() throws IOException {
        try(FileWriter writer = new FileWriter("src/main/java/ua/lviv/iot/coursework/csvcontainer/" +
                "panelcsvholder/solarPanels" + strDate + ".csv")){
            writer.write(templates.getTemplateList().get(0).getHeaders());
            for(SolarPanel elem: templates.getTemplateList()){
                writer.write("\r\n");
                writer.write(elem.toCSV());
                writer.write("\r\n");
            }
        }
    }
}
