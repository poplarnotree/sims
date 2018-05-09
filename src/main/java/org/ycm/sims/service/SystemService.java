package org.ycm.sims.service;

import org.ycm.sims.VO.*;
import org.ycm.sims.dto.MenuDTO;
import org.ycm.sims.dto.MenuPageDTO;
import org.ycm.sims.dto.RecordDTO;
import org.ycm.sims.dto.RecordPageDTO;

import java.util.List;

/**
 * Create by yangchangmin
 * on 2018/5/6 16:43
 */
public interface SystemService {

//    增加记录
    void addRecord(RecordDTO recordDTO);

//    记录分页
    PageVO<RecordVO> recordPage(RecordPageDTO recordPageDTO);

//    增加菜单
    CheckVO addMenu(MenuDTO menuDTO);

//    修改菜单
    CheckVO updateMenu(MenuDTO menuDTO);

//    删除菜单
    CheckVO deleteMenu(Integer id);

//    菜单分页
    PageVO<MenuVO> menuPage(MenuPageDTO menuPageDTO);

//    菜单树
    List<MenuTreeVO> menuTree();

}
