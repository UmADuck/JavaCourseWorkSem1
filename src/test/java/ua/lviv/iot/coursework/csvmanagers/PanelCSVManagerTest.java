package ua.lviv.iot.coursework.csvmanagers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.coursework.logic.panel.impl.PanelServiceImpl;
import ua.lviv.iot.coursework.models.PanelTypes;
import ua.lviv.iot.coursework.models.SolarPanel;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

class PanelCSVManagerTest {

    static private final String wayToCSVFiles = "src/test/resources/csvcontainer/panelcsvholder/";
    private final PanelServiceImpl service = new PanelServiceImpl();
    private final PanelCSVManager manager = new PanelCSVManager();
    private final List<String[]> columnValues = new ArrayList<String[]>();
    private final Date date = Calendar.getInstance().getTime();
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final String strDate = dateFormat.format(date);
    private List<SolarPanel> objList = new ArrayList<>();

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
        Assertions.assertEquals(objList.get(0).getPanelId(), tempList.get(0).getPanelId());
        Assertions.assertEquals(objList.get(0).getPanelType(), tempList.get(0).getPanelType());
        Assertions.assertEquals(objList.get(0).getPanelPower(), tempList.get(0).getPanelPower());
        Assertions.assertEquals(objList.get(0).getBatteryCapacity(), tempList.get(0).getBatteryCapacity());
        Assertions.assertEquals(objList.get(0).getPanelAddress(), tempList.get(0).getPanelAddress());
        Assertions.assertEquals(objList.get(0).getOwnerId(), tempList.get(0).getOwnerId());
    }

    @Test
    void creatingCSVEachDay() throws IOException {
        manager.creatingOnlyObjectDataCSV(objList, wayToCSVFiles);
        int i = 0;
        try {
            Scanner scanner = new Scanner(new File(wayToCSVFiles + "solarPanels" + strDate + ".csv"));
            while (scanner.hasNextLine()) {
                String[] values = scanner.nextLine().split(",\s");
                columnValues.add(values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(Integer.parseInt(columnValues.get(1)[0]), objList.get(0).getPanelId());
        Assertions.assertEquals(PanelTypes.valueOf(columnValues.get(1)[1]), objList.get(0).getPanelType());
        Assertions.assertEquals(Integer.parseInt(columnValues.get(1)[2]), objList.get(0).getPanelPower());
        Assertions.assertEquals(Integer.parseInt(columnValues.get(1)[3]), objList.get(0).getBatteryCapacity());
        Assertions.assertEquals(columnValues.get(1)[4], objList.get(0).getPanelAddress());
        Assertions.assertEquals(Integer.parseInt(columnValues.get(1)[5]), objList.get(0).getOwnerId());

    }

    @Test
    void creatingOnlyObjectDataCSV() throws IOException {
        manager.creatingOnlyObjectDataCSV(objList, wayToCSVFiles);
        var tempList = new ArrayList<>(manager.getAllObjectsFromCSVFile(wayToCSVFiles));
        Assertions.assertEquals(tempList.size(), objList.size());
        Assertions.assertEquals(objList.get(0).getPanelId(), tempList.get(0).getPanelId());
        Assertions.assertEquals(objList.get(0).getPanelType(), tempList.get(0).getPanelType());
        Assertions.assertEquals(objList.get(0).getPanelPower(), tempList.get(0).getPanelPower());
        Assertions.assertEquals(objList.get(0).getBatteryCapacity(), tempList.get(0).getBatteryCapacity());
        Assertions.assertEquals(objList.get(0).getPanelAddress(), tempList.get(0).getPanelAddress());
        Assertions.assertEquals(objList.get(0).getOwnerId(), tempList.get(0).getOwnerId());
    }
}