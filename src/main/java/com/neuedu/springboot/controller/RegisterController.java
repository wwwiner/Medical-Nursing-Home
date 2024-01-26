package com.neuedu.springboot.controller;

import com.neuedu.springboot.model.bean.DepartmentPageDto;
import com.neuedu.springboot.model.bean.Register;
import com.neuedu.springboot.model.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:mwy
 * @Date 2023/2/4 18:49
 */

@CrossOrigin
@RestController
@RequestMapping("/register")
@Api("预约管理")
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    //老人挂号
    @ApiOperation(value = "新增预约" , notes="老人预约")
    @RequestMapping("/newRegister")
    public String register(@RequestBody Register register) {
        return registerService.register(register);
    }

    //查询当前登陆人病历请求
    @ApiOperation(value = "查询病例" , notes="根据账号查询病例")
    @RequestMapping("/selectPatientRegisterByUserName")
    public DepartmentPageDto selectPatientRegisterByUserName(@RequestBody DepartmentPageDto departmentPageDto) {
        return registerService.selectPatientRegisterByUserName(departmentPageDto);
    }

    //查询当前登陆人病历请求
    @ApiOperation(value = "查询病例" , notes="根据id查询病例")
    @RequestMapping("/deletePatientRegisterById")
    public String deletePatientRegisterById(@RequestBody Register register) {
         return registerService.deletePatientRegisterById(register);
    }

    //查询当前登陆人病历请求
    @ApiOperation(value = "查询病例" , notes="根据医生信息查询病例")
    @RequestMapping("/selectDoctorDiagnosisByDocAccount")
    public List<Register> selectDoctorDiagnosisByDocAccount(@RequestBody Register register) {
        return registerService.selectDoctorDiagnosisByDocAccount(register);
    }
    //医生信息入挂号表
    @ApiOperation(value = "新增医生信息" , notes="根据id新增医生信息")
    @RequestMapping("/addDoctorInformationById")
    public String addDoctorInformationById(@RequestBody Register register) {
        return registerService.addDoctorInformationById(register);
    }

    //通过id查询病历（医生处理界面）
    @ApiOperation(value = "查询预约" , notes="根据id查询预约")
    @RequestMapping("/selectPatientRegisterById")
    public List<Register> selectPatientRegisterById(@RequestBody Register register) {
        return registerService.selectPatientRegisterById(register);
    }

    //提交诊断结果（医生处理界面）
    @ApiOperation(value = "提交结果" , notes="医生提交诊断结果")
    @RequestMapping("/diagnosisIsOk")
    public String diagnosisIsOk(@RequestBody Register register) {
        return registerService.diagnosisIsOk(register);
    }

    //查询药品价格
    @ApiOperation(value = "查询药品价格" , notes="")
    @RequestMapping("/payDrugPrice")
    public String[] payDrugPrice(@RequestBody Register register) {
        return registerService.payDrugPrice(register);
    }

    //缴费
    @RequestMapping("/pay")
    @ApiOperation(value = "缴费" , notes="")
    public String pay(@RequestBody Register register) {
        return registerService.pay(register);
    }

    //查询所有诊断数据
    @ApiOperation(value = "查询所有诊断数据" , notes="")
    @RequestMapping("/selectDoctorDiagnosisAll")
    public DepartmentPageDto selectDoctorDiagnosisAll(@RequestBody DepartmentPageDto departmentPageDto) {
        return registerService.selectDoctorDiagnosisAll(departmentPageDto);
    }

    //练习
    @RequestMapping("/test")
    public List test() {
        List l1 = new ArrayList();
        List l2 = new ArrayList();
        l1.add(1);l1.add(2);l1.add(3);
        l2.add(5);l2.add(6);l2.add(7);
        List resultList = new ArrayList();
        String str1 = "";
        String str2 = "";;
        for(int i=l1.size();i>0;i--){
            String x = String.valueOf(l1.get(i-1));
            str1 = str1 + x;
        }
        for(int j=l2.size();j>0;j--){
            str2 = str2 + l2.get(j-1);
        }
        int result = Integer.parseInt(str1) + Integer.parseInt(str2);
        String cell;
        String resultStr = String.valueOf(result);
        for(int i=resultStr.length();i>0;i--){
            cell = resultStr.substring(i-1,i);
            resultList.add(cell);
        }
        return resultList;

    }
}
