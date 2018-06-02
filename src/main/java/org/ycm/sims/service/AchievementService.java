package org.ycm.sims.service;

import org.ycm.sims.VO.AchievementVO;
import org.ycm.sims.VO.CheckVO;
import org.ycm.sims.VO.PageVO;
import org.ycm.sims.dto.AchievementDTO;
import org.ycm.sims.dto.AchievementPageDTO;
import org.ycm.sims.entity.Achievement;

/**
 * Create by yangchangmin
 * on 2018/5/24 22:22
 */
public interface AchievementService {

    CheckVO addAchievement(AchievementDTO achievementDTO);

    PageVO<AchievementVO> achievementPage(AchievementPageDTO achievementPageDTO);

}
