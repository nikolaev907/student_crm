package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginDB {
    private static Connection con;
    private static Statement stm;
    private static PreparedStatement preparedStm;
    private static ResultSet rs;

    public static List<String> getRoles() {
        ArrayList<String> roles = new ArrayList<>();
        con = ConnectionDB.getConnection();

        try {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT role FROM role");
            while (rs.next()) {
                roles.add(rs.getString(1));
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
        return roles;
    }

    public static boolean findAccountByLoginPasswordRole(String login, String pass, String role) {
        con = ConnectionDB.getConnection();

        try {
            preparedStm = con.prepareStatement("SELECT * FROM user_role " +
                    "LEFT JOIN user ON user_role.id_user = user.id " +
                    "WHERE user.login = ? AND user.password = ? AND user_role.id_role = ?");

            preparedStm.setString(1, login);
            preparedStm.setString(2, pass);
            preparedStm.setString(3, role);
            rs = preparedStm.executeQuery();
            if (rs.next()) {
                return true;
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
        return false;
    }
}
