<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/jsp/tamplate/bootstrap.jsp" %>
    <link rel="stylesheet" href="../../resources/css/template/header.css">
    <link rel="stylesheet" href="../../resources/css/termsList.css">
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
<%--                    <a id="disciplinesList" href="/terms" class="btn btn-outline-secondary btn-sm">Назад</a>--%>
                </div>
            </nav>
        </div>
        <div class="col-md-8">
            <section>
                <div id="titleTermsList" class="row">
                    <div class="form">
                        <div class="form-row">
                            <div id="divFormTitle" class="form-group col-sm-4">
                                <h5>Выбрать семестр:</h5>
                            </div>
                            <div class="form-group col-sm-3">
                                <select type="text" id="select" class="form-control">
                                    <option selected>Семестр 1</option>
                                    <option>Семестр 2</option>
                                    <option>Семестр 3</option>
                                    <option>Семестр 4</option>
                                    <option>Семестр 5</option>
                                </select>
                            </div>
                            <div class="form-group col-sm-3">
                                <button type="submit" id="btn" class="btn btn-outline-secondary">Выбрать</button>
                            </div>
                        </div>
                        <div id="divDurationTerm" class="row">
                            <h5>
                                <div class="col-sm-12">Длительность семестра: <b> 24 </b>недели.</div>
                            </h5>
                        </div>
                    </div>
                </div>
            </section>
            <section id="sectionBody">
                <div class="row">
                    <div class="col-sm-7">
                        <h5>Список дисциплин семестра:</h5>
                        <table id="table" class="table table-bordered table table-hover">
                            <thead>
                            <tr>
                                <th scope="col">Наименование дисциплины</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>Информатика</td>
                            </tr>
                            <tr>
                                <td>Системный анализ</td>
                            </tr>
                            <tr>
                                <td>Управление проектами</td>
                            </tr>
                            <tr>
                                <td>Основы Дискретной Математики</td>
                            </tr>
                            <tr>
                                <td>Высшая математика</td>
                            </tr>
                            <tr>
                                <td>История Науки и Техники</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-sm-5">
                        <section>
                            <div id="divTermsList1" class="row">
                                <div class="divTermsList col-sm-12">
                                    <a href="/terms/creating"class="btn btn-outline-secondary btn btn-block">
                                        Создать семестр
                                    </a>
                                </div>
                            </div>
                            <div id="divTermsList2" class="row">
                                <div class="divTermsList col-sm-12">
                                    <a href="/terms/modifying"class="btn btn-outline-secondary btn btn-block">
                                        Модифицировать текущий семестр
                                    </a>
                                </div>
                            </div>
                            <div id="divTermsList3" class="row">
                                <div class="divTermsList col-sm-12">
                                    <a href="/terms/delete"class="btn btn-outline-secondary btn btn-block">
                                        Удалить текущий семестр
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