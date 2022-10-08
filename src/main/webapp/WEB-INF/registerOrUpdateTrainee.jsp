<html>
 <head>
  <title>Update Trainee</title>
 </head>
 <body>
  <%@ page import="com.ideas2it.employee.model.Trainer , com.ideas2it.employee.model.Trainee,  com.ideas2it.employee.model.Employee" %>
  <%@ page import ="java.util.ArrayList"%>
  <%@ page import ="java.util.List , java.util.Arrays , java.util.Set, java.util.HashSet "%>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  <% String task = (String) request.getAttribute("action");
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
	String heading = "AddTrainee";
	if (!task.equals("traineeRegister")) {
	    heading = "UpdateTrainee";
	}	
	session.setAttribute("trainee", trainee);
  %>
  <h3> <%= heading %> </h3>
  <form:form action="traineeRegister?task=<%= task%>" method="get" modelAttribute = "trainee">
     <form:hidden path = "employee.employeeId" /><br>
     <form:hidden path = "traineeId" /><br>
     Name : </br><form:input path = "employee.name" /><br>
     DateOfBirth : </br><form:input type="date" path = "employee.dateOfBirth"/></br>
     DateOfJoin :</br><form:input type="date" path = "employee.dateOfJoin"/></br>
     Gender:
             Male <form:radiobutton path="employee.gender" value="Male"/>
             Female <form:radiobutton path="employee.gender" value="Female"/>
             Others <form:radiobutton path ="employee.gender" value="Others" /> <br>
     Phone Number : <form:input type="number" path="employee.phoneNumber" /></br>
     EmailId : <form:input type = "email" path = "employee.emailId" /><br>
     Salary : <form:input type="number" path="employee.salary" /></br>
     AadharId : <form:input type="number" path="employee.aadharId" /></br>
     BloodGroup:
                A Positive <form:radiobutton path="employee.bloodGroup" value="A Positive"/>
                B Positive <form:radiobutton path="employee.bloodGroup" value="B Positive"/>
                AB Positive <form:radiobutton path="employee.bloodGroup" value="AB Positive"/>
                O Positive <form:radiobutton path="employee.bloodGroup" value="O Positive"/>
                A Negative <form:radiobutton path="employee.bloodGroup" value="A Negative"/>
                B Negative <form:radiobutton path="employee.bloodGroup" value="B Negative"/>
                AB Negative <form:radiobutton path="employee.bloodGroup" value="AB Negative"/>
                O Negative <form:radiobutton path="employee.bloodGroup" value="O Negative"/><br>
     Qualification : <form:input path = "employee.qualification.qualification" /><br>
     TrainingPeriod : <form:input path = "trainingPeriod" /><br>
     Role : </br><form:input path = "employee.role.role" /><br>
     TrainersId : </br><form:input path = "trainersId" /><br>
     </br><input type="submit" value="<%= heading %>"/>
     <a href="showTrainee"> <button type="submit" value="back">Back</button>
    </form:form>
   </body>
</html>
