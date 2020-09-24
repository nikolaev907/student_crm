<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<div class="row justify-content-center">
    <div class="col-auto">
        <section>
            <div id="containerForm" class="row">
                <div class="col-12">
                    <form class="was-validated form-group" action="login" method="post">
                        <div class="form-group row ml-0">
                            Для входа заполните все поля и нажмите кнопку "Войти":
                        </div>
                        <div class="form-group row">
                            <label for="loginID" class="col-sm-2 col-form-label">Логин*</label>
                            <div class="col-auto">
                                <input type="text" class="form-control formInput" id="loginID" name="login"
                                       placeholder="Логин"
                                       required autofocus>
                            </div>
                            <div class="valid-tooltip">Хорошо!</div>
                            <div class="invalid-tooltip">Заполните это поле!</div>
                        </div>
                        <div class="form-group row">
                            <label for="passId" class="col-sm-2 col-form-label">Пароль*</label>
                            <div class="col-auto">
                                <input type="password" class="form-control formInput" id="passId" name="pass"
                                       placeholder="********"
                                       required>
                            </div>
                            <div class="valid-tooltip">Хорошо!</div>
                            <div class="invalid-tooltip">Заполните это поле!</div>
                        </div>
                        <div class="form-group row">
                            <label for="roleID" class=" col-sm-2">Роль*</label>
                            <div class="col-auto col-sm-6 col-md-6 col-lg-6">
                                <select class="custom-select formSelect" required
                                        id="roleID" name="role">
                                    <option value="" class="formOption">Выберите роль...</option>
                                    <c:forEach items="${roles}" var="role" varStatus="i">
                                        <option value="${i.count}">${role}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="roleID" class=" col-sm-2"></label>
                            <div class="col-auto">
                                <button type="submit"
                                        class="btn btn-outline-secondary btn-block">
                                    Войти
                                </button>
                            </div>
                        </div>
                        <c:if test="${errorMessage eq 1}">
                            <div class="row justify-content-center">
                                <div class="col-md-8 ml-4" id="errorLogin">Логин, пароль или роль не верны!!!
                                </div>
                            </div>
                        </c:if>
                    </form>
                </div>
            </div>
        </section>
    </div>
</div>
