package ua.lviv.iot.coursework.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.coursework.models.PanelOwner;
import ua.lviv.iot.coursework.logic.panelowner.PanelOwnerService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/panelowner")

public class PanelOwnerController extends PanelOwnerService {

    @Qualifier("PanelOwnerService")
    private final PanelOwnerService panelOwnerService = new PanelOwnerService();

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

    @GetMapping("/{id}")
    @Override
    public PanelOwner read(@PathParam("id") @PathVariable("id") int id) {

        return panelOwnerService.read(id);
    }

    @PutMapping("/{id}")
    @Override
    public boolean update(@RequestBody PanelOwner panelOwner, @PathVariable int id) {

        return panelOwnerService.update(panelOwner, id);
    }

    @DeleteMapping("/{id}")
    @Override
    public boolean delete(@PathVariable int id) {

        return panelOwnerService.delete(id);
    }
}
