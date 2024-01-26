package com.neuedu.springboot.controller;

//import com.mysql.jdbc.StringUtils;
import com.neuedu.springboot.model.bean.DepartmentPageDto;
import com.neuedu.springboot.model.bean.Hospitalization;
import com.neuedu.springboot.model.bean.Ward;
import com.neuedu.springboot.model.service.HospitalizationService;
import com.neuedu.springboot.model.service.WardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:mwy
 * @Date 2023/2/17 17:25
 */

@CrossOrigin
@RestController
@RequestMapping("/ward")
@Api("房间管理")
public class WardController {
    @Autowired
    private WardService wardService;

    //查询病房类型
    @ApiOperation(value = "查询房间" , notes="查询房间类型")
    @RequestMapping("/selectWardType")
    public List<Ward> selectWardType() {
        return wardService.selectWardType();
    }

    //查询病房名称（二级级联）
    @ApiOperation(value = "查询房间" , notes="根据房间类型查询房间名称")
    @RequestMapping("/selectWardNameByWardType")
    public List<Ward> selectWardNameByWardType(@RequestBody Ward ward) {
        return wardService.selectWardNameByWardType(ward);
    }

    //办理住院
    @ApiOperation(value = "办理入住" , notes="")
    @RequestMapping("/inWard")
    public String inWard(@RequestBody Ward ward) {
        return wardService.inWard(ward);
    }

    //出院
    @ApiOperation(value = "办理出院" , notes="")
    @RequestMapping("/outWardById")
    public String outWardById(@RequestBody Ward ward) {
        return wardService.outWardById(ward);
    }

    //查询待住院列表请求(分页有参)
    @ApiOperation(value = "查询待住院请求" , notes="")
    @RequestMapping("/selectWardList")
    public DepartmentPageDto selectWardList(@RequestBody DepartmentPageDto departmentPageDto) {
        return wardService.selectWardList(departmentPageDto);
    }

    //查询病房剩余占比（数据可视化）
    @ApiOperation(value = "查询房间占比" , notes="可视化展示房间入住情况")
    @RequestMapping("/selectWardRemainingProportion")
    public Ward selectWardRemainingProportion() {
        return wardService.selectWardRemainingProportion();
    }

    //输入两个字符串，返回最大字串
    @RequestMapping("/testDiaomao")
    public String testDiaomao(String a, String b) {
        String longStr;
        String shortStr;
        if (a.length() >= b.length()) {
            longStr = a;
            shortStr = b;
        } else {
            longStr = b;
            shortStr = a;
        }
        String resultLongStr = null;
        List<String> resultList = new ArrayList();
        int length = longStr.length();
        for (int i = length; i > 1; i--) {
            for (int j = 0; j < longStr.length(); j++) {
                if (j >= i) {
                    continue;
                } else {
                    String resultStr = longStr.substring(j, i);
                    if (shortStr.contains(resultStr)) {
                        resultList.add(resultStr);
                    }
                }
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = length; j > 1; j--) {
                if (i >= j) {
                    continue;
                } else {
                    String resultStr = longStr.substring(i, j);
                    if (shortStr.contains(resultStr)) {
                        resultList.add(resultStr);
                        System.out.println(String.valueOf(i) + String.valueOf(j));
                    }
                }
            }
        }
        if (resultList.size() > 0) {

            String[] stringArr = new String[resultList.size()];
            resultList.toArray(stringArr);

            String temp;//定义一个临时变量
            for (int i = 0; i < stringArr.length - 1; i++) {//冒泡趟数
                for (int j = 0; j < stringArr.length - i - 1; j++) {
                    if (stringArr[j + 1].length() < stringArr[j].length()) {
                        temp = stringArr[j];
                        stringArr[j] = stringArr[j + 1];
                        stringArr[j + 1] = temp;
                    }
                }
            }
            return stringArr[resultList.size() - 1];
        } else {
            return "没有相同字串";
        }
    }
}
