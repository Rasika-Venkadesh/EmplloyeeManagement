<html>
 <head>
  <title>Update Trainee</title>
 </head>
 <body>
  <%@ page import="com.ideas2it.employee.model.Trainer , com.ideas2it.employee.model.Trainee,  com.ideas2it.employee.model.Employee" %>
  <%@ page import ="java.util.ArrayList"%>
  <%@ page import ="java.util.List , java.util.Arrays , java.util.Set, java.util.HashSet "%>
  <% String task = request.getParameter("action");
	Trainee trainee = (Trainee)request.getAttribute("trainee");
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
        int trainingPeriod = 0;
        String role = "";
        List<Integer> trainersId = new ArrayList<>(); 
        Set<Trainer> trainersSet = new HashSet<>();
	String heading = "Add Trainee";
	if (!task.equals("traineeRegister")) {
	    name = trainee.getEmployee().getName();
            dateOfBirth = String.valueOf(trainee.getEmployee().getDateOfBirth());
            dateOfJoin = String.valueOf(trainee.getEmployee().getDateOfBirth()); 
	    gender = trainee.getEmployee().getGender();
	    phoneNumber = Long.valueOf(trainee.getEmployee().getPhoneNumber());
	    emailId = trainee.getEmployee().getEmailId();
            salary = trainee.getEmployee().getSalary();
            aadharId = Long.valueOf(trainee.getEmployee().getAadharId());	    
	    bloodGroup = trainee.getEmployee().getBloodGroup();
	    qualification = trainee.getEmployee().getQualification().getQualification();
	    trainingPeriod = Integer.valueOf(trainee.getTrainingPeriod());
            role = trainee.getEmployee().getRole().getRole();
            trainersSet = trainee.getTrainers();
            for (Trainer trainer : trainersSet) {
              int id = trainer.getEmployee().getEmployeeId();
              trainersId.add(id);
            }            
	    heading = "Update Trainee";
	}	
	session.setAttribute("trainee", trainee);
  %>
  <h3> <%= heading %> </h3>
  <form action="EmployeeController?action=traineeRegister&task=<%= task%>" method="post">
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
   Training Period : </br><input type="number" name="trainingPeriod" value="<%= trainingPeriod %>" required/></br>
   TrainersId : </br><input type ="text" name = "trainersId" value = "<%= trainersId %>" required/><br>
   </br><input type="submit" value="<%= heading %>"/>
   <a href="EmployeeServlet?action=showTrainee"> <input type="button" value="Back"></a>
  </form>
 </body>
</html>
