<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/4/28
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>addStu</title>
</head>
<body>
    <form action="StuServlet?type=addStu" method="post">
        <input type="hidden" name="id" value="${student.id}">
        姓名：<input type="text" name="name" value="${student.name}">
        生日：<input type="text" name="birthday" value="${student.birthday}">
        备注：<input type="text" name="description" value="${student.description}">
        平均分：<input type="text" name="avgscore" value="${student.avgscore}">
        <input type="submit" value="提交">
    </form>
</body>
</html>
