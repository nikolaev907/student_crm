<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/jsp/tamplate/bootstrap.jsp" %>
    <link rel="stylesheet" href="../../resources/css/template/header.css">
    <link rel="stylesheet" href="../../resources/css/disciplineModifying.css">
    <title>Student creating</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/tamplate/header" %>
<div id="container" class="container-fluid">
    <div class="row">
        <div class="col-sm-2">
            <nav>
                <div id="divNav" class="row">
                    <a id="home" href="../../index.jsp" class="btn btn-outline-secondary btn-sm">На главную</a>
                    <a id="disciplinesList" href="/disciplines" class="btn btn-outline-secondary btn-sm">Назад</a>
                </div>
            </nav>
        </div>
        <div class="col-md-8">
            <section>
                <div id="containerForm" class="row">
                    <form class="needs-validation was-validated">
                        <div id="titleForm" class="form-group row">
                            Для модификации дисциплины заполните все поля и нажмите кнопку "Применить":
                        </div>
                        <div class="form-group row">
                            <label for="name" class="col-sm-2 col-form-label">Название*</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="name" placeholder="Название дисциплины"
                                       required>
                            </div>
                            <div class="valid-tooltip">Хорошо!</div>
                            <div class="invalid-tooltip">Заполните это поле!</div>
                        </div>
                        <div class="form-group row">
                            <div id="divButton" class="col-sm-5">
                                <button type="submit" class="btn btn-outline-secondary btn btn-block">Применить
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </section>
        </div>
        <div class="col-sm-2"></div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/tamplate/jQuery.jsp" %>
</body>
</html>