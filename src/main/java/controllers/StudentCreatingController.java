package controllers;

import database.DBManager;
import entity.Discipline;
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
        String surname = req.getParameter("surname").trim();
        String name = req.getParameter("name").trim();
        String group = req.getParameter("group").trim();
        String date = req.getParameter("date_receipt").trim();
       /* SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");*/
//        SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
//        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        int successCreateStudent = 0;
        int isEmptyTerm = 1;
        int isEmptyDiscipline = 1;
        boolean isIdTermDiscipline = false;
        boolean isLastIdStudent = false;

//        try {
//            String reformattedStr = myFormat.format(fromUser.parse(date));
//        int emptyDiscipline = 1;
        if (!DBManager.getAllActiveDisciplines().isEmpty()) {
            isEmptyDiscipline = 0;
            if (!DBManager.getAllActiveTermAndDiscipline().isEmpty()) {
                isEmptyTerm = 0;
                successCreateStudent = DBManager.createStudent(name, surname, group, date /*reformattedStr*/);

                List<Student> allActiveStudents = DBManager.getAllActiveStudents();
                List<Integer> allTermDisciplineId = DBManager.getAllTermDisciplineId();
                Map<String, Integer> allFromMark = DBManager.getAllIdTermDisciplineFromMark();
                int lastStudentId = 0;
                if (allActiveStudents.stream().map(Student::getId).max(Integer::compareTo).isPresent()) {
                    lastStudentId = allActiveStudents.stream().map(Student::getId).max(Integer::compareTo).get();
                }
                List<Integer> getAllTermId = DBManager.getAllTermId();
                if (!allTermDisciplineId.isEmpty()) {
    //                emptyDiscipline = 0;
                    for (int tId : getAllTermId) {
                        List<Integer> termDisciplineIdByIdTerm = DBManager.getAllIdTermDisciplineByIdTerm(tId);
                        for (int tdId : termDisciplineIdByIdTerm) {
                            if (!allFromMark.isEmpty()) {
                                isIdTermDiscipline = allFromMark.get("idTermDiscipline").equals(tdId);
                                isLastIdStudent = allFromMark.get("idStudent").equals(lastStudentId);
                            }
                            for (Student stud : allActiveStudents) {
                                int studId = stud.getId();
                                if (isIdTermDiscipline && isLastIdStudent) {
                                    DBManager.createMark(studId, tdId);
                                }
                            }
                            DBManager.createMark(lastStudentId, tdId);
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
                    + "&date=" + date /*reformattedStr*/);
        }
       /* } catch (ParseException e) {
            e.printStackTrace();
        }*/
    }
}
