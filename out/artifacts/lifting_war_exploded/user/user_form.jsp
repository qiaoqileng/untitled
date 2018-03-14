<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
 
  </head>
    
  <body>
    <div id="main" class="container">
    <div class="row">
        <div class="col-lg-6 col-ms-6 col-sm-6">
            <form action="user/submit" method="post">
                <div class="form-group">
                    <label>姓名</label>
                    <input name="user.name" type="text" name="user.name" value="${user.name}" class="form-control" placeholder="请输入姓名">
                </div>
                 <div class="form-group">
                    <label>年龄</label>
                    <input name="user.age" type="text" name="user.age"  value="${user.age}" class="form-control" placeholder="请输入年龄">
                </div>
                <div class="form-group">
                    <label>性别</label>
                    <div class="radio-inline">
                        <input type="radio" name="user.sex" checked="${user.sex==1?'checked':'unchecked'}" value="1"> 男
                    </div>
                    <div class="radio-inline">
                        <input type="radio" name="user.sex" checked="${user.sex==2?'checked':'unchecked'}" value="2"> 女
                    </div>
                </div>
                <div class="form-group">
                    <label>备注</label>
                    <input type="text" class="form-control" name="user.remark" value="${user.remark}" placeholder="请输入备注">
                </div>
                <div style="text-align:center">
                    <button type="submit" class="btn btn-primary" style="width:120px">提交</button>
                </div>
            </form>
            
        </div>
    </div></div>
  </body>
</html>
