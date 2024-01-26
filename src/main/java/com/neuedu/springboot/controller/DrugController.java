package com.neuedu.springboot.controller;

import com.neuedu.springboot.model.bean.Department;
import com.neuedu.springboot.model.bean.DepartmentPageDto;
import com.neuedu.springboot.model.bean.Drug;
import com.neuedu.springboot.model.service.DrugService;
import com.neuedu.springboot.model.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author:mwy
 * @Date 2023/2/4 14:54
 */

@CrossOrigin
@RestController
@RequestMapping("/drug")
@Api("药品管理")
public class DrugController {
    @Autowired
    private DrugService drugService;

    //查询药品列表（分页）
    @ApiOperation(value = "查询药品" , notes = "查询药品列表")
    @RequestMapping("/selectDrugList")
    public DepartmentPageDto selectDrugList(@RequestBody DepartmentPageDto departmentPageDto) {
        return drugService.selectDrugList(departmentPageDto);
    }

    //查询药品分类
    @ApiOperation(value = "查询药品" , notes="分类查询药品")
    @RequestMapping("/selectDrugType")
    public List<Drug> selectDrugType() {
        return drugService.selectDrugType();
    }

    //编辑药品
    @ApiOperation(value = "编辑药品" , notes="根据id编辑药品")
    @RequestMapping("/updateDrugById")
    public String updateDrugById(@RequestBody Drug drug) {
        return drugService.updateDrugById(drug);
    }

    //删除药品
    @ApiOperation(value = "删除药品" , notes="根据id删除药品")
    @RequestMapping("/deleteDrugById")
    public String deleteDrugById(@RequestBody Drug drug) {
        return drugService.deleteDrugById(drug);
    }

    //新增药品
    @ApiOperation(value = "新增药品" , notes="增加药品信息")
    @RequestMapping("/insertDrug")
    public String insertDrug(@RequestBody Drug drug) {
        return drugService.insertDrug(drug);
    }

    //通过id删除科室（批量）
    @ApiOperation(value = "删除科室" , notes="根据id删除科室")
    @RequestMapping("/removeDrugBatch")
    public String removeDrugBatch(@RequestBody List<Drug> drug) {
        return drugService.removeDrugBatch(drug);
    }

    //根据药品分类查询药品名称请求
    @ApiOperation(value = "查询药品" , notes="根据药品分类查询药品")
    @RequestMapping("/selectDrugNameByDrugType")
    public List<Drug> selectDrugNameByDrugType(@RequestBody Drug drug) {
        return drugService.selectDrugNameByDrugType(drug);
    }
}
