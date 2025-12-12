package test;

import java.sql.*;

class PreparedStatement_retrive_Insert {

    private static final String url = "***********/record";
    private static final String name = "****";
    private static final String password = "****************";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.getException();
        }

        try {
            Connection con = DriverManager.getConnection(url, name, password);
            PreparedStatement psmt = con.prepareStatement("""
                    
                    SELECT * FROM players;
                    """);

            PreparedStatement psmt2 = con.prepareStatement("""
                    INSERT INTO players(p_name, p_rank)
                    VALUES ('Ayan', 73);
                    """);

            psmt2.executeUpdate();

            ResultSet res = psmt.executeQuery();

            while(res.next()) {

                int id = res.getInt("id");
                String name = res.getString("p_name");
                int rank = res.getInt("p_rank");

                System.out.println("id : " + id + " name : " + name + " rank : " + rank);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
