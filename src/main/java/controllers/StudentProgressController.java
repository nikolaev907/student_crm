package controllers;

import database.DisciplineDB;
import database.StudentDB;
import database.TermDB;
import entity.Discipline;
import entity.Term;
import utils.AverageMarkUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(name = "StudentProgressController", urlPatterns = "/student-progress")
public class StudentProgressController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AverageMarkUtils averageMarkUtils = new AverageMarkUtils();
        Map<String, Object> data;
        String idProgressStudent = req.getParameter("idProgressStudent");
        int idStudent = Integer.parseInt(idProgressStudent);
        List<Term> terms = TermDB.findAllActiveTermAndDiscipline();
        String idTermStr = req.getParameter("idTerm");
        double averageGradeByIdMark;

        if (idTermStr != null) {
            int idTerm = Integer.parseInt(req.getParameter("idTerm"));
            data = StudentDB.findTermsDisciplinesOfStudentByStudentIdAndTermId(idStudent, idTerm);
            averageGradeByIdMark = averageMarkUtils.getAverageMark(data);
            String[] setGrades = req.getParameterValues("setGrades");
            AtomicInteger currentMark = new AtomicInteger();
            req.setAttribute("averageMark", averageGradeByIdMark);

            if (setGrades != null) {
                data.entrySet().iterator().forEachRemaining(es -> {
                    if (es.getKey().equals("marksId")) {
                        List marksId = (ArrayList) es.getValue();
                        for (int i = 0; i < marksId.size(); i++) {
                            int grade = Integer.parseInt(setGrades[i]);
                            DisciplineDB.updateMark((Integer) marksId.get(i), grade);
                        }
                    }
                    if (es.getKey().equals("disciplines")) {
                        List disciplines = (ArrayList) es.getValue();
                        Iterator<Integer> grades = Arrays.stream(setGrades).map(Integer::parseInt).iterator();
                        for (Object o : disciplines) {
                            Discipline discipline = (Discipline) o;
                            currentMark.set(grades.next());
                            if (currentMark.get() != 0) {
                                discipline.setMark(currentMark.get());
                            }
                        }
                    }

                    data.put("marks", setGrades);
                });
                averageGradeByIdMark = averageMarkUtils.getAverageMark(data);
                req.setAttribute("grades", setGrades);
                req.setAttribute("averageMark", averageGradeByIdMark);
            }
            req.setAttribute("idTerm", idTerm);
        } else {
            List<Integer> allTermId = TermDB.findAllTermId();
            int firstTermId = allTermId.get(0);
            data = StudentDB.findTermsDisciplinesOfStudentByStudentIdAndTermId(idStudent, firstTermId);
            averageGradeByIdMark = averageMarkUtils.getAverageMark(data);

            req.setAttribute("idTerm", firstTermId);
            req.setAttribute("averageMark", averageGradeByIdMark);
        }
        req.setAttribute("idProgressStudent", idProgressStudent);
        req.setAttribute("data", data);
        req.setAttribute("terms", terms);
        req.setAttribute("currentPage", "/WEB-INF/jsp/studentProgress.jsp");
        req.setAttribute("titlePage", "Успеваемость студента");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
    }
}