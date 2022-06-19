package ua.lviv.iot.coursework.logic.sensor;

import ua.lviv.iot.coursework.models.Sensor;

import java.util.List;

public interface SensorService {

    public void create(Sensor sensor);
    public List<Sensor> readALL();
    public Sensor read(int id);
    public boolean update(int id, Sensor sensor);
    public boolean delete(int id);
}
