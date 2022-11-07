package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.SubjectData;
import com.atguigu.eduservice.entity.vo.OneSubject;
import com.atguigu.eduservice.entity.vo.TwoSubject;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author JiangH
 * @since 2022-11-05
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OneSubject> showTree() {
        //查询所有一级分类
        QueryWrapper<EduSubject> oneQueryWrapper = new QueryWrapper<>();
        oneQueryWrapper.eq("parent_id","0");
        List<EduSubject> oneList = baseMapper.selectList(oneQueryWrapper);

        //查询所有二级分类
        QueryWrapper<EduSubject> twoQueryWrapper = new QueryWrapper<>();
        twoQueryWrapper.ne("parent_id","0");
        List<EduSubject> twoList = baseMapper.selectList(twoQueryWrapper);

        //封装一级分类数据
        List<OneSubject> list1 = new ArrayList<>();
        for(int i=0;i<oneList.size();i++){
            OneSubject oneSubject = new OneSubject();
            EduSubject oneEduSubject = oneList.get(i);
            BeanUtils.copyProperties(oneEduSubject,oneSubject);

            list1.add(oneSubject);

            //封装二级分类数据
            List<TwoSubject> list2 = new ArrayList<>();
            for(int j=0;j<twoList.size();j++){
                EduSubject twoEduSubject = twoList.get(j);
                if(twoEduSubject.getParentId().equals(oneSubject.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(twoEduSubject,twoSubject);
                    list2.add(twoSubject);
                }
            }
            oneSubject.setChildren(list2);

        }
        return list1;
    }
}
