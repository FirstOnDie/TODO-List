package org.cexpositoce.todolist.controller;

import org.cexpositoce.todolist.model.Tarea;
import org.cexpositoce.todolist.service.TareaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TareaController.class)
public class TareaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TareaService tareaService;

    @Test
    void obtenerTareas_OK() throws Exception {
        when(tareaService.obtenerTareas()).thenReturn(Arrays.asList(
            new Tarea(),
            new Tarea()
        ));

        mockMvc.perform(get("/tareas"))
            .andExpect(status().isOk());
    }
}
