package ua.lviv.iot.coursework.csvmanagers;

import org.springframework.stereotype.Component;
import ua.lviv.iot.coursework.models.PanelOwner;

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
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final String strDate = dateFormat.format(date);

    private final Date dateWithHours = Calendar.getInstance().getTime();
    private final DateFormat dateFormatWithHours = new SimpleDateFormat("yyyy-MM-dd-hh:mm");
    private final String strDateWithHours = dateFormatWithHours.format(dateWithHours);

    private static PanelOwner createPanelOwner(String[] metadata) {
        int userId = Integer.parseInt(metadata[0]);
        String fullName = metadata[1];
        String ownAddress = metadata[2];

        return new PanelOwner(userId, fullName, ownAddress);
    }

    public List<PanelOwner> getAllObjectsFromCSVFile(String wayToCSVFiles) {
        var tempList = new ArrayList<PanelOwner>();
        var fileName = wayToCSVFiles + "panelOwnerData.csv";
        Path pathToFile = Paths.get(fileName);
        String line = "";
        String splitBy = ",\s";
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(splitBy);
                tempList.add(createPanelOwner(attributes));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempList;
    }


    public void creatingCSVEachDay(List<PanelOwner> panelOwnerList, String wayToCSVFiles) throws IOException {
        try (FileWriter writer = new FileWriter(wayToCSVFiles +
                "panelOwners" + strDate + ".csv")) {
            writer.write(panelOwnerList.get(0).getHeaders());
            for (PanelOwner elem : panelOwnerList) {
                writer.write("\r\n");
                writer.write(elem.toCSV());
            }
            writer.write("\ntime: " + strDateWithHours);
        }
    }

    public void creatingOnlyObjectDataCSV(List<PanelOwner> panelOwnerList, String wayToCSVFiles) throws IOException {
        try (FileWriter writer = new FileWriter(wayToCSVFiles +
                "panelOwnerData" + ".csv")) {
            for (PanelOwner elem : panelOwnerList) {
                writer.write(elem.toCSV());
                writer.write("\r\n");
            }
        }
    }
}
