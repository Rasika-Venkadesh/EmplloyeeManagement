<html>
 <head>
  <title>Trainer Portal</title>
 </head>

 <body>
  <h2>Add Trainer Details</h2>

  <%@page import ="com.ideas2it.employee.model.Trainer, com.ideas2it.employee.model.Employee" %>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
  <%@page contentType="text/html" pageEncoding="UTF-8"%>
  <!DOC TYPE html>
  <html>

  <form:form method = "Post" modelAttribute="trainer" action = "trainerRegister">

    <input type="hidden" name="action" value="trainerRegister" >


    <form:hidden path = "employee.employeeId" /><br>
         <form:hidden path = "trainerId" /><br>
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
         Experience : <form:input path = "experience" /><br>
         Role : </br><form:input path = "employee.role.role" /><br>
         </br><input type="submit" value="Add Trainer"/>
         <a href="showTrainer"> <button type="submit" value="back">Back</button>
        </form:form>
       </body>
</html>




    