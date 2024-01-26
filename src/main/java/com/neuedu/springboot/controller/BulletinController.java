package com.neuedu.springboot.controller;

import com.neuedu.springboot.model.bean.Bulletin;
import com.neuedu.springboot.model.bean.DepartmentPageDto;
import com.neuedu.springboot.model.service.BulletinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@CrossOrigin
@RestController
@RequestMapping("/bulletin")
@Api("留言管理")
public class BulletinController {
    @Autowired
    private BulletinService bulletinService;

    //查询留言列表（分页）
    @ApiOperation(value = "显示留言列表",notes="查询全部留言")
    @RequestMapping("/selectBulletinList")
    public DepartmentPageDto selectBulletinList(@RequestBody DepartmentPageDto departmentPageDto) {
        return bulletinService.selectBulletinList(departmentPageDto);
    }

    //查询留言分类
    @ApiOperation(value = "查询留言",notes="根据留言类型查询留言")
    @RequestMapping("/selectBullType")
    public List<Bulletin> selectBullType() {
        return bulletinService.selectBullType();
    }

    //编辑留言
    @ApiOperation(value = "更新留言",notes="根据id更新留言")
    @RequestMapping("/updateBulletinById")
    public String updateBulletinById(@RequestBody Bulletin bulletin) {
        return bulletinService.updateBulletinById(bulletin);
    }

    //删除留言
    @ApiOperation(value = "删除留言",notes="根据id删除留言")
    @RequestMapping("/deleteBulletinById")
    public String deleteDrugById(@RequestBody Bulletin bulletin) {
        return bulletinService.deleteBulletinById(bulletin);
    }

    //新增留言
    @ApiOperation(value = "新增留言",notes="插入新的留言信息")
    @RequestMapping("/insertBulletin")
    public String insertBulletin(@RequestBody Bulletin bulletin) {
        return bulletinService.insertBulletin(bulletin);
    }

    //通过id删除留言（批量）
    @ApiOperation(value = "删除留言",notes="批量删除留言")
    @RequestMapping("/removeBulletinBatch")
    public String removeBulletinBatch(@RequestBody List<Bulletin> bulletin) {
        return bulletinService.removeBulletinBatch(bulletin);
    }

    //根据留言分类查询留言名称请求
    @ApiOperation(value = "查询留言",notes="查给根据留言类型查询留言标题")
    @RequestMapping("/selectBullTitleByBullType")
    public List<Bulletin> selectBullTitleByBullType(@RequestBody Bulletin bulletin) {
        return bulletinService.selectBullTitleByBullType(bulletin);
    }
}
