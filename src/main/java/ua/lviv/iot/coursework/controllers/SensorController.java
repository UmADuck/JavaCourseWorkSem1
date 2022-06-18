package ua.lviv.iot.coursework.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.coursework.models.Sensor;
import ua.lviv.iot.coursework.logic.sensor.SensorService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/sensor")
public class SensorController extends SensorService {

    @Qualifier("SensorService")
    private final SensorService sensorService = new SensorService();

    @PostMapping
    @Override
    public void create(@RequestBody Sensor sensor) {

        sensorService.create(sensor);
    }

    @GetMapping
    @Override
    public List<Sensor> readALL() {

        return sensorService.readALL();
    }

    @GetMapping("/{id}")
    @Override
    public Sensor read(@PathParam("id") @PathVariable("id") int id) {

        return sensorService.read(id);
    }

    @PutMapping("/{id}")
    @Override
    public boolean update(@PathVariable int id, @RequestBody Sensor sensor) {

        return sensorService.update(id, sensor);
    }

    @DeleteMapping("/{id}")
    @Override
    public boolean delete(@PathVariable int id) {

        return sensorService.delete(id);
    }
}
