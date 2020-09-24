<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<div class="row justify-content-start">
    <div class="col-6 col-md-2 col-lg-2 mt-3 mb-1 mb-md-2">
        <a href="/" class="btn btn-outline-secondary btn-block font-size-12px-xs font-size-md font-size-lg">На
            главную</a>
    </div>
</div>
<section>
    <div class="row justify-content-center">
        <div class="col-sm-12 col-lg-8 mt-2">
            <div class="row justify-content-center">
                <div class="col-auto col-md-7 col-lg-7 order-2 order-md-0 order-lg-0">
                    <div class="row justify-content-start">
                        <h5 class="text-color-table mb-1 mb-md-2 mt-2 ml-3">Список дисциплин:</h5>
                    </div>
                    <div class="table-responsive">
                        <table id="table" class="table table-bordered table-hover record_table was-validated">
                            <thead>
                            <tr class="text-center text-color-table p-td-th-xs">
                                <c:if test="${role eq 1}">
                                    <th class="p-td-th-xs"><img class="iconCheck"
                                                                src="../../resources/images/icons-1.0.0-alpha2/icons/check-box.svg"
                                                                alt=""/>
                                    </th>
                                </c:if>
                                <th>№</th>
                                <th>Дисциплина:</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${disces}" var="d" varStatus="i">
                                <c:if test="${d.discipline.length() > 0}">
                                    <tr id="tr${i.count}" class="countList text-color-table p-td-th-xs">
                                        <c:if test="${role eq 1}">
                                            <th class="align-middle">
                                                <div class="custom-control custom-checkbox">
                                                    <input type="checkbox" value="${d.id}"
                                                           class="custom-control-input"
                                                           id="cbx${i.count}"
                                                           required>
                                                    <label class="custom-control-label" for="cbx${i.count}"></label>
                                                </div>
                                            </th>
                                        </c:if>
                                        <td class="align-middle">${i.count}</td>
                                        <td class="text-left text-nowrap align-middle">${d.discipline}</td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                            <c:if test="${disces[0].discipline.length() == null || disces[0].discipline.length() == 0 || disces[0].discipline eq ''}">
                                <td class="text-color-table text-center" colspan="3">< нет записей ></td>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
                <c:if test="${role eq 1}">
                <div class="col-11 col-md-5 col-lg-5 mt-md-3 pt-4 order-0">
                    <section>
                        <div class="row mb-2 mr-md-1">
                            <a href="discipline-create"
                               class="btn btn-outline-secondary btn-block font-size-12px-xs font-size-md font-size-lg"
                               tabindex="">
                                Создать дисциплину
                            </a>
                        </div>
                        <div class="row mb-2 mr-md-1">
                            <a onclick="modifyDiscipline()"
                               class="btn btn-outline-secondary btn-block font-size-12px-xs font-size-md font-size-lg"
                               tabindex="">
                                Модифицировать выбранную дисциплину
                            </a>
                        </div>
                        <div class="row mr-md-1">
                            <a onclick="deleteDiscipline()"
                               class="btn btn-outline-secondary btn-block font-size-12px-xs font-size-md font-size-lg"
                               tabindex="">
                                Удалить выбранные дисциплины
                            </a>
                        </div>
                    </section>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</section>
<form id="formModifyDiscipline" method="get" action="discipline-modify">
    <input type="hidden" id="idModifyDisc" name="idModifyDisc">
</form>
<form id="formDeleteDiscipline" method="post" action="disciplines">
    <input type="hidden" id="idDeleteDisc" name="idDeleteDisc">
</form>
