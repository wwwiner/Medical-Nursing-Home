package com.neuedu.springboot.model.mapper;

import com.neuedu.springboot.model.bean.DepartmentPageDto;
import com.neuedu.springboot.model.bean.Salary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:mwy
 * @Date 2023/2/15 9:37
 */
@Mapper
public interface SalaryMapper {

    public void newSalary(Salary salary);

    public List<Salary> selectAllTransferMoney(Salary salary);

    public List<Salary> selectAllTransferMoney_Transfer(Salary salary);

    public int selectCountFromSalary(Salary salary);

    public List<Salary> selectAll(DepartmentPageDto departmentPageDto);

}
