package dev.code.HelloWorld.controller;
import dev.code.HelloWorld.service.TodoService;
import dev.code.HelloWorld.models.Todo;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    //Path variable
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description="Todo retrieved successfully"),
            @ApiResponse(responseCode  ="404",description="Todo was not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<Todo> getTodoById(@PathVariable long id) {
        try {
            Todo createdTodo = todoService.getTodoById(id);
            return new ResponseEntity<>(createdTodo, HttpStatus.OK);
        } catch (RuntimeException exception) {
            log.info("Error");
            log.warn("");
            log.error("",exception);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping
    ResponseEntity<List<Todo>> getTodos() {
        return new ResponseEntity<List<Todo>>(todoService.getTodos(), HttpStatus.OK);
    }
    @GetMapping("/page")
    ResponseEntity<Page<Todo>> getTodosPage
            (@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(todoService.getAllTodos(page,size),HttpStatus.OK);
    }

    @PostMapping("/create")
    ResponseEntity<Todo> createUser(@RequestBody Todo todo) {
        Todo createdTodo = todoService.createTodo(todo);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);

    }

    @PutMapping
    ResponseEntity<Todo> updateTodoById(@RequestBody Todo todo) {
        return new ResponseEntity<>(todoService.updateTodo(todo),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteTodoById(@PathVariable long id) {
        todoService.deleteTodoById(id);
    }
}
