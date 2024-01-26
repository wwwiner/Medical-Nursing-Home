package com.neuedu.springboot.model.service;

import com.neuedu.springboot.model.bean.*;
import com.neuedu.springboot.model.mapper.DrugMapper;
import com.neuedu.springboot.model.mapper.RegisterMapper;
import com.neuedu.springboot.model.mapper.SalaryMapper;
import com.neuedu.springboot.model.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:mwy
 * @Date 2023/2/4 18:55
 */
@Service
@Transactional
public class RegisterService {
    @Autowired
    private RegisterMapper registerMapper;
    @Autowired
    private DrugMapper drugMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SalaryMapper salaryMapper;
    @Autowired
    private HospitalizationService hospitalizationService;

    public String register(Register register) {
         registerMapper.register(register);
        return "SUCCESS";
    }

    public DepartmentPageDto selectPatientRegisterByUserName(DepartmentPageDto departmentPageDto) {
        DepartmentPageDto resultPageDto = new DepartmentPageDto();

        int pageCount= registerMapper.selectAllCountByUserName(departmentPageDto);
        int totalRow = pageCount; //总行数
        int totalPageNum = 0; //总页数
        int pageNum = departmentPageDto.getPageNum();
        int preNum = departmentPageDto.getPageNum(); // 上一页
        int nextNum = departmentPageDto.getPageNum(); //下一页
        int beginNum = 0; //开始查询记录数
        departmentPageDto.setMaxPageNum(10);
        List<Register> list = null; //业务数据
        String deptCategoryName = departmentPageDto.getDeptCategoryName();
        String status = departmentPageDto.getStatus();
        String userAcccount = departmentPageDto.getUserAccount();
        //如果总行数为0,就直按结束
        if (totalRow == 0) {
            return null;
        }
        //计算总页数
        if (totalRow % 10 == 0) {
            totalPageNum = 1;
        } else {
            totalPageNum = (totalRow / 10) + 1;
        }
        //计算上一页和下一页
        if (preNum > 1) {
            preNum--;
        }
        if (nextNum < totalPageNum) {
            nextNum++;
        }
        //计算开始查询记录数
        beginNum = (pageNum - 1) * 10;

        //查询业务数据

        resultPageDto.setTotalRow(totalRow);
        resultPageDto.setTotalPageNum(totalPageNum);
        resultPageDto.setPageNum(pageNum);
        resultPageDto.setPreNum(preNum);
        resultPageDto.setNextNum(nextNum);
        resultPageDto.setBeginNum(beginNum);
        resultPageDto.setMaxPageNum(10);
        resultPageDto.setDeptCategoryName(deptCategoryName);
        resultPageDto.setStatus(status);
        resultPageDto.setUserAccount(userAcccount);
        List<Register> resultRegisterList = registerMapper.selectPatientRegisterByUserName(resultPageDto);
        list = resultRegisterList;
        resultPageDto.setList(list);
        return resultPageDto;
    }

    public String deletePatientRegisterById(Register register) {
        registerMapper.deletePatientRegisterById(register);
        return "SUCCESS";
    }

    public List<Register> selectDoctorDiagnosisByDocAccount(Register register) {
        return registerMapper.selectDoctorDiagnosisByDocAccount(register);
    }

    public String addDoctorInformationById(Register register) {
        registerMapper.addDoctorInformationById(register);
        return "SUCCESS";
    }

    public List<Register> selectPatientRegisterById(Register register) {
        return registerMapper.selectPatientRegisterById(register);
    }

    public String diagnosisIsOk(Register register) {
        //去掉第一个逗号
        String drugList = register.getDrug();
        drugList = drugList.substring(1);
        register.setDrug(drugList);
        //判断是否住院
        if("true".equals(register.getIsHosAdvice())){
            register.setIsHosAdvice("是");
            //入住院登记表
            Hospitalization hospitalization = new Hospitalization();
            hospitalization.setUserAccount(register.getUserAccount());
            hospitalization.setUserName(register.getUserName());
            hospitalization.setDocAccount(register.getDocAccount());
            hospitalization.setDocName(register.getDocName());
            hospitalization.setDocType(register.getDocType());
            hospitalization.setDeptType(register.getDeptName());
            hospitalization.setDeptCategoryName(register.getDeptCategoryName());
            hospitalization.setDisease(register.getDisease());
            hospitalization.setStatus("待用户选择");
            hospitalization.setInTime(new Date());
            hospitalization.setRegisterId(register.getId());
            hospitalizationService.hosByDoc(hospitalization);
        }else {
            register.setIsHosAdvice("否");
        }
        //入病历表
        registerMapper.diagnosisIsOk(register);
        return "SUCCESS";
    }

