package com.neuedu.springboot.controller;

import com.neuedu.springboot.model.bean.DepartmentPageDto;
import com.neuedu.springboot.model.bean.Hospitalization;
import com.neuedu.springboot.model.bean.UserInfo;
import com.neuedu.springboot.model.service.HospitalizationService;
import com.neuedu.springboot.model.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:mwy
 * @Date 2023/2/16 16:50
 */
@CrossOrigin
@RestController
@Api("入住管理")
@RequestMapping("/hospitalization")
public class HospitalizationController {
    @Autowired
    private HospitalizationService hospitalizationService;

    //入住院表（医生提交诊断页面）
    @ApiOperation(value = "提交入住信息" , notes="医生提交诊断页面")
    @RequestMapping("/hosByDoc")
    public String hosByDoc(@RequestBody Hospitalization hospitalization) {
        return hospitalizationService.hosByDoc(hospitalization);
    }

    //查询用户办理入住列表（分页）
    @ApiOperation(value = "查询入住信息 " , notes="根据用户名查询用户入住信息")
    @RequestMapping("/selectHospitalizationListByUserAccount")
    public DepartmentPageDto selectHospitalizationListByUserAccount(@RequestBody DepartmentPageDto departmentPageDto) {
        return hospitalizationService.selectHospitalizationListByUserAccount(departmentPageDto);
    }

    //拒绝住院请求
    @ApiOperation(value = "拒绝入住请求 " , notes="医生拒绝入住请求")
    @RequestMapping("/notInWardById")
    public String notInWardById(@RequestBody Hospitalization hospitalization) {
        return hospitalizationService.notInWardById(hospitalization);
    }

    //住院办理回显
    @ApiOperation(value = "查询入住信息" , notes="根据用户id查询入住信息")
    @RequestMapping("/selectHospitalizationById")
    public Hospitalization selectHospitalizationById(@RequestBody Hospitalization hospitalization) {
        return hospitalizationService.selectHospitalizationById(hospitalization);
    }
}
