package com.atguigu.exceptionHandler;

import com.atguigu.commonutils.R;
import com.atguigu.myException.GuiguException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: JiangH
 * @Date: 2022/11/01/13:14
 * @Description:
 */
//@ControllerAdvice
public class GlobalExceptionHandler {

    //指定出现什么异常会执行这个方法
    @ExceptionHandler(Exception.class)
    //因为他不在Controller中。没有@RestController，所以数据不会返回，需要加@ResponeseBody返回数据
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理。。。");
    }

    //指定自定义异常类
    @ExceptionHandler(GuiguException.class)
    @ResponseBody
    public R error(GuiguException e){
        e.printStackTrace();
        return R.error().message(e.getMsg()).code(e.getCode());
    }

}
