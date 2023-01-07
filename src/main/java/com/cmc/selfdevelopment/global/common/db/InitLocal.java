package com.cmc.selfdevelopment.global.common.db;

import com.cmc.selfdevelopment.domain.improvement.dto.ChangeDoneDto;
import com.cmc.selfdevelopment.domain.improvement.dto.CreateTodoDto;
import com.cmc.selfdevelopment.domain.improvement.dto.ImprovementDto;
import com.cmc.selfdevelopment.domain.improvement.service.ImprovementService;
import com.cmc.selfdevelopment.domain.improvement.service.TodoService;
import com.cmc.selfdevelopment.domain.user.dto.request.SignUpRequestDto;
import com.cmc.selfdevelopment.domain.user.entity.UserAccount;
import com.cmc.selfdevelopment.domain.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.Date;

@Configuration
@Profile({"local"})
public class InitLocal {
    // true 일때 DB 넣음
    private boolean initData = true;

    @Bean
    CommandLineRunner init(
            ImprovementService improvementService, UserService userService, TodoService todoService
    ){
        return args -> {
            if(!initData){
                return;
            }
            ImprovementDto improvementDto1 = new ImprovementDto("책 읽기");
            ImprovementDto improvementDto2 = new ImprovementDto("운동하기");
            ImprovementDto improvementDto3 = new ImprovementDto("산책하기");
            ImprovementDto improvementDto4 = new ImprovementDto("그림 그리기");
            ImprovementDto improvementDto5 = new ImprovementDto("영어 공부하기");
            ImprovementDto improvementDto6 = new ImprovementDto("일기 쓰기");
            ImprovementDto improvementDto7 = new ImprovementDto("블로그 쓰기");
            ImprovementDto improvementDto8 = new ImprovementDto("명상하기");
            ImprovementDto improvementDto9 = new ImprovementDto("경제 용어 공부하기");
            ImprovementDto improvementDto10 = new ImprovementDto("비타민 먹기");

            improvementService.create(improvementDto1);
            improvementService.create(improvementDto2);
            improvementService.create(improvementDto3);
            improvementService.create(improvementDto4);
            improvementService.create(improvementDto5);
            improvementService.create(improvementDto6);
            improvementService.create(improvementDto7);
            improvementService.create(improvementDto8);
            improvementService.create(improvementDto9);
            improvementService.create(improvementDto10);

            SignUpRequestDto signUpRequestDto1 = new SignUpRequestDto("test1@test.com", "password", "테스트이름1");
            userService.userSignUp(signUpRequestDto1);
            SignUpRequestDto signUpRequestDto2 = new SignUpRequestDto("test2@test.com", "password", "테스트이름2");
            userService.userSignUp(signUpRequestDto2);
            SignUpRequestDto signUpRequestDto3 = new SignUpRequestDto("test3@test.com", "password", "테스트이름3");
            userService.userSignUp(signUpRequestDto3);
            SignUpRequestDto signUpRequestDto4 = new SignUpRequestDto("test4@test.com", "password", "테스트이름4");
            userService.userSignUp(signUpRequestDto4);

            UserAccount userAccount1 = userService.findUserById(1L);
            CreateTodoDto createTodoDto1 = new CreateTodoDto("운동", new Date(2023 - 1900, 1, 1));
            todoService.create(createTodoDto1, userAccount1);
            CreateTodoDto createTodoDto2 = new CreateTodoDto("독서", new Date(2023 - 1900, 1, 3));
            todoService.create(createTodoDto2, userAccount1);
            CreateTodoDto createTodoDto3 = new CreateTodoDto("피아노 연주", new Date(2023 - 1900, 1, 5));
            todoService.create(createTodoDto3, userAccount1);
            CreateTodoDto createTodoDto4 = new CreateTodoDto("알고리즘 공부", new Date(2023 - 1900, 1, 7));
            todoService.create(createTodoDto4, userAccount1);
            CreateTodoDto createTodoDto5 = new CreateTodoDto("운동 열심히", new Date(2023 - 1900, 1, 2));
            todoService.create(createTodoDto5, userAccount1);
            CreateTodoDto createTodoDto6 = new CreateTodoDto("독서 열심히", new Date(2023 - 1900, 1, 2));
            todoService.create(createTodoDto6, userAccount1);
            CreateTodoDto createTodoDto7 = new CreateTodoDto("피아노 열심히", new Date(2023 - 1900, 1, 2));
            todoService.create(createTodoDto7, userAccount1);
            CreateTodoDto createTodoDto8 = new CreateTodoDto("알고리즘 열심히", new Date(2023 - 1900, 1, 2));
            todoService.create(createTodoDto8, userAccount1);
            CreateTodoDto createTodoDto9 = new CreateTodoDto("운동 꾸준히", new Date(2023 - 1900, 1, 3));
            todoService.create(createTodoDto9, userAccount1);
            CreateTodoDto createTodoDto10 = new CreateTodoDto("독서 꾸준히", new Date(2023 - 1900, 1, 3));
            todoService.create(createTodoDto10, userAccount1);
            CreateTodoDto createTodoDto11 = new CreateTodoDto("피아노 꾸준히", new Date(2023 - 1900, 1, 1));
            todoService.create(createTodoDto11, userAccount1);
            CreateTodoDto createTodoDto12 = new CreateTodoDto("알고리즘 꾸준히", new Date(2023 - 1900, 1, 1));
            todoService.create(createTodoDto12, userAccount1);
            CreateTodoDto createTodoDto13 = new CreateTodoDto("운동 매일", new Date(2023 - 1900, 1, 7));
            todoService.create(createTodoDto13, userAccount1);
            CreateTodoDto createTodoDto14 = new CreateTodoDto("독서 매일", new Date(2023 - 1900, 1, 7));
            todoService.create(createTodoDto14, userAccount1);
            CreateTodoDto createTodoDto15 = new CreateTodoDto("피아노 매일", new Date(2023 - 1900, 1, 7));
            todoService.create(createTodoDto15, userAccount1);
            CreateTodoDto createTodoDto16 = new CreateTodoDto("알고리즘 매일", new Date(2023 - 1900, 1, 6));
            todoService.create(createTodoDto16, userAccount1);
            CreateTodoDto createTodoDto17 = new CreateTodoDto("운동 하루씩", new Date(2023 - 1900, 1, 5));
            todoService.create(createTodoDto17, userAccount1);
            CreateTodoDto createTodoDto18 = new CreateTodoDto("독서 하루씩", new Date(2023 - 1900, 1, 4));
            todoService.create(createTodoDto18, userAccount1);
            CreateTodoDto createTodoDto19 = new CreateTodoDto("피아노 하루씩", new Date(2023 - 1900, 1, 3));
            todoService.create(createTodoDto19, userAccount1);
            CreateTodoDto createTodoDto20 = new CreateTodoDto("알고리즘 하루씩", new Date(2023 - 1900, 1, 8));
            todoService.create(createTodoDto20, userAccount1);

            todoService.changeCheck(userAccount1, new ChangeDoneDto("알고리즘 하루씩", new Date(2023 - 1900, 1, 8)));
            todoService.changeCheck(userAccount1, new ChangeDoneDto("피아노 하루씩", new Date(2023 - 1900, 1, 3)));
            todoService.changeCheck(userAccount1, new ChangeDoneDto("독서 하루씩", new Date(2023 - 1900, 1, 4)));
            todoService.changeCheck(userAccount1, new ChangeDoneDto("운동 하루씩", new Date(2023 - 1900, 1, 5)));
            todoService.changeCheck(userAccount1, new ChangeDoneDto("알고리즘 매일", new Date(2023 - 1900, 1, 6)));
            todoService.changeCheck(userAccount1, new ChangeDoneDto("운동 매일", new Date(2023 - 1900, 1, 7)));
        };
    }

}
