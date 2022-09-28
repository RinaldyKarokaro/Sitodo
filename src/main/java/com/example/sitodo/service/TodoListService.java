package com.example.sitodo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sitodo.model.TodoItem;
import com.example.sitodo.repository.TodoListRepository;


@Service
public class TodoListService {

	private TodoListRepository todoListRepository;

    @Autowired
    public void setTodoListRepository(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }
    
    public List<TodoItem> getTodoItems() {
        return (List<TodoItem>) todoListRepository.findAll();//test
    }

    public TodoItem addTodoItem(TodoItem todoItem) {
        return todoListRepository.save(todoItem);
    }
}
