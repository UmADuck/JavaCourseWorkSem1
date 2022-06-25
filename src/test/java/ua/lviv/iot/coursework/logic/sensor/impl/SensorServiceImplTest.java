package ua.lviv.iot.coursework.logic.sensor.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lviv.iot.coursework.logic.panel.impl.PanelServiceImpl;
import ua.lviv.iot.coursework.models.PanelTypes;
import ua.lviv.iot.coursework.models.Sensor;
import ua.lviv.iot.coursework.models.SolarPanel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.*;

class SensorServiceImplTest {

    private final SensorServiceImpl service = new SensorServiceImpl();
    private final List<Sensor> objList = new LinkedList<>();

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private final LocalDate sensor1Start = LocalDate.parse("2022/06/11", formatter);
    private final LocalDate sensor2Start = LocalDate.parse("2022/06/14", formatter);
    private final LocalDate sensor3Start = LocalDate.parse("2022/06/15", formatter);

    private final LocalDateTime now = LocalDateTime.now();
    private final long timeOfWork1 = DAYS.between(sensor1Start, now);
    private final long timeOfWork2 = DAYS.between(sensor2Start, now);
    private final long timeOfWork3 = DAYS.between(sensor3Start, now);


    @BeforeEach
    void setUp() {
        Sensor sensor1 = new Sensor(1, sensor1Start, (int) timeOfWork1,
                500, 5000, 7500,
                45, 40, 1);
        Sensor sensor2 = new Sensor(2, sensor2Start, (int) timeOfWork2,
                400, 4000, 6000,
                40, 40, 2);
        Sensor sensor3 = new Sensor(3, sensor3Start, (int) timeOfWork3,
                600, 6000, 8000,
                50, 40, 3);
        objList.add(sensor1);
        objList.add(sensor2);
        objList.add(sensor3);
        service.addStartingObjectsToCash();
    }

    @Test
    void addStartingObjectsToCash() {
        var tempService = new SensorServiceImpl();
        tempService.addStartingObjectsToCash();
        var tempList = new LinkedList<Sensor>(service.readALL());
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
    void create() {
        var tempService = new SensorServiceImpl();
        var tempList1 = new LinkedList<Sensor>();
        Sensor sensor1 = new Sensor(1, sensor1Start, (int) timeOfWork1,
                500, 5000, 7500,
                45, 40, 1);
        tempList1.add(sensor1);
        tempService.create(sensor1);
        var tempList2 = new LinkedList<Sensor>(tempService.readALL());
        Assertions.assertEquals(tempList1.size(), tempList2.size());
        Assertions.assertEquals(tempList1.get(0).getSensorId(), tempList2.get(0).getSensorId());
        Assertions.assertEquals(tempList1.get(0).getDaysOfWork(), tempList2.get(0).getDaysOfWork());
        Assertions.assertEquals(tempList1.get(0).getCurrentPriceForElectricity(),
                tempList2.get(0).getCurrentPriceForElectricity());
        Assertions.assertEquals(tempList1.get(0).getCurrentAngle(), tempList2.get(0).getCurrentAngle());
        Assertions.assertEquals(tempList1.get(0).getEnergyGivenPerDay(), tempList2.get(0).getEnergyGivenPerDay());
        Assertions.assertEquals(tempList1.get(0).getEnergyGivenPerHour(), tempList2.get(0).getEnergyGivenPerHour());
        Assertions.assertEquals(tempList1.get(0).getPriceOfSoldEnergyPerHour(),
                tempList2.get(0).getPriceOfSoldEnergyPerHour());
        Assertions.assertEquals(tempList1.get(0).getLevelOfBatteryCapacity(),
                tempList2.get(0).getLevelOfBatteryCapacity());
        Assertions.assertEquals(tempList1.get(0).getTimeOfLaunch(), tempList2.get(0).getTimeOfLaunch());
        Assertions.assertEquals(tempList1.get(0).getPanelId(), tempList2.get(0).getPanelId());
    }

    @Test
    void readALL() {
        var tempList = new LinkedList<Sensor>(service.readALL());
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
    void read() {
        Sensor sensor1 = new Sensor(1, sensor1Start, (int) timeOfWork1,
                500, 5000, 7500,
                45, 40, 1);
        var tempList = new LinkedList<Sensor>();
        tempList.add(service.read(1));
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
    void update() {
        var tempService = new SensorServiceImpl();
        var tempList1 = new LinkedList<Sensor>();
        var tempList2 = new LinkedList<Sensor>();
        Sensor sensor1 = new Sensor(1, sensor1Start, (int) timeOfWork1,
                500, 5000, 7500,
                45, 40, 1);
        Sensor sensor2 = new Sensor(2, sensor2Start, (int) timeOfWork2,
                400, 4000, 6000,
                40, 40, 2);
        tempService.create(sensor1);
        tempService.update(1, sensor2);
        tempList1.add(sensor2);
        tempList2.add(tempService.read(1));
        Assertions.assertEquals(tempList1.get(0).getSensorId(), tempList2.get(0).getSensorId());
        Assertions.assertEquals(tempList1.get(0).getDaysOfWork(), tempList2.get(0).getDaysOfWork());
        Assertions.assertEquals(tempList1.get(0).getCurrentPriceForElectricity(),
                tempList2.get(0).getCurrentPriceForElectricity());
        Assertions.assertEquals(tempList1.get(0).getCurrentAngle(), tempList2.get(0).getCurrentAngle());
        Assertions.assertEquals(tempList1.get(0).getEnergyGivenPerDay(), tempList2.get(0).getEnergyGivenPerDay());
        Assertions.assertEquals(tempList1.get(0).getEnergyGivenPerHour(), tempList2.get(0).getEnergyGivenPerHour());
        Assertions.assertEquals(tempList1.get(0).getPriceOfSoldEnergyPerHour(),
                tempList2.get(0).getPriceOfSoldEnergyPerHour());
        Assertions.assertEquals(tempList1.get(0).getLevelOfBatteryCapacity(),
                tempList2.get(0).getLevelOfBatteryCapacity());
        Assertions.assertEquals(tempList1.get(0).getTimeOfLaunch(), tempList2.get(0).getTimeOfLaunch());
        Assertions.assertEquals(tempList1.get(0).getPanelId(), tempList2.get(0).getPanelId());
    }

    @Test
    void delete() {
        var tempService = new SensorServiceImpl();
        var tempList1 = new LinkedList<Sensor>();
        Sensor sensor1 = new Sensor(1, sensor1Start, (int) timeOfWork1,
                500, 5000, 7500,
                45, 40, 1);
        tempService.create(sensor1);
        tempService.delete(1);
        tempList1.add(tempService.read(1));
        Assertions.assertNull(tempList1.get(0));

    }
}