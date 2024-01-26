package com.neuedu.springboot.exception;


import com.neuedu.springboot.result.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 统一异常处理
 *
 * @author mwy
 * @date 2023/04/18 ControllerAdvice注解的含义是当异常抛到controller层时会拦截下来
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 使用ExceptionHandler注解声明处理Exception异常
     *
     * @param e e
     * @return {@link R}
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public R exception(Exception e) {
        // 控制台打印异常
        e.printStackTrace();
        // 返回错误格式信息
        return R.error();
    }
    /**
     * 使用ExceptionHandler注解声明处理TestException异常
     *
     * @param e e
     * @return {@link R}
     */
    @ResponseBody
    @ExceptionHandler(TestException.class)
    public R exception(TestException e) {
        // 控制台打印异常
        e.printStackTrace();
        // 返回错误格式信息
        return R.error().message(e.getMessage()).code(e.getCode());
    }

}

