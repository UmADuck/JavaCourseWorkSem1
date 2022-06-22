package ua.lviv.iot.coursework.csvmanagers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ua.lviv.iot.coursework.models.Sensor;
import ua.lviv.iot.coursework.models.templates.SensorTemplates;

import java.util.LinkedList;

class SensorCSVManagerTest {

    @Test
    void addDataToHashFromCSVFile() {
        var sensorCSVManager = new SensorCSVManager();
        var testList = new LinkedList<Sensor>();
        sensorCSVManager.addDataToHashFromCSVFile();
        testList.add(sensorCSVManager.readHash(1));
        Assertions.assertEquals(1, testList.get(0).getSensorId());
    }

    @Test
    void addStartingValuesToHash() throws  NullPointerException{
        var sensorCSVManager = new SensorCSVManager();
        var sensorTemplates = new SensorTemplates();
        sensorCSVManager.addStartingValuesToHash(sensorTemplates.getIdList());
        Assertions.assertNotNull(sensorCSVManager.getAllHash());
    }

    @Test
    void readHash() throws  NullPointerException{
        var sensorCSVManager = new SensorCSVManager();
        var isEmptyHash = sensorCSVManager.readHash(1);
        Assertions.assertNull(isEmptyHash);
    }

    @Test
    void putToHash() {
        var sensorCSVManager = new SensorCSVManager();
        var objToHash = new Sensor(1, 45);
        var testList = new LinkedList<Sensor>();
        sensorCSVManager.putToHash(objToHash);
        testList.add(sensorCSVManager.readHash(1));
        Assertions.assertEquals(objToHash.getSensorId(), testList.get(0).getSensorId());
    }

    @Test
    void updateHash() {
        var sensorCSVManager = new SensorCSVManager();
        var objToHash = new Sensor(1, 45);
        var panel2 = new Sensor(2, 40);
        sensorCSVManager.putToHash(objToHash);
        sensorCSVManager.updateHash(1, panel2);
        var testList = new LinkedList<Sensor>();
        testList.add(sensorCSVManager.readHash(1));
        Assertions.assertEquals(1, panel2.getSensorId());
        Assertions.assertEquals(testList.getFirst().getCurrentAngle(), panel2.getCurrentAngle());
    }

    @Test
    void getAllHash() {
        var sensorCSVManager = new SensorCSVManager();
        var objToHash = new Sensor(1, 45);
        var sensor2 = new Sensor(2, 40);
        var testList = new LinkedList<Sensor>();
        testList.add(objToHash);
        testList.add(sensor2);
        sensorCSVManager.putToHash(objToHash);
        sensorCSVManager.putToHash(sensor2);
        var testMethodList = new LinkedList<Sensor>(sensorCSVManager.getAllHash());
        Assertions.assertEquals(testList.getFirst().getSensorId(), testMethodList.getFirst().getSensorId());
        Assertions.assertEquals(testList.getLast().getSensorId(), testMethodList.getLast().getSensorId());
    }

    @Test
    void removeFromHash() {
        var sensorCSVManager = new SensorCSVManager();
        var objToHash = new Sensor(1, 45);
        var testList = new LinkedList<Sensor>();
        testList.add(objToHash);
        sensorCSVManager.putToHash(objToHash);
        sensorCSVManager.removeFromHash(1);
        Assertions.assertNotEquals(sensorCSVManager.getAllHash(), testList);
    }

    @Test
    void addDataToHashFromCSVFileTest(){
        var sensorCSVManager = new SensorCSVManager();
        sensorCSVManager.addDataToHashFromCSVFile();
        var objToHash = new Sensor(1, 45);
        var testList = new LinkedList<Sensor>();
        testList.add(sensorCSVManager.readHash(1));
        Assertions.assertEquals(testList.get(0).getSensorId(), objToHash.getSensorId());
    }
}