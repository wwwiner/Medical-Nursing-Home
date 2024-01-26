package com.neuedu.springboot.controller;

import com.neuedu.springboot.model.bean.Department;
import com.neuedu.springboot.model.bean.DepartmentPageDto;
import com.neuedu.springboot.model.bean.UserInfo;
import com.neuedu.springboot.model.service.DepartmentService;
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
 * @Date 2023/2/1 23:53
 */
@CrossOrigin
@RestController
@RequestMapping("/department")
@Api("科室管理")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    //查询全部科室列表
    @ApiOperation(value = "查询科室",notes="查询全部科室")
    @RequestMapping("/selectDepartmentList")
    public List<Department> login() {
        return departmentService.selectDepartmentList();
    }

    //查询科室列表（分页）
    @ApiOperation(value = "查询科室",notes="查询科室列表分页")
    @RequestMapping("/selectCountFromDepartmentList")
    public DepartmentPageDto selectCountFromDepartmentList(@RequestBody DepartmentPageDto departmentPageDto) {
        return departmentService.selectCountFromDepartmentList(departmentPageDto);
    }

    //查询科室分类
    @ApiOperation(value = "查询科室", notes="分类查询科室")
    @RequestMapping("/selectDepartmentClassification")
    public List<Department> selectDepartmentClassification() {
        return departmentService.selectDepartmentClassification();
    }

    //查询科室类型
    @ApiOperation(value = "查询科室", notes="查询科室类型" )
    @RequestMapping("/selectDepartmentType")
    public List<Department> selectDepartmentType() {
        return departmentService.selectDepartmentType();
    }

    //编辑科室
    @ApiOperation(value = "编辑科室" , notes="更新科室信息")
    @RequestMapping("/updateDeptartment")
    public void updateDeptartment(@RequestBody Department department) {
        departmentService.updateDeptartment(department);
    }

    //新建科室
    @ApiOperation(value = "新建科室" , notes="新建科室信息")
    @RequestMapping("/insertDeptartment")
    public void insertDeptartment(@RequestBody Department department) {
        departmentService.insertDeptartment(department);
    }

    //通过id查科室
    @ApiOperation(value = "查询科室" , notes="根据id查询科室")
    @RequestMapping("/selectDepartmentbyId")
    public Department selectDepartmentbyId(@RequestBody Department department) {
        return departmentService.selectDepartmentbyId(department);
    }

    //通过id删除科室（单个）
    @ApiOperation(value = "删除科室", notes = "通过id删除科室")
    @RequestMapping("/deleteDepartmentbyId")
    public String deleteDepartmentbyId(@RequestBody Department department) {
        departmentService.deleteDepartmentbyId(department);
        return "SUCCESS";
    }

    //通过id删除科室（批量）
    @ApiOperation(value = "删除科室", notes = "批量删除科室")
    @RequestMapping("/removeDepartmentBatch")
    public String removeDepartmentBatch(@RequestBody List<Department> departmentList) {
        departmentService.removeDepartmentBatch(departmentList);
        return "SUCCESS";
    }

    //查询科室列表（分页）(挂号：只查询医疗科室)
    @ApiOperation(value = "查询科室", notes = "分页查询科室")
    @RequestMapping("/selectDepartmentListAsPatient")
    public DepartmentPageDto selectDepartmentListAsPatient(@RequestBody DepartmentPageDto departmentPageDto) {
        return departmentService.selectDepartmentListAsPatient(departmentPageDto);
    }
}
