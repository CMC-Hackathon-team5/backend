package com.cmc.selfdevelopment.domain.improvement.service;

import com.cmc.selfdevelopment.domain.improvement.dto.ChangeDoneDto;
import com.cmc.selfdevelopment.domain.improvement.dto.CreateTodoDto;
import com.cmc.selfdevelopment.domain.improvement.dto.ImprovementDto;
import com.cmc.selfdevelopment.domain.improvement.dto.TodoDto;
import com.cmc.selfdevelopment.domain.improvement.entity.Improvement;
import com.cmc.selfdevelopment.domain.improvement.entity.Todo;
import com.cmc.selfdevelopment.domain.improvement.repository.TodoRepository;
import com.cmc.selfdevelopment.domain.user.entity.User;
import com.cmc.selfdevelopment.global.common.api.ErrorCode;
import com.cmc.selfdevelopment.global.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
    private final ImprovementService improvementService;
    private final TodoRepository todoRepository;
    public TodoDto create(CreateTodoDto createTodoDto, User user) {
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

    public boolean existsByUserAndImprovement(User user, Improvement improvement, Date date){
        Optional<Todo> todo = todoRepository.findByUserAndImprovementAndDate(user, improvement, date);
        if(todo.isPresent()){
            return true;
        }
        return false;
    }

    public TodoDto changeCheck(User user, ChangeDoneDto changeDoneDto) {
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
}
