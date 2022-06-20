package ua.lviv.iot.coursework.csvmanagers;

import org.springframework.stereotype.Component;
import ua.lviv.iot.coursework.models.Sensor;
import ua.lviv.iot.coursework.models.templates.SensorTemplates;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class SensorCSVManager {

    private final Map<Integer, Sensor> sensorMap = new HashMap<>();
    SensorTemplates templates = new SensorTemplates();

    private final Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String strDate = dateFormat.format(date);

    private final Date dateWithHours = Calendar.getInstance().getTime();
    DateFormat dateFormatWithHours = new SimpleDateFormat("yyyy-MM-dd-hh:mm");
    String strDateWithHours = dateFormatWithHours.format(dateWithHours);

    static private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static Sensor createSensor(String[] metadata) throws ParseException {
        int sensorId = Integer.parseInt(metadata[0]);
        LocalDate timeOfLaunch = LocalDate.parse(metadata[1], formatter);
        int timeOfWork = Integer.parseInt(metadata[2]);
        int energyGivenPerHour = Integer.parseInt(metadata[3]);
        int energyGivenPerDay = Integer.parseInt(metadata[4]);
        int levelOfBatteryCapacity = Integer.parseInt(metadata[5]);
        int currentAngle = Integer.parseInt(metadata[6]);
        int currentPriceForElectricity = Integer.parseInt(metadata[7]);
        int priceOfSoldEnergyPerHour = Integer.parseInt(metadata[8]);

        return new Sensor(sensorId, timeOfLaunch, timeOfWork, energyGivenPerHour, energyGivenPerDay,
                levelOfBatteryCapacity, currentAngle, currentPriceForElectricity, priceOfSoldEnergyPerHour);
    }

    public void addStartingValuesToHash(List<Integer> list){
        var tempList = templates.getTemplateList();
        for(int i = 0; i<list.size(); i++){
            sensorMap.put(list.get(i), tempList.get(i));
        }
    }

    public void addDataToHashFromCSVFile() {
        var fileName = "src/main/resources/csvcontainer/" +
                "sensorcsvholder/sensorData.csv";
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            long numberOfLines = 0;
            numberOfLines = Files.lines(pathToFile).count();

            for(int i = 0; i < numberOfLines; i++){
                String line = br.readLine();
                String[] attributes = line.split(",\s");

                Sensor sensor = createSensor(attributes);
                if(!sensorMap.containsKey(sensor.getSensorId())) {
                    sensorMap.put(sensor.getSensorId(), sensor);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
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
            sensor.setSensorId(id);
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
        try(FileWriter writer = new FileWriter("src/main/resources/csvcontainer/" +
                "sensorcsvholder/sensors" + strDate + ".csv")){
            writer.write(templates.getTemplateList().get(0).getStaticHeaders());
            for(Sensor elem: templates.getTemplateList()){
                writer.write("\r\n");
                writer.write(elem.staticToCSV());
                writer.write("\r\n");
            }
        }
    }

    public void updateCSVEachHour()throws IOException{
        try(FileWriter writer = new FileWriter("src/main/resources/csvcontainer/" +
                "sensorcsvholder/sensors" + strDate + ".csv")){

            writer.write(templates.getTemplateList().get(0).getNonStaticHeaders() + "\ntime: "+ strDateWithHours);
            for(Sensor elem: templates.getTemplateList()){
                writer.write("\r\n");
                writer.write(elem.nonStaticToCSV());
            }
        }
    }

    public void creatingOnlyObjectDataCSV()throws IOException{
        try(FileWriter writer = new FileWriter("src/main/resources/csvcontainer/" +
                "sensorcsvholder/sensorData" + ".csv")){

            for(Sensor elem: templates.getTemplateList()){
                writer.write(elem.nonStaticToCSV());
                writer.write("\r\n");
            }
        }
    }
}
