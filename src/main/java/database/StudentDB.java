package database;

import entity.Discipline;
import entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDB {
    private static Connection con;
    private static Statement stm;
    private static PreparedStatement preparedStm;
    private static ResultSet rs;

    public static int createStudent(String name, String surname, String group_name, String date_receipt) {
        int i = 0;
        con = ConnectionDB.getConnection();

        try {
            preparedStm = con.prepareStatement("INSERT INTO student (name, surname, group_name, date_receipt) VALUES(?, ?, ?, ?)");
            preparedStm.setString(1, name);
            preparedStm.setString(2, surname);
            preparedStm.setString(3, group_name);
            preparedStm.setString(4, date_receipt);
            i = preparedStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStm.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return i;
    }

    public static int modifyStudent(String name, String surname, String group_name, String date_receipt, String id) {
        int i = 0;
        con = ConnectionDB.getConnection();

        try {
            preparedStm = con.prepareStatement("UPDATE student SET name = ?, surname = ?, group_name = ?, date_receipt = ? WHERE id = ?");
            preparedStm.setString(1, name);
            preparedStm.setString(2, surname);
            preparedStm.setString(3, group_name);
            preparedStm.setString(4, date_receipt);
            preparedStm.setString(5, id);
            i = preparedStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStm.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return i;
    }


    public static Student getStudentById(String id) {
        con = ConnectionDB.getConnection();

        Student student = new Student();
        try {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM student WHERE status = 1 AND id = " + id);
            while (rs.next()) {
                student.setId(rs.getInt(1));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setGroup(rs.getString("group_name"));
                student.setDate_receipt(rs.getDate("date_receipt"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stm.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return student;
    }


    public static Map<String, Object> findTermsDisciplinesOfStudentByStudentIdAndTermId(int idStudent, int idTerm) {
        Map<String, Object> map = new HashMap<>();
        Student student = new Student();
        List<Integer> marks = new ArrayList<>();
        List<Integer> marksId = new ArrayList<>();
        List<Discipline> disciplines = new ArrayList<>();
        con = ConnectionDB.getConnection();

        try {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT s.*, t.id, t.name, m.mark, d.discipline, m.id FROM mark m " +
                    "LEFT JOIN term_discipline td ON" +
                    " td.id = m.id_term_discipline LEFT JOIN term t ON" +
                    " t.id = td.id_term LEFT JOIN student s ON" +
                    " s.id = m.id_student LEFT JOIN discipline d ON" +
                    " td.id_discipline = d.id WHERE s.id = " + idStudent + " AND  td.id_term = " + idTerm +
                    " AND t.status = 1 AND d.status = 1 AND s.status = 1");
            if (!rs.first()) {
                Student studentById = StudentDB.getStudentById(idStudent + "");
                if (studentById.getId() != 0) {
                    map.put("student", studentById);
                }
            } else {
                rs.beforeFirst();
                while (rs.next()) {
                    if (student.getId() == 0) {
                        student.setId(rs.getInt(1));
                        student.setName(rs.getString("name"));
                        student.setSurname(rs.getString("surname"));
                        student.setGroup(rs.getString("group_name"));
                        student.setDate_receipt(rs.getDate("date_receipt"));
                    }
                    Discipline discipline = new Discipline();
                    discipline.setMark(rs.getInt(9));
                    discipline.setDiscipline(rs.getString(10));
                    disciplines.add(discipline);

                    marks.add(rs.getInt(9));
                    marksId.add(rs.getInt(11));
                }
                map.put("student", student);
                map.put("disciplines", disciplines);
                map.put("marks", marks);
                map.put("marksId", marksId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stm.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return map;
    }

    public static List<Student> findAllActiveStudents() {
        List<Student> students = new ArrayList<>();
        con = ConnectionDB.getConnection();

        try {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM student WHERE status = 1");
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt(1));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setGroup(rs.getString("group_name"));
                student.setDate_receipt(rs.getDate("date_receipt"));

                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stm.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return students;
    }

    public static List<Integer> findAllTermDisciplineId() {
        List<Integer> term_discipline = new ArrayList<>();
        con = ConnectionDB.getConnection();

        try {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT id FROM term_discipline");
            while (rs.next()) {
                term_discipline.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                rs.close();
                stm.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return term_discipline;
    }

    public static int deleteStudent(String ids) {
        int i = 0;
        con = ConnectionDB.getConnection();

        try {
            stm = con.createStatement();
            i = stm.executeUpdate("UPDATE student SET status = 0 WHERE (id IN (" + ids + "))");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stm.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return i;
    }

}
