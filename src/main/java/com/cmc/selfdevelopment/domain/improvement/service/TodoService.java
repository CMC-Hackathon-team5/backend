package com.cmc.selfdevelopment.domain.improvement.service;

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
        if(findByUserAndImprovement(user,improvement)){
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

    public boolean findByUserAndImprovement(User user, Improvement improvement){
        return todoRepository.existsByUserAndImprovement(user, improvement);
    }
}
