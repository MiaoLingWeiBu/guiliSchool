package com.atguigu.eduservice.listener;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.SubjectData;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: JiangH
 * @Date: 2022/11/05/18:37
 * @Description:
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public EduSubjectService eduSubjectService;

    public SubjectExcelListener(EduSubjectService eduSubjectService){
        this.eduSubjectService=eduSubjectService;
    }
    public  SubjectExcelListener(){}

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData==null){
            throw new RuntimeException("没有数据");
        }
        EduSubject eduSubject = this.existOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        if(eduSubject==null){
            eduSubject=new EduSubject();
            eduSubject.setTitle(subjectData.getOneSubjectName());
            eduSubject.setParentId("0");
            eduSubjectService.save(eduSubject);
        }

        //获取一级分类的id值
        String parent_id = eduSubject.getId();
        //判断是否有耳机分类是否重复
        EduSubject existTwoSubject = this.existTwoSubject(eduSubjectService, subjectData.getTwoSubjectName(), parent_id);
        if (existTwoSubject==null){//没有相同的二级分类，进行添加
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(parent_id); //设置二级分类id值
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());//设置二级分类名
            eduSubjectService.save(existTwoSubject);//给数据库添加二级分类
        }

    }



    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    public EduSubject existOneSubject(EduSubjectService eduSubjectService,String title){
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",title);
        queryWrapper.eq("parent_id","0");
        EduSubject one = eduSubjectService.getOne(queryWrapper);
        return one;
    }

    private EduSubject existTwoSubject(EduSubjectService eduSubjectService, String twoSubjectName, String parent_id) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",twoSubjectName);
        queryWrapper.eq("parent_id",parent_id);
        EduSubject one = eduSubjectService.getOne(queryWrapper);
        return one;
    }
}
