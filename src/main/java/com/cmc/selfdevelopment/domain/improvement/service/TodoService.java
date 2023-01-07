package com.cmc.selfdevelopment.domain.improvement.service;

import com.cmc.selfdevelopment.domain.improvement.dto.ImprovementDto;
import com.cmc.selfdevelopment.domain.improvement.dto.TodoDto;
import com.cmc.selfdevelopment.domain.improvement.dto.mapper.ImprovementMapper;
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
    public TodoDto create(ImprovementDto improvementDto, User user) {
        Optional<Improvement> findImprovement = improvementService.findByTitleToEntity(improvementDto.getTitle());
        if(findImprovement == null){
            improvementService.create(improvementDto);
            findImprovement = improvementService.findByTitleToEntity(improvementDto.getTitle());
        }
        Improvement improvement = findImprovement.get();
        System.out.println(improvement.getId());
        // TODO : mapper로 바꾸기
        Todo newTodo = Todo.builder()
                .user(user)
                .improvement(improvement)
                .build();
        todoRepository.save(newTodo);
        return TodoDto.builder()
                .title(improvementDto.getTitle())
                .isDone(false)
                .build();
    }
}
