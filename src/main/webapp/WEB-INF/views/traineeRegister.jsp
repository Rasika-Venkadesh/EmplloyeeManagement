<html>
 <head>
  <title>Trainee Portal</title>
 </head>

 <body>
  <h2>Add Trainee Details</h2>

  <%@page import ="com.ideas2it.employee.model.Trainer, com.ideas2it.employee.model.Trainee,  com.ideas2it.employee.model.Employee" %>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
  <%@page contentType="text/html" pageEncoding="UTF-8"%>
  <!DOC TYPE html>
        <form:form method = "Post" modelAttribute="trainee" action = "traineeRegister">
        <input type="hidden" name="action" value="traineeRegister" >
        <form:hidden path = "employee.employeeId" /><br>
        <form:hidden path = "traineeId" /><br>
        <table >
            <tr>
                <td>Name : </td>
                <td><form:input path = "employee.name" /></td>
            </tr>
            <tr>
                <td>Date Of Birth : </td>
                <td><form:input type="date" path = "employee.dateOfBirth"/></td>
            </tr>
            <tr>
                 <td>Date Of Join : </td>
                 <td><form:input type="date" path = "employee.dateOfJoin"/></td>
            </tr>
            <tr>
                 <td>Gender : </td>
                 <td><form:select path="employee.gender" title="Gender" multiple="true" size="3"></b>
                     <form:option value="Male" label="Male" />
                     <form:option value="Female" label="Female" />
                     <form:option value="Others" label="Others" />
                     </form:select></td>
            </tr>
            <tr>
                 <td>Phone Number : </td>
                 <td><form:input type="number" path="employee.phoneNumber" /></td>
            </tr>
            <tr>
                 <td>Email Id : </td>
                 <td><form:input type = "email" path = "employee.emailId" /></td>
            </tr>
            <tr>
                 <td>Salary : </td>
                 <td><form:input type="number" path="employee.salary" /></td>
            </tr>
            <tr>
                 <td>Aadhar Id : </td>
                 <td><form:input type="number" path="employee.aadharId" /></td>
            </tr>
            <tr>
                 <td>Blood Group : </td>
                 <td><form:select path="employee.bloodGroup" title="Blood Group" multiple="true" size="8"></b>
                     <form:option value="A Positive" label="A Positive" />
                     <form:option value="B Positive" label="B Positive" />
                     <form:option value="AB Positive" label="AB Positive" />
                     <form:option value="O Positive" label="O Positive" />
                     <form:option value="A Negative" label="A Negative" />
                     <form:option value="B Negative" label="B Negative" />
                     <form:option value="AB Negative" label="AB Negative" />
                     <form:option value="O Negative" label="O Negative" />
                     </form:select></td>
            </tr>
            <tr>
                 <td>Qualification: </td>
                 <td><form:input path = "employee.qualification.qualification" /></td>
            </tr>
            <tr>
                 <td>Training Period: </td>
                 <td><form:input path = "trainingPeriod" /></td>
            </tr>
            <tr>
                 <td>Role : </td>
                 <td><form:select path = "employee.role.role" size="1">
                 <form:option value = "Trainee" label = "Trainee" />
                 </form:select></td>
            </tr>
            <tr>
                  <td>Trainer Ids: </td>
                  <td><form:input path = "trainersId" /></td>
            </tr>
            <tr>
                <td colspan="2"></br><input type="submit" value="Add Trainer"/>
                <a href="showTrainee"> <button type="submit" value="back">Back</button></td>
            </tr>
        </table>
        </form:form>
  </body>
</html>




