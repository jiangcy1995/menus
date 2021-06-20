
<%@page pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>前台首页</title>
    <link rel="stylesheet" href="${path}/menu/static/css/bootstrap.min.css"/>
    <script src="${path}/menu/static/js/jquery-3.5.1.min.js"></script>
    <script>

    </script>
</head>
<body>

<div class="container-fluid">

    <!--搜索框-->
    <div class="row" style="margin-top: 20px;">
        <div class="col-sm-12" style="text-align: center">
            <form class="form-inline" action="${path}/menu/searchAll">
                <div class="form-group" style="width: 600px;">
                    <input type="text" style="width: 600px;" class="form-control" placeholder="请输入查询条件" name="message">
                </div>
                <button type="submit" class="btn btn-primary">搜索</button>
            </form>
        </div>
    </div>

    <h1 class="page-header">搜索结果</h1>
    <!--搜索列表-->
    <div class="row " style="margin-top: 20px">
        <c:forEach items="${menus}" var="menu">
        <div class="col-sm-3">
            <div class="thumbnail">
                <img src="${path}/menu/static/imgs${menu.photo}" class="img-circle" style="width: 200px;height: 120px;">
                <div class="caption">
                    <h3 class="text-center">${menu.name}</h3>
                    <p>${menu.digest}</p>
                    <p><a href="${path}/menu/getDetail?id=${menu.id}" class="btn btn-danger btn-block" role="button">查看详细</a></p>
                </div>
            </div>
        </div>
        </c:forEach>


    </div>

    <!--分页工具栏-->
    <nav aria-label="Page navigation">
        <ul class="pagination pull-right">
            <c:if test="${currentPage==1}">
            <li>
                <a href="" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            </c:if>
            <c:if test="${currentPage>1}">
            <li>
                <a href="${path}/menu/queryByPage?currentPage=${currentPage-1}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            </c:if>
            <c:forEach begin="1" end="${page}" var="i" step="1">

            <li><a href="${path}/menu/queryByPage?currentPage=${i}">${i}</a></li>

            </c:forEach>

            <c:if test="${currentPage!=page}">
            <li>
                <a href="${path}/menu/queryByPage?currentPage=${currentPage+1}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            </c:if>
            <c:if test="${currentPage==page}">
            <li>
                <a href="" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            </c:if>
        </ul>
    </nav>


</div>
</body>
</html>