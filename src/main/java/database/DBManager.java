package database;

import entity.Discipline;
import entity.Student;
import entity.Term;

import java.sql.*;
import java.util.*;

public class DBManager {
    private static Connection con;
    private static PreparedStatement createDisciplinePrepareStm;
    private static PreparedStatement modifyDisciplinePrepareStm;
    private static PreparedStatement createStudentPrepareStm;
    private static PreparedStatement modifyStudentPrepareStm;
    private static PreparedStatement getAccountByLoginPasswordRole;
    private static PreparedStatement getActiveTermAndDiscipline;
    private static PreparedStatement createTerm;
    private static PreparedStatement createTerm_discipline;
    private static PreparedStatement modifyTerm;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/student_crm?serverTimezone=UTC&autoReconnect=true", "sammy", "password");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_crm?serverTimezone=UTC&autoReconnect=true", "root", "root123456");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/student_crm?serverTimezone=UTC&autoReconnect=true", "root", "root123456");
            /*XAMPP ( "root", "")*/
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_crm?serverTimezone=UTC", "root", "");

            createDisciplinePrepareStm = con.prepareStatement("INSERT INTO discipline (discipline) VALUES(?)");
            modifyDisciplinePrepareStm = con.prepareStatement("UPDATE discipline SET discipline = ? WHERE id = ?");
            createStudentPrepareStm = con.prepareStatement("INSERT INTO student (name, surname, group_name, date_receipt) VALUES(?, ?, ?, ?)");
            modifyStudentPrepareStm = con.prepareStatement("UPDATE student SET name = ?, surname = ?, group_name = ?, date_receipt = ? WHERE id = ?");
            getAccountByLoginPasswordRole = con.prepareStatement("SELECT * FROM user_role " +
                    "LEFT JOIN user ON user_role.id_user = user.id " +
                    "WHERE user.login = ? AND user.password = ? AND user_role.id_role = ?");
            getActiveTermAndDiscipline = con.prepareStatement("SELECT * FROM term_discipline as td\n" +
                    "LEFT JOIN  term as t on td.id_term = t.id\n" +
                    "LEFT JOIN  discipline as d on td.id_discipline = d.id\n" +
                    "WHERE t.status = 1 AND d.status = 1 ORDER BY td.id_term");
            createTerm = con.prepareStatement("INSERT INTO term (name, duration) VALUES (?, ?)");
            createTerm_discipline = con.prepareStatement("INSERT INTO term_discipline (id_term, id_discipline) VALUES (?, ?)");
            modifyTerm = con.prepareStatement("UPDATE term SET duration = ? WHERE term.id = ?");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Discipline> getAllActiveDisciplines() {
        ArrayList<Discipline> disciplines = new ArrayList<>();
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM discipline WHERE status = 1");
            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt(1));
                discipline.setDiscipline(rs.getString("discipline"));
                disciplines.add(discipline);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplines;
    }

    public static Discipline getDisciplineById(String id) {
        Discipline discipline = new Discipline();

        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM discipline WHERE status = 1 AND id = " + id);
            while (rs.next()) {
                discipline.setId(rs.getInt(1));
                discipline.setDiscipline(rs.getString("discipline"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discipline;
    }

    public static int createDiscipline(String name) {
        int i = 0;
        try {
            PreparedStatement prepareStm = createDisciplinePrepareStm;
            prepareStm.setString(1, name);
            i = prepareStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static int modifyDiscipline(String name, String id) {
        int i = 0;
        try {
            PreparedStatement stm = modifyDisciplinePrepareStm;
            stm.setString(1, name);
            stm.setString(2, id);
            i = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static int deleteDiscipline(String ids) {
        int i = 0;
        try {
            Statement statement = con.createStatement();
            i = statement.executeUpdate("UPDATE discipline SET status = 0 WHERE (id IN (" + ids + "))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static List<Student> getAllActiveStudents() {
        List<Student> students = new ArrayList<>();
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM student WHERE status = 1");
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
        }
        return students;
    }

    public static int createStudent(String name, String surname, String group_name, String date_receipt) {
        int i = 0;
        try {
            PreparedStatement stm = createStudentPrepareStm;
            stm.setString(1, name);
            stm.setString(2, surname);
            stm.setString(3, group_name);
            stm.setString(4, date_receipt);
            i = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static int modifyStudent(String name, String surname, String group_name, String date_receipt, String id) {
        int i = 0;
        try {
            PreparedStatement stm = modifyStudentPrepareStm;
            stm.setString(1, name);
            stm.setString(2, surname);
            stm.setString(3, group_name);
            stm.setString(4, date_receipt);
            stm.setString(5, id);
            i = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static int deleteStudent(String ids) {
        int i = 0;
        try {
            Statement statement = con.createStatement();
            i = statement.executeUpdate("UPDATE student SET status = 0 WHERE (id IN (" + ids + "))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static Student getStudentById(String id) {
        Student student = new Student();
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM student WHERE status = 1 AND id = " + id);
            while (rs.next()) {
                student.setId(rs.getInt(1));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setGroup(rs.getString("group_name"));
                student.setDate_receipt(rs.getDate("date_receipt"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public static Map<String, Object> extractStudentTermsDisciplines(int idStudent, int idTerm) {
        Map<String, Object> map = new HashMap<>();
        Student student = new Student();
        List<Integer> marks = new ArrayList<>();
        List<Integer> marksId = new ArrayList<>();
        List<Discipline> disciplines = new ArrayList<>();

        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT s.*, t.id, t.name, m.mark, d.discipline, m.id FROM mark m " +
                    "LEFT JOIN term_discipline td ON" +
                    " td.id = m.id_term_discipline LEFT JOIN term t ON" +
                    " t.id = td.id_term LEFT JOIN student s ON" +
                    " s.id = m.id_student LEFT JOIN discipline d ON" +
                    " td.id_discipline = d.id WHERE s.id = " + idStudent + " AND  td.id_term = " + idTerm +
                    " AND t.status = 1 AND d.status = 1 AND s.status = 1");
            if (!rs.first()) {
                Student studentById = DBManager.getStudentById(idStudent + "");
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
        }
        return map;
    }

    public static boolean getAccountByLoginPasswordRole(String login, String pass, String role) {
        try {
            getAccountByLoginPasswordRole.setString(1, login);
            getAccountByLoginPasswordRole.setString(2, pass);
            getAccountByLoginPasswordRole.setString(3, role);
            ResultSet rs = getAccountByLoginPasswordRole.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Term> getAllActiveTermAndDiscipline() {
        LinkedList<Term> terms = new LinkedList<>();
        try {
            ResultSet rs = getActiveTermAndDiscipline.executeQuery();
            int lastTermId = -1;
            while (rs.next()) {
                if (lastTermId == -1) {
                    lastTermId = getLastTermId(terms, rs);
                } else {
                    int currentTermId = rs.getInt("id_term");
                    if (currentTermId == lastTermId) {
                        Discipline discipline = new Discipline();
                        discipline.setId(rs.getInt("id_discipline"));
                        discipline.setDiscipline(rs.getString("discipline"));

                        Term lastTerm = terms.removeLast();
                        lastTerm.addDiscipline(discipline);
                        terms.add(lastTerm);
                        lastTermId = rs.getInt("id_term");
                    } else {
                        lastTermId = getLastTermId(terms, rs);
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return terms;
    }

    private static int getLastTermId(LinkedList<Term> terms, ResultSet rs) throws SQLException {
        int lastTermId;
        Term term = new Term();
        term.setId(rs.getInt("id_term"));
        term.setName(rs.getString("name"));
        term.setDuration(rs.getString("duration"));

        Discipline discipline = new Discipline();
        discipline.setId(rs.getInt("id_discipline"));
        discipline.setDiscipline(rs.getString("discipline"));

        term.addDiscipline(discipline);
        terms.add(term);
        lastTermId = rs.getInt("id_term");
        return lastTermId;
    }

    public static int getLastIndexInStringTerm() {
        LinkedList<Term> terms = new LinkedList<>();
        int index = 0;
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT name FROM term");
            while (rs.next()) {
                Term term = new Term();
                term.setName(rs.getString("name"));
                terms.add(term);
            }
            if (!terms.isEmpty()) {
                String strIndex = terms.removeLast().getName().split("Семестр")[1];
                index = Integer.parseInt(strIndex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return index;
    }

    public static int createTerm(String termDuration) {
        String termName = String.format("Семестр%d", getLastIndexInStringTerm() + 1);
        int success = 0;
        try {
            createTerm.setString(1, termName);
            createTerm.setString(2, termDuration);
            success = createTerm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public static int extractLastTermId() {
        LinkedList<Term> terms = new LinkedList<>();
        int id = 0;
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id FROM term");
            while (rs.next()) {
                Term term = new Term();
                term.setId(rs.getInt("id"));
                terms.add(term);
            }
            id = terms.removeLast().getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static int createTerm_discipline(String disciplineId) {
        int success = 0;
        int termId = DBManager.extractLastTermId();
        int term_disciplineId = DBManager.getDisciplineById(disciplineId).getId();
        try {
            createTerm_discipline.setInt(1, termId);
            createTerm_discipline.setInt(2, term_disciplineId);
            success = createTerm_discipline.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public static int modifyTerm(String termIdStr, String duration, String[] disciplinesId) {
        int success = 0;
        int termId = Integer.parseInt(termIdStr);
        try {
            modifyTerm.setString(1, duration);
            modifyTerm.setInt(2, termId);
            success = modifyTerm.executeUpdate();

            Statement st = con.createStatement();
            success = st.executeUpdate("DELETE FROM term_discipline WHERE id_term = " + termId);

            for (String s : disciplinesId) {
                success = st.executeUpdate("INSERT INTO term_discipline (id_term, id_discipline)" +
                        " VALUES (" + termId + "," + Integer.parseInt(s) + ")");
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return success;
    }

    public static int deleteTerm(String id) {
        int success = 0;
        try {
            Statement st = con.createStatement();
            success = st.executeUpdate("UPDATE term SET status = 0 WHERE id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public static List<Integer> getAllIdTermDisciplineByIdTerm(int idTerm) {
        List<Integer> listTermId = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM term_discipline WHERE id_term = " + idTerm);
            while (rs.next()) {
                listTermId.add(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTermId;
    }

    public static int createMark(int studId, int tdId) {
        int success = 0;
        try {
            Statement st = con.createStatement();
            success = st.executeUpdate("INSERT INTO mark VALUES (null, " + studId + "," + tdId + ", null )");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public static List<Integer> getAllTermDisciplineId() {
        List<Integer> term_discipline = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM term_discipline");
            while (rs.next()) {
                term_discipline.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return term_discipline;
    }

    public static List<Integer> getAllTermId() {
        List<Integer> term = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM term");
            while (rs.next()) {
                term.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return term;
    }

    public static Map<String, Integer> getAllIdTermDisciplineFromMark() {
        Map<String, Integer> map = new LinkedHashMap<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM mark");
            while (rs.next()) {
                map.put("id", rs.getInt(1));
                map.put("idStudent", rs.getInt(2));
                map.put("idTermDiscipline", rs.getInt(3));
                map.put("mark", rs.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }

    public static void updateMark(int markId, int mark) {
        try {
            Statement st = con.createStatement();
            st.execute("UPDATE mark SET mark = " + mark + " WHERE id = " + markId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getRoles() {
        ArrayList<String> roles = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT role FROM role");
            while (rs.next()) {
                roles.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    public static double getAverageGradeByIdMark(String grades) {
        double avgMark = 0;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT AVG(m.mark) FROM mark m WHERE m.id IN (" + grades + ")");
            if (rs.next()) {
                avgMark = rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avgMark;
    }
}

