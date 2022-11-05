package com.atguigu.eduservice.entity.test;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: JiangH
 * @Date: 2022/11/05/17:04
 * @Description:
 */
//设置表头和添加的数据字段
@Data
@ToString
public class DemoData {

    //学生序号
    //设置excel表头名称
    @ExcelProperty(value = "学生序号",index = 0)
    private Integer sno;

    //学生名称
    //设置excel表头名称
    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;

}

