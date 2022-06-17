package ua.lviv.iot.coursework.csvmanagers;

import org.springframework.stereotype.Component;
import ua.lviv.iot.coursework.models.Sensor;
import ua.lviv.iot.coursework.models.templates.SensorTemplates;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class SensorCSVManager {

    Map<Integer, Sensor> sensorMap = new HashMap<>();
    SensorTemplates templates = new SensorTemplates();

    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String strDate = dateFormat.format(date);

    Date dateWithHours = Calendar.getInstance().getTime();
    DateFormat dateFormatWithHours = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    String strDateWithHours = dateFormatWithHours.format(dateWithHours);

    public void addStartingValuesToHash(List<Integer> list){
        var tempList = templates.getTemplateList();
        for(int i = 0; i<list.size(); i++){
            sensorMap.put(list.get(i), tempList.get(i));
        }
    }

    public Sensor readHash(int id) {
        return sensorMap.get(id);
    }

    public void putToHash(Sensor sensor) {
        sensorMap.put(sensor.getSensorId(), sensor);

    }

    public void updateData() {
        var tempList = new LinkedList<Sensor>();
        for (Map.Entry<Integer, Sensor> entry : sensorMap.entrySet()) {
            tempList.add(entry.getValue());
            tempList = templates.getTemplateList();
        }
    }

    public boolean updateHash(int id, Sensor sensor) {

        if(sensorMap.containsKey(id)){
            sensorMap.replace(id, sensor);
            return true;
        }
        else return false;
    }

    public List<Sensor> getAllHash(){
        var tempList = new LinkedList<Sensor>();
        for (Map.Entry<Integer, Sensor> entry : sensorMap.entrySet()) {
            tempList.add(entry.getValue());
        }
        return tempList;
    }

    public boolean removeFromHash(int id){
        if(sensorMap.containsKey(id)){
            sensorMap.remove(id);
            return true;
        }
        else return false;
    }

    public void creatingCSVEachDay() throws IOException {
        try(FileWriter writer = new FileWriter("src/main/java/ua/lviv/iot/coursework/csvcontainer/" +
                "sensorcsvholder/sensors" + strDate + ".csv")){
            writer.write(templates.getTemplateList().get(0).getStaticHeaders());
            for(Sensor elem: templates.getTemplateList()){
                writer.write("\r\n");
                writer.write(elem.staticToCSV());
                writer.write("\r\n");
            }
        }

        }

    public void creatingCSVEachHour()throws IOException{
        try(FileWriter writer = new FileWriter("src/main/java/ua/lviv/iot/coursework/csvcontainer/" +
                "sensorcsvholder/sensors" + strDate + ".csv")){

            writer.write(templates.getTemplateList().get(0).getNonStaticHeaders());
            for(Sensor elem: templates.getTemplateList()){
                writer.write("\r\n");
                writer.write(elem.nonStaticToCSV());
                writer.write("\r\n");
            }
        }
    }
}
