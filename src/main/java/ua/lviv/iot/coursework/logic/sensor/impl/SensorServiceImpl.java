package ua.lviv.iot.coursework.logic.sensor.impl;

import org.springframework.stereotype.Service;
import ua.lviv.iot.coursework.csvmanagers.SensorCSVManager;
import ua.lviv.iot.coursework.logic.sensor.SensorService;
import ua.lviv.iot.coursework.models.Sensor;

import java.util.LinkedList;
import java.util.List;

@Service("SensorService")
public class SensorServiceImpl implements SensorService {

    private final SensorCSVManager manager = new SensorCSVManager();

    @Override
    public void create(Sensor sensor) {

        var checkIfEmpty = new LinkedList<Sensor>(manager.getAllHash());
        if (checkIfEmpty.isEmpty()) {
            manager.addDataToHashFromCSVFile();
        }
        manager.putToHash(sensor);
    }

    @Override
    public List<Sensor> readALL() {

        var checkIfEmpty = new LinkedList<Sensor>(manager.getAllHash());
        if (checkIfEmpty.isEmpty()) {
            manager.addDataToHashFromCSVFile();
        }
        return new LinkedList<Sensor>(manager.getAllHash());
    }

    @Override
    public Sensor read(int id) {

        var checkIfEmpty = new LinkedList<Sensor>(manager.getAllHash());
        if (checkIfEmpty.isEmpty()) {
            manager.addDataToHashFromCSVFile();
        }
        return manager.readHash(id);
    }

    @Override
    public boolean update(int id, Sensor sensor) {

        var checkIfEmpty = new LinkedList<Sensor>(manager.getAllHash());
        if (checkIfEmpty.isEmpty()) {
            manager.addDataToHashFromCSVFile();
        }
        return manager.updateHash(id, sensor);
    }

    @Override
    public boolean delete(int id) {

        return manager.removeFromHash(id);
    }
}
