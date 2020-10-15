package database;

import entity.Discipline;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplineDB {
    private static Connection con;
    private static Statement stm;
    private static PreparedStatement preparedStm;
    private static ResultSet rs;

    public static Discipline findDisciplineById(String id) {
        Discipline discipline = new Discipline();
        con = ConnectionDB.getConnection();

        try {
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM discipline WHERE status = 1 AND id = " + id);
            while (rs.next()) {
                discipline.setId(rs.getInt(1));
                discipline.setDiscipline(rs.getString("discipline"));
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
        return discipline;
    }

    public static int createDiscipline(String name) {
        int i = 0;
        con = ConnectionDB.getConnection();

        try {
            preparedStm = con.prepareStatement("INSERT INTO discipline (discipline) VALUES(?)");
            preparedStm.setString(1, name);
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

    public static int modifyDiscipline(String name, String id) {
        int i = 0;
        con = ConnectionDB.getConnection();

        try {
            preparedStm = con.prepareStatement("UPDATE discipline SET discipline = ? WHERE id = ?");
            preparedStm.setString(1, name);
            preparedStm.setString(2, id);
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

    public static int createMark(int studId, int tdId) {
        int success = 0;
        con = ConnectionDB.getConnection();

        try {
            stm = con.createStatement();
            success = stm.executeUpdate("INSERT INTO mark VALUES (null, " + studId + "," + tdId + ", null )");
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

    public static List<Discipline> findAllActiveDisciplines() {
        ArrayList<Discipline> disciplines = new ArrayList<>();
        con = ConnectionDB.getConnection();

        try {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT * FROM discipline WHERE status = 1");
            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt(1));
                discipline.setDiscipline(rs.getString("discipline"));
                disciplines.add(discipline);
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
        return disciplines;
    }

    public static void updateMark(int markId, int mark) {
        con = ConnectionDB.getConnection();

        try {
            stm = con.createStatement();
            stm.execute("UPDATE mark SET mark = " + mark + " WHERE id = " + markId);
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
    }

    public static double getAverageGradeByIdMark(String grades) {
        double avgMark = 0;
        con = ConnectionDB.getConnection();

        try {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT AVG(m.mark) FROM mark m WHERE m.id IN (" + grades + ")");
            if (rs.next()) {
                avgMark = rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return avgMark;
    }

    public static int deleteDiscipline(String ids) {
        int i = 0;
        con = ConnectionDB.getConnection();

        try {
            stm = con.createStatement();
            i = stm.executeUpdate("UPDATE discipline SET status = 0 WHERE (id IN (" + ids + "))");
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