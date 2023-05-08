package de.neuefische.backend.repository;

import de.neuefische.backend.model.TodoElement;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

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
        if (database.containsKey(id)){
            return database.get(id);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Todo found with ID:" + id);
        }

    }
    public TodoElement updateTodo(String id, TodoElement todoElement) {
        getTodoById(id);
        database.replace(id, todoElement);
        return database.get(id);
    }

    public TodoElement deleteTodo(String id) {
        getTodoById(id);
       return database.remove(id);
    }


}
