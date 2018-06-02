package org.ycm.sims.dto;

import lombok.Data;

/**
 * Create by yangchangmin
 * on 2018/6/2 22:23
 */
@Data
public class AchievementPageDTO extends PageDTO {

    public AchievementPageDTO() {
    }

    public AchievementPageDTO(Integer page, Integer limit) {
        super(page, limit);
    }
}
