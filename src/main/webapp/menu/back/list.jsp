<%@page pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台列表页面</title>
    <link rel="stylesheet" href="${path}/menu/static/css/bootstrap.min.css"/>
    <script src="${path}/menu/static/js/jquery-3.5.1.min.js"></script>
    <script>
        $(function(){
            $("#create").click(function(){
                $.ajax({
                    url:"${path}/menu/createIndex",
                    type:"get",
                    dataType:"JSON",
                    success:function (data){
                        if (data.status)alert("成功重建索引");
                    }
                })
            });
            $("#deleteAll").click(function(){
                $.ajax({
                    url:"${path}/menu/deleteAll",
                    type:"get",
                    dataType:"JSON",
                    success:function (data){
                        if (data.status)alert("成功清空索引");

                    }
                })
            })
        })
    </script>

</head>
<body>

<!--功能按钮-->
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">后台食谱管理系统</a>
        </div>

        <!--功能按钮 -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <form class="navbar-form navbar-left">
                <button type="button" class="btn btn-danger" id="deleteAll">清空ES索引库</button>
                <button type="button" class="btn btn-info" id="create">基于mysql数据重建索引</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="text-info">${user.username}</span></a></li>
                <li><a href="${path}/user/logout">退出系统</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12">
            <a href="${path}/menu/back/add.jsp" class="btn btn-info">添加</a>
        </div>
    </div>
    <div class="row" style="margin-top: 20px;">
        <div class="col-sm-12">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>菜谱名称</th>
                    <th>图片</th>
                    <th>录入时间</th>
                    <th>录入人</th>
                    <th>关于摘要</th>
                    <th>步骤摘要</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${menus}" var="menu">
                    <tr>
                        <th scope="row">${menu.id}</th>
                        <td class="col-sm-1">${menu.name}</td>
                        <td class="col-sm-2"><img style="width: 200px;height: 120px;" src="${path}/menu/static/imgs${menu.photo}" class="img-thumbnail" alt=""></td>
                        <td class="col-sm-1"><fmt:formatDate value="${menu.createDate}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                        <td>${menu.createPerson}</td>
                        <td>${menu.digest}</td>
                        <td>${menu.step}</td>
                        <td><a href="${path}/menu/delete?id=${menu.id}" class="btn btn-danger">删除</a>&nbsp;&nbsp;<a href="${path}/menu/findOne?id=${menu.id}" class="btn btn-info">修改</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!--热词处理-->

</div>


</body>
</html>