package com.todolist.service;

import com.todolist.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodo(Long id);
    List<TodoDto> getAllTodos();
TodoDto updateTodo(TodoDto todoDto,Long id);

void deleteTodo(Long id);

TodoDto completeTodd(Long id);

TodoDto inComplete(Long id);

}
