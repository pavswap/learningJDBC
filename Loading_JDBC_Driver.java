class Loading_JDBC_Driver {
    public static void main(String args[]) {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            } catch(ClassNotFoundException e) {
            
            System.out.println(e.getMessage());
        }
    }
}
