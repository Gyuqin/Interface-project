package com.gyq.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.gyq.pojo.User;

public class UserController extends HttpServlet {


private String message;

public void init() throws ServletException
{
   // 执行必需的初始化
   message = "userClient";
}

//get访问方式进入方法
public void doGet(HttpServletRequest req,
                 HttpServletResponse resp)
         throws ServletException, IOException
{
   // 设置响应内容类型
	resp.setContentType("application/json");
	resp.setCharacterEncoding("UTF-8");
	
	//获取request请求参数
	req.setCharacterEncoding("UTF-8");
	String name = req.getParameter("name");
	name = new String(name.getBytes("iso8859-1"),"UTF-8");
	System.out.println("请求的name值为："+name);
	
	//构建http请求
    String urlNameString = "http://localhost:8080/user-service/HelloWorld?name=郭宇沁";
    String result="";
      try {
            // 根据地址获取请求
            HttpGet request = new HttpGet(urlNameString);//这里发送get请求
            // 获取当前客户端对象
            HttpClient httpClient = new DefaultHttpClient();
            // 通过请求对象获取响应对象
            HttpResponse response = httpClient.execute(request);
            
            // 判断网络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result= EntityUtils.toString(response.getEntity(),"utf-8");
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
//    return result;
      System.out.println("请求接口返回的数据为：" + result);
    //....result是用户信息,站内业务以及具体的json转换这里也不写了...
//      User user = (User) JSON.parse(result);
      User user = JSON.parseObject(result, User.class, null);
      System.out.println("获取到的User的name为"+user.getName());
      if(user.getAge() == 18) {
    	  System.out.println(user.getName() + "是一个大美女！！");
      } else {
    	  System.out.println(user.getName() + "是一个大美人！！");
      }

   // 实际的逻辑是在这里
   PrintWriter out = resp.getWriter();
   out.println();
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
