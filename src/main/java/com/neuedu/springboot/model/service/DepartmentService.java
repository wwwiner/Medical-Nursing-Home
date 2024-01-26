package com.neuedu.springboot.model.service;

import com.neuedu.springboot.model.bean.Department;
import com.neuedu.springboot.model.bean.DepartmentPageDto;
import com.neuedu.springboot.model.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:mwy
 * @Date 2023/2/1 23:56
 */
@Service
@Transactional
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> selectDepartmentList() {
        DepartmentPageDto resultPageDto = new DepartmentPageDto();
        return departmentMapper.selectDepartmentList_create();
    }

    public DepartmentPageDto selectCountFromDepartmentList(DepartmentPageDto departmentPageDto) {
        DepartmentPageDto resultPageDto = new DepartmentPageDto();

        int pageCount = departmentMapper.selectCountFromDepartmentList();
        int totalRow = pageCount; //总行数
        int totalPageNum = 0; //总页数
        int pageNum = departmentPageDto.getPageNum();
        int preNum = departmentPageDto.getPageNum(); // 上一页
        int nextNum = departmentPageDto.getPageNum(); //下一页
        int beginNum = 0; //开始查询记录数
        List<Department> list = null; //业务数据
        String deptName = departmentPageDto.getDeptName();
        String deptCategoryName = departmentPageDto.getDeptCategoryName();
        String deptType = departmentPageDto.getDeptType();

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
        resultPageDto.setDeptName(deptName);
        resultPageDto.setDeptCategoryName(deptCategoryName);
        resultPageDto.setDeptType(deptType);
        list = departmentMapper.selectDepartmentList(resultPageDto);
        resultPageDto.setList(list);
        return resultPageDto;
    }

    public List<Department> selectDepartmentClassification() {
        List<Department> list = departmentMapper.selectDepartmentClassification();
        //遍历去重
        List<Department> resultList = new ArrayList<>();
        String result = "";
        for (Department d : list) {
            String deptCategoryName = d.getDeptCategoryName();
            if (deptCategoryName != null) {
                if (!result.contains(deptCategoryName)) {
                    result = result + deptCategoryName + ",";
                }
            }
        }

        String[] deptCategoryName = result.split(",");
        for (int i = 0; i < deptCategoryName.length; i++) {
            Department department = new Department();
            department.setId(i);
            department.setDeptCategoryName(deptCategoryName[i]);
            resultList.add(department);
        }
        return resultList;
    }

    public List<Department> selectDepartmentType() {
        List<Department> list = departmentMapper.selectDepartmentType();
        //遍历去重
        List<Department> resultList = new ArrayList<>();
        String result = "";
        for (Department d : list) {
            String deptType = d.getDeptType();
            if (deptType != null) {
                if (!result.contains(deptType)) {
                    result = result + deptType + ",";
                }
            }
        }

        String[] deptType = result.split(",");
        for (int i = 0; i < deptType.length; i++) {
            Department department = new Department();
            department.setId(i);
            department.setDeptType(deptType[i]);
            resultList.add(department);
        }
        return resultList;
    }

    public void updateDeptartment(Department department) {
        department.setCreateDate(new Date());
        departmentMapper.updateDeptartment(department);
    }

    public String insertDeptartment(Department department) {
        department.setCreateDate(new Date());
        departmentMapper.insertDeptartment(department);
        return "SUCCESS";
    }

    public Department selectDepartmentbyId(Department department) {
        return departmentMapper.selectDepartmentbyId(department);
    }

    public void deleteDepartmentbyId(Department department) {
        departmentMapper.deleteDepartmentbyId(department);
    }

    public void removeDepartmentBatch(List<Department> departmentList) {
        departmentMapper.removeDepartmentBatch(departmentList);
    }

    public DepartmentPageDto selectDepartmentListAsPatient(DepartmentPageDto departmentPageDto) {
        DepartmentPageDto resultPageDto = new DepartmentPageDto();

        int pageCount = departmentMapper.selectCountFromDepartmentListAsPatient();
        int totalRow = pageCount; //总行数
        int totalPageNum = 0; //总页数
        int pageNum = departmentPageDto.getPageNum();
        int preNum = departmentPageDto.getPageNum(); // 上一页
        int nextNum = departmentPageDto.getPageNum(); //下一页
        int beginNum = 0; //开始查询记录数
        List<Department> list = null; //业务数据
        String deptName = departmentPageDto.getDeptName();
        String deptCategoryName = departmentPageDto.getDeptCategoryName();
        String deptType = departmentPageDto.getDeptType();

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
        resultPageDto.setDeptName(deptName);
        resultPageDto.setDeptCategoryName(deptCategoryName);
        list = departmentMapper.selectDepartmentListAsPatient(resultPageDto);
        resultPageDto.setList(list);
        return resultPageDto;
    }
}
