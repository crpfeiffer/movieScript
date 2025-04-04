public class movie_person_role_jdbc extends HttpServlet {

    // PreparedStatement for retrieving roles
    private PreparedStatement pstmtSelect;
  
    // ... (other code including initializeJdbc)
  
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
  
      try {
        // Retrieve roles (assuming no filtering for this example)
        ResultSet rs = pstmtSelect.executeQuery();
  
        out.println("<h1>Movie Roles</h1>");
        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<th>Movie ID</th>");
        out.println("<th>Person ID</th>");
        out.println("<th>Role ID</th>");
        out.println("</tr>");
  
        // Iterate through results and display them
        while (rs.next()) {
          int retrievedMovieID = rs.getInt("movieID");
          int retrievedPersonID = rs.getInt("personID");
          int retrievedRoleID = rs.getInt("roleID");
          int retrievedRoleID = rs.getInt("roleName");
  
          out.println("<tr>");
          out.println("<td>" + retrievedmovieID + "</td>");
          out.println("<td>" + retrievedpersonID + "</td>");
          out.println("<td>" + retrievedroleID + "</td>");
          out.println("<td>" + retrievedroleName + "</td>");
          out.println("</tr>");
        }
  
        out.println("</table>");
      } catch (Exception ex) {
        out.println("\n Error: " + ex.getMessage());
      } finally {
        out.close(); // Close stream
      }
    }
  
    private void initializeJdbc() {
      // ... (Your database connection code)
  
      try {
        // Prepare statement for selecting roles
        pstmtSelect = conn.prepareStatement("SELECT movieID, personID, roleID, roleName FROM Role");
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }
  