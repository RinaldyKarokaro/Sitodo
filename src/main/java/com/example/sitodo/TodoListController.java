package com.example.sitodo;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.sitodo.model.TodoItem;
import com.example.sitodo.service.TodoListService;

@Controller
public class TodoListController {
	private TodoListService todoListService;

    @Autowired
    public void setTodoListService(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @GetMapping("/list")
    public String showList(Model model) {
        List<TodoItem> todoItems = todoListService.getTodoItems();

        model.addAttribute("todoList",todoItems);
//        add("todoList", todoItems);

        return "list";
    }
    
    @PostMapping("/list")
    public RedirectView addTodoItem(@RequestParam("item_text") String text) {
    	todoListService.addTodoItem(new TodoItem(text));
		return new RedirectView("/list");
 
    }
}
