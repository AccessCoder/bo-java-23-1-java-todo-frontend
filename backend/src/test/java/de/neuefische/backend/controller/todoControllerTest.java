package de.neuefische.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.backend.model.TodoElement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.server.ResponseStatusException;


import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@SpringBootTest
@AutoConfigureMockMvc
class todoControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void whenGetAllTodo_returnInitialEmptyListOfTodos_return200() throws Exception {
        //GIVEN

        //WHEN & THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(status().is(200))
                .andExpect(content().json("[]"));

    }

    @Test
    void whenGetTodoById_thenReturnCorrectTodo_andStatus200() throws Exception {
        //GIVEN
        String responseBody = """
                  {
                  "description": "test",
                  "status": "OPEN"
                  }
                """;

        MvcResult response = mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/todo")
                                .contentType("application/json")
                                .content(responseBody))
                .andReturn();

        String content = response.getResponse().getContentAsString();
        TodoElement todo = objectMapper.readValue(content, TodoElement.class);

        //WHEN & THEN

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/todo/" + todo.getId()))
                .andExpect(status().is(200))
                .andExpect(content().json(responseBody))
                .andExpect(jsonPath("$.id").value(todo.getId()));
    }

    @Test
    void whenGetTodoByIdWithInvalidId_thenThrowResponseStatusException_andStatus404() throws Exception {
        //GIVEN

        //WHEN & THEN

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/imInvalid"))
                .andExpect(status().is(404))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> {
                    ResponseStatusException exception = (ResponseStatusException) result.getResolvedException();
                    assert exception != null;
                    assertEquals("404 NOT_FOUND \"No Todo found with ID:imInvalid\"", exception.getMessage());
                });
        // besser wäre es für den User, wenn eine Eindeutige, vllt auch einzeilige Fehlermeldung zurückgegeben wird
        // nach dieser können wir auch besser testen statt, das Object so zu manipulieren bis wir an die Fehlermeldung kommen

    }

    @Test
    void whenAddTodo_thenReturnCreatedTodo_andStatus200() throws Exception {
        //GIVEN
        String requestBody = """
                  {
                  "description": "test",
                  "status": "OPEN"
                  }
                """;
        //WHEN & THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/todo")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                .andExpect(status().is(200))
                .andExpect(content().json(requestBody))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }


    @Test
    void updateTodo() throws Exception {
        //WHEN
        String responseBody = """
                  {
                  "description": "test",
                  "status": "OPEN"
                  }
                """;

        MvcResult response = mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/todo")
                                .contentType("application/json")
                                .content(responseBody))
                .andReturn();

        String content = response.getResponse().getContentAsString();
        TodoElement todo = objectMapper.readValue(content, TodoElement.class);

        //WHEN & THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/todo/" + todo.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(responseBody))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    void deleteTodo() {
    }
}