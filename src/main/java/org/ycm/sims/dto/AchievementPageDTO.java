package org.ycm.sims.dto;

import lombok.Data;

/**
 * Create by yangchangmin
 * on 2018/6/2 22:23
 */
@Data
public class AchievementPageDTO extends PageDTO {

    private String number;

    private Integer sInformationId;

    private Integer tInformationId;

    public AchievementPageDTO() {
    }

    public AchievementPageDTO(Integer page, Integer limit, Integer sInformationId) {
        super(page, limit);
        this.sInformationId = sInformationId;
    }

    public AchievementPageDTO(Integer page, Integer limit, String number) {
        super(page, limit);
        this.number = number;
    }

    public AchievementPageDTO(Integer page, Integer limit) {
        super(page, limit);
    }
}
