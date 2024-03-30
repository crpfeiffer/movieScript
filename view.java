import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class view extends HttpServlet {
  // Use a prepared statement to retrieve movie roles from the database
  private PreparedStatement pstmt;

  /** Initialize global variables */
  public void init() throws ServletException {
    initializeJdbc();
  }

  /** Process the HTTP Get request */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    // Obtain parameters from the client (if any, e.g., for filtering)
    String movieID = request.getParameter("movieID");

    try {
      // Execute the query to retrieve movie roles
      ResultSet rs = pstmt.executeQuery();

      out.println("<h1>Movie Roles</h1>");
      out.println("<table border=\"1\">");
      out.println("<tr>");
      out.println("<th>Movie ID</th>");
      out.println("<th>Movie Title</th>");
      out.println("<th>Person ID</th>");
      out.println("<th>Role Name</th>");
      out.println("<th>Character Name</th>");
      out.println("</tr>");

      // Iterate through the results and display them
      while (rs.next()) {
        int retrievedMovieID = rs.getInt("m_id");
        String retrievedMovieTitle = rs.getString("m_title");
        String retrievedPersonID = rs.getString("person_id");
        String retrievedRoleName = rs.getString("role_name");
        String retrievedCharacterName = rs.getString("character_name");

        // Optionally filter based on movieID if provided
        if (movieID == null || movieID.equals(Integer.toString(retrievedMovieID))) {
          out.println("<tr>");
          out.println("<td>" + retrievedMovieID + "</td>");
          out.println("<td>" + retrievedMovieTitle + "</td>");
          out.println("<td>" + retrievedPersonID + "</td>");
          out.println("<td>" + retrievedRoleName + "</td>");
          out.println("<td>" + retrievedCharacterName + "</td>");
          out.println("</tr>");
        }
      }

      out.println("</table>");
    } catch (Exception ex) {
      out.println("\n Error: " + ex.getMessage());
    } finally {
      out.close(); // Close stream
    }
  }

  /** Initialize database connection */
  private void initializeJdbc() {
    try {
      // ... (Your database connection code remains the same)

      // Create a Statement for retrieving roles
      pstmt = conn.prepareStatement("SELECT * FROM movie");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}