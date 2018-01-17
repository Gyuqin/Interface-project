package com.gyq.service;

//导入必需的 java 库
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.gyq.dao.UserService;
import com.gyq.pojo.User;

//扩展 HttpServlet 类
public class HelloWorld extends HttpServlet {

private String message;

public void init() throws ServletException
{
   // 执行必需的初始化
   message = "userService";
}

//get访问方式进入方法
public void doGet(HttpServletRequest request,
                 HttpServletResponse response)
         throws ServletException, IOException
{
   // 设置响应内容类型
//   response.setContentType("text/html");
	response.setContentType("application/json");
	response.setCharacterEncoding("UTF-8");
	
	//获取request请求参数
	request.setCharacterEncoding("UTF-8");
	String name = request.getParameter("name");
	name = new String(name.getBytes("iso8859-1"),"UTF-8");
	System.out.println("请求的name值为："+name);
	
	//构建返回数据
	UserService userService = new UserService();
	User user = userService.getUserByName(name);
	

   // 实际的逻辑是在这里
   PrintWriter out = response.getWriter();
//   out.println("<h1>" + message + "</h1>");
//   out.println("{\"message\":\""+name+","+message+"\"}");
   out.println(JSON.toJSONString(user));
}

//Post访问方式进入方法
public void doPost(HttpServletRequest request,
               HttpServletResponse response)
       throws ServletException, IOException {
	this.doGet(request, response);
}

public void destroy()
{
   // 什么也不做
}
}