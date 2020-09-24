<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<div class="row justify-content-start">
    <div class="col-sm-6">
        <nav>
            <div class="row mt-3 ml-1">
                <a id="home" href="/" class="btn btn-outline-secondary btn-sm mr-2 mb-md-2">На главную</a>
                <a id="disciplinesList" href="disciplines"
                   class="btn btn-outline-secondary btn-sm btn-sm mb-md-2">Назад</a>
            </div>
        </nav>
    </div>
</div>
<div class="row justify-content-center">
    <div class="col-md-8">
        <section>
            <div id="containerForm" class="row">
                <form class="needs-validation was-validated" method="post">
                    <div class="row form-group ml-0">
                        Для модификации дисциплины заполните все поля и нажмите кнопку "Применить":
                    </div>
                    <div class="row form-group">
                        <label for="nameId" class="col-sm-3 col-form-label">Название*</label>
                        <div class="col-12 col-md-9 col-lg-7">
                            <input type="text" class="form-control" id="nameId" name="name"
                                   value="${disc.discipline}"
                                   required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-3"></div>
                        <div class="col-auto">
                            <button type="submit" class="btn btn-outline-secondary btn btn-block">Применить</button>
                        </div>
                    </div>
                    <input type="hidden" class="form-control" name="id" value="${disc.id}">
                </form>
            </div>
        </section>
    </div>
</div>