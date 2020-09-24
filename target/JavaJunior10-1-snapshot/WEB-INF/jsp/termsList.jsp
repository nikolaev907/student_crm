<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<div class="row justify-content-start">
    <div class="col-6 col-md-2 col-lg-2 mt-3 mb-2">
        <a href="/" class="btn btn-outline-secondary btn-block font-size-12px-xs font-size-md font-size-lg">На
            главную</a>
    </div>
</div>

<section>
    <div class="row justify-content-center">
        <div class="col-sm-12 col-lg-8 mt-2">
            <section>
                <div class="form-group justify-content-center">
                    <form class="form-row" action="terms" method="get">
                        <div class="col-12 col-md-5 text-color-table">
                            <h5>Выбрать семестр:</h5>
                        </div>
                        <div class="form-group col-auto col-md-3 col-lg-auto">
                            <select id="op" type="text" class="form-control select text-color-table " name="selTerm">
                                <c:forEach items="${terms}" var="t">
                                    <c:choose>
                                        <c:when test="${t.id eq selectedTerm.id}">
                                            <option value="${t.id}" class="text-center text-color-table" selected>${t.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${t.id}" class="text-center text-color-table">${t.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <c:if test="${terms[0] == null}">
                                    <option selected class="text-center text-color-table">< нет записей ></option>
                                </c:if>
                            </select>
                        </div>
                        <div class="col-auto col-lg-4">
                            <button id="btn" class="btn btn-sm btn-outline-secondary" type="submit">Выбрать</button>
                        </div>
                        <div class="col-auto col-md-5 text-color-table">
                            <h5>Длительность семестра:</h5>
                        </div>
                        <div class="col-12 col-md-6 text-color-table">
                            <h5 class="color-header text-left">${selectedTerm.duration}</h5
                        </div>
                    </form>
                </div>
            </section>
            <section>
                <div class="row justify-content-center">
                    <div class="col-md-7 order-1 order-md-0 mt-2 mt-md-0">
                        <div class="row justify-content-start ml-0 mb-md-3">
                            <h5 class="text-color-table">Список дисциплин семестра:</h5>
                        </div>
                        <div class="table-responsive">
                            <table id="table" class="table table-bordered table-hover">
                                <thead>
                                <tr class="countList p-td-th-xs text-color-table">
                                    <th>№</th>
                                    <th class="text-nowrap">Наименование дисциплины</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${selectedTerm.disciplines}" var="d" varStatus="i">
                                    <tr class="countList text-color-table text-left p-td-th-xs">
                                        <td>${i.count}</td>
                                        <td class="text-nowrap">${d.discipline}</td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${terms[0] == null}">
                                    <tr class="text-color-table text-center">
                                        <td colspan="2">< нет записей ></td>
                                    </tr>
                                </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-11 col-md-5 order-0 order-md-1">
                        <section>
                            <c:if test="${role eq 1}">
                                <div class="row mb-2 mr-md-1 mt-md-5">
                                    <a href="term-create"
                                       class="btn btn-outline-secondary btn btn-block font-size-12px-xs font-size-md font-size-lg"
                                       tabindex="">
                                        Создать семестр
                                    </a>
                                </div>
                                <div class="row mb-2 mr-md-1">
                                    <a onclick="modifyTerm()"
                                       class="btn btn-outline-secondary btn btn-block font-size-12px-xs font-size-md font-size-lg"
                                       tabindex=>
                                        Модифицировать текущий семестр
                                    </a>
                                </div>
                                <div class="row mb-2 mr-md-1">
                                    <a onclick="deleteTerm()"
                                       class="btn btn-outline-secondary btn btn-block font-size-12px-xs font-size-md font-size-lg"
                                       tabindex="">
                                        Удалить текущий семестр
                                    </a>
                                </div>
                            </c:if>
                        </section>
                    </div>
                </div>
            </section>
        </div>
    </div>
</section>
<form action="terms" method="post" id="formDeleteTerm">
    <input type="hidden" id="deleteTerm" name="deleteTerm"/>
</form>
<form action="term-modify" method="get" id="formTermId">
    <input type="hidden" id="termId" name="termId" value="${termId}"/>
</form>
