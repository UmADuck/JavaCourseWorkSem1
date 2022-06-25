package ua.lviv.iot.coursework.logic.sensor.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.lviv.iot.coursework.csvmanagers.SensorCSVManager;
import ua.lviv.iot.coursework.logic.sensor.SensorService;
import ua.lviv.iot.coursework.models.Sensor;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.BeforeCompletion;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@EnableScheduling
public class SensorServiceImpl implements SensorService {

    static private final String wayToCSVFile = "src/main/resources/csvcontainer/sensorcsvholder/";
    private final Map<Integer, Sensor> sensorMap = new HashMap<>();
    @Autowired
    private SensorCSVManager sensorCSVManager;

    @BeforeCompletion
    @PostConstruct
    public void addStartingObjectsToCash() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate sensor1Start = LocalDate.parse("2022/06/11", formatter);
        LocalDate sensor2Start = LocalDate.parse("2022/06/14", formatter);
        LocalDate sensor3Start = LocalDate.parse("2022/06/15", formatter);

        LocalDateTime now = LocalDateTime.now();
        long timeOfWork1 = DAYS.between(sensor1Start, now);
        long timeOfWork2 = DAYS.between(sensor2Start, now);
        long timeOfWork3 = DAYS.between(sensor3Start, now);

        Sensor sensor1 = new Sensor(1, sensor1Start, (int) timeOfWork1,
                500, 5000, 7500,
                45, 40, 1);
        Sensor sensor2 = new Sensor(2, sensor2Start, (int) timeOfWork2,
                400, 4000, 6000,
                40, 40, 2);
        Sensor sensor3 = new Sensor(3, sensor3Start, (int) timeOfWork3,
                600, 6000, 8000,
                50, 40, 3);
        create(sensor1);
        create(sensor2);
        create(sensor3);
    }

    @PreDestroy
    public void createOnlyObjectCSV() throws IOException {
        sensorCSVManager.creatingOnlyObjectDataCSV(readALL(), wayToCSVFile);
    }

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24)
    public void scheduledCSVWriter() throws IOException {
        sensorCSVManager.creatingCSVEachDay(readALL(), wayToCSVFile);
    }

    @Override
    public void create(Sensor sensor) {
        if (!sensorMap.containsKey(sensor.getSensorId())) {
            sensorMap.put(sensor.getSensorId(), sensor);
        }
    }

    @Override
    public List<Sensor> readALL() {
        var tempList = new LinkedList<Sensor>();
        for (Map.Entry<Integer, Sensor> entry : sensorMap.entrySet()) {
            tempList.add(entry.getValue());
        }
        return tempList;
    }

    @Override
    public Sensor read(int id) {
        return sensorMap.get(id);
    }

    @Override
    public boolean update(int id, Sensor sensor) {

        if (sensorMap.containsKey(id)) {
            sensor.setSensorId(id);
            sensorMap.replace(sensor.getSensorId(), sensor);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        if (sensorMap.containsKey(id)) {
            sensorMap.remove(id);
            return true;
        } else {
            return false;
        }
    }
}
