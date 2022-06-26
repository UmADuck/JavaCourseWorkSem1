package ua.lviv.iot.coursework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.coursework.logic.panel.PanelService;
import ua.lviv.iot.coursework.logic.panelowner.PanelOwnerService;
import ua.lviv.iot.coursework.logic.sensor.SensorService;
import ua.lviv.iot.coursework.models.PanelOwner;
import ua.lviv.iot.coursework.models.Sensor;
import ua.lviv.iot.coursework.models.SolarPanel;
import ua.lviv.iot.coursework.payroll.OwnerNotFoundException;
import ua.lviv.iot.coursework.payroll.PanelNotFoundException;
import ua.lviv.iot.coursework.payroll.SensorNotFoundException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @Autowired
    private PanelService panelService;

    @Autowired
    private PanelOwnerService panelOwnerService;

    @PostMapping
    public void create(@RequestBody Sensor sensor) {
        sensorService.create(sensor);
    }

    @GetMapping
    public List<Sensor> readALL() {
        return sensorService.readALL();
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable int id, @RequestBody Sensor sensor) {
        return sensorService.update(id, sensor);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        Sensor sensor = sensorService.read(id);
        if (sensor == null)
            throw new SensorNotFoundException("id" + id);
        return sensorService.delete(id);
    }

    @GetMapping("/{id}")
    public List<Object> read(@PathVariable int id) {
        var tempList = new ArrayList<Object>();
        Sensor sensor = sensorService.read(id);
        if (sensor == null) {
            throw new SensorNotFoundException("id" + id);
        }
        SolarPanel panel = panelService.read(sensor.getPanelId());
        if (panel == null) {
            throw new PanelNotFoundException("id" + sensor.getPanelId());
        }
        PanelOwner panelOwner = panelOwnerService.read(panel.getOwnerId());
        if (panelOwner == null){
            throw new OwnerNotFoundException("id" + panel.getOwnerId());
        }
        tempList.add(sensor);
        tempList.add(panel);
        tempList.add(panelOwner);
        return tempList;
    }
}

