package org.ycm.sims.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.ycm.sims.enums.ParameterEnum;
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

    /**
     * 首页
     * @param loginName
     * @return
     */
    @RequestMapping("/")
    public String index(String loginName){
        log.info("loginName={}",request.getSession().getAttribute("loginName"));
        return ControllerJumpUtil.ControllerJumpUtil(request, "/admin/index", "role/index");
    }
}
