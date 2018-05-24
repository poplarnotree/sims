package org.ycm.sims.dao;

import org.apache.ibatis.annotations.Mapper;
import org.ycm.sims.entity.Achievement;

/**
 * Create by yangchangmin
 * on 2018/5/23 23:26
 */
@Mapper
public interface AchievementDao {

    Integer insertAchievement(Achievement achievement);

}
