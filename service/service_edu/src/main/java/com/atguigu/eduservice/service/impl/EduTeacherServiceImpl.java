package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.mapper.EduTeacherMapper;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author JiangH
 * @since 2022-10-28
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {


    @Override
    public void pageQuery(Page<EduTeacher> pages, EduTeacher eduTeacher) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(eduTeacher.getName())){
            queryWrapper.like("name",eduTeacher.getName());
        }
        if(!StringUtils.isEmpty(eduTeacher.getLevel())){
            queryWrapper.eq("level",eduTeacher.getLevel());
        }
        if(!StringUtils.isEmpty(eduTeacher.getGmtCreate())){
            queryWrapper.ge("gmt_create",eduTeacher.getGmtCreate());
        }
        if(!StringUtils.isEmpty(eduTeacher.getGmtModified())){
            queryWrapper.le("gmt_modified",eduTeacher.getGmtModified());
        }

        baseMapper.selectPage(pages,queryWrapper);

    }
}
