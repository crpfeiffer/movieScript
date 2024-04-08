import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class personJdbc extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        Statement state4 = null;
        ResultSet result = null;
        String query="";
        Connection con=null;

        try
        {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "project", "project");
            System.out.println("Congratulations! You are connected successfully.");
        }
        catch(SQLException e)
        {
            System.out.println("Error: "+e);
        }
        catch(Exception e)
        {
            System.err.println("Exception while loading  driver");
        }
        try
        {
            state4 = con.createStatement();
        }
        catch (SQLException e)
        {
            System.err.println("SQLException while creating statement");
        }

        response.setContentType("text/html");
        PrintWriter out = null ;
        try
        {
            out =  response.getWriter();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        query = "select personName, personGender, personDateOfBirth, personSalary from person";

        out.println("<html><head><title>person Table Report</title>");
        out.println("</head><body>");

        out.print( "<br /><b><center><font color=\"RED\"><H2>person Table Report</H2></font>");
        out.println( "</center><br />" );
        try
        {
            result=state4.executeQuery(query);
        }
        catch (SQLException e)
        {
            System.err.println("SQLException while executing SQL Statement.");
        }
        out.println("<center><table border=\"1\">");
        out.println("<tr BGCOLOR=\"#cccccc\">");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">Title</td>");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">Release Date</td>");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">Duration</td>");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">Category</td>");
        out.println("<td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">Rating</td>");
        out.println("</tr>");
        try
        {
            while(result.next())
            {
                out.println("<tr>");
                out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(1)+"</td>");
                out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(2)+"</td>");
                out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(3)+"</td>");
                out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(4)+"</td>");
                out.println("     <td align = \"justify\"><font face =\"times new roman\"  size=\"4pt\">"+result.getString(5)+"</td>");
                out.println("</tr>");
            }
        }
        catch (SQLException e)
        {
            System.out.println("ResultSet is not connected");
        }

        out.println("</table></CENTER>");
        try
        {
            result.close();
            state4.close();
            con.close();
            System.out.println("Connection is closed successfully.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        out.println("</body></html>");
    }
}
