package ua.lviv.iot.coursework.csvmanagers;

import org.springframework.stereotype.Component;
import ua.lviv.iot.coursework.models.PanelOwner;
import ua.lviv.iot.coursework.models.SolarPanel;
import ua.lviv.iot.coursework.models.templates.PanelOwnerTemplates;

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
public class PanelOwnerCSVManager {

    private final Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String strDate = dateFormat.format(date);

    private final Date dateWithHours = Calendar.getInstance().getTime();
    DateFormat dateFormatWithHours = new SimpleDateFormat("yyyy-MM-dd-hh:mm");
    String strDateWithHours = dateFormatWithHours.format(dateWithHours);

    private final Map<Integer, PanelOwner> panelOwnerMap = new HashMap<>();
    private final PanelOwnerTemplates templates = new PanelOwnerTemplates();

    private static PanelOwner createPanelOwner(String[] metadata) {
        int userId = Integer.parseInt(metadata[0]);
        String fullName = metadata[1];
        String ownAddress = metadata[2];

        return new PanelOwner(userId, fullName, ownAddress);
    }

    public void addStartingValuesToHash(List<Integer> list){
        var tempList = templates.getTemplateList();
        for(int i = 0; i<list.size(); i++){
            panelOwnerMap.put(list.get(i), tempList.get(i));
        }
    }

    public void addDataToHashFromCSVFile() {
        var fileName = "src/main/resources/csvcontainer/" +
                "panelownercsvholder/panelOwnerData.csv";
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {
            long numberOfLines;
            numberOfLines = Files.lines(pathToFile).count();

            for(int i = 0; i < numberOfLines; i++){
                String line = br.readLine();
                String[] attributes = line.split(",\s");

                PanelOwner panelOwner = createPanelOwner(attributes);
                if(!panelOwnerMap.containsKey(panelOwner.getUserId())) {
                    panelOwnerMap.put(panelOwner.getUserId(), panelOwner);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewObjectDataToCSV(PanelOwner panelOwner) {
        try(FileWriter writer = new FileWriter("src/main/resources/csvcontainer/" +
                "panelownercsvholder/panelOwners"+ strDate + ".csv")){
            writer.write(templates.getTemplateList().get(0).getHeaders() + "\ntime: "+ strDateWithHours);
            for(PanelOwner elem: templates.getTemplateList()){
                writer.write("\r\n");
                writer.write(elem.toCSV());
            }
            writer.write(panelOwner.toCSV());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try(FileWriter otherWriter = new FileWriter("src/main/resources/csvcontainer/" +
                "panelownercsvholder/panelOwnerData" + ".csv")){

            for(PanelOwner elem: templates.getTemplateList()){
                otherWriter.write(elem.toCSV());
                otherWriter.write("\r\n");
            }
            otherWriter.write(panelOwner.toCSV());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public PanelOwner readHash(int id) {

        return panelOwnerMap.get(id);
    }

    public void putToHash(PanelOwner panelOwner) {

        panelOwnerMap.put(panelOwner.getUserId(), panelOwner);
    }

    public boolean updateHash(int id, PanelOwner panelOwner) {

        if(panelOwnerMap.containsKey(id)){
            panelOwnerMap.replace(id, panelOwner);
            panelOwner.setUserId(id);
            return true;
        }
        else panelOwnerMap.put(id, panelOwner);
        return false;
    }

    public List<PanelOwner> getAllHash(){
        var tempList = new LinkedList<PanelOwner>();
        for (Map.Entry<Integer, PanelOwner> entry : panelOwnerMap.entrySet()) {
            tempList.add(entry.getValue());
        }
        return tempList;
    }

    public boolean removeFromHash(int id){
        if(panelOwnerMap.containsKey(id)){
            panelOwnerMap.remove(id);
            return true;
        }
        else return false;
    }

    public void creatingCSVEachDay() throws IOException {
        try(FileWriter writer = new FileWriter("src/main/resources/csvcontainer/" +
                "panelownercsvholder/panelOwners"+ strDate + ".csv")){
            writer.write(templates.getTemplateList().get(0).getHeaders() + "\ntime: "+ strDateWithHours);
            for(PanelOwner elem: templates.getTemplateList()){
                writer.write("\r\n");
                writer.write(elem.toCSV());
            }
        }
    }
    public void creatingOnlyObjectDataCSV()throws IOException{
        try(FileWriter writer = new FileWriter("src/main/resources/csvcontainer/" +
                "panelownercsvholder/panelOwnerData" + ".csv")){

            for(PanelOwner elem: templates.getTemplateList()){
                writer.write(elem.toCSV());
                writer.write("\r\n");
            }
        }
    }
}
