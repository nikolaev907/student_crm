package controllers;

import database.LoginDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> roles = LoginDB.getRoles();

        req.setAttribute("roles", roles);
        req.setAttribute("currentPage", "/WEB-INF/jsp/login.jsp");
        req.setAttribute("titlePage", "Авторизация");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> roles = LoginDB.getRoles();

        String login = req.getParameter("login");
        String password = req.getParameter("pass");
        String role = req.getParameter("role");

        boolean isSuccessAccess = LoginDB.findAccountByLoginPasswordRole(login, password, role);
        if (isSuccessAccess) {
            req.getSession().setAttribute("isLogin", "1");
            req.getSession().setAttribute("role", role);
            req.getSession().setAttribute("username", login);
            req.setAttribute("currentPage", "/WEB-INF/jsp/home.jsp");
            resp.sendRedirect("/");
        } else {
            req.setAttribute("errorMessage", "1");
            req.setAttribute("roles", roles);
            req.setAttribute("currentPage", "/WEB-INF/jsp/login.jsp");
            req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
        }
    }
}
