
<%@page import="com.ideas2it.employee.dto.EmployeeDto, com.ideas2it.employee.dto.TrainerDto "%>
<%@page import="java.util.ArrayList,java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOC TYPE html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>


            .table{

              width: 300px;
              height: 100px;
              overflow-y: scroll;
              scrollbar-color: rebeccapurple green;
              scrollbar-width: thin;

            }


        </style>
    </head>
    <body>
        <table>
               <style>table, th, td {border: 1px solid black;border-collapse: collapse;}</style>
            <h2>Trainer List</h2> 
            <table style="width:100%">
                <tr><th>Id</th>
                    <th>Name</th>                    
                    <th>Date of Birth</th>
                    <th>Date of Join</th>
                    <th>Gender</th>
                    <th>Phone Number</th>
                    <th>Email Id</th>
                    <th>Salary</th>
                    <th>Aadhar Id</th>
                    <th>Blood Group</th>
                    <th>Qualification</th>
                    <th>Experience</th>                    
                    <td colspan="2">Action</td>
                </tr>

		    <%
		    ArrayList<TrainerDto> trainers = (ArrayList<TrainerDto>)request.getAttribute("Trainers");
		    for (TrainerDto trainerDto: trainers) {
		    %>
		       <tr><td><%= trainerDto.getEmployeeId() %></td>
		    <td><%= trainerDto.getName() %></td>
                    <td><%= trainerDto.getDateOfBirth() %></td>
                    <td><%= trainerDto.getDateOfJoin() %></td>
		    <td><%= trainerDto.getGender() %></td>
                    <td><%= trainerDto.getPhoneNumber() %></td>
                    <td><%= trainerDto.getEmailId() %></td>
                    <td><%= trainerDto.getSalary() %></td>
                    <td><%= trainerDto.getAadharId() %></td>
                    <td><%= trainerDto.getBloodGroup() %></td>
		    <td><%= trainerDto.getQualificationDto().getQualification() %></td>
		    <td><%= trainerDto.getExperience() %></td>
            <td><a href="updateTrainer?trainerId=<%=trainerDto.getEmployeeId()%>" ><input type="button" value="update"></a></td>
            <td><a href="deleteTrainer?trainerId=<%=trainerDto.getEmployeeId()%>"  ><input type="button" value="delete"></a></td>
		    </tr>

		<% } %>
		</table>
                <input type="button" value="Back to Main Menu" onclick="history.go(-1)">
                </div>
	</body>
</html>
