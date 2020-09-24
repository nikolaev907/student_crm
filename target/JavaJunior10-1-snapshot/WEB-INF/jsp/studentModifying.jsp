<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<div class="row justify-content-start">
    <div class="col-sm-6">
        <nav>
            <div class="row mt-3 ml-1">
                <a href="/" class="btn btn-outline-secondary btn-sm mr-2 mb-md-2">На главную</a>
                <a href="students" class="btn btn-outline-secondary btn-sm mb-md-2">Назад</a>

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
                        Для модификации студента введите новые значения и нажмите кнопку "Применить":
                    </div>
                    <div class="row form-group">
                        <label for="surnameId" class="col-sm-3 col-form-label">Фамилия*</label>
                        <div class="col-12 col-md-9 col-lg-7">
                            <input type="text" class="form-control" id="surnameId" name="surname"
                                   value="${student.surname}"
                                   required>
                        </div>
                    </div>
                    <div class="row form-group">
                        <label for="name" class="col-sm-3 col-form-label">Имя*</label>
                        <div class="col-12 col-md-9 col-lg-7">
                            <input type="text" class="form-control" id="name" name="name" value="${student.name}"
                                   required>
                        </div>
                    </div>
                    <div class="row form-group">
                        <label for="group_name" class="col-sm-3 col-form-label">Группа*</label>
                        <div class="col-12 col-md-9 col-lg-7">
                            <input type="text" class="form-control" id="group_name" name="group"
                                   value="${student.group}"
                                   required>
                        </div>
                    </div>
                    <div class="row form-group">
                        <label for="date_receipt" class="col-sm-3 col-form-label">Дата поступления*</label>
                        <div class="col-auto">
                            <input type="date" class="form-control" id="date_receipt" name="date_receipt"
                                   value="${student.date_receipt}" required>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-sm-3 col-form-label"></div>
                        <div class="col-auto">
                            <button type="submit" class="btn btn-outline-secondary btn-block">Применить</button>
                        </div>
                    </div>
                    <input type="hidden" class="form-control" name="id" value="${student.id}">
                </form>
            </div>
        </section>
    </div>
</div>