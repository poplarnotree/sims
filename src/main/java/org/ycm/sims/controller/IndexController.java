package org.ycm.sims.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ycm.sims.VO.HeadVO;
import org.ycm.sims.enums.ParameterEnum;
import org.ycm.sims.service.IndexService;
import org.ycm.sims.utils.ControllerJumpUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 默认首页为登录页面
 * Create by yangchangmin
 * 2018/4/15 15:53
 */
@Controller
@Slf4j
public class IndexController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private IndexService indexService;

    /**
     * 首页
     * @param loginName
     * @return
     */
    @RequestMapping("/")
    public String index(String loginName){
        return ControllerJumpUtil.ControllerJumpUtil(request, "/admin/index", "role/index");
    }

    @RequestMapping("/head")
    @ResponseBody
    public HeadVO head(){
        return indexService.roleName();
    }
}
