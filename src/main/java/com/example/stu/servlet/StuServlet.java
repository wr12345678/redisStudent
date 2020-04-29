package com.example.stu.servlet;


import com.alibaba.fastjson.JSON;
import com.example.stu.pojo.PageBean;
import com.example.stu.pojo.Student;
import com.example.stu.service.StuService;
import com.example.stu.util.StringDateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet("/StuServlet")
public class StuServlet extends BaseServlet {

    private StuService stuService = new StuService();

    public void addStu(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
        Student student = new Student();
        student.setId(request.getParameter("id"));
        student.setName(request.getParameter("name"));
        student.setBirthday(StringDateUtil.stringToDate(request.getParameter("birthday"),"yy-MM-dd"));
        student.setDescription(request.getParameter("description"));
        student.setAvgscore(Integer.parseInt(request.getParameter("avgscore")));
       // stuService.addStu(student);
        request.setAttribute("result",stuService.addStu(student));
        request.setAttribute("limit",2);
        request.setAttribute("page",1);
//        response.sendRedirect("show.jsp");
        request.getRequestDispatcher("StuServlet?type=getAll&limit=2&page=1").forward(request,response);
    }

    public void delStu(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        stuService.delStu(id);
//        request.getRequestDispatcher("show.jsp").forward(request,response);
//        response.sendRedirect("show.jsp");
        request.setAttribute("limit",2);
        request.setAttribute("page",1);
//        response.sendRedirect("show.jsp");
        request.getRequestDispatcher("StuServlet?type=getAll&limit=2&page=1").forward(request,response);
    }

    public void updateStu(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
        Student student = new Student();
        student.setId(request.getParameter("id"));
        student.setName(request.getParameter("name"));
        student.setBirthday(StringDateUtil.stringToDate(request.getParameter("birthday"),"yy-MM-dd"));
        student.setDescription(request.getParameter("description"));
        student.setAvgscore(Integer.parseInt(request.getParameter("avgscore")));
       // stuService.updateStu(student);
        request.setAttribute("result",stuService.updateStu(student));
        request.setAttribute("limit",2);
        request.setAttribute("page",1);
        request.getRequestDispatcher("StuServlet?type=getAll&limit=2&page=1").forward(request,response);
//        response.sendRedirect("show.jsp");
    }


    public void getOne(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException{
        Integer id = Integer.parseInt(request.getParameter("id"));
        Student student = stuService.getStuById(id);
        request.setAttribute("student",student);
        request.getRequestDispatcher("updateStu.jsp").forward(request,response);
    }

    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException{

        PageBean<Student> pageBean = stuService.getStuByLimit(request.getParameter("limit"),request.getParameter("page"));
        request.setAttribute("students",pageBean);
        System.out.println(JSON.toJSONString(pageBean));
        request.getRequestDispatcher("show.jsp").forward(request,response);
    }

    public void colse(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException{
        stuService.close();
    }
}
