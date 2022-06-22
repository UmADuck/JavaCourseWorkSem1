package ua.lviv.iot.coursework.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.coursework.models.SolarPanel;
import ua.lviv.iot.coursework.logic.panel.impl.PanelServiceImpl;
import ua.lviv.iot.coursework.payroll.PanelNotFoundException;

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

    @PutMapping("/{id}")
    @Override
    public boolean update(@PathVariable int id, @RequestBody SolarPanel solarPanel) {

        return panelServiceImpl.update(id, solarPanel);
    }

    @DeleteMapping("/{id}")
    @Override
    public boolean delete(@PathVariable int id) {
        SolarPanel solarPanel = panelServiceImpl.read(id);
        if(solarPanel == null)
            throw new PanelNotFoundException("id" + id);
        return panelServiceImpl.delete(id);
    }

    @GetMapping("/{id}")
    @Override
    public SolarPanel read(@PathVariable int id){
        SolarPanel solarPanel = panelServiceImpl.read(id);
        if(solarPanel == null)
            throw new PanelNotFoundException("id" + id);
        return solarPanel;
    }
}


