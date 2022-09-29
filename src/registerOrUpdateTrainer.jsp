<html>
 <head>
  <title>Update Trainer</title>
 </head>
 <body>
  <%@ page import="com.ideas2it.employee.model.Trainer , com.ideas2it.employee.model.Employee" %>
  <% String task = request.getParameter("action");
	Trainer trainer = (Trainer)request.getAttribute("trainer");
        String name ="";
        String dateOfBirth ="";
        String dateOfJoin ="";
        String gender ="";
        long phoneNumber = 0;
        String emailId = "";
        double salary = 0.0;
        long aadharId = 0;
        String bloodGroup = "";
        String qualification = "";
        int experience = 0;
        String role = "";
	String heading = "Add Trainer";
	if (!task.equals("trainerRegister")) {
	    name = trainer.getEmployee().getName();
            dateOfBirth = String.valueOf(trainer.getEmployee().getDateOfBirth());
            dateOfJoin = String.valueOf(trainer.getEmployee().getDateOfBirth()); 
	    gender = trainer.getEmployee().getGender();
	    phoneNumber = Long.valueOf(trainer.getEmployee().getPhoneNumber());
	    emailId = trainer.getEmployee().getEmailId();
            salary = trainer.getEmployee().getSalary();
            aadharId = Long.valueOf(trainer.getEmployee().getAadharId());	    
	    bloodGroup = trainer.getEmployee().getBloodGroup();
	    qualification = trainer.getEmployee().getQualification().getQualification();
	    experience = Integer.valueOf(trainer.getExperience());
            role = trainer.getEmployee().getRole().getRole();
	    heading = "Update Trainer";
	}	
	session.setAttribute("trainer", trainer);
  %>
  <h3> <%= heading %> </h3>
  <form action="EmployeeController?action=trainerRegister&task=<%= task%>" method="post">
   Name : </br><input type="text" name="name" value="<%= name %>" required/></br>
   DateOfBirth : </br><input type="date" name="dateOfBirth" value="<%= dateOfBirth%>" required/></br>
   DateOfJoin : </br><input type="date" name="dateOfJoin" value="<%= dateOfJoin%>" required/></br>
   Gender : </br><input type="radio" id = "Male" name="gender" value= "Male" <%= (gender.equals("Male") ? "checked = checked" : "")%>>
                 <label for="Male">Male</label><br>
                 <input type="radio" id = "Female" name="gender" value= "Female" <%= (gender.equals("Female") ? "checked = checked" : "")%>>
                 <label for="Female">Female</label><br>
                 <input type="radio" id = "Others" name="gender" value= "Others" <%= (gender.equals("Others") ? "checked = checked" : "")%>>
                 <label for="Others">Others</label><br>
   Phone Number : </br><input type="number" name="phoneNumber" value="<%= phoneNumber %>" required/></br>
   EmailId : </br><input type="email" name="emailId" value="<%= emailId %>" required/></br>
   Salary : </br><input type="number" name="salary" value="<%= salary %>" required/></br>
   AadharId : </br><input type="number" name="aadharId" value="<%= aadharId %>" required/></br>
   Blood Group : </br><input type="radio" id="a+" name="bloodGroup" value="A Positive" <%=(bloodGroup.equals("A Positive") ? "checked = checked" : "")%>>
		 <label for="a+">A Positive</label><br>
		 <input type="radio" id="b+" name="bloodGroup" value="B Positive" <%=(bloodGroup.equals("B Positive") ? "checked = checked" : "")%>>
		 <label for="b+">B Positive</label><br>
		 <input type="radio" id="o+" name="bloodGroup" value="O Positive" <%=(bloodGroup.equals("O Positive") ? "checked = checked" : "")%>>
		 <label for="o+">O Positive</label><br>
		 <input type="radio" id="ab+" name="bloodGroup" value="AB Positive" <%=(bloodGroup.equals("AB Positive") ? "checked = checked" : "")%>>
		 <label for="ab+">AB Positive</label><br>
		 <input type="radio" id="a-" name="bloodGroup" value="A Negative" <%=(bloodGroup.equals("A Negative") ? "checked = checked" : "")%>>
		 <label for="a-">A Negative</label><br>
		 <input type="radio" id="b-" name="bloodGroup" value="B Negative" <%=(bloodGroup.equals("B Negative") ? "checked = checked" : "")%>>
		 <label for="b-">B Negative</label><br>
		 <input type="radio" id="o-" name="bloodGroup" value="O Negative" <%=(bloodGroup.equals("O Negative") ? "checked = checked" : "")%>>
		 <label for="o-">O Negative</label><br>
		 <input type="radio" id="ab-" name="bloodGroup" value="AB Negative" <%=(bloodGroup.equals("AB Negative") ? "checked = checked" : "")%>>
		 <label for="ab-">AB Negative</label><br>
   Qualification : </br><input type="text" name="qualification" value="<%= qualification %>" required/></br>
   Experience : </br><input type="number" name="experience" value="<%= experience %>" required/></br>
   </br><input type="submit" value="<%= heading %>"/>
   <a href="EmployeeServlet?action=showTrainer"> <input type="button" value="Back"></a>
  </form>
 </body>
</html>
