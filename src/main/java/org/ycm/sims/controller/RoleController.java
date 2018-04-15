package org.ycm.sims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ycm.sims.dto.LoginDTO;
import org.ycm.sims.service.RoleService;

/**
 * Create by yangchangmin
 * 2018/4/14 23:17
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/login")
    public String login(){
        return "/role/login";
    }

    @RequestMapping("/checkLogin")
    @ResponseBody
    public LoginDTO checkLogin(String loginName, String loginPassword){

        return roleService.login(loginName, loginPassword);
    }

}
