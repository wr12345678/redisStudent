<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/4/28
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.stu.util.StringDateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.4.1.js"></script>
</head>
<style>
    table,table tr th, table tr td
    {
        border:1px solid black; /* 设置边框格式*/
    }
    table {
        width: 800px; /*表格宽度*/
        text-align: center; /*文本内容居中*/
        border-collapse: collapse /*去除重复便捷*/;
    }
</style>
<body>
    <table>
        <a href="oneStu.jsp">添加学生</a>

        <form action="StuServlet?type=getAll&page=1" method="post">
        每页显示<input type="text" name="limit" >
            <input type="submit" value="确认">
        </form>

        <tr>
            <td>id</td>
            <td>姓名</td>
            <td>出生日期</td>
            <td>备注</td>
            <td>平均分</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${students.pageList}" var="student">
            <tr>
                <td id="${student.id}">${student.id}</td>
                <td id="${student.name}">${student.name}</td>
                <td id="${student.birthday}">${student.birthday}</td>
                <td id="${student.description}">${student.description}</td>
                <td id="${student.avgscore}">${student.avgscore}</td>
                <td><a href="../StuServlet?type=getOne&id=${student.id}">修改该学生</a><a href="../StuServlet?type=delStu&id=${student.id}">删除该学生</a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="../StuServlet?type=getAll&limit=${students.pageSize}&page=1">首页  </a>
    <c:if test="${students.currentPage>1}">
       <a href="../StuServlet?type=getAll&limit=${students.pageSize}&page=${students.currentPage-1}"> <c:out value="<<"/>  </a>
    </c:if>
    &nbsp; &nbsp; &nbsp;
    <c:if test="${students.currentPage>1}">
        <a href="../StuServlet?type=getAll&limit=${students.pageSize}&page=${students.currentPage-1}"> <c:out value="${students.currentPage-1}"/>  </a>
    </c:if>
    &nbsp; &nbsp; &nbsp;
    <a href="../StuServlet?type=getAll&limit=${students.pageSize}&page=${students.currentPage}"> <c:out value="${students.currentPage}"/>  </a>
    &nbsp; &nbsp; &nbsp;

    <c:if test="${students.currentPage < students.totalPage}">
        <a href="../StuServlet?type=getAll&limit=${students.pageSize}&page=${students.currentPage+1}"> <c:out value="${students.currentPage+1}"/>  </a>
    </c:if>
    &nbsp; &nbsp; &nbsp;
    <c:if test="${students.currentPage < students.totalPage}">
        <a href="../StuServlet?type=getAll&limit=${students.pageSize}&page=${students.currentPage+1}"> <c:out value=">>"/>  </a>
    </c:if>

    <a href="../StuServlet?type=getAll&limit=${students.pageSize}&page=${students.totalPage}">尾页 </a>

    <p>总页数${students.totalPage}</p>
    <form action="StuServlet?type=getAll&limit=${students.pageSize}" method="post">
        到<input type="text" name="page">页
        <input type="submit" value="跳转">
    </form>


</body>
<script>
    //检测浏览器或者页面关闭事件，如果关闭了使之退出
    $(window).bind('beforeunload unload',function(e){
        $.ajax({
            url:'/StuServlet?type=close',
            type:'get',
            data:{
                uname:username,sz, uid,salt, utc, unlock
            },
            async:false, //必须加异步，不然无法检测浏览器的关闭情况
            success:function(res){

            }
        });
    });
</script>
</html>
