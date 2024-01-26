package com.neuedu.springboot.model.mapper;

import com.neuedu.springboot.model.bean.DepartmentPageDto;
import com.neuedu.springboot.model.bean.Ward;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author:mwy
 * @Date 2023/2/17 17:44
 */

@Mapper
public interface WardMapper {

    public List<Ward> selectWardType();

    public List<Ward> selectWardNameByWardType(Ward ward);

    public List<Map<String,Object>> selectWardRemainingProportion();

    public int nullWardCount(Ward ward);

    public int notNullWardCount(Ward ward);

    public void inWard(Ward ward);

    public void outWardById(Ward ward);

    public void outWardClearById(Ward ward);

    public int selectCountFromWardList();

    public List<Ward> selectWardList(DepartmentPageDto resultPageDto);
}
