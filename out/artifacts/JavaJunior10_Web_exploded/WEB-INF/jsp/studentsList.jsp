<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/jsp/tamplate/bootstrap.jsp" %>
    <link rel="stylesheet" href="../../resources/css/template/header.css">
    <link rel="stylesheet" href="../../resources/css/studentsList.css">
    <title>Students list</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/tamplate/header" %>
<div id="container" class="container-fluid">
    <div class="row">
        <div class="col-md-2">
            <nav>
                <div id="divNav" class="row">
                    <a id="home" href="../../index.jsp" class="btn btn-outline-secondary btn-sm">На главную</a>
                </div>
            </nav>
        </div>
        <div class="col-md-8">
            <section>
                <div id="divStudentsList1" class="row">
                    <div class="col-sm">
                        <a href="/students/progress" class="btn btn-outline-secondary btn btn-block">
                            Посмотреть успеваемость выбранного студента
                        </a>
                    </div>
                    <div class="col-sm">
                        <a href="/students/creating" class="btn btn-outline-secondary btn btn-block">
                            Создать студента
                        </a>
                    </div>
                </div>
                <div id="divStudentsList2" class="row">
                    <div class="col-sm">
                        <a href="/students/modifying" class="btn btn-outline-secondary btn btn-block">
                            Модифицировать выбранного студента
                        </a>
                    </div>
                    <div class="col-sm">
                        <a href="/students/delete" class="btn btn-outline-secondary btn btn-block">
                            Удалить выбранных студентов
                        </a>
                    </div>
                </div>
            </section>
            <section id="sectionTable">
                <h5>Список студентов</h5>
                <table id="table" class="table table-bordered table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="cbxAll">
                                <label class="custom-control-label" for="cbxAll">Выбрать все</label>
                            </div>
                        </th>
                        <th scope="col">#</th>
                        <th scope="col">Фамилия</th>
                        <th scope="col">Имя</th>
                        <th scope="col">Группа</th>
                        <th scope="col">Дата поступления</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th scope="row">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="cbx1">
                                <label class="custom-control-label" for="cbx1"></label>
                            </div>
                        </th>
                        <th scope="row">1</th>
                        <td>Mark</td>
                        <td>Otto</td>
                        <td>КТ-21</td>
                        <td>01.06.2019</td>
                    </tr>
                    <tr>
                        <th scope="row">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="cbx2">
                                <label class="custom-control-label" for="cbx2"></label>
                            </div>
                        </th>
                        <th scope="row">2</th>
                        <td>Tom</td>
                        <td>Thornton</td>
                        <td>КТ-21</td>
                        <td>01.06.2019</td>
                    </tr>
                    <tr>
                        <th scope="row">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="cbx3">
                                <label class="custom-control-label" for="cbx3"></label>
                            </div>
                        </th>
                        <th scope="row">3</th>
                        <td>Jacob</td>
                        <td>Larry</td>
                        <td>КТ-21</td>
                        <td>01.06.2019</td>
                    </tr>
                    <tr>
                        <th scope="row">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="cbx4">
                                <label class="custom-control-label" for="cbx4"></label>
                            </div>
                        </th>
                        <th scope="row">4</th>
                        <td>Mikel</td>
                        <td>Bird</td>
                        <td>КТ-21</td>
                        <td>01.06.2019</td>
                    </tr>
                    <tr>
                        <th scope="row">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="cbx5">
                                <label class="custom-control-label" for="cbx5"></label>
                            </div>
                        </th>
                        <th scope="row">5</th>
                        <td>Mark</td>
                        <td>Otto</td>
                        <td>КТ-21</td>
                        <td>01.06.2019</td>
                    </tr>
                    <tr>
                        <th scope="row">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="cbx6">
                                <label class="custom-control-label" for="cbx6"></label>
                            </div>
                        </th>
                        <th scope="row">6</th>
                        <td>Tom</td>
                        <td>Thornton</td>
                        <td>КТ-21</td>
                        <td>01.06.2019</td>
                    </tr>
                    <tr>
                        <th scope="row">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="cbx7">
                                <label class="custom-control-label" for="cbx7"></label>
                            </div>
                        </th>
                        <th scope="row">7</th>
                        <td>Jacob</td>
                        <td>Larry</td>
                        <td>КТ-21</td>
                        <td>01.06.2019</td>
                    </tr>
                    <tr>
                        <th scope="row">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="cbx8">
                                <label class="custom-control-label" for="cbx8"></label>
                            </div>
                        </th>
                        <th scope="row">8</th>
                        <td>Mikel</td>
                        <td>Bird</td>
                        <td>КТ-21</td>
                        <td>01.06.2019</td>
                    </tr>
                    </tbody>
                </table>
            </section>
        </div>
        <div class="col-md-2"></div>
    </div>
<%@ include file="/WEB-INF/jsp/tamplate/jQuery.jsp" %>
</body>
</html>
