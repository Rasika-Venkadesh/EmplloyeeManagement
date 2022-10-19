<html>
     <head>
         <title>Update Trainee</title>
     </head>
    <%@page isELIgnored="false" %>
     <body>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
         <%@ page import = "java.util.ArrayList, java.util.List" %>
         <%@ page import="com.ideas2it.employee.model.Trainer , com.ideas2it.employee.model.Trainee" %>
         <%@ page import="com.ideas2it.employee.dto.TrainerDto , com.ideas2it.employee.dto.TraineeDto" %>
         <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
         <% String task = (String)request.getAttribute("action");
	     TraineeDto traineeDto = (TraineeDto)request.getAttribute("traineeDto");
	     String heading = "AddTrainee";
	     if (!task.equals("traineeRegister")) {
	         heading = "UpdateTrainee";
	     }
	     session.setAttribute("traineeDto", traineeDto);
         %>
         <h3> <%= heading %> </h3>
         <form:form action="traineeRegister?task=<%= task%>" method="get" modelAttribute = "traineeDto">
         <form:hidden path = "employeeId" /><br>

         <table >
                   <tr>
                       <td>Name : </td>
                       <td><form:input path = "name" /></td>
                   </tr>
                   <tr>
                       <td>Date Of Birth : </td>
                       <td><form:input type="date" path = "dateOfBirth"/></td>
                   </tr>
                   <tr>
                        <td>Date Of Join : </td>
                        <td><form:input type="date" path = "dateOfJoin"/></td>
                   </tr>
                   <tr>
                        <td>Gender : </td>
                        <td><form:select path="gender" title="Gender" multiple="true" size="3"></b>
                            <form:option value="Male" label="Male" />
                            <form:option value="Female" label="Female" />
                            <form:option value="Others" label="Others" />
                            </form:select></td>
                   </tr>
                   <tr>
                        <td>Phone Number : </td>
                        <td><form:input type="number" path="phoneNumber" /></td>
                   </tr>
                   <tr>
                        <td>Email Id : </td>
                        <td><form:input type = "email" path = "emailId" /></td>
                   </tr>
                   <tr>
                        <td>Salary : </td>
                        <td><form:input type="number" path="salary" /></td>
                   </tr>
                   <tr>
                        <td>Aadhar Id : </td>
                        <td><form:input type="number" path="aadharId" /></td>
                   </tr>
                   <tr>
                        <td>Blood Group : </td>
                        <td><form:select path="bloodGroup" title="Blood Group" multiple="true" size="8"></b>
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
                        <td><form:input path = "qualificationDto.qualification" /></td>
                   </tr>
                   <tr>
                        <td>TrainingPeriod : </td>
                        <td><form:input path = "trainingPeriod" /></td>
                   </tr>
                   <tr>
                        <td>Role : </td>
                        <td><form:select path = "roleDto.role" size="1">
                        <form:option value = "Trainee" label = "Trainee" />
                        </form:select></td>
                   </tr>
                   <tr>
                        <td>TrainersId : </td>
                        <td><form:select path = "trainersId">
                            <c:forEach var="trainerDto" items="${trainers}" >
                            <form:option value="${trainerDto.employeeId}" label="${trainerDto.employeeId} - ${trainerDto.name}"/>
                            </c:forEach>
                            </form:select>
                            </td>
                   </tr>
                   <tr>
                       <td colspan="2"></br><input type="submit" value="Add Trainee"/>
                       <input type="button" value="Back to Main Menu" onclick="history.go(-1)">
                   </tr>
         </table>
         </form:form>
     </body>
</html>
