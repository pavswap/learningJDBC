import java.sql.*;

class Making_Connection_with_DB {
  
  public static void main(String args[]) {
    
    private static final String url = "/* put your mysql jdbc connection url*/";
    private static final String username = "/*put your username*/";
    private static final String password = "/*put your password*/";

    // Loading JDBC Driver
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch(ClassNotFoundException e) {
      System.out.println(e.getMessage()); // Checking for Potential Exception
    }

    // Making Connection with DataBase
    try {
      Connection con = DriverManager.getConnection(url, username, password);

      
    } catch(SQLException e) {
      System.out.println(e.getMessage()); // Checking for Potential Exception
    }
  }
}
