<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
  </head>
  
  <body>
    <div id="main" class="container">
        <div class="row">
            <div class="col-lg-12 col-sm-12 col-md-12">
            <div class="pull-left"><a href="user/taobao" class="btn btn-primary">淘宝测试</a></div>
            <div class="pull-right"><a href="user/form" class="btn btn-primary">新增用户</a></div>
                <table class="table table-hover table-bordered">
                    <thead><th>ID</th><th>姓名</th><th>年龄</th><th>性别</th><th>备注</th><th>操作</th></thead>
                    <tbody>
                    <c:forEach items="${users }" var="user">
                        <tr><td>${user.id}</td><td>${user.name}</td><td>${user.age}</td><td>${user.sex==1?"男":"女"}</td><td>${user.remark}</td><td><a href="user/edit/${user.id}" class="btn btn-primary btn-sm">修改</a><a href="user/delete/${user.id}" class="btn btn-danger btn-sm">删除</a></td></tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
  </body>
</html>
