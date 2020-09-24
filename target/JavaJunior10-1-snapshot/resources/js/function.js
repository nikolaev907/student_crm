function extractedCheckedInput() {
    let items = $("input[type=checkbox]:checked");
    if (items.length === 0) {
        alert("Пожалуйста, выберите одну строку в списке");
        return;
    }
    if (items.length > 1) {
        alert("Пожалуйста, выберите ТОЛЬКО одну строку в списке");
        return;
    }
    return items;
}

function extractedCheckedInputs() {
    let items = $("input[type=checkbox]:checked");
    if (items.length === 0) {
        alert("Пожалуйста, выберите хотя бы одну строку в списке");
        return;
    }

    return items;
}

function selectItems() {
    let items = extractedCheckedInputs();
    let ids = null;
    for (let i = 0; i < items.length; i++) {
        if (ids == null) {
            ids = "'" + $(items[i]).attr("value");
        } else {
            ids = ids + "','" + $(items[i]).attr("value");
        }
        if (i === items.length - 1) {
            ids = ids + "'"
        }
    }
    return ids;
}

function modifyDiscipline() {
    let items = extractedCheckedInput();
    let id = $(items[0]).attr("value");
    $('#formModifyDiscipline input').val(id);
    $('#formModifyDiscipline').submit();

}

function deleteDiscipline() {
    let ids = selectItems();

    $('#formDeleteDiscipline input').val(ids);
    $('#formDeleteDiscipline').submit();
}

function modifyStudent() {
    let items = extractedCheckedInput();
    let id = $(items[0]).attr("value");
    $('#formModifyStudent input').val(id);
    $('#formModifyStudent').submit();

}

function progressStudent() {
    let items = extractedCheckedInput();
    let id = $(items[0]).attr("value");
    $('#formProgressStudent input').val(id);
    $('#formProgressStudent').submit();

}

function deleteStudent() {
    let ids = selectItems();

    $('#formDeleteStudent input').val(ids);
    $('#formDeleteStudent').submit();
}

function deleteTerm() {
    let item = $("#op").val();

    $('#formDeleteTerm input').val(item);
    $('#formDeleteTerm').submit();
}

function modifyTerm() {
    $('#formTermId').submit();
}

function emptyTerm() {
    $('#modalTerm').modal('show');
}

function emptyDiscipline() {
    $('#modalDiscipline').modal('show');
}

$(document).ready(function () {
    $('.record_table tr').click(function (event) {
        if (event.target.type !== 'checkbox') {
            $(':checkbox', this).trigger('click');
        }
        $("input[type='checkbox']").change(function () {
            if ($(this).is(":checked")) {
                $(this).closest('tr').addClass("colorize");
            } else {
                $(this).closest('tr').removeClass("colorize");
            }
        });
    });
});

$(function () {
    /* $("#receiptDate").datepicker(
         {dateFormat: "dd/mm/yy"}
     ).val();*/
});

