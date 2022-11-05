package com.atguigu.eduservice.entity.test;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: JiangH
 * @Date: 2022/11/05/17:08
 * @Description:
 */
public class TestEasyExcel {
    public static void main(String[] args) {
        //实现excel写操作
        //1、设置写入文件夹地址和excel文件名称
        String fileName="C:\\Users\\Lenovo\\Desktop\\DemoData.xlsx";

        //调用easyExcel里面的方法实现写操作
        //参数1：文件名称
        //参数2：对应实体类
//        EasyExcel.write(fileName,DemoData.class).sheet("studentList").doWrite(list());
        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();
    }

    public static List<DemoData> list(){
        ArrayList<DemoData> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            DemoData demoData = new DemoData();
            demoData.setSno(i+1);
            demoData.setSname("student"+(i+1));
            list.add(demoData);
        }
        return list;
    }
}
