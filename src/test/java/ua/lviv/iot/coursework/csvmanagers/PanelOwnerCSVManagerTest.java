package ua.lviv.iot.coursework.csvmanagers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.coursework.logic.panelowner.impl.PanelOwnerServiceImpl;
import ua.lviv.iot.coursework.models.PanelOwner;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

class PanelOwnerCSVManagerTest {

    static private final String wayToCSVFiles = "src/test/resources/csvcontainer/panelownercsvholder/";
    private final PanelOwnerServiceImpl service = new PanelOwnerServiceImpl();
    private final PanelOwnerCSVManager manager = new PanelOwnerCSVManager();
    private final List<String[]> columnValues = new ArrayList<String[]>();
    private final Date date = Calendar.getInstance().getTime();
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final String strDate = dateFormat.format(date);
    private List<PanelOwner> objList = new ArrayList<>();

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
        Assertions.assertEquals(objList.get(0).getOwnerId(), tempList.get(0).getOwnerId());
        Assertions.assertEquals(objList.get(0).getFullName(), tempList.get(0).getFullName());
        Assertions.assertEquals(objList.get(0).getOwnAddress(), tempList.get(0).getOwnAddress());

    }

    @Test
    void creatingCSVEachDay() throws IOException {
        manager.creatingOnlyObjectDataCSV(objList, wayToCSVFiles);
        int i = 0;
        try {
            Scanner scanner = new Scanner(new File(wayToCSVFiles + "panelOwners" + strDate + ".csv"));
            while (scanner.hasNextLine()) {
                String[] values = scanner.nextLine().split(",\s");
                columnValues.add(values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(Integer.parseInt(columnValues.get(1)[0]), objList.get(0).getOwnerId());
        Assertions.assertEquals(columnValues.get(1)[1], objList.get(0).getFullName());
        Assertions.assertEquals(columnValues.get(1)[2], objList.get(0).getOwnAddress());
    }

    @Test
    void creatingOnlyObjectDataCSV() throws IOException {
        manager.creatingOnlyObjectDataCSV(objList, wayToCSVFiles);
        var tempList = new ArrayList<>(manager.getAllObjectsFromCSVFile(wayToCSVFiles));
        Assertions.assertEquals(tempList.size(), objList.size());
        Assertions.assertEquals(objList.get(0).getOwnerId(), tempList.get(0).getOwnerId());
        Assertions.assertEquals(objList.get(0).getFullName(), tempList.get(0).getFullName());
        Assertions.assertEquals(objList.get(0).getOwnAddress(), tempList.get(0).getOwnAddress());
    }
}