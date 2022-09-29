import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class TrainerServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
                      throws ServletException,IOException {
        
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        String name = request.getParameter("Name");
        String dateOfBirth = request.getParameter("DateOfBirth");
        String dateOfJoin = request.getParameter("DateOfJoin");
        String gender = request.getParameter("Gender");
        String phoneNumber = request.getParameter("PhoneNumber");
        String emailId = request.getParameter("EmailId");
        String salary = request.getParameter("Salary");
        String aadharId = request.getParameter("AadharId");
        String bloodGroup = request.getParameter("BloodGroup");
        String qualification = request.getParameter("Qualification");
        String experience = request.getParameter("Experience");
        String role = request.getParameter("Role");

        printWriter.println("<html><body>");
        printWriter.println("Welcome to Trainer Portal");
        printWriter.println("</body></html>");
        printWriter.close();
    }
}


