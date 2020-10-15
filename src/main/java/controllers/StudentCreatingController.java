package controllers;

import database.DisciplineDB;
import database.StudentDB;
import database.TermDB;
import entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "StudentCreatingController", urlPatterns = "/student-create")
public class StudentCreatingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("currentPage", "/WEB-INF/jsp/studentCreating.jsp");
        req.setAttribute("titlePage", "Создание студента");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int successCreateStudent = 0;
        int isEmptyTerm = 1;
        int isEmptyDiscipline = 1;
        boolean isIdTermDiscipline = false;
        boolean isLastIdStudent = false;
        String surname = req.getParameter("surname").trim();
        String name = req.getParameter("name").trim();
        String group = req.getParameter("group").trim();
        String date = req.getParameter("date_receipt").trim();

        if (!DisciplineDB.findAllActiveDisciplines().isEmpty()) {
            isEmptyDiscipline = 0;
            if (!TermDB.findAllActiveTermAndDiscipline().isEmpty()) {
                isEmptyTerm = 0;
                successCreateStudent = StudentDB.createStudent(name, surname, group, date);

                List<Student> allActiveStudents = StudentDB.findAllActiveStudents();
                List<Integer> allTermDisciplineId = StudentDB.findAllTermDisciplineId();
                Map<String, Integer> allFromMark = TermDB.findAllIdTermDisciplineFromMark();
                int lastStudentId = 0;
                if (allActiveStudents.stream().map(Student::getId).max(Integer::compareTo).isPresent()) {
                    lastStudentId = allActiveStudents.stream().map(Student::getId).max(Integer::compareTo).get();
                }
                List<Integer> getAllTermId = TermDB.findAllTermId();
                if (!allTermDisciplineId.isEmpty()) {
                    for (int tId : getAllTermId) {
                        List<Integer> termDisciplineIdByIdTerm = TermDB.findAllIdTermDisciplineByIdTerm(tId);
                        for (int tdId : termDisciplineIdByIdTerm) {
                            if (!allFromMark.isEmpty()) {
                                isIdTermDiscipline = allFromMark.get("idTermDiscipline").equals(tdId);
                                isLastIdStudent = allFromMark.get("idStudent").equals(lastStudentId);
                            }
                            for (Student stud : allActiveStudents) {
                                int studId = stud.getId();
                                if (isIdTermDiscipline && isLastIdStudent) {
                                    DisciplineDB.createMark(studId, tdId);
                                }
                            }
                            DisciplineDB.createMark(lastStudentId, tdId);
                        }
                    }
                }

                req.getSession().setAttribute("date", date /*reformattedStr*/);
                req.getSession().setAttribute("name", name);
                req.getSession().setAttribute("surname", surname);
                req.getSession().setAttribute("myDate", date /*reformattedStr*/);
            }
        }
        if (isEmptyDiscipline == 1) {
            resp.sendRedirect("/discipline-create?&emptyDiscipline=" + isEmptyDiscipline);
        } else if (isEmptyTerm == 1) {
            resp.sendRedirect("/term-create?isEmptyTerm=" + isEmptyTerm);
        } else {
            resp.sendRedirect("/students?successCreateStudent=" + successCreateStudent
                    + "&date=" + date);
        }
    }
}
