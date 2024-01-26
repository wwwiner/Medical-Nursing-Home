package com.neuedu.springboot.model.mapper;

import com.neuedu.springboot.model.bean.Department;
import com.neuedu.springboot.model.bean.DepartmentPageDto;
import com.neuedu.springboot.model.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:mwy
 * @Date 2023/2/1 23:59
 */
@Mapper
public interface DepartmentMapper {
    public List<Department> selectDepartmentList_create();

    public List<Department> selectDepartmentList(DepartmentPageDto resultPageDto);

    public int selectCountFromDepartmentList();

    public List<Department> selectDepartmentClassification();

    public List<Department> selectDepartmentType();

    public void updateDeptartment(Department department);

    public void insertDeptartment(Department department);

    public Department selectDepartmentbyId(Department department);

    public void deleteDepartmentbyId(Department department);

    public void removeDepartmentBatch(List<Department> departmentList);

    public int selectCountFromDepartmentListAsPatient();

    public List<Department> selectDepartmentListAsPatient(DepartmentPageDto resultPageDto);
}
