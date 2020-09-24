<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<div class="row justify-content-start">
    <div class="col-sm-6">
        <nav>
            <div class="row mt-3 ml-0">
                <a href="/" class="btn btn-outline-secondary btn-sm mr-2 mb-md-2">На главную</a>
                <a href="disciplines" class="btn btn-outline-secondary btn-sm mb-md-2">Назад</a>
            </div>
        </nav>
    </div>
</div>
<div class="row justify-content-center">
    <div class="col-md-8">
        <section>
            <div id="containerForm" class="row">
                <form class="needs-validation was-validated" action="discipline-create" method="post">
                    <div class="row form-group ml-1">
                        Для создания новой дисциплины заполните все поля и нажмите кнопку "Создать":
                    </div>
                    <div class="row form-group">
                        <label for="name" class="col-sm-3 col-form-label">Название*</label>
                        <div class="col-12 col-md-9 col-lg-7">
                            <input type="text" class="form-control" id="name" name="disc"
                                   placeholder="Название новой дисциплины" required>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-sm-3 col-form-label"></div>
                        <div class="col-auto">
                            <button type="submit" class="btn btn-outline-secondary btn btn-block">Создать</button>
                        </div>
                    </div>
                    <div class="form-group row">
                    </div>
                </form>
            </div>
        </section>
    </div>
</div>
<script>
    <c:if test="${param.emptyDiscipline == 1}">
    emptyDiscipline();
    </c:if>
</script>