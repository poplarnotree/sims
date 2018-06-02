package org.ycm.sims.dao;

import org.apache.ibatis.annotations.Mapper;
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

    /*添加成绩记录*/
    Integer insertAchievement(Achievement achievement);


    List<AchievementPojo> findAchievement(AchievementPageDTO achievementPageDTO);


}
