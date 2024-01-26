package com.neuedu.springboot.controller;

import com.neuedu.springboot.model.bean.DepartmentPageDto;
import com.neuedu.springboot.model.bean.Register;
import com.neuedu.springboot.model.bean.Salary;
import com.neuedu.springboot.model.service.SalaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:mwy
 * @Date 2023/2/15 9:09
 */

@CrossOrigin
@RestController
@RequestMapping("/salary")
@Api("资金管理")
public class SalaryController {
    @Autowired
    private SalaryService salaryService;

    //录入转账信息
    @ApiOperation(value = "新增转账" , notes="录入转账信息")
    @RequestMapping("/newSalary")
    public String newSalary(@RequestBody Salary salary) {
        return salaryService.newSalary(salary);
    }

    //查询转账记录
    @ApiOperation(value = "查找转账" , notes="查询转账记录")
    @RequestMapping("/selectAllTransferMoney")
    public DepartmentPageDto selectAllTransferMoney(@RequestBody DepartmentPageDto departmentPageDto) {
        return salaryService.selectAll(departmentPageDto);
    }

}
