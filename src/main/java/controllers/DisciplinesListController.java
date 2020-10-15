package controllers;

import database.DisciplineDB;
import entity.Discipline;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DisciplinesListController", urlPatterns = "/disciplines")
public class DisciplinesListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Discipline> disciplines = DisciplineDB.findAllActiveDisciplines();
        req.setAttribute("disciplines", disciplines);
        req.setAttribute("currentPage", "/WEB-INF/jsp/disciplinesList.jsp");
        req.setAttribute("titlePage", "Список дисиплин");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("idDeleteDisc");
        int success = DisciplineDB.deleteDiscipline(id);
        resp.sendRedirect("disciplines?success=" + success + "&id=" + id);
    }
}
