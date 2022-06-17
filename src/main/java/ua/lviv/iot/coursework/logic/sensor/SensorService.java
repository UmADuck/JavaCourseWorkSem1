package ua.lviv.iot.coursework.logic.sensor;

import org.springframework.stereotype.Service;
import ua.lviv.iot.coursework.csvmanagers.SensorCSVManager;
import ua.lviv.iot.coursework.models.Sensor;
import ua.lviv.iot.coursework.logic.sensor.impl.SensorServiceImpl;

import java.util.LinkedList;
import java.util.List;

@Service("SensorService")
public class SensorService implements SensorServiceImpl {

    SensorCSVManager manager = new SensorCSVManager();


    public void addAll(List<Sensor> list) {
        for (Sensor item: list) {
            manager.putToHash(item);
        }
    }
    @Override
    public void create(Sensor sensor) {

        manager.updateData();
        manager.putToHash(sensor);
    }

    @Override
    public List<Sensor> readALL() {

        manager.updateData();
        return new LinkedList<Sensor>(manager.getAllHash());
    }

    @Override
    public Sensor read(int id) {

        manager.updateData();
        return manager.readHash(id);
    }

    @Override
    public boolean update(Sensor sensor, int id) {

        manager.updateData();
        return manager.updateHash(id, sensor);
    }

    @Override
    public boolean delete(int id) {

        manager.updateData();
        return manager.removeFromHash(id);
    }
}
