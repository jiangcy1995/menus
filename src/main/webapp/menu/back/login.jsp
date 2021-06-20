<%@page pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <title>login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${path}/menu/static/css/bootstrap.min.css"/>
    <script src="${path}/menu/static/js/jquery-3.5.1.min.js"></script>
    <script>
        $(function(){
           $("#login").click(function(){
               $.ajax({
                   url:"${path}/user/login",
                   type:"post",
                   dataType:"JSON",
                   data:$("#formId").serialize(),
                   success:function (data){
                       $("#msg").text(data.msg);
                       if (data.status)location.href='${path}/menu/findAll';

                   }
               })
           });
        })
    </script>
</head>

<body>
<div id="wrap" class="container-fluid">
    <div id="top_content" class="row"  style="margin: 0 auto;">
        <div class="col-sm-8 col-sm-offset-2">
            <div id="header">
                <div id="topheader">
                    <h1 class="text-center text-info">欢迎进入菜谱管理系统v1.0</h1>
                </div>
                <div id="navigation">
                </div>
            </div>
        </div>
    </div>
    <div class="row" style="margin-top: 20px;">
        <div class="col-sm-8 col-sm-offset-2">
            <span id="msg"></span>
            <div id="content">
                <form id="formId">
                    <div class="form-group">
                        <label for="username">用户名</label>
                        <input type="text"  v-model="username" id="username" class="form-control" name="username"/>
                    </div>
                    <div class="form-group">
                        <label for="password">密码</label>
                        <input type="password" id="password"  v-model="password"  class="form-control" name="password"/>                    </div>
                    <br>
                    <input type="button" style="width: 98%"   class="btn btn-danger" value="登录&raquo;" id="login"/>
                </form>
            </div>
        </div>
    </div>
    <div class="row" style="margin-top: 40px;">
        <div class="col-sm-8 col-sm-offset-2">
            <h5 class="text-center">cookie book @136.com</h5>
        </div>
    </div>
</div>
</body>
</html>
