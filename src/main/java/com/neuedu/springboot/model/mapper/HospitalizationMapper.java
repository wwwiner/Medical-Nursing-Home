package com.neuedu.springboot.model.mapper;

import com.neuedu.springboot.model.bean.DepartmentPageDto;
import com.neuedu.springboot.model.bean.Hospitalization;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:mwy
 * @Date 2023/2/16 17:36
 */
@Mapper
public interface HospitalizationMapper {
    public void hosByDoc(Hospitalization hospitalization);

    public int selectAllCount();

    public List<Hospitalization> selectHospitalizationListByUserAccount(DepartmentPageDto departmentPageDto);

    public void notInWardById(Hospitalization hospitalization);

    public Hospitalization selectHospitalizationById(Hospitalization hospitalization);

    public void inWard(Hospitalization hospitalization);
}
