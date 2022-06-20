package ua.lviv.iot.coursework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.coursework.models.PanelOwner;
import ua.lviv.iot.coursework.logic.panelowner.impl.PanelOwnerServiceImpl;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/panelowner")
public class PanelOwnerController extends PanelOwnerServiceImpl {

    @Qualifier("PanelOwnerService")
    private final PanelOwnerServiceImpl panelOwnerService = new PanelOwnerServiceImpl();

    @PostMapping
    @Override
    public void create(@RequestBody PanelOwner panelOwner) {

        panelOwnerService.create(panelOwner);
    }

    @GetMapping
    @Override
    public List<PanelOwner> readALL() {

        return panelOwnerService.readALL();
    }

    @GetMapping
    @RequestMapping("/{id}")
    @Override
    public PanelOwner read(@PathParam("id") @PathVariable("id") int id) {

            return panelOwnerService.read(id);
    }

    @PutMapping("/{id}")
    @Override
    public boolean update(@PathVariable int id, @RequestBody PanelOwner panelOwner) {

        return panelOwnerService.update(id, panelOwner);
    }

    @DeleteMapping("/{id}")
    @Override
    public boolean delete(@PathVariable int id) {

        return panelOwnerService.delete(id);
    }
}
