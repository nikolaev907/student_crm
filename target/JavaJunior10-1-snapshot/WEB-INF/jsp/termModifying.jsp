<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<div class="row justify-content-start">
    <div class="col-sm-6">
        <nav>
            <div class="row mt-3 ml-0">
                <a href="/" class="btn btn-outline-secondary btn-sm mr-2 mb-md-2">На главную</a>
                <a href="terms" class="btn btn-outline-secondary btn-sm mb-md-2">Назад</a>
            </div>
        </nav>
    </div>
</div>
<div class="row justify-content-center">
    <div class="col-md-8">
        <section>
            <div id="containerForm" class="row justify-content-lg-center">
                <form action="term-modify" method="post">
                    <div class="row form-group text-lg-justify ml-0">
                        Для модификации семестра заполните все поля и нажмите кнопку "Применить":
                    </div>
                    <div class="row form-group needs-validation was-validated justify-content-md-start justify-content-lg-between">
                        <label for="input" class="col-auto">Длительность семестра (в
                            неделях):</label>
                        <div id="divInput" class="col-auto">
                            <input id="input" type="text" name="week" class="form-control" placeholder="24 недели"
                                   maxlength="20"
                                   required>
                        </div>
                        <input type="hidden" id="termId" name="termId" value="${termId}"/>
                    </div>
                    <div class="row form-group needs-validation was-validated justify-content-md-start justify-content-lg-between">
                        <label for="formControlSelect" class="col-auto">Дисциплины в семестре:</label>
                        <div class="col-auto"><select multiple class="form-control table-responsive" name="disciplines"
                                                      id="formControlSelect"
                                                      required size="8">
                            <c:forEach items="${disciplines}" var="d" varStatus="i">
                                <option value="${d.id}">${d.discipline}</option>
                            </c:forEach>
                        </select></div>
                    </div>
                    <div class="row form-group justify-content-md-between justify-content-lg-end">
                        <div class="col-auto">
                            <button type="submit" class="btn btn-outline-secondary btn btn-block">Применить</button>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    </div>
</div>
