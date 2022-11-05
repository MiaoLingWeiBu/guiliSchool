package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: JiangH
 * @Date: 2022/11/01/20:44
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/eduService/user")
public class EduLoginController {

    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","edu-token");
    }

    @GetMapping("/info")
    public R info(){
        return R.ok().data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