    public String[] payDrugPrice(Register register) {
        String[] resultArray = new String[100];;
        String drug = register.getDrug();
        String[] drugArray = drug.split(",");
        int sumPrice = 0;
        for(int i = 0;i<drugArray.length;i++){
            int drugPrice = drugMapper.selectDrugPrice(drugArray[i]);
            String p =String.valueOf(drugPrice);
            String namePrice = drugArray[i] + ":" + p + "元";
            resultArray[i] = namePrice;
            sumPrice += drugPrice;
        }
        resultArray[99] = "合计：" + sumPrice + "元";
        return resultArray;
    }

    public String pay(Register register) {
        String drug = register.getDrug();
        String[] drugArray = drug.split(",");
        int sumPrice = 0;
        for(int i = 0;i<drugArray.length;i++){
            int drugPrice = drugMapper.selectDrugPrice(drugArray[i]);
            String p =String.valueOf(drugPrice);
            String namePrice = drugArray[i] + ":" + p + "元";
            sumPrice += drugPrice;
            Drug d = new Drug();
            d.setDrugName(drugArray[i]);
            //扣药品数量
            drugMapper.drugCountMinusOne(d);
        }
        String result = "缴费成功,合计：" + sumPrice + "元";

        //扣钱
        UserInfo user = new UserInfo();
        user.setUserName(register.getUserAccount());
        user.setMoney(sumPrice);
        //查询用户余额，若余额足够，则正常缴费，若余额不足，则返回 “余额不足”
        int balance = userInfoMapper.selectBalanceByUserName(user);
        if(balance >= sumPrice){
            userInfoMapper.kouQian(user);
            //设置状态为已缴费
            register.setStatus("已缴费：" + sumPrice + "元");
            registerMapper.setStatusPayOk(register);
            //用户缴费金额入到医院总部
            UserInfo hiser = new UserInfo();
            hiser.setUserName("his");
            hiser.setMoney(sumPrice);
            userInfoMapper.jiaQian(hiser);
            //存入转账记录
            Salary salary = new Salary();
            salary.setUserAccount("his");
            salary.setUserName("医院总部");
            salary.setTransferAccount(register.getUserAccount());
            salary.setTransferName(register.getUserName());
            salary.setTransferMoney(sumPrice);
            salary.setTransferTime(new Date());
            salary.setTransferType("医疗缴费");
            //查询转账总金额
            List<Salary> list = salaryMapper.selectAllTransferMoney(salary);
            //所有转账的和
            int allMoney = 0;
            for (Salary s : list) {
                int money = s.getTransferMoney();
                allMoney = allMoney + money;
            }
            salary.setAllTransferMoney(allMoney + sumPrice);
            salaryMapper.newSalary(salary);
            return result;
        }else {
            return "余额不足,缴费失败,请充值";
        }
    }

    public DepartmentPageDto selectDoctorDiagnosisAll(DepartmentPageDto departmentPageDto) {
        DepartmentPageDto resultPageDto = new DepartmentPageDto();

        int pageCount= registerMapper.selectAllCount();
        int totalRow = pageCount; //总行数
        int totalPageNum = 0; //总页数
        int pageNum = departmentPageDto.getPageNum();
        int preNum = departmentPageDto.getPageNum(); // 上一页
        int nextNum = departmentPageDto.getPageNum(); //下一页
        int beginNum = 0; //开始查询记录数
        List<Register> list = null; //业务数据
        String docName = departmentPageDto.getDocName();
        String deptCategoryName = departmentPageDto.getDeptCategoryName();
        String status = departmentPageDto.getStatus();

        //如果总行数为0,就直按结束
        if (totalRow == 0) {
            return null;
        }
        //计算总页数
        if (totalRow % 10 == 0) {
            totalPageNum = 1;
        } else {
            totalPageNum = (totalRow / 10) + 1;
        }
        //计算上一页和下一页
        if (preNum > 1) {
            preNum--;
        }
        if (nextNum < totalPageNum) {
            nextNum++;
        }
        //计算开始查询记录数
        beginNum = (pageNum - 1) * 10;

        //查询业务数据

        resultPageDto.setTotalRow(totalRow);
        resultPageDto.setTotalPageNum(totalPageNum);
        resultPageDto.setPageNum(pageNum);
        resultPageDto.setPreNum(preNum);
        resultPageDto.setNextNum(nextNum);
        resultPageDto.setBeginNum(beginNum);
        resultPageDto.setMaxPageNum(10);
        resultPageDto.setDocName(docName);
        resultPageDto.setDeptCategoryName(deptCategoryName);
        resultPageDto.setStatus(status);
        List<Register> registerList = registerMapper.selectDoctorDiagnosisAll(resultPageDto);
        list = registerList;
        resultPageDto.setList(list);
        return resultPageDto;
    }
}
