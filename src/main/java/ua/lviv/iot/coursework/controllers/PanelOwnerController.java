package ua.lviv.iot.coursework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.coursework.logic.panelowner.PanelOwnerService;
import ua.lviv.iot.coursework.models.PanelOwner;
import ua.lviv.iot.coursework.payroll.OwnerNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/panelowner")
public class PanelOwnerController {

    @Autowired
    private PanelOwnerService panelOwnerService;

    @PostMapping
    public void create(@RequestBody PanelOwner panelOwner) {
        panelOwnerService.create(panelOwner);
    }

    @GetMapping
    public List<PanelOwner> readALL() {
        return panelOwnerService.readALL();
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable int id, @RequestBody PanelOwner panelOwner) {
        return panelOwnerService.update(id, panelOwner);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        PanelOwner panelOwner = panelOwnerService.read(id);
        if (panelOwner == null)
            throw new OwnerNotFoundException("id" + id);
        return panelOwnerService.delete(id);
    }

    @GetMapping("/{id}")
    public PanelOwner read(@PathVariable int id) {
        PanelOwner panelOwner = panelOwnerService.read(id);
        if (panelOwner == null)
            throw new OwnerNotFoundException("id" + id);
        return panelOwner;
    }
}

