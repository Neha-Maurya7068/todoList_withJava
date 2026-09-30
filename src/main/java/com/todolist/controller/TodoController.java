package com.todolist.controller;

import com.todolist.dto.TodoDto;
import com.todolist.service.TodoService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

//    public TodoController(TodoService todoService) {
//        this.todoService = todoService;
//    }

    @PostMapping
    public ResponseEntity<TodoDto>addTodo(@RequestBody  TodoDto todoDto){
        TodoDto savedTodo=todoService.addTodo(todoDto);
        return  new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<TodoDto>getTodo(@PathVariable("id") Long todoId){
        TodoDto todoDto=todoService.getTodo(todoId);
        return new ResponseEntity<>(todoDto,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<TodoDto>>getAllTodos(){
        List<TodoDto>todo=todoService.getAllTodos();
        return ResponseEntity.ok(todo);
    }
    @PutMapping ("{id}")
    public ResponseEntity<TodoDto>updateTodo(@RequestBody TodoDto todoDto,@PathVariable("id") Long todoId){
        TodoDto updateTodo=todoService.updateTodo(todoDto,todoId);
        return ResponseEntity.ok(updateTodo);

    }
    @DeleteMapping("{id}")
    public ResponseEntity<String>deleteTodo(@PathVariable("id") Long todoId){
        todoService.deleteTodo(todoId);
        return  ResponseEntity.ok("Todo deleted Succesfully");
    }
    @PatchMapping("{id}/complete")
public ResponseEntity<TodoDto>completeTodo(@PathVariable("id") Long todoId){
        TodoDto updateDto=todoService.completeTodd(todoId);
        return ResponseEntity.ok(updateDto);
}
@PatchMapping("{id}/in-complete")
public ResponseEntity<TodoDto>inCompleteDto(@PathVariable("id") Long todoId){
        TodoDto updatedTodo=todoService.inComplete(todoId);
        return ResponseEntity.ok(updatedTodo);
}

}
