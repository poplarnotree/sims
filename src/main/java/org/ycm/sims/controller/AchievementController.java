package org.ycm.sims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ycm.sims.VO.AchievementVO;
import org.ycm.sims.VO.PageVO;
import org.ycm.sims.dto.AchievementPageDTO;
import org.ycm.sims.service.AchievementService;
import org.ycm.sims.utils.ControllerJumpUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Create by yangchangmin
 * on 2018/5/24 23:21
 */
@Controller
@RequestMapping("/achievement")
public class AchievementController {

    @Autowired
    private AchievementService achievementService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/index")
    public String index(){
        return ControllerJumpUtil.ControllerJumpUtil(request, "", "role/achievement");
    }

    @RequestMapping("/myAchievement")
    public String myAchievement(){
        return ControllerJumpUtil.ControllerJumpUtil(request, "", "role/my_achievement");
    }

    @RequestMapping("/achievementList")
    @ResponseBody
    public PageVO<AchievementVO> achievementList(AchievementPageDTO achievementPageDTO){
        return achievementService.achievementPage(achievementPageDTO);
    }

    @RequestMapping("/myStudentAchievement")
    public String myStudentAchievement(){
        return ControllerJumpUtil.ControllerJumpUtil(request, "", "role/my_student_achievement");
    }

    @RequestMapping("/myStudentAchievementList")
    @ResponseBody
    public PageVO<AchievementVO> myStudentAchievementList(AchievementPageDTO achievementPageDTO){
        return achievementService.myStudentAchievementPage(achievementPageDTO);
    }

}
