package com.example.sitodo.model;

import javax.persistence.*;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class TodoList {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<TodoItem> items;

    public TodoList(List<TodoItem> items) {
        this.items= items;
    }

    public void addTodoItem(TodoItem item) {
        items.add(item);
    }
}
