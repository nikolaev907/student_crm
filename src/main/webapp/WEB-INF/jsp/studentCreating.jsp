<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<div class="row justify-content-start">
    <div class="col-sm-6">
        <nav>
            <div class="row mt-3 ml-0">
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
                <form class="needs-validation was-validated" action="student-create" method="post">
                    <div class="row form-group ml-0">
                        Для создания студента заполните все поля и нажмите кнопку "Создать":
                    </div>
                    <div class="row form-group">
                        <label for="surnameID" class="col-sm-3 col-form-label">Фамилия*</label>
                        <div class="col-auto">
                            <input type="text" class="form-control" id="surnameID" name="surname"
                                   placeholder="Иванов"
                                   required>
                        </div>
                    </div>
                    <div class="row form-group">
                        <label for="nameId" class="col-sm-3 col-form-label">Имя*</label>
                        <div class="col-auto">
                            <input type="text" class="form-control" id="nameID" name="name" placeholder="Иван"
                                   required>
                        </div>
                    </div>
                    <div class="row form-group">
                        <label for="groupID" class="col-sm-3 col-form-label">Группа*</label>
                        <div class="col-auto">
                            <input type="text" class="form-control" id="groupID" name="group"
                                   placeholder="КТ-21"
                                   required>
                        </div>
                    </div>
                    <div class="row form-group">
                        <label for="receiptDate" class="col-sm-3 col-form-label">Дата поступления*</label>
                        <div class="col-9 col-sm-5 col-md-auto">
                            <input type="date" class="form-control" id="receiptDate" name="date_receipt"
                                   placeholder="03/05/2020" required>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-sm-3 col-form-label"></div>
                        <div class="col-auto">
                            <button type="submit" class="btn btn-outline-secondary btn-block">Создать</button>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    </div>
</div>
