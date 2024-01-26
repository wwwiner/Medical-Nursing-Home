package com.neuedu.springboot.model.service;

import com.neuedu.springboot.model.bean.Department;
import com.neuedu.springboot.model.bean.DepartmentPageDto;
import com.neuedu.springboot.model.bean.Drug;
import com.neuedu.springboot.model.mapper.DepartmentMapper;
import com.neuedu.springboot.model.mapper.DrugMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:mwy
 * @Date 2023/2/4 15:04
 */
@Service
@Transactional
public class DrugService {
    @Autowired
    private DrugMapper drugMapper;

    public DepartmentPageDto selectDrugList(DepartmentPageDto departmentPageDto) {
        DepartmentPageDto resultPageDto = new DepartmentPageDto();

        int pageCount = drugMapper.selectCountFromDrugList();
        int totalRow = pageCount; //总行数
        int totalPageNum = 0; //总页数
        int pageNum = departmentPageDto.getPageNum();
        int preNum = departmentPageDto.getPageNum(); // 上一页
        int nextNum = departmentPageDto.getPageNum(); //下一页
        int beginNum = 0; //开始查询记录数
        List<Drug> list = null; //业务数据
        String drugName = departmentPageDto.getDrugName();
        String drugType = departmentPageDto.getDrugType();

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
        resultPageDto.setDrugName(drugName);
        resultPageDto.setDrugType(drugType);
        list = drugMapper.selectDrugList(resultPageDto);
        resultPageDto.setList(list);
        return resultPageDto;
    }

    public List<Drug> selectDrugType(){
        List<Drug> list = drugMapper.selectDrugType();
        //遍历去重
        List<Drug> resultList = new ArrayList<>();
        String result = "";
        for (Drug drug : list) {
            String drugType = drug.getDrugType();
            if (drugType != null) {
                if (!result.contains(drugType)) {
                    result = result + drugType + ",";
                }
            }
        }

        String[] drugType = result.split(",");
        for (int i = 0; i < drugType.length; i++) {
            Drug drug = new Drug();
            drug.setId(i);
            drug.setDrugType(drugType[i]);
            resultList.add(drug);
        }
        return resultList;
    }

    public String updateDrugById(Drug drug){
        drugMapper.updateDrugById(drug);
        return "SUCCESS";
    }

    public String deleteDrugById(Drug drug){
        drugMapper.deleteDrugById(drug);
        return "SUCCESS";
    }

    public String insertDrug(Drug drug){
        drugMapper.insertDrug(drug);
        return "SUCCESS";
    }

    public String removeDrugBatch(List<Drug> drug){
        drugMapper.removeDrugBatch(drug);
        return "SUCCESS";
    }

    public List<Drug> selectDrugNameByDrugType(Drug drug) {
        return drugMapper.selectDrugNameByDrugType(drug);
    }
}
