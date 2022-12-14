package com.example.sitodo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.TEXT_HTML;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;

import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.example.sitodo.model.TodoItem;
import com.example.sitodo.service.TodoListService;
import com.google.common.net.MediaType;

@WebMvcTest(TodoListController.class)
@Tag("unit")
public class TodoListControllerTest {
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoListService todoListService;

    // New annotation!
    // @DisplayName allows you to customise the name of a test case.
    @Test
    @DisplayName("HTTP GET '/list' retrieves list view")
    void showList_correctView() throws Exception {
        mockMvc.perform(get("/list")).andExpectAll(
            status().isOk(),
            content().contentTypeCompatibleWith(TEXT_HTML),
            content().encoding(UTF_8),
            view().name("list")
        );
    }

    @Test
    @DisplayName("HTTP GET '/list' returns an HTML page")
    void showList_returnHtml() throws Exception {
        mockMvc.perform(get("/list")).andExpectAll(
            status().isOk(),
            content().contentTypeCompatibleWith(TEXT_HTML),
            content().encoding(UTF_8),
            content().string(containsString("</html>"))
        );
    }

    @Test
    @DisplayName("HTTP GET '/list' returns an HTML page with non-empty list")
    void showList_withSampleData_ok() throws Exception {
        TodoItem mockTodoItem = new TodoItem("Buy milk");
        when(todoListService.getTodoItems()).thenReturn(List.of(mockTodoItem));

        mockMvc.perform(get("/list")).andExpectAll(
            status().isOk(),
            content().contentTypeCompatibleWith(TEXT_HTML),
            content().encoding(UTF_8),
            content().string(containsString("Buy milk"))
        );
    }
    
//    @Test
//    @DisplayName("HTTP POST")
//    void addList_correctView() throws Exception {
//    	mockMvc.perform(post("/list")
//                .content("text"))
//                .andDo(print())
//                .andExpect(status().is3xxRedirection());
//    }
    
    @Test
    @DisplayName("HTTP POST")
    void addList_withsample() throws Exception {
        TodoItem mockTodoItem = new TodoItem("Buy milk");
        when(todoListService.addTodoItem(mockTodoItem)).thenReturn(mockTodoItem);
        
        mockMvc.perform(post("/list").param("item_text", "text")).andExpectAll(status().is3xxRedirection()
        		);
    }
}
