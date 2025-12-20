package test;

import java.sql.*;
import java.util.ArrayList;

public class CRUDApplication {

    private static final String url = "YourConnectionURL"; // Local JDBC Url
    private static final String name = "YourUserName"; // Sql Connection Username
    private static final String password = "YourPassword"; // Local connection SQL password

    private static Connection con;

    private static String userName;

    // Making Connection with DataBase
    void makeConnection() {
        try {
            con = DriverManager.getConnection(url, name, password);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Search For Match With Interest or School
    public ArrayList<String> findMatchSchool(String interest, String school) {
        String query = """
            SELECT Name, Interest FROM People
            WHERE (Name != ? AND Interest = ?) OR School = ?;
            """;

        var list = new ArrayList<String>();

        try {
            PreparedStatement psmt = con.prepareStatement(query);

            psmt.setString(1, userName);
            psmt.setString(2, interest);
            psmt.setString(3, school);

            ResultSet res = psmt.executeQuery();

            while(res.next()) {
                String column = "Name : " + res.getString("Name")
                        + " Interest : " + res.getString("Interest") + "\n";

                list.add(column);
            }
            psmt.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    // Inserting User Credentials
    public int login(String name, String job, String school, String interest) {

        String query = """
            INSERT INTO People(Name, Interest, School, Job)
            VALUES(?, ?, ?, ?);
            """;

        int res = 0;

        try {
            PreparedStatement psmt = con.prepareStatement(query);

            psmt.setString(1, name);
            psmt.setString(2, interest);
            psmt.setString(3, school);
            psmt.setString(4, job);

            res = psmt.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    // Deleting User Credentials
    public int logout(String name) {

        String query = """
            DELETE FROM People
            WHERE Name = ?;
            """;

        int res = 0;

        try {
            PreparedStatement psmt = con.prepareStatement(query);

            psmt.setString(1, name);

            res = psmt.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    // Updating User Credentials
    public int updateYourSelf(String originalName, String name, String job, String school, String interest) {

        String query = """
            UPDATE People
            SET Name = ?, Job = ?, School = ?, Interest = ?
            WHERE Name = ?;
            """;

        int res = 0;

        try {
            PreparedStatement psmt = con.prepareStatement(query);

            psmt.setString(1, name);
            psmt.setString(2, job);
            psmt.setString(3, school);
            psmt.setString(4, interest);
            psmt.setString(5, originalName);

            res = psmt.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    // Test Main Function :
    public static void main(String[] args) {

        var clg = new Main();

        clg.makeConnection();

        System.out.println(clg.findMatchSchool("Sound Editing", "CBSE"));

        try {
            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
