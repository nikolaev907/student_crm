package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Main {
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/student_crm?serverTimezone=UTC", "root", "root123456");
            PreparedStatement stmt = con.prepareStatement("INSERT INTO student_crm.discipline(discipline) VALUES(?)");
            stmt.setString(1,"Test");
            int i = stmt.executeUpdate();
            System.out.println(i + ": records inserted");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
