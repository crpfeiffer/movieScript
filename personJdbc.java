import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class personJdbc extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {        
        Statement state4 = null;
        ResultSet result = null;
        String query="";        
        Connection con=null; 

        String personID = request.getParameter("personID");
        String personName = request.getParameter("personName");
        String personGender = request.getParameter("personGender");
        String personDateOfBirth = request.getParameter("personDateOfBirth");

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
            System.err.println("Exception while loading driver"); 
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
        
        //query filtering stuff
        query = "select personID, personName, personGender, personDateOfBirth from PERSON where 1=1";

        if(personID != null && !personID.isEmpty()) {
            query += " AND personID = " + personID;
        }

        if(personName != null && !personName.isEmpty()) {
            query += " AND personName LIKE '%" + personName + "%'";
        }

        out.println("<html><head><title>Person Table Report</title>"); 
        out.println("<style>");
        out.println("table {width: 100%; border-collapse: collapse;}");
        out.println("th, td {border: 1px solid black; padding: 8px; text-align: left;}");
        out.println("th {background-color: #cccccc;}");
        out.println("</style>");
        out.println("</head><body>");
        
        out.println("<h2>Person Table Report</h2>");
        out.println("<form action=\"personJdbc\" method=\"get\">");
        out.println("Person ID: <input type=\"text\" name=\"personID\" value=\"" + (personID != null ? personID : "") + "\"> <br>");
        out.println("Person Name: <input type=\"text\" name=\"personName\" value=\"" + (personName != null ? personName : "") + "\"> <br>");
        out.println("Gender: <input type=\"text\" name=\"personGender\" value=\"" + (personGender != null ? personGender : "") + "\"> <br>");
        out.println("Date of Birth: <input type=\"text\" name=\"personDateOfBirth\" value=\"" + (personDateOfBirth != null ? personDateOfBirth : "") + "\"> <br>");
        out.println("<input type=\"submit\" value=\"Search\">");
        out.println("</form>");
        
        try 
        { 
            result=state4.executeQuery(query);
        }
        catch (SQLException e) 
        {
            System.err.println("SQLException while executing SQL Statement."); 
        }

        out.println("<table>"); 
        out.println("<tr>");
        out.println("<th>Person ID</th>");
        out.println("<th>Name</th>");
        out.println("<th>Gender</th>");
        out.println("<th>Date of Birth</th>");
        out.println("</tr>");

        try 
        { 
            while(result.next()) 
            { 
                out.println("<tr>");
                out.println("<td>" + result.getString(1) + "</td>");
                out.println("<td>" + result.getString(2) + "</td>");
                out.println("<td>" + result.getString(3) + "</td>");
                out.println("<td>" + result.getString(4) + "</td>");
                out.println("</tr>");
            } 
        }
        catch (SQLException e) 
        {
            System.out.println("ResultSet is not connected"); 
        }

        out.println("</table>");
        
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
