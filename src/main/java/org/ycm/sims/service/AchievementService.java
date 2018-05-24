package org.ycm.sims.service;

import org.ycm.sims.VO.CheckVO;
import org.ycm.sims.dto.AchievementDTO;

/**
 * Create by yangchangmin
 * on 2018/5/24 22:22
 */
public interface AchievementService {

    CheckVO addAchievement(AchievementDTO achievementDTO);

}
