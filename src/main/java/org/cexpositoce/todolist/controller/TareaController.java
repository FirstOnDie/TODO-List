package org.cexpositoce.todolist.controller;

import org.cexpositoce.todolist.model.Tarea;
import org.cexpositoce.todolist.service.TareaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tareas")
public class TareaController {
    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    public List<Tarea> obtenerTareas() {
        return tareaService.obtenerTareas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> getById(@PathVariable Long id) {
        return tareaService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tarea addTarea(@RequestBody Tarea tarea) {
        return tareaService.addTarea(tarea);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Tarea> updateTarea(@PathVariable Long id, @RequestBody Tarea tarea) {
        return tareaService.updateTarea(id, tarea)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/completada")
    public ResponseEntity<Tarea> marcarTareaCompletada(@PathVariable Long id) {
        return tareaService.getById(id)
                .map(tarea -> {
                    tarea.setCompletada(true);
                    return ResponseEntity.ok(tareaService.addTarea(tarea));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarea(@PathVariable Long id) {
        return tareaService.deleteTarea(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
