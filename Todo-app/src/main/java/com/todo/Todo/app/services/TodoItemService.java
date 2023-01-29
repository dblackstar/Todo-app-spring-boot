package com.todo.Todo.app.services;


import com.todo.Todo.app.models.TodoItem;
import com.todo.Todo.app.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class TodoItemService {

    @Autowired
    private TodoItemRepository todoItemRepository;


    // get all methods
    public Iterable<TodoItem> getAll(){
        return todoItemRepository.findAll();
    }

    // get one method
    public Optional<TodoItem> getById(Long id){
        return todoItemRepository.findById(id);
    }

    // save method
    public TodoItem save(TodoItem todoItem){

        // insertion
        if(todoItem.getId() == null){
            todoItem.setCreatedAt(Instant.now());
        }
        // update
        todoItem.setUpdatedAt(Instant.now());

        // return
        return todoItemRepository.save(todoItem);
    }


    // delete
    public void delete(TodoItem todoItem){
        todoItemRepository.delete(todoItem);
    }

}
