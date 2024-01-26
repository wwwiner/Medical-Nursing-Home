package com.neuedu.springboot.model.service;

import com.neuedu.springboot.model.bean.*;
import com.neuedu.springboot.model.mapper.HospitalizationMapper;
import com.neuedu.springboot.model.mapper.SalaryMapper;
import com.neuedu.springboot.model.mapper.UserInfoMapper;
import com.neuedu.springboot.model.mapper.WardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

/**
 * @Author:mwy
 * @Date 2023/2/17 17:43
 */

@Service
@Transactional
public class WardService {
    @Autowired
    private WardMapper wardMapper;
    @Autowired
    private HospitalizationMapper hospitalizationMapper;
    @Autowired
    private SalaryMapper salaryMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    public List<Ward> selectWardType() {
        List<Ward> list = wardMapper.selectWardType();
        //遍历去重
        List<Ward> resultList = new ArrayList<>();
        String result = "";
        for (Ward w : list) {
            String wardType = w.getWardType();
            if (wardType != null) {
                if (!result.contains(wardType)) {
                    result = result + wardType + ",";
                }
            }
        }

        String[] wardType = result.split(",");
        for (int i = 0; i < wardType.length; i++) {
            Ward ward = new Ward();
            ward.setId(i);
            ward.setWardType(wardType[i]);
            resultList.add(ward);
        }
        return resultList;
    }

    public List<Ward> selectWardNameByWardType(Ward ward) {
        return wardMapper.selectWardNameByWardType(ward);
    }

    public Ward selectWardRemainingProportion() {
        Ward ward = new Ward();
        List<Ward> resultList = new ArrayList();

        ward.setWardType("普通护理房");
        //查询空病房
        int nullWard_Pt = wardMapper.nullWardCount(ward);
        //查询病房总数
        int notNullWard_Pt = wardMapper.notNullWardCount(ward);

        float result_Pt = (nullWard_Pt / notNullWard_Pt) * 100;
        ward.setPT((float)((nullWard_Pt*10 / (notNullWard_Pt/10))));
        ward.setPTCount(nullWard_Pt);

        ward.setWardType("高级护理房");
        //查询空病房
        int nullWard_GJ = wardMapper.nullWardCount(ward);
        //查询病房总数
        int notNullWard_GJ = wardMapper.notNullWardCount(ward);

        float result_GJ = (nullWard_GJ / notNullWard_GJ) * 100;
        ward.setGJ((float)((nullWard_GJ*10 / (notNullWard_GJ/10))));
        ward.setGJCount(nullWard_GJ);

        ward.setWardType("临终护理房");
        //查询空病房
        int nullWard_LZ = wardMapper.nullWardCount(ward);
        //查询病房总数
        int notNullWard_LZ = wardMapper.notNullWardCount(ward);

        float result_LZ = (nullWard_LZ / notNullWard_LZ) * 100;
        ward.setLZ((float)((nullWard_LZ*10 / (notNullWard_LZ/10))));
        ward.setLZCount(nullWard_LZ);
        ward.setWardType("重症监护室");
        //查询空病房
        int nullWard_ICU = wardMapper.nullWardCount(ward);
        //查询病房总数
        int notNullWard_ICU = wardMapper.notNullWardCount(ward);

        float result_ICU = (nullWard_ICU / notNullWard_ICU) * 100;
        ward.setICU((float)((nullWard_ICU*10 / (notNullWard_ICU/10))));
        ward.setICUCount(nullWard_ICU);
        return ward;
    }

    public String inWard(Ward ward) {
        ward.setInTime(new Date());
        //扣钱
        //查询用户余额，若余额足够，则正常缴费，若余额不足，则返回 “余额不足”
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(ward.getUserAccount());
        int resultMoney = ward.getWardPrice()*Integer.parseInt(ward.getContinuedTime());
        userInfo.setMoney(resultMoney);
        int balance = userInfoMapper.selectBalanceByUserName(userInfo);
        if (balance >= resultMoney) {
            userInfoMapper.kouQian(userInfo);
            //用户缴费金额入到医院总部
            UserInfo hiser = new UserInfo();
            hiser.setUserName("his");
            hiser.setMoney(resultMoney);
            userInfoMapper.jiaQian(hiser);
            //入病房表
            ward.setWardPrice(resultMoney);
            wardMapper.inWard(ward);

            //入住院登记表
            Hospitalization hospitalization = new Hospitalization();
            hospitalization.setId(ward.getHosId());
            hospitalization.setUserAccount(ward.getUserAccount());
            hospitalization.setPayMoney(ward.getWardPrice());
            hospitalization.setInTime(new Date());
            hospitalization.setContinuedTime(Integer.parseInt(ward.getContinuedTime()));
            hospitalization.setIsIn(1);
            hospitalization.setWardName(ward.getWardName());
            hospitalization.setStatus("住院中");
            hospitalizationMapper.inWard(hospitalization);
//SSM  Sping+SpingBoot+Mybats/Hibni/JPA
            //入转账表
            Salary salary = new Salary();
            //查询转账总金额
            salary.setTransferAccount(ward.getUserAccount());
            List<Salary> list = salaryMapper.selectAllTransferMoney_Transfer(salary);
            //所有转账的和
            int allMoney = 0;
            for (Salary s : list) {
                int money = s.getTransferMoney();
                allMoney = allMoney + money;
            }
            salary.setUserAccount("his");
            salary.setUserName("医院总部");
            salary.setTransferName(ward.getUserName());
            salary.setTransferTime(new Date());
            salary.setTransferMoney(ward.getWardPrice());
            salary.setAllTransferMoney(allMoney + resultMoney);
            salary.setTransferType("住院缴费");
            salaryMapper.newSalary(salary);
            return "SUCCESS";
        } else {
            return "余额不足，请即使充值！";
        }
    }

    public String outWardById(Ward ward) {
        //将住院登记表转状态设置为已出院
        wardMapper.outWardById(ward);
        //清空病房表
        wardMapper.outWardClearById(ward);
        return "SUCCESS";
    }

    public DepartmentPageDto selectWardList(DepartmentPageDto departmentPageDto) {
        DepartmentPageDto resultPageDto = new DepartmentPageDto();

        int pageCount = wardMapper.selectCountFromWardList();
        int totalRow = pageCount; //总行数
        int totalPageNum = 0; //总页数
        int pageNum = departmentPageDto.getPageNum();
        int preNum = departmentPageDto.getPageNum(); // 上一页
        int nextNum = departmentPageDto.getPageNum(); //下一页
        int beginNum = 0; //开始查询记录数
        List<Ward> list = null; //业务数据
        String userName = departmentPageDto.getUserName();
        String wardType = departmentPageDto.getWardType();

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
        resultPageDto.setUserName(userName);
        resultPageDto.setWardType(wardType);
        list = wardMapper.selectWardList(resultPageDto);
        resultPageDto.setList(list);
        return resultPageDto;
    }
}
