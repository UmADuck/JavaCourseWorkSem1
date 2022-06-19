package ua.lviv.iot.coursework.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.coursework.models.SolarPanel;
import ua.lviv.iot.coursework.logic.panel.impl.PanelServiceImpl;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/solarpanel")

public class PanelController extends PanelServiceImpl {

    @Qualifier("PanelService")
    private final PanelServiceImpl panelServiceImpl = new PanelServiceImpl();

    @PostMapping
    @Override
    public void create(@RequestBody SolarPanel solarPanel) {

        panelServiceImpl.create(solarPanel);
    }

    @GetMapping
    @Override
    public List<SolarPanel> readALL() {

        return panelServiceImpl.readALL();
    }

    @GetMapping("/{id}")
    @Override
    public SolarPanel read(@PathParam("id") @PathVariable("id") int id) {

        return panelServiceImpl.read(id);
    }
    @PutMapping("/{id}")
    @Override
    public boolean update(@PathVariable int id, @RequestBody SolarPanel solarPanel) {

        return panelServiceImpl.update(id, solarPanel);
    }

    @DeleteMapping("/{id}")
    @Override
    public boolean delete(@PathVariable int id) {
        return panelServiceImpl.delete(id);
    }
}
