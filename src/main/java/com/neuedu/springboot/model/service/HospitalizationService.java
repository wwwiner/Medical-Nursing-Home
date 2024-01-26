package com.neuedu.springboot.model.service;

import com.neuedu.springboot.model.bean.DepartmentPageDto;
import com.neuedu.springboot.model.bean.Hospitalization;
import com.neuedu.springboot.model.bean.Register;
import com.neuedu.springboot.model.bean.UserInfo;
import com.neuedu.springboot.model.mapper.HospitalizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author:mwy
 * @Date 2023/2/16 17:33
 */

@Service
@Transactional
public class HospitalizationService {
    @Autowired
    private HospitalizationMapper hospitalizationMapper;

    public String hosByDoc(Hospitalization hospitalization) {
        hospitalizationMapper.hosByDoc(hospitalization);
        return "SUCCESS";
    }

    public DepartmentPageDto selectHospitalizationListByUserAccount(DepartmentPageDto departmentPageDto) {
        DepartmentPageDto resultPageDto = new DepartmentPageDto();

        int pageCount= hospitalizationMapper.selectAllCount();
        int totalRow = pageCount; //总行数
        int totalPageNum = 0; //总页数
        int pageNum = departmentPageDto.getPageNum();
        int preNum = departmentPageDto.getPageNum(); // 上一页
        int nextNum = departmentPageDto.getPageNum(); //下一页
        int beginNum = 0; //开始查询记录数
        List<Hospitalization> list = null; //业务数据
        String status = departmentPageDto.getStatus();
        String deptCategoryName = departmentPageDto.getDeptCategoryName();
        String docName = departmentPageDto.getDocName();
        String userAccount = departmentPageDto.getUserAccount();
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
        resultPageDto.setStatus(status);
        resultPageDto.setDeptCategoryName(deptCategoryName);
        resultPageDto.setDocName(docName);
        resultPageDto.setUserAccount(userAccount);
        List<Hospitalization> registerList = hospitalizationMapper.selectHospitalizationListByUserAccount(resultPageDto);
        list = registerList;
        resultPageDto.setList(list);
        return resultPageDto;
    }

    public String notInWardById(Hospitalization hospitalization) {
        hospitalizationMapper.notInWardById(hospitalization);
        return "SUCCESS";
    }

    public Hospitalization selectHospitalizationById(Hospitalization hospitalization) {
        return hospitalizationMapper.selectHospitalizationById(hospitalization);
    }

}
