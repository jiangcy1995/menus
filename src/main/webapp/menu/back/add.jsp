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
        /*$(function(){
            $("#saveMenu").click(function (){
                $.post("${path}/menu/save",$("#formId").serialize(),function(data){
                    console(data);
                },"JSON");
            })
        })*/
    </script>
</head>
<body>



<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12">
            <form action="${path}/menu/save" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="exampleInputEmail1">菜谱名称:</label>
                    <input type="text" class="form-control" id="exampleInputEmail1" name="name">
                </div>
                <div class="form-group">
                    <label >菜谱图片:</label>
                    <input type="file" class="form-control" name="photos">
                </div>

                <div class="form-group">
                    <label >菜谱关于:</label>
                    <input type="text" class="form-control" name="digest">
                </div>

                <div class="form-group">
                    <label >烹饪步骤:</label>
                    <input type="text" class="form-control" name="step">
                </div>
                <input type="submit" class="btn btn-info btn-block">录入菜谱</input>
                <a href="list.html" type="button" class="btn btn-danger btn-block">返回列表</a>
            </form>
        </div>
    </div>

</div>

</body>
</html>