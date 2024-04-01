import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

public class Role extends HttpServlet {
  // Use a prepared statement to store a student into the database
  private PreparedStatement pstmt;

  /** Initialize global variables */
  public void init() throws ServletException {
    initializeJdbc();
  }

  /** Process the HTTP Post request */
  public void doPost(HttpServletRequest request, HttpServletResponse
      response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    // Obtain parameters from the client
    String roleID = request.getParameter("movieID");
    String roleName = request.getParameter("roleName");

    try {

		//out.println("roleID, roleName" );
      if (roleID.length() == 0 || roleName.length() == 0) {
        out.println("All fields are required");
        return; // End the method
      }

      storeRole(roleID, roleName);

      out.println(roleID + " " + roleName +
        "is now in the database");
    }
    catch(Exception ex) {
      out.println("\n Error: " + ex.getMessage());
    }
    finally {
      out.close(); // Close stream
    }
  }

  /** Initialize database connection */
  private void initializeJdbc() {
    try {
      // Declare driver and connection string
     // String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
     // String connectionString = "jdbc:odbc:exampleMDBDataSource";
      // For MySQL
      // String driver = "com.mysql.jdbc.Driver";
      // String connectionString = "jdbc:mysql://localhost/test";
      // For Oracle
       String driver = "oracle.jdbc.driver.OracleDriver";
       
      // Load the driver
      Class.forName(driver);

    String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
    String user = "project";
    String password = "project";

      // Connect to the sample database
      Connection conn = DriverManager.getConnection
        (url,user, password);

      // Create a Statement
      pstmt = conn.prepareStatement("insert into Role " +
        "(roleID, roleName) values (?, ?)");
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /** Store a student record to the database */
  private void storeRole(String roleID, String roleName) throws SQLException {
    pstmt.setString(1, roleID);
    pstmt.setString(parameterIndex:2, roleName);

    pstmt.executeUpdate();
  }
}
