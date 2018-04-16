package org.ycm.sims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ycm.sims.dto.LoginDTO;
import org.ycm.sims.service.RoleService;

import javax.servlet.http.HttpServletRequest;

/**
 * 角色模块
 * Create by yangchangmin
 * 2018/4/14 23:17
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RoleService roleService;

    /**
     * 登录页面
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "/role/login";
    }

    /**
     * 验证登录
     * @param loginName
     * @param loginPassword
     * @return
     */
    @RequestMapping("/checkLogin")
    @ResponseBody
    public LoginDTO checkLogin(String loginName, String loginPassword){

        return roleService.login(loginName, loginPassword);
    }

    @RequestMapping("/logout")
    public String logout(){
        request.getSession().removeAttribute("loginName");
        return "/role/login";
    }

}
