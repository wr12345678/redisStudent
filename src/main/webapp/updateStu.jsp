<%@ page import="com.example.stu.util.StringDateUtil" %>
<%@ page import="com.example.stu.pojo.Student" %><%--
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
    <title>updatetu</title>
</head>
<body>
<form action="StuServlet?type=updateStu" method="post" onsubmit="return validate_form(this)">
    <input type="hidden" name="id" value="${student.id}">
    姓名：<input type="text" name="name" value="${student.name}">
    生日：<input type="text" name="birthday" value="<%= StringDateUtil.dateToString(((Student)request.getAttribute("student")).getBirthday())%>">
    备注：<input type="text" name="description" value="${student.description}">
    平均分：<input type="text" name="avgscore" value="${student.avgscore}">
    <input type="submit" value="提交">
</form>
</body>
<script>


    //验证只能为数字
    function checkNumber(obj){
        var reg = "/^[0-9]+$/";
        if(obj!=""&&!reg.test(obj)){
            alert('只能输入数字！');
            return false;
        }
    }
    function CheckForm(obj)
    {
        if (obj.length == 0) {
            alert("姓名不能为空!");
            return false;
        }
        return true;
        alert("姓名不能为空!");
    }
    //检验时间大小(与当前时间比较)
    function checkDate(obj){
        var obj_value=obj.replace(/-/g,"/");//替换字符，变成标准格式(检验格式为：'2009-12-10')
// var obj_value=obj.replace("-","/");//替换字符，变成标准格式(检验格式为：'2010-12-10 11:12')
        var date1=new Date(Date.parse(obj_value));
        var date2=new Date();//取今天的日期
        if(date1>date2){
            alert("不能大于当前时间！");
            return false;
        }
    }
    function validate_form(thisform) {
        with (thisform)
        {
            // if (validate_email(email,"Not a valid e-mail address!")==false)
            // {email.focus();return false}

            if(CheckForm(name) && checkNumber(avgscore) && checkDate(birthday)){
                return true;
            }else {
                return false;
            }
        }
    }

</script>
</html>
