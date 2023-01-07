package com.cmc.selfdevelopment.global.common.db;

import com.cmc.selfdevelopment.domain.improvement.dto.ImprovementDto;
import com.cmc.selfdevelopment.domain.improvement.service.ImprovementService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"local"})
public class InitLocal {
    // true 일때 DB 넣음
    private boolean initData = true;

    @Bean
    CommandLineRunner init(
            ImprovementService improvementService
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

        };
    }

}
