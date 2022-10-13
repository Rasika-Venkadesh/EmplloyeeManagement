
<%@page import="com.ideas2it.employee.model.Employee, com.ideas2it.employee.model.Trainer "%>
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
		    ArrayList<Trainer> trainers = (ArrayList<Trainer>)request.getAttribute("Trainers");
		    for (Trainer trainer: trainers) {
		    %>
		       <tr><td><%= trainer.getEmployeeId() %></td>
		    <td><%= trainer.getName() %></td>
                    <td><%= trainer.getDateOfBirth() %></td>
                    <td><%= trainer.getDateOfJoin() %></td>
		    <td><%= trainer.getGender() %></td>
                    <td><%= trainer.getPhoneNumber() %></td>
                    <td><%= trainer.getEmailId() %></td>
                    <td><%= trainer.getSalary() %></td>
                    <td><%= trainer.getAadharId() %></td>
                    <td><%= trainer.getBloodGroup() %></td>
		    <td><%= trainer.getQualification().getQualification() %></td>
		    <td><%= trainer.getExperience() %></td>
            <td><a href="updateTrainer?trainerId=<%=trainer.getEmployeeId()%>" ><input type="button" value="update"></a></td>
            <td><a href="deleteTrainer?trainerId=<%=trainer.getEmployeeId()%>"  ><input type="button" value="delete"></a></td>
		    </tr>

		<% } %>
		</table>
                <input type="button" value="Back to Main Menu" onclick="history.go(-1)">
                </div>
	</body>
</html>
