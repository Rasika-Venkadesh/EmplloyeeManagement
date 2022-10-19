<!DOC TYPE html>
<html xmlns:class="http://www.w3.org/1999/xhtml">
<%@page isELIgnored="false" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@page import="com.ideas2it.employee.dto.EmployeeDto, com.ideas2it.employee.dto.TrainerDto "%>
 <%@page import="java.util.ArrayList,java.util.List"%>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
  body {
  font-family: consolas;
  color: #1f253b;
}

h2, h3{
  margin:0;
}

.split {
  height: 100%;
  position: absolute;
  z-index: 1;
  top: 10;
  overflow-x: hidden;
  padding-top: 20px;
}

.left {
  width: 25%;
  left: 0;
  background-color: #FFFFFF;
  padding-top : 50px;

}

.right {
    overflow : auto;
    width: 75%;
    right: 0;
    padding-right : 60px;
    padding-left : 20px;

}

.centeredleft {
  position: absolute;
  top: 35%;
  left: 40%;
  transform: translate(-50%, -50%);
  text-align: center;
}

.centeredright {
  position: absolute;
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: left;
  margin-top : 1px;
  margin-bottom : 10px;
  margin-left : 10px;
  margin-right : 10px;
  padding-left : 30px;
}

.centered img {
  width: 200px;
  border-radius: 50%;
  padding-top : 10px;
  padding-bottom:10px;
  padding-left : 0px;
}

.properties {
  width : 200px;
  border-bottom-style : outset;
  border-radius : 10%;
}

table{
  border: 1px solid transparent;
  width : 100%;
  border-collapse:collapse;
}

th {
  text-align:left;
  padding-left : 40px;
  font-size : 17px;
  font-weight : 100;
}

td {
  text-align:left;
  padding-left : 80px;
  padding-right:50px;
  font-size : 10px;
}

tr {
  padding-top :10px;
  padding-bottom :10px;
  border-bottom-style : outset;
}

placeleft {
  float : left;
  padding-left : 30px;
  width: 12px;
}

placeright {
  float : right;
  padding-right : 150px;
}

.place {
  margin-left : 20px;
}

.round {
  border: 2px transparent green;
  border-radius: 20px;
  padding: 6px;
  background-color : #abb825;
  float : right;

}

.part1 {
  padding-left : 5px;
  padding-right : 50px;
  padding-bottom : 10px;
  text-align : left;
}

.topic {
  padding-left : 20px;
  padding-top : 5px;
  font-size: 25px;
}

.topicbody {
  padding-left : 40px;
}

.button {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}


 </style>
</head>
<body>

<div class="split left">
    <div class="centeredleft">
        <img alt="Avatar" src="user.png" width="70%">
        <h2><p>${trainerDto.name}</p></h2>
        <p class="properties"><b>Software Engineer ${trainerDto.roleDto.role}</b></p>
        <p class="properties"><b>${trainerDto.dateOfBirth}</b></p>
        <p class="properties"><b>${trainerDto.getPhoneNumber()}</b></p>
        <p class="properties"><b>${trainerDto.getEmailId()}</b></p>
        <a href="updateTrainee?traineeId=${traineeDto.employeeId}" class="button">Update</a>
        <a href="deleteTrainee?traineeId=${traineeDto.employeeId}" class="button">Delete</a>
        <p class="button" onclick="history.go(-2)">Back</p>
    </div>
</div>

<div class="split right">
    <div class = "part1">
        <p class = "topic" ><b>Professional Summary</b></p>
        <p class = "topicbody" > Experienced software engineer with a passion for developing innovative
            programs that expedite the efficiency and effectiveness of organizational success.
            Well-versed in technology and writing code to create systems that are reliable and
            user-friendly. Skilled leader who has the proven ability to motivate, educate, and
            manage a team of professionals to build software programs and effectively track
            changes. Confident communicator, strategic thinker, and innovative creator to develop
            software that is customized to meet a company's organizational needs, highlight their
            core competencies, and further their success.</p>
    </div>

    <table>
        <tr>
            <p class = "topic" ><b>Work Experience</b></p>
        </tr>
        <th colspan="3">Senior Developer</th>
        <tr>
            <td class = "placeleft properties ">Apple Inc</td>
            <td class = "placeleft  properties">Los Angles</td>
            <td class ="placeright round">Full Time</td>
        </tr>


        <th colspan = "3">Junior Developer</th>
        <tr>
            <td class = "placeleft properties ">Figma</td>
            <td class = "placeleft  properties">San Fransisco</td>
            <td class = "placeright round ">Full Time</td>
        </tr>

        <th colspan = "3">Intern Developer</th>
        <tr>
            <td class = "placeleft properties">MicroSoft</td>
            <td class = "placeleft properties">NewYork City</td>
            <td class = "placeright round">Full Time</td>
        </tr>
    </table>

    <table>
        <tr>
            <p class = "topic" ><b>Education</b></p>
        </tr>

        <th colspan = "3">Master of Computer Applications(MCA)</th>
        <tr>
            <td class= "placeleft properties">University Of Madras</td>
            <td class= "placeleft properties">Chennai</td>
        </tr>

        <th colspan = "3">Bachelor of Computer Science(B.Sc CS)</th>
        <tr>
            <td class= "placeleft properties">Yadava College</td>
            <td class= "placeleft properties">Madurai</td>
        </tr>
    </table>

</div>
</body>
</html>