package com.example.stu.service;

import com.alibaba.fastjson.JSON;
import com.example.stu.dao.StuDao;
import com.example.stu.pojo.PageBean;
import com.example.stu.pojo.Student;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StuService {

    private StuDao stuDao = new StuDao();


    public String addStu(Student student){
        return stuDao.addStu(student);
    }

    public String delStu(Integer id){
        return stuDao.deleteStu(id);
    }

    public String updateStu(Student student){
        return stuDao.updateStu(student);
    }

    public Student getStuById(Integer id) throws ParseException {
        return stuDao.getStuById(id);
    }

    public  PageBean<Student> getStuByLimit(String limit,String page) throws ParseException {
        List<Student> list = stuDao.getAllStu();
        Collections.sort(list, new Comparator<Student>() {

            @Override
            public int compare(Student o1, Student o2) {
                int i = o2.getAvgscore() - o1.getAvgscore();
                if(i == 0){
                    return o1.getAvgscore() - o2.getAvgscore();
                }
                return i;
            }
        });
        List<Student> listStu = new ArrayList<>();
        int totalpage;
        if(list.size()%Integer.parseInt(limit.trim())>0){
            totalpage = list.size()/Integer.parseInt(limit)+1;
        }else {
            totalpage = list.size()/Integer.parseInt(limit);
        }
        if(totalpage>(Integer.parseInt(page))){
            for(int i=Integer.parseInt(limit)*(Integer.parseInt(page)-1);i<Integer.parseInt(page)*Integer.parseInt(limit);i++){
                listStu.add(list.get(i));
            }
        }else {
            for(int i=Integer.parseInt(limit)*(Integer.parseInt(page)-1);i<list.size();i++){
                listStu.add(list.get(i));
            }
        }

        System.out.println(JSON.toJSONString(listStu)+"   -- "+list.size()+" --   "+Integer.parseInt(limit)*(Integer.parseInt(page)-1));

        PageBean<Student> pageBean = new PageBean<>();
        pageBean.setPageList(listStu);
        pageBean.setPageSize(Integer.parseInt(limit));
        pageBean.setCurrentPage(Integer.parseInt(page));
        pageBean.setTotalPage(totalpage);
        return pageBean;
    }

    public void close(){
        stuDao.close();
    }
}
