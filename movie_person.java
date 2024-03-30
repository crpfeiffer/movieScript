import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

public class movie_person extends HttpServlet {
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
    String movieID = request.getParameter("movieID");
    String movieTitle = request.getParameter("movieTitle");
    String personID = request.getParameter("personID");
    String roleName = request.getParameter("roleName");
    String characterName = request.getParameter("characterName");


    try {

		//out.println("First Name: " + firstName );
      if (movieID.length() == 0 || movieTitle.length() == 0 || personID.length() == 0 || roleName.length() == 0 || characterName.length() == 0) {
        out.println("All fields are required");
        return; // End the method
      }

      storeRole(movieID, movieTitle, personID, roleName, characterName);

      out.println(personID + " " + movieID +
        "are now connected in the database");
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
      pstmt = conn.prepareStatement("insert into movie " +
        "(m_id, m_title) values (?, ?)");
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /** Store a student record to the database */
  private void storeStudent(String movieID, String movieTitle, String personID, String roleName, String characterName) throws SQLException {
    pstmt.setString(1, movieID);
    pstmt.setString(2, movieTitle);
    pstmt.setString(parameterIndex:3, personID);
    pstmt.setString(parameterIndex:4, roleName);
    pstmt.setString(paramenterIndex:5, characterName);

    pstmt.executeUpdate();
  }
}
