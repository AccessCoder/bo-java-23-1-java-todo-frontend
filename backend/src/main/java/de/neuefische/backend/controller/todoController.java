package de.neuefische.backend.controller;

import de.neuefische.backend.model.TodoElement;
import de.neuefische.backend.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class todoController {

    private final TodoService service;

    @GetMapping
    public List<TodoElement> getAllTodo(){
        return service.getAllTodo();
    }

    @PostMapping
    public TodoElement addTodo(@RequestBody TodoElement todoElement){
        return service.addTodo(todoElement);
    }

    @GetMapping("/{id}")
    public TodoElement getTodoById(@PathVariable String id){
        return service.getTodoById(id);
    }

    @PutMapping("/{id}")
    public TodoElement updateTodo(@RequestBody TodoElement todoElement){
        return service.updateTodo(todoElement);
    }

}
