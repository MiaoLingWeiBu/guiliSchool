package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author JiangH
 * @since 2022-10-28
 */
@CrossOrigin //解决跨域问题
@Api(description="讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("{id}")
    public R getTeacherByid(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);

        return R.ok().data("teacher",eduTeacher);
    }

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("all")
    public R findAll(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("list",list);
    }

    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R deleteById(@PathVariable String id){
        boolean b = eduTeacherService.removeById(id);
        if(b){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("删除失败");
        }
    }

    @GetMapping("/findPage/{page}/{limit}")
    public R findByPage(@ApiParam(name = "page", value = "当前页码", required = true)@PathVariable Long page,
                        @ApiParam(name = "limit", value = "每页记录数", required = true)@PathVariable Long limit){
        Page<EduTeacher> pages = new Page<>(page,limit);
        eduTeacherService.page(pages,null);
        long total = pages.getTotal();
        List<EduTeacher> records = pages.getRecords();
        return R.ok().data("total",total).data("data",records);
    }

    @PostMapping("/pageTeacherCondition/{page}/{limit}")
    public R pageTeacherCondition(@ApiParam(name = "page",value = "当前页码",required = true)@PathVariable Long page,
                                  @ApiParam(name = "limit", value = "每页记录数",required = true)@PathVariable Long limit,
                                  @ApiParam(name = "teacherQuery",value = "查询条件",required = false)@RequestBody(required = false) EduTeacher eduTeacher){
        Page<EduTeacher> pages = new Page<>(page,limit);
        eduTeacherService.pageQuery(pages,eduTeacher);
        return R.ok().data("list",pages.getRecords());
    }

    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.save(eduTeacher);
        if(b){
            return R.ok().message("添加成功");
        }else{
            return R.error().message("添加失败");
        }
    }

    @PutMapping("/updataTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.updateById(eduTeacher);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

}

