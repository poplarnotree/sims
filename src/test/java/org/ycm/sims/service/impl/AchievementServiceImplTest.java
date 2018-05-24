package org.ycm.sims.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.ycm.sims.VO.CheckVO;
import org.ycm.sims.dto.AchievementDTO;
import org.ycm.sims.service.AchievementService;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;

/**
 * Create by yangchangmin
 * on 2018/5/24 22:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AchievementServiceImplTest {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AchievementService achievementService;

    @Test
    public void addAchievement() {
        request.getSession().setAttribute("loginName","lisi");
        AchievementDTO achievementDTO = new AchievementDTO();
        achievementDTO.setSInformationId(3);
        achievementDTO.setYear("2018-2019");
        achievementDTO.setMouth("6");
        achievementDTO.setNum("1");
        achievementDTO.setScore(90);
        CheckVO checkVO = achievementService.addAchievement(achievementDTO);
        log.info("checkVO={}", checkVO);

    }
}