package org.ycm.sims.service;

import org.ycm.sims.VO.AchievementVO;
import org.ycm.sims.VO.CheckVO;
import org.ycm.sims.VO.PageVO;
import org.ycm.sims.dto.AchievementDTO;
import org.ycm.sims.dto.AchievementPageDTO;

/**
 * Create by yangchangmin
 * on 2018/5/24 22:22
 */
public interface AchievementService {

    /**
     * 成绩管理
     * @param achievementDTO
     * @return
     */
    CheckVO achievementManage(AchievementDTO achievementDTO);

    /**
     * 查询成绩分页
     * @param achievementPageDTO
     * @return
     */
    PageVO<AchievementVO> achievementPage(AchievementPageDTO achievementPageDTO);

    /**
     * 教师查询我的学生成绩
     * @param achievementPageDTO
     * @return
     */
    PageVO<AchievementVO> myStudentAchievementPage(AchievementPageDTO achievementPageDTO);

}
