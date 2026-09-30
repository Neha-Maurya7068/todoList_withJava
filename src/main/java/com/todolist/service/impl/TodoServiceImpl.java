package com.todolist.service.impl;

import com.todolist.dto.TodoDto;
import com.todolist.entity.Todo;
import com.todolist.exception.ResourceNotFoundException;
import com.todolist.repository.TodoRepository;
import com.todolist.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
private TodoRepository todoRepository;
private ModelMapper modelMapper;
@Override
    public TodoDto addTodo(TodoDto todoDto) {
    //convert TodoDto to Todo Jpa entity

    Todo todo=modelMapper.map(todoDto,Todo.class);

    Todo saveTodo = todoRepository.save(todo);


    //convert saved todo jpa entity object into todo object

TodoDto savedTodoDto=modelMapper.map(saveTodo,TodoDto.class);

    return  savedTodoDto;
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo=todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo not found with id:"+id));
        return modelMapper.map(todo,TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo>todos=todoRepository.findAll();

        return  todos.stream().map((todo)->modelMapper.map(todo,TodoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        Todo todo=todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo not found with id : " + id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        Todo updateTodo=todoRepository.save(todo);

        return modelMapper.map(updateTodo,TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo=todoRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Todo not found with id :" +id)
        );
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodd(Long id) {
        Todo todo=todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo not found with id : "+id));
        todo.setCompleted(Boolean.TRUE);
        Todo updateTodo=todoRepository.save(todo);
        return  modelMapper.map(updateTodo,TodoDto.class);
    }

    @Override
    public TodoDto inComplete(Long id) {
        Todo todo=todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo not found with id : " + id));
        todo.setCompleted(Boolean.FALSE);
        Todo updateedTodo=todoRepository.save(todo);
        return  modelMapper.map(updateedTodo,TodoDto.class);
    }
}

