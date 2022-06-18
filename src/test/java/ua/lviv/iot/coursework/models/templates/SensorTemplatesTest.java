package ua.lviv.iot.coursework.models.templates;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.coursework.models.PanelTypes;
import ua.lviv.iot.coursework.models.Sensor;
import ua.lviv.iot.coursework.models.SolarPanel;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SensorTemplatesTest {

    SensorTemplates sensorTemplates = new SensorTemplates();

    @Test
    void getTemplateList() {
        var tempList = new ArrayList<Sensor>(sensorTemplates.getTemplateList());
        var tempList1 = new ArrayList<Sensor>();
        var sensor1 = new Sensor(1, 45);
        var sensor2 = new Sensor(2, 40);
        var sensor3 = new Sensor(3, 50);
        tempList1.add(sensor1);
        tempList1.add(sensor2);
        tempList1.add(sensor3);
        Assertions.assertEquals(tempList.get(0).getSensorId(), tempList1.get(0).getSensorId());
        Assertions.assertEquals(tempList.get(1).getSensorId(), tempList1.get(1).getSensorId());
        Assertions.assertEquals(tempList.get(2).getSensorId(), tempList1.get(2).getSensorId());
    }

    @Test
    void getIdList() {
        var tempIdList = new ArrayList<Integer>();
        var tempMethodIdList = new ArrayList<Integer>(sensorTemplates.getIdList());
        tempIdList.add(1);
        tempIdList.add(2);
        tempIdList.add(3);
        Assertions.assertEquals(tempIdList.get(0), tempMethodIdList.get(0));
        Assertions.assertEquals(tempIdList.get(1), tempMethodIdList.get(1));
        Assertions.assertEquals(tempIdList.get(2), tempMethodIdList.get(2));
    }
}