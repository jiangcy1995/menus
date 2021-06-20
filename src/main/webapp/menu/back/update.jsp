<%@page pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台修改页面</title>
    <link rel="stylesheet" href="${path}/menu/static/css/bootstrap.min.css"/>
    <script src="${path}/menu/static/js/jquery-3.5.1.min.js"></script>
</head>
<body>



<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12">
            <form action="${path}/menu/update" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label class="col-sm-2 control-label">ID</label>
                    <div class="col-sm-10">
                        <p class="form-control-static"></p>
                        <input hidden name="id" value="${menu.id}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label >菜谱名称:</label>
                    <input type="text" value="${menu.name}" name="name" class="form-control" >
                </div>
                <div class="form-group">
                    <label >菜谱图片:</label>
                    <input type="file" name="photos" class="form-control" >
                </div>

                <div class="form-group">
                    <label >菜谱关于:</label>
                    <input type="text" value="${menu.digest}" name="digest" class="form-control" >
                </div>

                <div class="form-group">
                    <label >烹饪步骤:</label>
                    <input type="text" value="${menu.step}" name="step" class="form-control" >
                </div>
                <input type="submit" class="btn btn-info btn-block" value="录入菜谱"></input>
                <a href="list.html" type="button" class="btn btn-danger btn-block">返回列表</a>
            </form>
        </div>
    </div>

</div>

</body>
</html>