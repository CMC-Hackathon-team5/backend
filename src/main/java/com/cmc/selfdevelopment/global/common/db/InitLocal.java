package com.cmc.selfdevelopment.global.common.db;

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

            CreateTodoDto createTodoDto1 = new CreateTodoDto("낚시", new Date(2023 - 1900, 1, 15));
            UserAccount userAccount1 = userService.findUserById(1L);
            todoService.create(createTodoDto1, userAccount1);
        };
    }

}
