<%@page pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>详情页面</title>
    <link rel="stylesheet" href="${path}/menu/static/css/bootstrap.min.css"/>
    <script src="${path}/menu/static/js/jquery-3.5.1.min.js"></script>
</head>
<body>

<div class="container-fluid" style="padding-top: 40px;">

    <div class="panel panel-default" >
        <div class="panel-heading">
            <h3 class="panel-title">详细菜谱</h3>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-sm-12">
                    <img src="${path}/menu/static/imgs${menu.photo}" alt="...">
                    <div class="caption">
                        <h2>西红柿炒蛋${menu.name}</h2>
                        <h4 class="text-info">关于:</h4>
                        <p style="margin-left: 30px;">${menu.digest}</p>
                        <h4 class="text-danger">步骤:</h4>
                        <ul>
                            <li>${menu.step}</li>

                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <a href="${path}/menu/queryAll" class="btn btn-success btn-block">返回主页</a>
        </div>
    </div>
</div>
</body>
</html>