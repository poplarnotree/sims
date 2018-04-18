package org.ycm.sims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ycm.sims.VO.RoleVO;
import org.ycm.sims.enums.ExceptionEnum;
import org.ycm.sims.enums.ParameterEnum;
import org.ycm.sims.exception.SimsException;
import org.ycm.sims.service.RoleService;
import org.ycm.sims.utils.ControllerJumpUtil;

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
    public RoleVO checkLogin(String loginName, String loginPassword){
        return roleService.login(loginName, loginPassword);
    }

    /**
     * 登出
     * @return
     */
    @RequestMapping("/logout")
    public String logout(){
        request.getSession().invalidate();
        return "/role/login";
    }

    /**
     * 密码修改页面
     * @return
     */
    @RequestMapping("/password")
    public String updatePassword(){
        return ControllerJumpUtil.ControllerJumpUtil(request, "/admin/password", "role/password");
    }

    /**
     * 密码修改
     * @param originalPassword
     * @param newPassword
     * @return
     */
    @RequestMapping("/updatePassword")
    @ResponseBody
    public RoleVO updatePassword(String originalPassword, String newPassword){
        return roleService.updatePassword(originalPassword, newPassword);
    }

    /**
     * 创建角色页面
     * @return
     */
    @RequestMapping("/create")
    public String create(){
//        int roleType = (int)request.getSession().getAttribute(ParameterEnum.ROLE_TYPE.getValue());
//        if (roleType == 0){
//            return "/admin/create";
//        }
//        if (roleType == 1 || roleType == 2){
//            return "role/crate";
//        }else {
//            request.getSession().removeAttribute(ParameterEnum.LOGIN_NAME.getValue());
//            return "/role/login";
//        }
        return ControllerJumpUtil.ControllerJumpUtil(request, "/admin/create", "role/crate");
    }

    /**
     * 创建角色
     * @param loginName
     * @param loginPassword
     * @param roleType
     * @return
     */
    @RequestMapping("/createRole")
    @ResponseBody
    public RoleVO createRole(String loginName, String loginPassword, int roleType){
        return roleService.createRole(loginName, loginPassword, roleType);
    }

    /**
     * 注销帐号页面
     * @return
     */
    @RequestMapping("/cancel")
    public String cancel(){
        return ControllerJumpUtil.ControllerJumpUtil(request, "/admin/cancel", "role/cancel");
    }

    /**
     * 注销帐号
     * @param id
     * @param roleType
     * @return
     */
    @RequestMapping("/cancelRole")
    @ResponseBody
    public RoleVO cancelRole(int id, int roleType){
        return roleService.cancelRole(id, roleType);
    }

    /**
     * 重置密码
     * @param id
     * @param roleType
     * @return
     */
    @RequestMapping("/resetPassword")
    @ResponseBody
    public RoleVO resetPassword(int id, int roleType){
        return roleService.resetPassword(id, roleType);
    }
}
