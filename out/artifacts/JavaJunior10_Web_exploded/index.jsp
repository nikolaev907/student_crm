<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/jsp/tamplate/bootstrap.jsp" %>
    <link rel="stylesheet" href="resources/css/template/header.css">
    <link rel="stylesheet" href="resources/css/index.css">
    <title>Title page</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/tamplate/header" %>
<div id="container" class="container-fluid">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <nav>
                <div id="navHeader" class="row">
                    <div class="col-sm-4">
                        <a class="btn btn-outline-secondary btn-lg btn-block" href="/students">Студенты</a>
                    </div>
                    <div class="col-sm-4">
                        <a href="/disciplines" class="btn btn-outline-secondary btn-lg btn-block">Дисциплины</a>
                    </div>
                    <div class="col-sm-4">
                        <a href="/terms" class="btn btn-outline-secondary btn-lg btn-block">Семестры</a>
                    </div>
                </div>
            </nav>
        </div>
        <div class="col-md-2"></div>
    </div></div>
<%@ include file="/WEB-INF/jsp/tamplate/jQuery.jsp" %>
</body>
</html>