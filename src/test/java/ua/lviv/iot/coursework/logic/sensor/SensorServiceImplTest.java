package ua.lviv.iot.coursework.logic.sensor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.coursework.logic.sensor.impl.SensorServiceImpl;
import ua.lviv.iot.coursework.models.Sensor;

import java.util.LinkedList;

class SensorServiceImplTest {

    @Test
    void create() {
        var sensorService = new SensorServiceImpl();
        var objToHash = new Sensor(1, 45);
        var testList = new LinkedList<Sensor>();
        sensorService.create(objToHash);
        testList.add(sensorService.read(1));
        Assertions.assertEquals(objToHash.getSensorId(), testList.get(0).getSensorId());
    }

    @Test
    void readALL() {
        var sensorService = new SensorServiceImpl();
        var objToHash = new Sensor(1, 45);
        var sensor2 = new Sensor(2, 40);
        var testList = new LinkedList<Sensor>();
        testList.add(objToHash);
        testList.add(sensor2);
        sensorService.create(objToHash);
        sensorService.create(sensor2);
        var testMethodList = new LinkedList<Sensor>(sensorService.readALL());
        Assertions.assertEquals(testList.getFirst().getSensorId(), testMethodList.getFirst().getSensorId());
        Assertions.assertEquals(testList.getLast().getSensorId(), testMethodList.getLast().getSensorId());
    }

    @Test
    void read() {
        var sensorService = new SensorServiceImpl();
        var isEmptyHash = sensorService.read(1);
        Assertions.assertNull(isEmptyHash);
    }

    @Test
    void update() {
        var sensorService = new SensorServiceImpl();
        var objToHash = new Sensor(1, 45);
        var panel2 = new Sensor(2, 40);
        sensorService.create(objToHash);
        sensorService.update(1, panel2);
        var testList = new LinkedList<Sensor>();
        testList.add(sensorService.read(1));
        Assertions.assertEquals(1, panel2.getSensorId());
        Assertions.assertEquals(testList.getFirst().getCurrentAngle(), panel2.getCurrentAngle());
    }

    @Test
    void delete() {
        var sensorService = new SensorServiceImpl();
        var objToHash = new Sensor(1, 45);
        var testList = new LinkedList<Sensor>();
        testList.add(objToHash);
        sensorService.create(objToHash);
        sensorService.delete(1);
        Assertions.assertNotEquals(sensorService.readALL(), testList);
    }
}