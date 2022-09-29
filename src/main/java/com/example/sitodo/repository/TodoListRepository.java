package com.example.sitodo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.sitodo.model.TodoItem;
import com.example.sitodo.model.TodoList;


@Repository
public interface TodoListRepository extends CrudRepository<TodoList, Long>{

}
