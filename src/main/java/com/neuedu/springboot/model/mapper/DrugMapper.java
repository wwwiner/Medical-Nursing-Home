package com.neuedu.springboot.model.mapper;

import com.neuedu.springboot.model.bean.Department;
import com.neuedu.springboot.model.bean.DepartmentPageDto;
import com.neuedu.springboot.model.bean.Drug;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author:mwy
 * @Date 2023/2/4 15:09
 */
@Mapper
public interface DrugMapper {

    public int selectCountFromDrugList();

    public List<Drug> selectDrugList(DepartmentPageDto departmentPageDto);

    public List<Drug> selectDrugType();

    public void updateDrugById(Drug drug);

    public void deleteDrugById(Drug drug);

    public void insertDrug(Drug drug);

    public void removeDrugBatch(List<Drug> drug);

    public List<Drug> selectDrugNameByDrugType(Drug drug);

    public int selectDrugPrice(String drugName);

    public void drugCountMinusOne(Drug drug);
}
