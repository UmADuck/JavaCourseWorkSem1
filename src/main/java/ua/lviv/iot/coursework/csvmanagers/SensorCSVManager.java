package ua.lviv.iot.coursework.csvmanagers;

import org.springframework.stereotype.Component;
import ua.lviv.iot.coursework.models.Sensor;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class SensorCSVManager {

    private final Date date = Calendar.getInstance().getTime();
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final String strDate = dateFormat.format(date);

    private final Date dateWithHours = Calendar.getInstance().getTime();
    private final DateFormat dateFormatWithHours = new SimpleDateFormat("yyyy-MM-dd-hh:mm");
    private final String strDateWithHours = dateFormatWithHours.format(dateWithHours);

    static private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static Sensor createSensor(String[] metadata) {
        int sensorId = Integer.parseInt(metadata[0]);
        LocalDate timeOfLaunch = LocalDate.parse(metadata[1], formatter);
        int daysOfWork = Integer.parseInt(metadata[2]);
        int energyGivenPerHour = Integer.parseInt(metadata[3]);
        int energyGivenPerDay = Integer.parseInt(metadata[4]);
        int levelOfBatteryCapacity = Integer.parseInt(metadata[5]);
        int currentAngle = Integer.parseInt(metadata[6]);
        int currentPriceForElectricity = Integer.parseInt(metadata[7]);
        int priceOfSoldEnergyPerHour = Integer.parseInt(metadata[8]);
        int panelId = Integer.parseInt(metadata[9]);

        return new Sensor(sensorId, timeOfLaunch, daysOfWork, energyGivenPerHour, energyGivenPerDay,
                levelOfBatteryCapacity, currentAngle, currentPriceForElectricity, priceOfSoldEnergyPerHour, panelId);
    }

    public List<Sensor> getAllObjectsFromCSVFile(String wayToCSVFiles) {
        var tempList = new ArrayList<Sensor>();
        var fileName = wayToCSVFiles + "sensorData.csv";
        Path pathToFile = Paths.get(fileName);
        String line = "";
        String splitBy = ",\s";
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

            while ((line = br.readLine()) != null ) {
                String[] attributes = line.split(splitBy);

                tempList.add(createSensor(attributes));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempList;
    }


    public void creatingCSVEachDay(List<Sensor> sensorList, String wayToCSVFiles) throws IOException {
        try (FileWriter writer = new FileWriter(wayToCSVFiles +
                "sensors" + strDate + ".csv")) {
            writer.write(sensorList.get(0).getHeaders());
            for (Sensor elem : sensorList) {
                writer.write("\r\n");
                writer.write(elem.toCSV());
            }
            writer.write("\ntime: " + strDateWithHours);
        }
    }

    public void creatingOnlyObjectDataCSV(List<Sensor> sensorList, String wayToCSVFiles) throws IOException {
        try (FileWriter writer = new FileWriter(wayToCSVFiles +
                "sensorData" + ".csv")) {

            for (Sensor elem : sensorList) {
                writer.write(elem.toCSV());
                writer.write("\r\n");
            }
        }
    }
}
