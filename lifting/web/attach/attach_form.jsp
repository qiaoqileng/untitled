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
 <script type="javascript" src="../jquery/jquery-3.2.1.min.js"/>
<script type="application/javascript">
   $(document).ready(function () {
       $("")
   });
 </script>
  </head>
    
  <body>
    <div id="main" class="container">
    <div class="row">
        <div class="col-lg-6 col-ms-6 col-sm-6">
            <form action="attach/submit" enctype="multipart/form-data" method="post">
                <div class="form-group">
                    <label>名称</label>
                    <input type="text" name="attaches.name" value="${attaches.name}" class="form-control" placeholder="请输入名称">
                </div>
                <div class="form-group">
                    <label>请选择文件</label>
                    <input name="attaches.file" type="file" placeholder="请选择文件">
                </div>
                <div style="text-align:center">
                    <button type="submit" class="btn btn-primary" style="width:120px">提交</button>
                </div>
            </form>
            
        </div>
    </div>
        <a href="attach/download">下载</a>
    </div>
  </body>
</html>
