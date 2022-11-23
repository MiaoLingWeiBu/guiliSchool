package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.vo.OneSubject;
import com.atguigu.eduservice.service.EduSubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author JiangH
 * @since 2022-11-05
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("/addSubject")
    public R addSubject(MultipartFile file){
        eduSubjectService.saveSubject(file,eduSubjectService);
        return R.ok();
    }

    @ApiOperation(value = "课程树形列表")
    @GetMapping("/getAllSubject")
    public R showTree(){
        List<OneSubject> list = eduSubjectService.showTree();
        return R.ok().data("list",list);
    }

    @GetMapping("/getId/{id}")
    public R getId(@PathVariable String id){
        EduSubject eduSubject = eduSubjectService.getById(id);
        String parentId = eduSubject.getParentId();
        return R.ok().data("parentId",parentId);
    }
}

