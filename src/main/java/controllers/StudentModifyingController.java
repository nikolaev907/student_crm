package controllers;

import database.StudentDB;
import entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudentModifyingController", urlPatterns = "/student-modify")
public class StudentModifyingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idModifyStudent = req.getParameter("idModifyStudent");
        Student student = StudentDB.getStudentById(idModifyStudent);
        req.setAttribute("student", student);
        req.setAttribute("currentPage", "/WEB-INF/jsp/studentModifying.jsp");
        req.setAttribute("titlePage", "Редактирование студента");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String surname = req.getParameter("surname").trim();
        String name = req.getParameter("name").trim();
        String group = req.getParameter("group").trim();
        String date = req.getParameter("date_receipt");
        String id = req.getParameter("id");
        req.setAttribute("success", StudentDB.modifyStudent(name, surname, group, date, id));
        req.setAttribute("name", name);
        req.setAttribute("surname", surname);
        req.setAttribute("date", date);

        resp.sendRedirect("/students");
    }
}
