package ua.lviv.iot.coursework.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.coursework.models.SolarPanel;
import ua.lviv.iot.coursework.logic.panel.PanelService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/solarpanel")

public class PanelController extends PanelService {

    @Qualifier("PanelService")
    private final PanelService panelService = new PanelService();

    @PostMapping
    @Override
    public void create(@RequestBody SolarPanel solarPanel) {

        panelService.create(solarPanel);
    }

    @GetMapping
    @Override
    public List<SolarPanel> readALL() {

        return panelService.readALL();
    }

    @GetMapping("/{id}")
    @Override
    public SolarPanel read(@PathParam("id") @PathVariable("id") int id) {

        return panelService.read(id);
    }
    @PutMapping("/{id}")
    @Override
    public boolean update(@PathVariable int id, @RequestBody SolarPanel solarPanel) {

        return panelService.update(id, solarPanel);
    }

    @DeleteMapping("/{id}")
    @Override
    public boolean delete(@PathVariable int id) {
        return panelService.delete(id);
    }
}
