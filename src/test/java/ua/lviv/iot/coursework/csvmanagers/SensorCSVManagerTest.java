package ua.lviv.iot.coursework.csvmanagers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.coursework.logic.sensor.impl.SensorServiceImpl;
import ua.lviv.iot.coursework.models.Sensor;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

class SensorCSVManagerTest {

    static private final String wayToCSVFiles = "src/test/resources/csvcontainer/sensorcsvholder/";
    private final SensorServiceImpl service = new SensorServiceImpl();
    private final SensorCSVManager manager = new SensorCSVManager();
    private final List<String[]> columnValues = new ArrayList<String[]>();
    private final Date date = Calendar.getInstance().getTime();
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final String strDate = dateFormat.format(date);
    private List<Sensor> objList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        service.addStartingObjectsToCash();
        objList = service.readALL();
    }

    @Test
    void getAllObjectsFromCSVFile() throws IOException {
        manager.creatingCSVEachDay(objList, wayToCSVFiles);
        var tempList = new ArrayList<>(manager.getAllObjectsFromCSVFile(wayToCSVFiles));
        Assertions.assertEquals(tempList.size(), objList.size());
        Assertions.assertEquals(objList.get(0).getSensorId(), tempList.get(0).getSensorId());
        Assertions.assertEquals(objList.get(0).getDaysOfWork(), tempList.get(0).getDaysOfWork());
        Assertions.assertEquals(objList.get(0).getCurrentPriceForElectricity(),
                tempList.get(0).getCurrentPriceForElectricity());
        Assertions.assertEquals(objList.get(0).getCurrentAngle(), tempList.get(0).getCurrentAngle());
        Assertions.assertEquals(objList.get(0).getEnergyGivenPerDay(), tempList.get(0).getEnergyGivenPerDay());
        Assertions.assertEquals(objList.get(0).getEnergyGivenPerHour(), tempList.get(0).getEnergyGivenPerHour());
        Assertions.assertEquals(objList.get(0).getPriceOfSoldEnergyPerHour(),
                tempList.get(0).getPriceOfSoldEnergyPerHour());
        Assertions.assertEquals(objList.get(0).getLevelOfBatteryCapacity(),
                tempList.get(0).getLevelOfBatteryCapacity());
        Assertions.assertEquals(objList.get(0).getTimeOfLaunch(), tempList.get(0).getTimeOfLaunch());
        Assertions.assertEquals(objList.get(0).getPanelId(), tempList.get(0).getPanelId());
    }

    @Test
    void creatingCSVEachDay() throws IOException {
        manager.creatingOnlyObjectDataCSV(objList, wayToCSVFiles);
        int i = 0;
        try {
            Scanner scanner = new Scanner(new File(wayToCSVFiles + "sensors" + strDate + ".csv"));
            while (scanner.hasNextLine()) {
                String[] values = scanner.nextLine().split(",\s");
                columnValues.add(values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(Integer.parseInt(columnValues.get(1)[0]), objList.get(0).getPanelId());
        Assertions.assertEquals(LocalDate.parse(columnValues.get(1)[1]), objList.get(0).getTimeOfLaunch());
        Assertions.assertEquals(Integer.parseInt(columnValues.get(1)[2]), objList.get(0).getDaysOfWork());
        Assertions.assertEquals(Integer.parseInt(columnValues.get(1)[3]), objList.get(0).getEnergyGivenPerHour());
        Assertions.assertEquals(Integer.parseInt(columnValues.get(1)[4]), objList.get(0).getEnergyGivenPerDay());
        Assertions.assertEquals(Integer.parseInt(columnValues.get(1)[5]), objList.get(0).getLevelOfBatteryCapacity());
        Assertions.assertEquals(Integer.parseInt(columnValues.get(1)[6]), objList.get(0).getCurrentAngle());
        Assertions.assertEquals(Integer.parseInt(columnValues.get(1)[7]),
                objList.get(0).getCurrentPriceForElectricity());
        Assertions.assertEquals(Integer.parseInt(columnValues.get(1)[8]), objList.get(0).getPriceOfSoldEnergyPerHour());
        Assertions.assertEquals(Integer.parseInt(columnValues.get(1)[9]), objList.get(0).getPanelId());


    }

    @Test
    void creatingOnlyObjectDataCSV() throws IOException {
        manager.creatingOnlyObjectDataCSV(objList, wayToCSVFiles);
        var tempList = new ArrayList<>(manager.getAllObjectsFromCSVFile(wayToCSVFiles));
        Assertions.assertEquals(tempList.size(), objList.size());
        Assertions.assertEquals(objList.get(0).getSensorId(), tempList.get(0).getSensorId());
        Assertions.assertEquals(objList.get(0).getDaysOfWork(), tempList.get(0).getDaysOfWork());
        Assertions.assertEquals(objList.get(0).getCurrentPriceForElectricity(),
                tempList.get(0).getCurrentPriceForElectricity());
        Assertions.assertEquals(objList.get(0).getCurrentAngle(), tempList.get(0).getCurrentAngle());
        Assertions.assertEquals(objList.get(0).getEnergyGivenPerDay(), tempList.get(0).getEnergyGivenPerDay());
        Assertions.assertEquals(objList.get(0).getEnergyGivenPerHour(), tempList.get(0).getEnergyGivenPerHour());
        Assertions.assertEquals(objList.get(0).getPriceOfSoldEnergyPerHour(),
                tempList.get(0).getPriceOfSoldEnergyPerHour());
        Assertions.assertEquals(objList.get(0).getLevelOfBatteryCapacity(),
                tempList.get(0).getLevelOfBatteryCapacity());
        Assertions.assertEquals(objList.get(0).getTimeOfLaunch(), tempList.get(0).getTimeOfLaunch());
        Assertions.assertEquals(objList.get(0).getPanelId(), tempList.get(0).getPanelId());
    }
}