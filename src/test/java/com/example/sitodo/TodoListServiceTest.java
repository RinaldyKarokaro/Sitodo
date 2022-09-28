package com.example.sitodo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;


import com.example.sitodo.model.TodoItem;
import com.example.sitodo.repository.TodoListRepository;
import com.example.sitodo.service.TodoListService;

@SpringBootTest
public class TodoListServiceTest {

	@Autowired
    private TodoListService todoListService;

    @MockBean
    private TodoListRepository todoListRepository;

    @Test
    void getTodoItems_someItems_ok() {
        when(todoListRepository.findAll()).thenReturn(List.of(
            new TodoItem("A"),
            new TodoItem("B"),
            new TodoItem("C")
        ));

        List<TodoItem> todoItems = todoListService.getTodoItems();

        assertEquals(3, todoItems.size());
    }

    @Test
    void addTodoItem_ok() {
        TodoItem todoItem = new TodoItem("Buy milk");
        when(todoListRepository.save(any(TodoItem.class))).thenReturn(todoItem);

        TodoItem savedTodoItem = todoListService.addTodoItem(todoItem);

        assertEquals("Buy milk", savedTodoItem.getTitle());
    }
}
