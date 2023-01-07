package com.cmc.selfdevelopment.domain.improvement.service;

import com.cmc.selfdevelopment.domain.improvement.dto.*;
import com.cmc.selfdevelopment.domain.improvement.entity.Improvement;
import com.cmc.selfdevelopment.domain.improvement.entity.Todo;
import com.cmc.selfdevelopment.domain.improvement.repository.TodoRepository;
import com.cmc.selfdevelopment.domain.user.entity.UserAccount;
import com.cmc.selfdevelopment.global.common.api.ErrorCode;
import com.cmc.selfdevelopment.global.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
    private final ImprovementService improvementService;
    private final TodoRepository todoRepository;
    public TodoDto create(CreateTodoDto createTodoDto, UserAccount user) {
        Optional<Improvement> findImprovement = improvementService.findByTitleToEntity(createTodoDto.getTitle());
        if(findImprovement == null){
            improvementService.create(ImprovementDto.builder()
                    .title(createTodoDto.getTitle())
                    .build());
            findImprovement = improvementService.findByTitleToEntity(createTodoDto.getTitle());
        }
        Improvement improvement = findImprovement.get();
        if(existsByUserAndImprovement(user, improvement, createTodoDto.getDate())){
            throw new CustomException(ErrorCode.TODO_DUPLICATED);
        }
        // TODO : mapper로 바꾸기
        Todo newTodo = Todo.builder()
                .user(user)
                .improvement(improvement)
                .date(createTodoDto.getDate())
                .build();
        todoRepository.save(newTodo);
        return TodoDto.builder()
                .title(createTodoDto.getTitle())
                .isDone(false)
                .date(createTodoDto.getDate())
                .build();
    }

    public boolean existsByUserAndImprovement(UserAccount user, Improvement improvement, Date date){
        Optional<Todo> todo = todoRepository.findByUserAndImprovementAndDate(user, improvement, date);
        if(todo.isPresent()){
            return true;
        }
        return false;
    }

    public TodoDto changeCheck(UserAccount user, ChangeDoneDto changeDoneDto) {
        Optional<Improvement> findImprovement = improvementService.findByTitleToEntity(changeDoneDto.getTitle());
        if(findImprovement == null){
            throw new CustomException(ErrorCode.TODO_NOT_FOUND);
        }
        Improvement improvement = findImprovement.get();
        Todo finTodo = todoRepository.findByUserAndImprovementAndDate(user, improvement, changeDoneDto.getDate()).get();
        finTodo.change();
        todoRepository.save(finTodo);
        return TodoDto.builder()
                .title(improvement.getTitle())
                .isDone(finTodo.isDone())
                .date(finTodo.getDate())
                .build();
    }

    public List<TodoDto> findAllTodo(Date date, UserAccount user) {
        List<Todo> todos = todoRepository.findByDateAndUser(date, user);
        return todos.stream()
                .map(todo -> TodoDto.builder()
                        .title(todo.getImprovement().getTitle())
                        .isDone(todo.isDone())
                        .date(todo.getDate())
                        .build())
                .collect(Collectors.toList());
    }

    public List<TodoPercentDto> findMonthPercent(Date date, UserAccount user) {
        Year year = Year.of(date.getYear());
        Month month = Month.of(date.getMonth() + 1);
        int lastDay = month.length(year.isLeap());
        List<TodoPercentDto> todoPercentDtos = new ArrayList<>();
        System.out.println(year.getValue());
        System.out.println(month.getValue());
        System.out.println(lastDay);
        for(int i = 1; i <= lastDay; i++){
            Date eachDate = new Date(year.getValue(), month.getValue() - 1, i);
            List<TodoDto> todos = findAllTodo(eachDate, user);
            todoPercentDtos.add(TodoPercentDto.builder()
                    .percent(todos.size() == 0 ? 0 : countDone(todos)*100/todos.size())
                    .date(eachDate)
                    .build());
        }

        return todoPercentDtos;
    }

    public int countDone(List<TodoDto> todoDtos){
        int cnt = 0;
        for(TodoDto todoDto : todoDtos){
            if(todoDto.isDone()){
                cnt++;
            }
        }
        return cnt;
    }
}
