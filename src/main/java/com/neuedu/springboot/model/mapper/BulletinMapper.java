package com.neuedu.springboot.model.mapper;

import com.neuedu.springboot.model.bean.Bulletin;
import com.neuedu.springboot.model.bean.DepartmentPageDto;
import com.neuedu.springboot.model.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:mwy
 * @Date 2023/2/1 23:59
 */
@Mapper
public interface BulletinMapper {

    public List<Bulletin> selectBulletinList(DepartmentPageDto resultPageDto);

    public int selectCountFromBulletinList();

    public List<Bulletin> selectBullType();

    public void updateBulletinById(Bulletin bulletin);

    public void insertBulletin(Bulletin bulletin);

    public void deleteBulletinById(Bulletin bulletin);

    public void removeBulletinBatch(List<Bulletin> bulletinList);

    List<Bulletin> selectBullTitleByBullType();

}
