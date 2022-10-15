
<%@page import="com.ideas2it.employee.dto.TraineeDto, com.ideas2it.employee.dto.TrainerDto "%>
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
                    <th>TrainingPeriod</th>
                    <th>TrainersId</th>
                    <td colspan="2">Action</td>
                </tr>

		    <%
		    ArrayList<TraineeDto> trainees = (ArrayList<TraineeDto>)request.getAttribute("Trainees");
		    for (TraineeDto traineeDto: trainees) {
		        List<Integer> trainerIds = new ArrayList<>();
                for (TrainerDto trainerDto : traineeDto.getTrainers()) {
                    trainerIds.add(trainerDto.getEmployeeId());
                }
            %>

		       <tr>
		           <td><%= traineeDto.getEmployeeId() %></td>
		           <td><%= traineeDto.getName() %></td>
                   <td><%= traineeDto.getDateOfBirth() %></td>
                   <td><%= traineeDto.getDateOfJoin() %></td>
		           <td><%= traineeDto.getGender() %></td>
                   <td><%= traineeDto.getPhoneNumber() %></td>
                   <td><%= traineeDto.getEmailId() %></td>
                   <td><%= traineeDto.getSalary() %></td>
                   <td><%= traineeDto.getAadharId() %></td>
                   <td><%= traineeDto.getBloodGroup() %></td>
		           <td><%= traineeDto.getQualification().getQualification() %></td>
		           <td><%= traineeDto.getTrainingPeriod() %></td>
                   <td><%= trainerIds.toString().replaceAll("[\\[\\]]","") %></td>
                   <td><a href="updateTrainee?traineeId=<%=traineeDto.getEmployeeId()%>" ><input type="button" value="update"></a></td>
                   <td><a href="deleteTrainee?traineeId=<%=traineeDto.getEmployeeId()%>"  ><input type="button" value="delete"></a></td>
		       </tr>

		<% } %>
		</table>
                <input type="button" value="Back to Main Menu" onclick="history.go(-1)">
                </div>
	</body>
</html>
