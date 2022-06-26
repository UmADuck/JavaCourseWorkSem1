package ua.lviv.iot.coursework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.coursework.logic.panel.PanelService;
import ua.lviv.iot.coursework.logic.panelowner.PanelOwnerService;
import ua.lviv.iot.coursework.models.PanelOwner;
import ua.lviv.iot.coursework.models.SolarPanel;
import ua.lviv.iot.coursework.payroll.OwnerNotFoundException;
import ua.lviv.iot.coursework.payroll.PanelNotFoundException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/solarpanel")
public class PanelController {

    @Autowired
    private PanelService panelService;

    @Autowired
    private PanelOwnerService panelOwnerService;

    @PostMapping
    public void create(@RequestBody SolarPanel solarPanel) {
        panelService.create(solarPanel);
    }

    @GetMapping
    public List<SolarPanel> readALL() {
        return panelService.readALL();
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable int id, @RequestBody SolarPanel solarPanel) {
        var resultOfUpdating = panelService.update(id, solarPanel);
        if (!resultOfUpdating) {
            throw new PanelNotFoundException("id" + id);
        }
        return true;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        SolarPanel solarPanel = panelService.read(id);
        if (solarPanel == null)
            throw new PanelNotFoundException("id" + id);
        return panelService.delete(id);
    }

    @GetMapping("/{id}")
    public List<Object> read(@PathVariable int id) {
        var tempList = new ArrayList<Object>();
        SolarPanel solarPanel = panelService.read(id);
        if (solarPanel == null){
            throw new PanelNotFoundException("id" + id);
        }
        PanelOwner panelOwner = panelOwnerService.read(solarPanel.getOwnerId());
        if (panelOwner == null){
            throw new OwnerNotFoundException("id" + solarPanel.getOwnerId());
        }
        tempList.add(solarPanel);
        tempList.add(panelOwner);
        return tempList;
    }
}


