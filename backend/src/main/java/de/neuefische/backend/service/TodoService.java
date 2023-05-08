package de.neuefische.backend.service;

import de.neuefische.backend.model.TodoElement;
import de.neuefische.backend.repository.TodoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepo repo;
    private final RandomIdService randomIdService;
    public List<TodoElement> getAllTodo() {
        return repo.getAllTodo();
    }

    public TodoElement addTodo(TodoElement todoElement) {
        return repo.addTodo(todoElement.withId(randomIdService.generateRandomId()));
    }

    public TodoElement getTodoById(String id) {
        return repo.getTodoById(id);
    }

    public TodoElement updateTodo(TodoElement todoElement) {
        return repo.updateTodo(todoElement);
    }

    public TodoElement deleteTodo(String id) {
        return repo.deleteTodo(id);
    }
}
