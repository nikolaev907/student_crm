<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/jsp/tamplate/bootstrap.jsp" %>
    <link rel="stylesheet" href="../../resources/css/template/header.css">
    <link rel="stylesheet" href="../../resources/css/disciplinesList.css">
    <title>Disciplines list</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/tamplate/header" %>
<div id="container" class="container-fluid">
    <div class="row">
        <div class="col-sm-2">
            <nav>
                <div id="divNav" class="row">
                    <a id="home" href="../../index.jsp" class="btn btn-outline-secondary btn-sm">На главную</a>
                    <%--                            <a id="disciplinesList" href="/disciplines" class="btn btn-outline-secondary btn-sm">Назад</a>--%>
                </div>
            </nav>
        </div>
        <div class="col-md-8">
            <section>
                <div id="titleDisciplinesList" class="row">
                    <h5>Список дисциплин:</h5>
                </div>
            </section>
            <section id="sectionBody">
                <div class="row">
                    <div class="col-sm-7">
                        <table id="table" class="table table-bordered table table-hover">
                            <thead>
                            <tr>
                                <th scope="col">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" id="cbxAll">
                                        <label class="custom-control-label" for="cbxAll">Выбрать все</label>
                                    </div>
                                </th>
                                <th scope="col">Наименование дисциплины</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${disces}" var="d">
                                <tr>
                                    <th scope="row">
                                        <div class="custom-control custom-checkbox">
                                            <input type="checkbox" class="custom-control-input" id="cbx1">
                                            <label class="custom-control-label" for="cbx1"></label>
                                        </div>
                                    </th>
                                    <td>${d.discipline}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-sm-5">
                        <section>
                            <div id="divDisciplineList1" class="row">
                                <div class="divDisciplineList col-sm-12">
                                    <a href="/disciplines/creating">
                                        <button id="button1" type="button"
                                                class="btn btn-outline-secondary btn-lg btn-block">Создать дисциплину
                                        </button>
                                    </a>
                                </div>
                            </div>
                            <div id="divDisciplineList2" class="row">
                                <div class="divDisciplineList col-sm-12">
                                    <a href="/disciplines/modifying">
                                        <button id="button2" type="button"
                                                class="btn btn-outline-secondary btn-lg btn-block">Модифицировать
                                            выбранную дисциплину
                                        </button>
                                    </a>
                                </div>
                            </div>
                            <div id="divDisciplineList3" class="row">
                                <div class="divDisciplineList col-sm-12">
                                    <a href="/disciplines/delete">
                                        <button id="button3" type="button"
                                                class="btn btn-outline-secondary btn-lg btn-block">Удалить выбранную
                                            дисциплину
                                        </button>
                                    </a>
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
            </section>
        </div>
        <div class="col-sm-2"></div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/tamplate/jQuery.jsp" %>
</body>
</html>