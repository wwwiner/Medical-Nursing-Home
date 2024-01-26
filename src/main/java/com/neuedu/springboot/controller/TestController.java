package com.neuedu.springboot.controller;

/**
 * Description:
 * Param:
 * return:
 * Author:
 * Date:2023/4/20
 */

import com.neuedu.springboot.exception.TestException;
import com.neuedu.springboot.result.R;
import com.neuedu.springboot.result.ResultCodeEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试控制器
 *
 * @author 爷爷的茶七里香
 * @date 2022/05/30
 */
@RestController
@RequestMapping("testR")
public class TestController {

    /**
     * @return {@link R}
     */
    @GetMapping("ok")
    public R testOk() {
        try {
            int a =1/0;
        }catch (Exception e){
            throw new TestException(ResultCodeEnum.CAU_ERROR);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("name", "李太白");
        return R.ok().data(data);
    }
}

