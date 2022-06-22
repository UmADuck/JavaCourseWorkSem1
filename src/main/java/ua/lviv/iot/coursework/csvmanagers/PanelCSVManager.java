package ua.lviv.iot.coursework.csvmanagers;

import org.springframework.stereotype.Component;
import ua.lviv.iot.coursework.models.PanelOwner;
import ua.lviv.iot.coursework.models.PanelTypes;
import ua.lviv.iot.coursework.models.Sensor;
import ua.lviv.iot.coursework.models.SolarPanel;
import ua.lviv.iot.coursework.models.templates.SolarPanelTemplates;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class PanelCSVManager {

    private final Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String strDate = dateFormat.format(date);

    private final Date dateWithHours = Calendar.getInstance().getTime();
    DateFormat dateFormatWithHours = new SimpleDateFormat("yyyy-MM-dd-hh:mm");
    String strDateWithHours = dateFormatWithHours.format(dateWithHours);

    private final Map<Integer, SolarPanel> panelMap = new HashMap<>();
    private final SolarPanelTemplates templates = new SolarPanelTemplates();

    private static SolarPanel createSolarPanel(String[] metadata) {
        int panelId = Integer.parseInt(metadata[0]);
        PanelTypes panelType = PanelTypes.valueOf(metadata[1]);
        int panelPower = Integer.parseInt(metadata[2]);
        int batteryCapacity = Integer.parseInt(metadata[3]);
        String panelAddress = metadata[4];

        return new SolarPanel(panelId, panelType, panelPower, batteryCapacity, panelAddress);
    }

    public void addStartingValuesToHash(List<Integer> list){
        var tempList = templates.getTemplateList();
        for(int i = 0; i<list.size(); i++){
            panelMap.put(list.get(i), tempList.get(i));
        }
    }

    public void addDataToHashFromCSVFile() {
        var fileName = "src/main/resources/csvcontainer/" +
                "panelcsvholder/panelData.csv";
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {
            long numberOfLines = 0;
            numberOfLines = Files.lines(pathToFile).count();

            for(int i = 0; i < numberOfLines; i++){
                String line = br.readLine();
                String[] attributes = line.split(",\s");

                SolarPanel solarPanel = createSolarPanel(attributes);
                if(!panelMap.containsKey(solarPanel.getPanelId())) {
                    panelMap.put(solarPanel.getPanelId(), solarPanel);
                }
                line = null;
            }

        } catch (IOException e) {
            e.printStackTrace();
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
        else panelMap.put(id, solarPanel);
        return false;
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
        try(FileWriter writer = new FileWriter("src/main/resources/csvcontainer/panelcsvholder/" +
                "solarPanels" + strDate + ".csv")){
            writer.write(templates.getTemplateList().get(0).getHeaders() + "\ntime: "+ strDateWithHours);
            for(SolarPanel elem: templates.getTemplateList()){
                writer.write("\r\n");
                writer.write(elem.toCSV());
            }
        }
    }

    public void creatingOnlyObjectDataCSV()throws IOException{
        try(FileWriter writer = new FileWriter("src/main/resources/csvcontainer/panelcsvholder/" +
                "panelData" + ".csv")){

            for(SolarPanel elem: templates.getTemplateList()){
                writer.write(elem.toCSV());
                writer.write("\r\n");
            }
        }
    }
}
