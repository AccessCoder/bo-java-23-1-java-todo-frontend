package de.neuefische.backend.repository;

import de.neuefische.backend.model.TodoElement;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TodoRepo {

    Map<String, TodoElement> database = new HashMap<>();
    public List<TodoElement> getAllTodo() {
        return new ArrayList<>(database.values());
    }

    public TodoElement addTodo(TodoElement todoElement){
        database.put(todoElement.getId(), todoElement);
        return database.get(todoElement.getId());
    }

    public TodoElement getTodoById(String id) {
        return database.get(id);
    }
}
