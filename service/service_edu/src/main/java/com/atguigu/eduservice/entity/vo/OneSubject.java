package com.atguigu.eduservice.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: JiangH
 * @Date: 2022/11/07/16:19
 * @Description:
 */
@Data
public class OneSubject {
    private String id;
    private String title;

    private List<TwoSubject> children=new ArrayList<>();
}
