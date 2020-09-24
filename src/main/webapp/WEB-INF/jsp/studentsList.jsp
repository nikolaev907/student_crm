<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<section>
    <div class="row justify-content-md-start justify-content-lg-end">
        <div class="col-6 col-md-2 col-lg-2 mt-3 mb-2">
            <a href="/"
               class="col-auto btn btn-outline-secondary btn-block ml-lg-3 font-size-12px-xs font-size-md font-size-lg">На
                главную</a>
        </div>
        <div class="col-md-5 col-lg-4 mt-1 mt-md-3 mt-lg-3">
            <a class="btn btn-outline-secondary btn btn-block font-size-12px-xs font-size-md font-size-lg"
               onclick="progressStudent()" tabindex="">
                Посмотреть успеваемость студента
            </a>
        </div>
        <c:if test="${role ne 1}">
            <div class="col-md-5 col-lg-4 mt-2 mt-md-3 mt-lg-3"></div>
            <div class="col-md-2 col-lg-2"></div>
        </c:if>
        <c:if test="${role eq 1}">
        <div class="col-md-5 col-lg-4 mt-2 mt-md-3 mt-lg-3">
            <a href="student-create"
               class="btn btn-outline-secondary btn btn-block font-size-12px-xs font-size-md font-size-lg" tabindex="">
                Создать студента
            </a>
        </div>
        <div class="col-md-2 col-lg-2"></div>
    </div>
    <div class="row justify-content-md-start justify-content-lg-end">
        <div class="col-4 col-md-2 col-lg-2 mt-lg-3"></div>
        <div class="col-md-5 col-lg-4 mt-2 mt-md-3 mt-lg-3">
            <a onclick="modifyStudent()"
               class="btn btn-outline-secondary btn btn-block font-size-12px-xs font-size-md font-size-lg" tabindex="">
                Модифицировать студента
            </a>
        </div>
        <div class="col-md-5 col-lg-4 mt-2 mt-md-3 mt-lg-3">
            <a onclick="deleteStudent()"
               class="btn btn-outline-secondary btn btn-block font-size-12px-xs font-size-md font-size-lg" tabindex="">
                Удалить выбранных студентов
            </a>
        </div>
        <div class="col-md-2 col-lg-2"></div>
    </div>
    </c:if>
</section>
<section>
    <div class="row">
        <div class="col-lg-2"></div>
        <div class="justify-content-start"><h5 class="text-color-table pl-3 mb-n1 mt-2">Список студентов:</h5></div>
    </div>
    <div class="row justify-content-center">
        <div class="table-responsive col-sm-12 col-lg-8 mt-2">
            <table class="table table-bordered table-hover was-validated record_table d-xl-table">
                <thead>
                <tr class="text-center text-color-table p-td-th-xs">
                    <th class="p-td-th-xs"><img class="iconCheck"
                                                src="../../resources/images/icons-1.0.0-alpha2/icons/check-box.svg"
                                                alt=""/></th>
                    <th>№</th>
                    <th>Фамилия</th>
                    <th>Имя</th>
                    <th>Группа</th>
                    <th class="text-nowrap">Дата поступления</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${stud}" var="s" varStatus="i">
                    <tr id="tr${i.count}" class="countList text-color-table">
                        <th class="p-td-th-xs">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" value="${s.id}"
                                       id="cbx${i.count}" required>
                                <label class="custom-control-label border-radius-xs"
                                       for="cbx${i.count}"></label>
                            </div>
                        </th>
                        <td class="p-td-th-xs">${i.count}</td>
                        <td class="text-left">${s.surname}</td>
                        <td class="text-left">${s.name}</td>
                        <td class="text-left">${s.group}</td>
                        <td type="Date"><fmt:formatDate pattern="dd/MM/yyyy"
                                                        value="${s.date_receipt}"/></td>
                    </tr>
                </c:forEach>
                <c:if test="${stud[0].name.length() == null}">
                    <td colspan="6" id="tdSuccess" class="p-td-th-xs text-center text-color-table">< нет записей ></td>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
</section>

<form id="formModifyStudent" method="get" action="student-modify">
    <input type="hidden" id="idModifyStudent" name="idModifyStudent"/>
</form>
<form id="formDeleteStudent" method="post" action="students">
    <input type="hidden" id="idDeleteStudent" name="idDeleteStudent"/>
</form>
<form id="formProgressStudent" method="get" action="student-progress">
    <input type="hidden" id="idProgressStudent" name="idProgressStudent"/>
</form>

<%--<script>
    <c:if test="${isEmptyTerm == 1}">
    emptyTerm();
    </c:if>
    <c:if test="${emptyDiscipline == 1}">
    emptyDiscipline();
    </c:if>
</script>--%>
