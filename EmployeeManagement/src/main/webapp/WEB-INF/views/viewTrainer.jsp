
<%@page import="com.ideas2it.employee.dto.EmployeeDto, com.ideas2it.employee.dto.TrainerDto "%>
<%@page import="java.util.ArrayList,java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOC TYPE html>
    <form:form method = "get" modelAttribute="trainerDto" action = "viewPage">
    <input type="hidden" name="action" value="viewPage" >
    <form:hidden path = "employeeId" /><br>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>

    <body>
        <table>
            <style>table, th, td {border: 1px solid black;border-collapse: collapse;}</style>
                <h2>Trainer List</h2>
                <table style="width:100%">
                <tr><th>Id</th>
                    <th>Name</th>
                    <td colspan="2">Action</td>
                </tr>

		    <%
		    ArrayList<TrainerDto> trainers = (ArrayList<TrainerDto>)request.getAttribute("Trainers");
		    for (TrainerDto trainerDto: trainers) {
		    %>
		       <tr>
		            <td><%= trainerDto.getEmployeeId() %></td>
		            <td><%= trainerDto.getName() %></td>
		            <td><a href ="viewPage?trainerId=<%=trainerDto.getEmployeeId()%>"><input type = "button" value = "view"></a></td>
		       </tr>

		<% } %>
		</table>
                <input type="button" value="Back to Main Menu" onclick="history.go(-1)">
                </div>
	</body>
</html>
