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
     * ���е�������뵽ĳ��servlet��ʱ�򣬽��뵽servlet��service��������service������ȷ��������get����post
     */
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		arg0.setCharacterEncoding("utf-8");
		arg1.setCharacterEncoding("utf-8");
		//�����Ѿ�����
		System.out.println(this);//���õ�ǰ�����Ķ���url�е�servlet
		//�������󣺵���url�е�servlet��Ķ�Ӧ����  ActionServlet?type=getAction
		String methodName = arg0.getParameter("type");
		Method method = null;
		Object returnResult = null;
//		System.out.println(methodName);
		try {
			method = this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			//System.out.println(JSON.toJSON(method));
			//���÷�����������
			returnResult = method.invoke(this,arg0,arg1);//ActionServlet.getAction()    GetAction(actionServlet,request,response);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//��Ӧ�ķ���
		if(returnResult==null){
			//��ǰִ�з�����void�޷��أ�ֱ�����������
			return;
		}else{
			//�з��أ�String�ַ���     ��ʽ��������Ӧ�Ĳ�ͬ����  �ض���  r:url������ת��  d:url
			String[] result = ((String)returnResult).split(":");
			if(result[0].equals("r")){
				arg1.sendRedirect(result[1]);
			}else if(result[0].equals("d")){
				arg0.getRequestDispatcher(result[1]).forward(arg0, arg1);
			}
		}
		

	}
	
	

}
