package org.ycm.sims.controller;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ycm.sims.VO.PageVO;
import org.ycm.sims.VO.RoleCheckVO;
import org.ycm.sims.VO.RoleVO;
import org.ycm.sims.dto.PageDTO;
import org.ycm.sims.dto.RoleDTO;
import org.ycm.sims.dto.UpdatePasswordDTO;
import org.ycm.sims.entity.Role;
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
     * @param roleDTO
     * @return
     */
    @RequestMapping("/checkLogin")
    @ResponseBody
    public RoleCheckVO checkLogin(RoleDTO roleDTO){
        return roleService.login(roleDTO);
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
     * 修改密码
     * @param updatePasswordDTO
     * @return
     */
    @RequestMapping("/updatePassword")
    @ResponseBody
    public RoleCheckVO updatePassword(UpdatePasswordDTO updatePasswordDTO){
        return roleService.updatePassword(updatePasswordDTO);
    }

    /**
     * 创建角色页面
     * @return
     */
    @RequestMapping("/create")
    public String create(){
        return ControllerJumpUtil.ControllerJumpUtil(request, "/admin/create_teacher", "role/crate");
    }

    /**
     * 创建角色
     * @param roleDTO
     * @return
     */
    @RequestMapping("/createRole")
    @ResponseBody
    public RoleCheckVO createRole(RoleDTO roleDTO){
        return roleService.createRole(roleDTO);
    }

    /**
     * 注销帐号
     * @param roleDTO
     * @return
     */
    @RequestMapping("/cancelRole")
    @ResponseBody
    public RoleCheckVO cancelRole(RoleDTO roleDTO){
        return roleService.cancelRole(roleDTO);
    }

    /**
     * 重置密码
     * @param roleDTO
     * @return
     */
    @RequestMapping("/resetPassword")
    @ResponseBody
    public RoleCheckVO resetPassword(RoleDTO roleDTO){
        return roleService.resetPassword(roleDTO);
    }

    /**
     * 角色管理页面
     * @return
     */
    @RequestMapping("/roleList")
    public String roleList(){
        return ControllerJumpUtil.ControllerJumpUtil(request, "/admin/roleList", "role/roleList");
    }

    /**
     * 分页
     * @param pageDTO
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public PageVO<RoleVO> page(PageDTO pageDTO){
        return roleService.rolePage(pageDTO);
    }

}
