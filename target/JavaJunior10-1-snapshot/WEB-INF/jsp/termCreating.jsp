<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <form action="term-create" method="post">
                    <div class="row form-group text-lg-justify ml-0">
                        Для создания семестра заполните все поля и нажмите кнопку "Создать":
                    </div>
                    <div class="row form-group needs-validation was-validated justify-content-md-start justify-content-lg-between">
                        <label for="input" class="col-auto col-form-label">Длительность семестра (в
                            неделях):</label>
                        <div class="col-auto form-group">
                            <input id="input" type="text" name="week" class="form-control mt-md-2"
                                   placeholder="24 недели"
                                   maxlength="20"
                                   required>
                        </div>

                    </div>
                    <div class="row form-group needs-validation was-validated justify-content-md-start justify-content-lg-between">
                        <label for="formControlSelect" class="col-auto col-md-8 col-lg-auto col-form-label">Дисциплины в
                            семестре:</label>
                        <div class="col-auto">
                            <select multiple class="form-control table-responsive" name="disciplines"
                                    id="formControlSelect"
                                    required size="8">
                                <c:forEach items="${disciplines}" var="d" varStatus="i">
                                    <option class="text-color-table" value="${i.count}">${d.discipline}</option>
                                </c:forEach>
                                <c:if test="${disciplines[0].discipline == null}">
                                    <option class="text-color-table" disabled>< нет записей ></option>
                                </c:if>
                            </select>
                        </div>
                    </div>
                    <div class="row form-group justify-content-lg-end">
                        <div class="col-auto">
                            <button type="submit" class="btn btn-outline-secondary btn-block">Создать</button>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    </div>
</div>
<script>
    <c:if test="${param.isEmptyTerm == 1}">
    emptyTerm();
    </c:if>
</script>
