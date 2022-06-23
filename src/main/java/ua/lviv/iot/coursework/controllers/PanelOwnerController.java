package ua.lviv.iot.coursework.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.coursework.models.PanelOwner;
import ua.lviv.iot.coursework.logic.panelowner.impl.PanelOwnerServiceImpl;
import ua.lviv.iot.coursework.payroll.OwnerNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/panelowner")
public class PanelOwnerController extends PanelOwnerServiceImpl {

    @Qualifier("PanelOwnerServiceImpl")
    private PanelOwnerServiceImpl panelOwnerServiceImpl;

    @PostMapping
    @Override
    public void create(@RequestBody PanelOwner panelOwner) {

        panelOwnerServiceImpl.create(panelOwner);
    }

    @GetMapping
    @Override
    public List<PanelOwner> readALL() {

        return panelOwnerServiceImpl.readALL();
    }

    @PutMapping("/{id}")
    @Override
    public boolean update(@PathVariable int id, @RequestBody PanelOwner panelOwner) {

        return panelOwnerServiceImpl.update(id, panelOwner);
    }

    @DeleteMapping("/{id}")
    @Override
    public boolean delete(@PathVariable int id) {
        PanelOwner panelOwner = panelOwnerServiceImpl.read(id);
        if(panelOwner == null)
            throw new OwnerNotFoundException("id" + id);
        return panelOwnerServiceImpl.delete(id);
    }

    @GetMapping("/{id}")
    @Override
    public PanelOwner read(@PathVariable int id){
        PanelOwner panelOwner = panelOwnerServiceImpl.read(id);
        if(panelOwner == null)
            throw new OwnerNotFoundException("id" + id);
        return panelOwner;
    }
}

