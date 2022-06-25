package ua.lviv.iot.coursework.csvmanagers;

import org.springframework.stereotype.Component;
import ua.lviv.iot.coursework.models.PanelTypes;
import ua.lviv.iot.coursework.models.SolarPanel;

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
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final String strDate = dateFormat.format(date);
    private final Date dateWithHours = Calendar.getInstance().getTime();
    private final DateFormat dateFormatWithHours = new SimpleDateFormat("yyyy-MM-dd-hh:mm");
    private final String strDateWithHours = dateFormatWithHours.format(dateWithHours);

    private static SolarPanel createSolarPanel(String[] metadata) {
        int panelId = Integer.parseInt(metadata[0]);
        PanelTypes panelType = PanelTypes.valueOf(metadata[1]);
        int panelPower = Integer.parseInt(metadata[2]);
        int batteryCapacity = Integer.parseInt(metadata[3]);
        String panelAddress = metadata[4];
        int ownerId = Integer.parseInt(metadata[5]);

        return new SolarPanel(panelId, panelType, panelPower, batteryCapacity, panelAddress, ownerId);
    }

    public List<SolarPanel> getAllObjectsFromCSVFile(String wayToCSVFiles) {
        var tempList = new ArrayList<SolarPanel>();
        var fileName = wayToCSVFiles + "panelData.csv";
        Path pathToFile = Paths.get(fileName);
        String line = "";
        String splitBy = ",\s";
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(splitBy);

                tempList.add(createSolarPanel(attributes));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempList;
    }

    public void creatingCSVEachDay(List<SolarPanel> panelList, String wayToCSVFiles) throws IOException {
        try (FileWriter writer = new FileWriter(wayToCSVFiles + "solarPanels" + strDate + ".csv")) {
            writer.write(panelList.get(0).getHeaders());
            for (SolarPanel elem : panelList) {
                writer.write("\r\n");
                writer.write(elem.toCSV());
            }
            writer.write("\ntime: " + strDateWithHours);
        }
    }

    public void creatingOnlyObjectDataCSV(List<SolarPanel> panelList, String wayToCSVFiles) throws IOException {
        try (FileWriter writer = new FileWriter(wayToCSVFiles + "panelData" + ".csv")) {
            for (SolarPanel elem : panelList) {
                writer.write(elem.toCSV());
                writer.write("\r\n");
            }
        }
    }
}
