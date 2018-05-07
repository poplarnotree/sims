package org.ycm.sims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ycm.sims.VO.CheckVO;
import org.ycm.sims.VO.MenuVO;
import org.ycm.sims.VO.PageVO;
import org.ycm.sims.VO.RecordVO;
import org.ycm.sims.dto.MenuDTO;
import org.ycm.sims.dto.MenuPageDTO;
import org.ycm.sims.dto.RecordPageDTO;
import org.ycm.sims.service.SystemService;

/**
 * Create by yangchangmin
 * on 2018/5/7 0:46
 */
@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @RequestMapping("/record")
    public String record(){
        return "/admin/record";
    }

    @RequestMapping("/recordList")
    @ResponseBody
    public PageVO<RecordVO> recordList(RecordPageDTO recordPageDTO){
        return systemService.recordPage(recordPageDTO);
    }

    @RequestMapping("/menu")
    public String menu(){
        return "/admin/menu";
    }

    @RequestMapping("/menuList")
    @ResponseBody
    public PageVO<MenuVO> menuList(MenuPageDTO menuPageDTO){
        return systemService.menuPage(menuPageDTO);
    }

    @RequestMapping("/createMenu")
    public String createMenu(){
        return "/admin/createMenu";
    }

    @RequestMapping("/addMenu")
    @ResponseBody
    public CheckVO addMenu(@RequestBody MenuDTO menuDTO){
        return systemService.addMenu(menuDTO);
    }

    @RequestMapping("/updateMenu")
    @ResponseBody
    public CheckVO updateMenu(MenuDTO menuDTO){
        return systemService.updateMenu(menuDTO);
    }

    @RequestMapping("/deleteMenu")
    @ResponseBody
    public CheckVO deleteMenu(Integer id){
        return systemService.deleteMenu(id);
    }

}
