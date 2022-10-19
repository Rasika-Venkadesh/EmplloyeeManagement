
<%@page import="com.ideas2it.employee.dto.TraineeDto, com.ideas2it.employee.dto.TrainerDto "%>
<%@page import="java.util.ArrayList,java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOC TYPE html>
    <form:form method = "get" modelAttribute="traineeDto" action = "viewTraineePage">
        <input type="hidden" name="action" value="viewPage" >
        <form:hidden path = "employeeId" /><br>
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
            <h2>Trainee List</h2>
            <table style="width:100%">
                <tr><th>Id</th>
                    <th>Name</th>
                    <td colspan="2">Action</td>
                </tr>
            <%
     		    ArrayList<TraineeDto> trainees = (ArrayList<TraineeDto>)request.getAttribute("Trainees");
     		    for (TraineeDto traineeDto: trainees) {
     		    %>
		       <tr>
		           <td><%= traineeDto.getEmployeeId() %></td>
		           <td><%= traineeDto.getName() %></td>
                   <td><a href ="viewTraineePage?traineeId=<%=traineeDto.getEmployeeId()%>"><input type = "button" value = "view"></a></td>
		       </tr>

		<% } %>
		</table>
                <input type="button" value="Back to Main Menu" onclick="history.go(-1)">
                </div>
	</body>
</html>
