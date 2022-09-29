package com.example.sitodo.service;

import com.example.sitodo.model.TodoItem;
import com.example.sitodo.model.TodoList;
import com.example.sitodo.repository.TodoListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class TodoListService {

	private static final Logger LOG = LoggerFactory.getLogger(TodoListService.class);
    private static final String TODO_LIST_DOES_NOT_EXIST_FMT = "TodoList(id=%d) does not exist";

    private TodoListRepository todoListRepository;

    @Autowired
    public void setTodoListRepository(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public TodoList getTodoListById(Long id) throws NoSuchElementException {
        Optional<TodoList> result = todoListRepository.findById(id);

        return result.get();
    }

    public TodoList addTodoItem(TodoItem todoItem) {
        TodoList list = new TodoList(List.of(todoItem));

        return todoListRepository.save(list);
    }

    public TodoList addTodoItem(Long id, TodoItem todoItem) throws NoSuchElementException {
        Optional<TodoList> result = todoListRepository.findById(id);

        TodoList foundList = result.get();
        foundList.addTodoItem(todoItem);

        return todoListRepository.save(foundList);
    }

    public TodoList updateTodoItem(Long listId, Long itemId, Boolean status) throws NoSuchElementException {
        Optional<TodoList> result = todoListRepository.findById(listId);

        // TODO: Implement me!
        return null;
    }

    public Boolean deleteTodoItem(Long listId, Long itemId) throws NoSuchElementException {
        Optional<TodoList> result = todoListRepository.findById(listId);

        // TODO: Implement me!
        return Boolean.FALSE;
    }
}
