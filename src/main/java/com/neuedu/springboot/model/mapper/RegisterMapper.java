package com.neuedu.springboot.model.mapper;

import com.neuedu.springboot.model.bean.DepartmentPageDto;
import com.neuedu.springboot.model.bean.Register;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author:mwy
 * @Date 2023/2/4 18:57
 */

@Mapper
public interface RegisterMapper {

    public void register(Register register);

    public List<Register> selectPatientRegisterByUserName(DepartmentPageDto departmentPageDto);

    public void deletePatientRegisterById(Register register);

    public List<Register> selectDoctorDiagnosisByDocAccount(Register register);

    public void addDoctorInformationById(Register register);

    public List<Register> selectPatientRegisterById(Register register);

    public void diagnosisIsOk(Register register);

    public void setStatusPayOk(Register register);

    public List<Register> selectDoctorDiagnosisAll(DepartmentPageDto departmentPageDto);

    public int selectAllCount();

    public int selectAllCountByUserName(DepartmentPageDto departmentPageDto);
}
