package controllers;

import database.DBManager;
import entity.Term;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TermsListController", urlPatterns = "/terms")
public class TermsListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Term> terms = DBManager.getAllActiveTermAndDiscipline();
        req.setAttribute("terms", terms);
        String selTerm = req.getParameter("selTerm");
        if (selTerm != null) {
            for (Term t : terms) {
                String id = t.getId() + "";
                if (id.equals(selTerm)) {
                    req.setAttribute("selectedTerm", t);
                    req.setAttribute("termId", id);
                }
            }
        } else {
            final String term_disciplineAdd = req.getParameter("term_disciplineAdd");
            if (term_disciplineAdd != null) {
                req.setAttribute("selectedTerm", terms.get(terms.size() - 1));
                req.setAttribute("termId", terms.get(terms.size() - 1).getId());
            } else {
                if (!terms.isEmpty()) {
                    req.setAttribute("selectedTerm", terms.get(0));
                    req.setAttribute("termId", terms.get(0).getId());
                }
            }
        }
        req.setAttribute("titlePage", "Список семестров");
        req.setAttribute("currentPage", "/WEB-INF/jsp/termsList.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("deleteTerm");
        int success = DBManager.deleteTerm(id);
//        success += DBManager.deleteTermDisciplineById(id);
        resp.sendRedirect("terms?success=" + success);
    }
}
