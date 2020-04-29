package com.example.stu.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BaseServlet
 */
//@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
    /**
     * 所有的请求进入到某个servlet的时候，进入到servlet的service方法，由service方法来确定请求是get还是post
     */
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		arg0.setCharacterEncoding("utf-8");
		arg1.setCharacterEncoding("utf-8");
		//请求已经到达
		System.out.println(this);//调用当前方法的对象：url中的servlet
		//处理请求：调用url中的servlet类的对应方法  ActionServlet?type=getAction
		String methodName = arg0.getParameter("type");
		Method method = null;
		Object returnResult = null;
//		System.out.println(methodName);
		try {
			method = this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			//System.out.println(JSON.toJSON(method));
			//调用方法处理请求
			returnResult = method.invoke(this,arg0,arg1);//ActionServlet.getAction()    GetAction(actionServlet,request,response);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//响应的返回
		if(returnResult==null){
			//当前执行方法是void无返回，直接数据流输出
			return;
		}else{
			//有返回：String字符串     格式，代表响应的不同类型  重定向  r:url，请求转发  d:url
			String[] result = ((String)returnResult).split(":");
			if(result[0].equals("r")){
				arg1.sendRedirect(result[1]);
			}else if(result[0].equals("d")){
				arg0.getRequestDispatcher(result[1]).forward(arg0, arg1);
			}
		}
		

	}
	
	

}
