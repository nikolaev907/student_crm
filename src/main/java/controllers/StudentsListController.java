package controllers;

import database.StudentDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudentsListController", urlPatterns = "/students")
public class StudentsListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("stud", StudentDB.findAllActiveStudents());
        req.setAttribute("currentPage", "/WEB-INF/jsp/studentsList.jsp");
        req.setAttribute("titlePage", "Список студентов");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("idDeleteStudent");
        int success = StudentDB.deleteStudent(id);
        resp.sendRedirect("students?success=" + success + "&id=" + id);
    }
}
