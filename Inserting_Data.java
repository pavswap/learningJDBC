package test;

import java.sql.*;

public class Inserting_Data {

    private static final String url = "/***/";
    private static final String username = "/***/";
    private static final String password = "/***/";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.driver");
        } catch (ClassNotFoundException e) {

            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();

            stmt.executeUpdate("""
                    INSERT INTO list(name, age)
                    VALUES('Paavan', 19);
                    """);

            ResultSet res2 = stmt.executeQuery("SELECT id FROM list");

            while(res2.next()) {

                int id = res2.getInt("id");

                System.out.println(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } 
    }
}
