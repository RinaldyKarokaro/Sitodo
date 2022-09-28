package com.example.sitodo.model;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
public class TodoItem {
	@Getter
	@Setter
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private Boolean finished = Boolean.FALSE;

    public TodoItem(String title) {
        this.title = title;
    }

    public TodoItem(Long id, String title) {
        this.id = id;
        this.title = title;
    }
    
    
}
