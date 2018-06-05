package org.ycm.sims.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.ycm.sims.VO.AchievementVO;
import org.ycm.sims.VO.CheckVO;
import org.ycm.sims.VO.PageVO;
import org.ycm.sims.dto.AchievementDTO;
import org.ycm.sims.dto.AchievementPageDTO;
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
        request.getSession().setAttribute("loginName","liuba");
        AchievementDTO achievementDTO = new AchievementDTO();
        achievementDTO.setSInformationId(13);
        achievementDTO.setYear("2018-2019");
        achievementDTO.setMouth("6");
        achievementDTO.setNum("2");
        achievementDTO.setScore(75);
        CheckVO checkVO = achievementService.achievementManage(achievementDTO);
        log.info("checkVO={}", checkVO);

    }

    @Test
    public void achievementPage() {
        request.getSession().setAttribute("loginName","lisi");
        AchievementPageDTO achievementPageDTO = new AchievementPageDTO();
        achievementPageDTO.setLimit(10);
        achievementPageDTO.setPage(1);
        PageVO<AchievementVO> achievementVOPageVO = achievementService.achievementPage(achievementPageDTO);
        log.info("achievementVOPageVO = {}", achievementVOPageVO);


    }
}