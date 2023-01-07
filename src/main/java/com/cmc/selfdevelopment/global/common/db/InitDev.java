package com.cmc.selfdevelopment.global.common.db;

import com.cmc.selfdevelopment.domain.improvement.dto.ImprovementDto;
import com.cmc.selfdevelopment.domain.improvement.service.ImprovementService;
import com.cmc.selfdevelopment.domain.user.UserTempDto;
import com.cmc.selfdevelopment.domain.user.UserTempService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"dev"})
public class InitDev {
    // true 일때 DB 넣음
    private boolean initData = true;

    @Bean
    CommandLineRunner init(
            ImprovementService improvementService, UserTempService userTempService
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

            UserTempDto userTempDto1 = new UserTempDto("test1@test.com", "password", "테스트이름1", 12);
            userTempService.create(userTempDto1);
            UserTempDto userTempDto2 = new UserTempDto("test2@test.com", "password", "테스트이름2", 13);
            userTempService.create(userTempDto2);
            UserTempDto userTempDto3 = new UserTempDto("test3@test.com", "password", "테스트이름3", 14);
            userTempService.create(userTempDto3);
            UserTempDto userTempDto4 = new UserTempDto("test4@test.com", "password", "테스트이름4", 15);
            userTempService.create(userTempDto4);
        };
    }

}

