<html>
 <head>
  <title>Update Trainer</title>
 </head>
 <body>
  <%@ page import="com.ideas2it.employee.models.Trainer" %>
  <% String action = request.getParameter("action");
	Trainer trainer = null;	
	String heading = "Add Trainer";
	if (action.equals("updateTrainer")) {
	    trainer = (Trainer) request.getAttribute("trainer");
	    String name = trainer.getEmployee().getName();
            String dateOfBirth = trainer.getEmployee().getDateOfBirth();
            String dateOfJoin = trainer.getEmployee().getDateOfBirth(); 
	    String gender = trainer.getEmployee().getGender();
	    long phoneNumber = Long.valueOf(trainer.getEmployee().getMobileNumber());
	    String emailId = trainer.getEmployee().getEmailId();
            double salary = trainer.getEmployee().getSalary();
            long aadharId = Long.valueOf(trainer.getEmployee().getAadharId());	    
	    String bloodGroup = trainer.getEmployee().getBloodGroup();
	    String qualification = trainer.getEmployee().getQualification().getDescription();
	    int experience = Integer.valueOf(trainer.getExperience());
            String role = trainer.getEmployee().getRole().getRole();
	    heading = "Update Trainer";
	}	
	session.setAttribute("trainer", trainer);
  %>
  <h3> <%= heading %> </h3>
  <form action="EmployeeController?action=updateTrainer&action=<%=action%>" method="post">
   Name : </br><input type="text" name="name" value="<%= name %>" required/></br>
   DateOfBirth : </br><input type="date" name="dateOfBirth" value="<%= dateOfBirth%>" required/></br>
   DateOfJoin : </br><input type="date" name="dateOfJoin" value="<%= dateOfJoin%>" required/></br>
   Gender : </br><input type="radio" id = "Male" name="gender" value= "Male" "<%= (gender.equals("Male") ? "checked = checked" : "")%>>
                 <label for="Male">Male</label><br>
                 <input type="radio" id = "Female" name="gender" value= "Female" "<%= (gender.equals("Female") ? "checked = checked" : "")%>>
                 <label for="Female">Female</label><br>
                 <input type="radio" id = "Others" name="gender" value= "Others" "<%= (gender.equals("Others") ? "checked = checked" : "")%>>
                 <label for="Others">Others</label><br>
   Phone Number : </br><input type="number" name="mobileNumber" value="<%= mobileNumber %>" required/></br>
   EmailId : </br><input type="email" name="email" value="<%= email %>" required/></br>
   Salary : </br><input type="number" name="salary" value="<%= salary %>" required/></br>
   AadharId : </br><input type="number" name="aadharId" value="<%= aadharId %>" required/></br>
   Blood Group : </br><input type="select" id="a+" name="bloodGroup" value="A_Positive" <%=(bloodGroup.equals("A_Positive") ? "checked = checked" : "")%>>
		 <label for="a+">A_Positive</label><br>
		 <input type="radio" id="b+" name="bloodGroup" value="B_Positive" <%=(bloodGroup.equals("B_Positive") ? "checked = checked" : "")%>>
		 <label for="b+">B_Positive</label><br>
		 <input type="radio" id="o+" name="bloodGroup" value="O_Positive" <%=(bloodGroup.equals("O_Positive") ? "checked = checked" : "")%>>
		 <label for="o+">O_Positive</label><br>
		 <input type="radio" id="ab+" name="bloodGroup" value="AB_Positive" <%=(bloodGroup.equals("AB_Positive") ? "checked = checked" : "")%>>
		 <label for="ab+">AB_Positive</label><br>
		 <input type="radio" id="a-" name="bloodGroup" value="A_Negative" <%=(bloodGroup.equals("A_Negative") ? "checked = checked" : "")%>>
		 <label for="a-">A_Negative</label><br>
		 <input type="radio" id="b-" name="bloodGroup" value="B_Negative" <%=(bloodGroup.equals("B_Negative") ? "checked = checked" : "")%>>
		 <label for="b-">B_Negative</label><br>
		 <input type="radio" id="o-" name="bloodGroup" value="O_Negative" <%=(bloodGroup.equals("O_Negative") ? "checked = checked" : "")%>>
		 <label for="o-">O_Negative</label><br>
		 <input type="radio" id="ab-" name="bloodGroup" value="AB_Negative" <%=(bloodGroup.equals("AB_Negative") ? "checked = checked" : "")%>>
		 <label for="ab-">AB_Negative</label><br>
   Qualification : </br><input type="text" name="qualification" value="<%= qualification %>" required/></br>
   Experience : </br><input type="number" name="trainingExperience" value="<%= experience %>" required/></br>
   </br><input type="submit" value="<%= heading %>"/>
   <a href="EmployeeServlet?action=showTrainer"> <input type="button" value="Back"></a>
  </form>
 </body>
</html>
