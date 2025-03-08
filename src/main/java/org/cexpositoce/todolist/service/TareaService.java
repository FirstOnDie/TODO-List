package org.cexpositoce.todolist.service;

import org.cexpositoce.todolist.model.Tarea;
import org.cexpositoce.todolist.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService {
    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public List<Tarea> obtenerTareas() {
        return tareaRepository.findAll();
    }

    public Optional<Tarea> getById(Long id) {
        return tareaRepository.findById(id);
    }

    public Optional<Tarea> getByDescription(String description) {
        return tareaRepository.findByDescripcion(description);
    }

    public Tarea addTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Optional<Tarea> updateTarea(Long id, Tarea tarea) {
        Optional<Tarea> tareaOptional = tareaRepository.findById(id);
        if (tareaOptional.isPresent()) {
            Tarea tareaToUpdate = tareaOptional.get();
            tareaToUpdate.setDescripcion(tarea.getDescripcion());
            tareaToUpdate.setCompletada(tarea.isCompletada());
            tareaRepository.save(tareaToUpdate);
        }
        return tareaOptional;
    }

    public boolean deleteTarea(Long id) {
        if(tareaRepository.existsById(id)) {
            tareaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
