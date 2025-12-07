package test;
import java.sql.*;

class Loading_JDBC_Driver {

    private static final String url = "jdbc:mysql://xyz/mydb";
    private static final String username = "your_username";
    private static final String password = "your_password";
    public static void main(String []args) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException e){

            System.out.println(e.getMessage());
        }
        try {

            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            String query = "SELECT name, age FROM list";
            ResultSet resultset = stmt.executeQuery(query);

            while(resultset.next()) {

                String name = resultset.getString("name");
                int age = resultset.getInt("age");

                System.out.printf("""
                        name : %s
                        age : %d
                        """, name, age);
            }
        } catch(SQLException e) {

            System.out.println(e.getMessage());
        }
    }
}
