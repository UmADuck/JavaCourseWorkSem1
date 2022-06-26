package ua.lviv.iot.coursework.logic.sensor;

import ua.lviv.iot.coursework.models.Sensor;

import java.util.List;

public interface SensorService {

    void create(Sensor sensor);
    List<Sensor> readALL();
    Sensor read(int id);
    boolean update(int id, Sensor sensor);
    boolean delete(int id);
}
