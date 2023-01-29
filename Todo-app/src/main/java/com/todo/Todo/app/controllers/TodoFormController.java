package com.todo.Todo.app.controllers;

import com.todo.Todo.app.models.TodoItem;
import com.todo.Todo.app.services.TodoItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TodoFormController {

    @Autowired
    private TodoItemService todoItemService;

    // get a form
    @GetMapping("/create-todo")
    public String createForm(TodoItem todoItem){
        return "new-todo";
    }

    // create
    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, Model model){
        TodoItem item = new TodoItem();
        item.setDescription(todoItem.getDescription());
        item.setIsComplete(todoItem.getIsComplete());
        todoItemService.save(todoItem);
        return "redirect:/";
    }

    // delete
    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") Long id, Model model){
        TodoItem todoItem = todoItemService.getById(id)
                .orElseThrow(()->new IllegalArgumentException("TodoItem : " + id + "not found"));

        todoItemService.delete((todoItem));
        return "redirect:/";
    }

    // update
    @GetMapping("/edit/{id}")
    public String updateItem(@PathVariable("id") Long id, Model model){
        TodoItem todoItem = todoItemService.getById(id)
                .orElseThrow(()->new IllegalArgumentException("TodoItem : " + id + "not found"));
        model.addAttribute("todo", todoItem);
        return "edit-item";
    }

    // handle update
    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @Valid TodoItem todoItem, BindingResult result, Model model){
        TodoItem item = todoItemService.getById(id)
                .orElseThrow(()->new IllegalArgumentException("TodoItem : " + id + "not found"));

        item.setDescription(todoItem.getDescription());
        item.setIsComplete(todoItem.getIsComplete());
        todoItemService.save(item);

        return "redirect:/";
    }
}
