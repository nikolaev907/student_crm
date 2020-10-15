package controllers;

import database.DisciplineDB;
import database.StudentDB;
import database.TermDB;
import entity.Discipline;
import entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TermCreatingController", urlPatterns = "/term-create")
public class TermCreatingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Discipline> disciplines = DisciplineDB.findAllActiveDisciplines();
        req.setAttribute("disciplines", disciplines);
        req.setAttribute("currentPage", "/WEB-INF/jsp/termCreating.jsp");
        req.setAttribute("titlePage", "Создание семестра");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] disciplines = req.getParameterValues("disciplines");
        int term_disciplineAdd = 0;
        String week = req.getParameter("week");
        TermDB.createTerm(week);
        int termId = TermDB.findLastTermId();
        for (String idDiscipline : disciplines) {
            term_disciplineAdd = TermDB.createTerm_discipline(idDiscipline);
        }
        final List<Student> allActiveStudents = StudentDB.findAllActiveStudents();
        int successCreateMark = 0;
        for (int tdId : TermDB.findAllIdTermDisciplineByIdTerm(termId)) {
            for (Student stud : allActiveStudents) {
                int studId = stud.getId();
                successCreateMark = DisciplineDB.createMark(studId, tdId);
            }
        }

        req.getSession().setAttribute("termAdd", term_disciplineAdd);
        resp.sendRedirect("terms?term_disciplineAdd=" + term_disciplineAdd + "&successCreateMark=" + successCreateMark);
    }
}
