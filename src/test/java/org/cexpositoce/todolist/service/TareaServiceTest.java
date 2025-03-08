package org.cexpositoce.todolist.service;

import org.cexpositoce.todolist.model.Tarea;
import org.cexpositoce.todolist.repository.TareaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Description;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TareaServiceTest {
    @Mock
    private TareaRepository tareaRepository;

    @InjectMocks
    private TareaService tareaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Description("Debería devolver todas las tareas")
    void obtenerTodasLasTareas_OK() {

        List<Tarea> tareas = Arrays.asList(
                new Tarea(),
                new Tarea()
        );

        when(tareaRepository.findAll()).thenReturn(tareas);

        List<Tarea> resultado = tareaService.obtenerTareas();

        assertEquals(2, resultado.size());
        verify(tareaRepository, times(1)).findAll();
    }

    @Test
    @Description("Debería devolver una tarea por su id")
    void obtenerTareaPorId_OK() {
        Tarea tarea = new Tarea();
        tarea.setId(1L);
        tarea.setDescripcion("Tarea 1");
        tarea.setCompletada(false);

        when(tareaRepository.findById(1L)).thenReturn(java.util.Optional.of(tarea));

        assertEquals(tarea, tareaService.getById(1L).get());
        verify(tareaRepository, times(1)).findById(1L);
    }
}
