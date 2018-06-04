package org.ycm.sims.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ycm.sims.dto.AchievementPageDTO;
import org.ycm.sims.entity.Achievement;
import org.ycm.sims.pojo.AchievementPojo;

import java.util.List;

/**
 * Create by yangchangmin
 * on 2018/5/23 23:26
 */
@Mapper
public interface AchievementDao {

    /**
     * 添加成绩
     * @param achievement
     * @return
     */
    Integer insertAchievement(Achievement achievement);

    /**
     * 查找学生分页成绩
     * @param achievementPageDTO
     * @return
     */
    List<AchievementPojo> findAchievement(AchievementPageDTO achievementPageDTO);

    /**
     * 查询成绩数量
     * @return
     */
    Integer achievementCount(@Param(value = "number") String number);


}
