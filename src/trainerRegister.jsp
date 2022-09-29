<%@page import ="com.ideas2it.employee.model.Trainer, com.ideas2it.employee.model.Employee" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>

<h2><% String task = request.getParameter("action");
       if (task.equals("addTrainer")) {
           %>Add Trainer Details<%;
       } else {
           %>Update Trainer Details<%;
       }%></h2>
<%     Trainer trainer = (Trainer)request.getAttribute("Trainer");
       Employee employee = null;
       String gender = "";
       if (null != trainer) {
           employee = trainer.getEmployee();
           gender = employee.getGender();
       }
       session.setAttribute("trainer",trainer);


%>

  <form action="EmployeeController?action=updateTrainer&task=<%=task%>" method="post">
   <input type="hidden" id="flag" name="flag" value="addTrainer" >
  <input type="hidden" id="flag" name="method" value=<%= ((action.equals("trainerRegister")) ? "trainerRegister" : "updateTrainer")%> >
  <% request.setAttribute("trainer",trainer); %>
  Name:<br>
  <input type="text" id="name" name="name" value ="<%=((action.equals("updateTrainer")) ? employee.getName() : "" )%>" required ><br><br>

  <label for="dateOfBirth">Date Of Birth (DD/MM/YYYY):</label><br>
  <input type="date" id="dateOfBirth" name="dateOFBirth" value ="<%=((action.equals("updateTrainer")) ? employee.getDateOfBirth() : "" )%>" required ><br>

  <label for="dateOFJoin">Date of Join (DD/MM/YYYY):</label><br>
  <input type="date" id="DateOfJoining" name="DateOfJoin" value ="<%=((action.equals("updateTrainer")) ? employee.getDateOfJoin() : "" )%>" required ><br>
 
  <form:select path="gender">  
        <form:option value="Male" label="Male" value ="<%=((action.equals("updateTrainer")) ? employee.getGender() : "" )%>" required ><br>/>  
        <form:option value="Female" label="Female" value ="<%=((action.equals("updateTrainer")) ? employee.getGender() : "" )%>" required ><br>/>/>  
        <form:option value= "Others" label="Others" value ="<%=((action.equals("updateTrainer")) ? employee.getGender() : "" )%>" required ><br>/>/>   
        </form:select> <br>
 
  <label for="phoneNumber">phoneNumber :</label><br>
  <input type="number" id="phoneNumber" name="phoneNumber" value ="<%=((action.equals("updateTrainer")) ? employee.getPhoneNumber() : "" )%>" required ><br>

  <label for="emailId">EmailId :</label><br>
  <input type="email" id="emailId" name="emailID" value ="<%=((action.equals("updateTrainer")) ? employee.getEmailId() : "" )%>" required ><br>

  <label for="salary">Salary :</label><br>
  <input type="number" id="salary" name="salary" value ="<%=((action.equals("updateTrainer")) ? employee.getSalary() : "" )%>" required ><br>

  <label for="aadharId">Aadhar ID :</label><br>
  <input type="number" id="aadharId" name="aadharId" value ="<%=((action.equals("updateTrainer")) ? employee.getAadharId() : "" )%>" required ><br>

  <form:select path="bloodGroup">  
        <form:option value="A_Positive" label="A_Positive" value ="<%=((action.equals("updateTrainer")) ? employee.getBloodGroup() : "" )%>" required ><br>/>  
        <form:option value="B_Positive" label="B_Positive" value ="<%=((action.equals("updateTrainer")) ? employee.getBloodGroup() : "" )%>" required ><br>/>/>  
        <form:option value= "AB_Positive" label="AB_Positive" value ="<%=((action.equals("updateTrainer")) ? employee.getBloodGroup() : "" )%>" required ><br>/>/> 
        <form:option value= "O_Positive" label="O_Positive" value ="<%=((action.equals("updateTrainer")) ? employee.getBloodGroup() : "" )%>" required ><br>/>/>
        <form:option value= "A_Negative" label="A_Negative" value ="<%=((action.equals("updateTrainer")) ? employee.getBloodGroup() : "" )%>" required ><br>/>/>
        <form:option value= "B_Negative" label="B_Negative" value ="<%=((action.equals("updateTrainer")) ? employee.getBloodGroup() : "" )%>" required ><br>/>/>
        <form:option value= "AB_Negative" label="AB_Negative" value ="<%=((action.equals("updateTrainer")) ? employee.getBloodGroup() : "" )%>" required ><br>/>/>
        <form:option value= "O_Negative" label="O_Negative" value ="<%=((action.equals("updateTrainer")) ? employee.getBloodGroup() : "" )%>" required ><br>/>/>  
        </form:select> <br>

  <label for="qualification">Qualification :</label><br>
  <input type="text" id="qualification" name="qualification" value ="<%=((action.equals("updateTrainer")) ? employee.getQualification().getQualification() : "" )%>" required ><br>
  
  <label for="Experience">Experience :</label><br>
  <input type="text" id="experience" name="experience" value ="<%=((action.equals("updateTrainer")) ? employee.getExperience() : "" )%>" required ><br>

  <label for="Role">Role :</label><br>
  <input type="text" id="role" name="role" value ="<%=((action.equals("updateTrainer")) ? employee.getRole().getRole() : "" )%>" required ><br>

  <input type="submit" value="Submit">
</form>

</body>
</html>


</html>