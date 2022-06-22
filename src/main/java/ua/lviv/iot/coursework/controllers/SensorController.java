package ua.lviv.iot.coursework.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.coursework.models.Sensor;
import ua.lviv.iot.coursework.logic.sensor.impl.SensorServiceImpl;
import ua.lviv.iot.coursework.payroll.SensorNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/sensor")
public class SensorController extends SensorServiceImpl {

    @Qualifier("SensorService")
    private final SensorServiceImpl sensorServiceImpl = new SensorServiceImpl();

    @PostMapping
    @Override
    public void create(@RequestBody Sensor sensor) {

        sensorServiceImpl.create(sensor);
    }

    @GetMapping
    @Override
    public List<Sensor> readALL() {

        return sensorServiceImpl.readALL();
    }

    @PutMapping("/{id}")
    @Override
    public boolean update(@PathVariable int id, @RequestBody Sensor sensor) {

        return sensorServiceImpl.update(id, sensor);
    }

    @DeleteMapping("/{id}")
    @Override
    public boolean delete(@PathVariable int id) {

        Sensor sensor = sensorServiceImpl.read(id);
        if(sensor == null)
            throw new SensorNotFoundException("id" + id);
        return sensorServiceImpl.delete(id);
    }
    @GetMapping("/{id}")
    @Override
    public Sensor read(@PathVariable int id){
        Sensor sensor = sensorServiceImpl.read(id);
        if(sensor == null)
            throw new SensorNotFoundException("id" + id);
        return sensor;
    }
}

