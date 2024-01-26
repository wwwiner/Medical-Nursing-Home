package com.neuedu.springboot.model.service;

import com.neuedu.springboot.model.bean.Department;
import com.neuedu.springboot.model.bean.DepartmentPageDto;
import com.neuedu.springboot.model.bean.Salary;
import com.neuedu.springboot.model.bean.UserInfo;
import com.neuedu.springboot.model.mapper.SalaryMapper;
import com.neuedu.springboot.model.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;;

import java.util.Date;
import java.util.List;

/**
 * @Author:mwy
 * @Date 2023/2/15 9:30
 */


@Service
@Transactional
public class SalaryService {
    @Autowired
    private SalaryMapper salaryMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    public String newSalary(Salary salary) {
        salary.setTransferTime(new Date());
        //查询转账总金额
        List<Salary> list = salaryMapper.selectAllTransferMoney(salary);
        //所有转账的和
        int allMoney = 0;
        for (Salary s : list) {
            int money = s.getTransferMoney();
            allMoney = allMoney + money;
        }
        int transferMoney = salary.getTransferMoney();
        salary.setAllTransferMoney(allMoney + transferMoney);
        salary.setTransferType("工资发放");
        //查询医院总部余额
        UserInfo hisInfo = new UserInfo();
        hisInfo.setUserName("his");
        UserInfo his = userInfoMapper.selectMoneyByUserName(hisInfo);
        int hisMoney = his.getMoney();
        //如果余额充足，则扣除
        if (hisMoney >= transferMoney) {
            //录入转账记录
            salaryMapper.newSalary(salary);
            //医院总部扣钱
            hisInfo.setMoney(transferMoney);
            userInfoMapper.kouQian(hisInfo);
            //收款人加钱
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName(salary.getUserAccount());
            userInfo.setMoney(transferMoney);
            userInfoMapper.jiaQian(userInfo);
            return "SUCCESS";
        } else {
            return "医院总部余额不足,无法转账";
        }
    }

    public DepartmentPageDto selectAll(DepartmentPageDto departmentPageDto) {
        DepartmentPageDto resultPageDto = new DepartmentPageDto();
        Salary s = new Salary();
        s.setUserAccount(departmentPageDto.getUserAccount());
        int pageCount = salaryMapper.selectCountFromSalary(s);
        int totalRow = pageCount; //总行数
        int totalPageNum = 0; //总页数
        int pageNum = departmentPageDto.getPageNum();
        int preNum = departmentPageDto.getPageNum(); // 上一页
        int nextNum = departmentPageDto.getPageNum(); //下一页
        int beginNum = 0; //开始查询记录数
        List<Salary> list = null; //业务数据
        String userAccount = departmentPageDto.getUserAccount();

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
        resultPageDto.setUserAccount(userAccount);
        list = salaryMapper.selectAll(resultPageDto);
        resultPageDto.setList(list);
        return resultPageDto;
    }

}
