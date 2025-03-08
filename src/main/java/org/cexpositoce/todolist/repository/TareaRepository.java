package org.cexpositoce.todolist.repository;

import org.cexpositoce.todolist.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
    Optional<Tarea> findByDescripcion(String description);
}
