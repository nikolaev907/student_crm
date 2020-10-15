package database;

import entity.Discipline;
import entity.Term;

import java.sql.*;
import java.util.*;

public class TermDB {
    private static Connection con;
    private static Statement stm;
    private static PreparedStatement preparedStm;
    private static ResultSet rs;

    public static int createTerm(String termDuration) {
        int success = 0;
        String termName = String.format("Семестр%d", findLastIndexInStringTerm() + 1);
        con = ConnectionDB.getConnection();

        try {
            preparedStm = con.prepareStatement("INSERT INTO term (name, duration) VALUES (?, ?)");

            preparedStm.setString(1, termName);
            preparedStm.setString(2, termDuration);
            success = preparedStm.executeUpdate();
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

        return success;
    }

    public static int createTerm_discipline(String disciplineId) {
        int success = 0;
        int termId = TermDB.findLastTermId();
        int term_disciplineId = DisciplineDB.findDisciplineById(disciplineId).getId();
        con = ConnectionDB.getConnection();

        try {
            preparedStm = con.prepareStatement("INSERT INTO term_discipline (id_term, id_discipline) VALUES (?, ?)");
            preparedStm.setInt(1, termId);
            preparedStm.setInt(2, term_disciplineId);
            success = preparedStm.executeUpdate();
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

        return success;
    }

    public static List<Integer> findAllIdTermDisciplineByIdTerm(int idTerm) {
        List<Integer> listTermId = new ArrayList<>();
        con = ConnectionDB.getConnection();

        try {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT id FROM term_discipline WHERE id_term = " + idTerm);
            while (rs.next()) {
                listTermId.add(rs.getInt(1));
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

        return listTermId;
    }

    public static List<Integer> findAllTermId() {
        List<Integer> term = new ArrayList<>();
        con = ConnectionDB.getConnection();

        try {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT id FROM term");
            while (rs.next()) {
                term.add(rs.getInt(1));
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

        return term;
    }


    public static int findLastIndexInStringTerm() {
        int index = 0;
        LinkedList<Term> terms = new LinkedList<>();
        con = ConnectionDB.getConnection();

        try {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT name FROM term");
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
        } finally {
            try {
                rs.close();
                stm.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return index;
    }


    public static int findLastTermId() {
        int id = 0;
        LinkedList<Term> terms = new LinkedList<>();
        con = ConnectionDB.getConnection();

        try {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT id FROM term");
            while (rs.next()) {
                Term term = new Term();
                term.setId(rs.getInt("id"));
                terms.add(term);
            }
            id = terms.removeLast().getId();
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

        return id;
    }

    public static Map<String, Integer> findAllIdTermDisciplineFromMark() {
        Map<String, Integer> map = new LinkedHashMap<>();
        con = ConnectionDB.getConnection();

        try {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM mark");
            while (rs.next()) {
                map.put("id", rs.getInt(1));
                map.put("idStudent", rs.getInt(2));
                map.put("idTermDiscipline", rs.getInt(3));
                map.put("mark", rs.getInt(4));
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

    public static List<Term> findAllActiveTermAndDiscipline() {
        LinkedList<Term> terms = new LinkedList<>();
        con = ConnectionDB.getConnection();
        try {
            preparedStm = con.prepareStatement("SELECT * FROM term_discipline as td\n" +
                    "LEFT JOIN  term as t on td.id_term = t.id\n" +
                    "LEFT JOIN  discipline as d on td.id_discipline = d.id\n" +
                    "WHERE t.status = 1 AND d.status = 1 ORDER BY td.id_term");
            rs = preparedStm.executeQuery();
            int lastTermId = -1;
            while (rs.next()) {
                if (lastTermId == -1) {
                    lastTermId = findLastTermId(terms, rs);
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
                        lastTermId = findLastTermId(terms, rs);
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                preparedStm.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return terms;
    }

    private static int findLastTermId(LinkedList<Term> terms, ResultSet rs) throws SQLException {
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


    public static int modifyTerm(String termIdStr, String duration, String[] disciplinesId) {
        int success = 0;
        int termId = Integer.parseInt(termIdStr);
        con = ConnectionDB.getConnection();

        try {
            preparedStm = con.prepareStatement("UPDATE term SET duration = ? WHERE term.id = ?");
            preparedStm.setString(1, duration);
            preparedStm.setInt(2, termId);
            success = preparedStm.executeUpdate();

            stm = con.createStatement();
            success = stm.executeUpdate("DELETE FROM term_discipline WHERE id_term = " + termId);

            for (String s : disciplinesId) {
                success = stm.executeUpdate("INSERT INTO term_discipline (id_term, id_discipline)" +
                        " VALUES (" + termId + "," + Integer.parseInt(s) + ")");
            }
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

        return success;
    }

    public static int deleteTerm(String id) {
        int success = 0;
        con = ConnectionDB.getConnection();

        try {
            stm = con.createStatement();
            success = stm.executeUpdate("UPDATE term SET status = 0 WHERE id = " + id);
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

        return success;
    }

}
