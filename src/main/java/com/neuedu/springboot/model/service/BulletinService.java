package com.neuedu.springboot.model.service;

import com.neuedu.springboot.model.bean.Bulletin;
import com.neuedu.springboot.model.bean.DepartmentPageDto;
import com.neuedu.springboot.model.mapper.BulletinMapper;
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
public class BulletinService {
    @Autowired
    private BulletinMapper bulletinMapper;

    public DepartmentPageDto selectBulletinList(DepartmentPageDto departmentPageDto) {
        DepartmentPageDto resultPageDto = new DepartmentPageDto();

        int pageCount = bulletinMapper.selectCountFromBulletinList();
        int totalRow = pageCount; //总行数
        int totalPageNum = 0; //总页数
        int pageNum = departmentPageDto.getPageNum();
        int preNum = departmentPageDto.getPageNum(); // 上一页
        int nextNum = departmentPageDto.getPageNum(); //下一页
        int beginNum = 0; //开始查询记录数
        List<Bulletin> list = null; //业务数据
        String bullTitle = departmentPageDto.getBullTitle();
        String bullType = departmentPageDto.getBullType();

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
        resultPageDto.setBullTitle(bullTitle);
        resultPageDto.setBullType(bullType);
        list = bulletinMapper.selectBulletinList(resultPageDto);
        resultPageDto.setList(list);
        return resultPageDto;
    }

    public List<Bulletin> selectBullType(){
        List<Bulletin> list = bulletinMapper.selectBullType();
        //遍历去重
        List<Bulletin> resultList = new ArrayList<>();
        String result = "";
        for (Bulletin bulletin : list) {
            String bullType = bulletin.getBullType();
            if (bullType != null) {
                if (!result.contains(bullType)) {
                    result = result + bullType + ",";
                }
            }
        }

        String[] bullType = result.split(",");
        for (int i = 0; i < bullType.length; i++) {
            Bulletin bulletin = new Bulletin();
            bulletin.setId(i);
            bulletin.setBullType(bullType[i]);
            resultList.add(bulletin);
        }
        return resultList;
    }

    public String updateBulletinById(Bulletin bulletin){
        bulletinMapper.updateBulletinById(bulletin);
        return "SUCCESS";
    }

    public String deleteBulletinById(Bulletin bulletin){
        bulletinMapper.deleteBulletinById(bulletin);
        return "SUCCESS";
    }

    public String insertBulletin(Bulletin bulletin){
        bulletinMapper.insertBulletin(bulletin);
        return "SUCCESS";
    }

    public String removeBulletinBatch(List<Bulletin> bulletin){
        bulletinMapper.removeBulletinBatch(bulletin);
        return "SUCCESS";
    }

    public List<Bulletin> selectBullTitleByBullType(Bulletin bulletin) {
        return bulletinMapper.selectBullTitleByBullType();
    }
}
