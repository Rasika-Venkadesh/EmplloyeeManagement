<!DOC TYPE html>
<html xmlns:class="http://www.w3.org/1999/xhtml">
<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@page import="com.ideas2it.employee.dto.EmployeeDto, com.ideas2it.employee.dto.TrainerDto, com.ideas2it.employee.dto.TraineeDto "%>
 <%@page import="java.util.ArrayList,java.util.List"%>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%
     String action= "${action}";
     %>
     <style>
       body {
          font-family: consolas;
          color: #1f253b;
          margin: 0;
          padding: 0;
       }

       h2, h3{
          margin:0;
       }

       .split {
          padding-top: 20px;
       }

       .left {
          width: 20%;
          background-color: #FFFFFF;
          float: left;
       }

       .right {
           float: right;
           width: 80%;
       }

       .centeredleft {
           text-align: center;
           padding-left : 40px;
           padding-right : 40px;
       }

       .centeredright {
           text-align: left;
           margin-top : 1px;
           margin-bottom : 10px;
           margin-left : 10px;
           margin-right : 10px;
           padding-left : 30px;
           padding-right : 30px;
       }

       .centered img {
           width: 100px;
           border-radius: 50%;
           padding-top : 10px;
           padding-bottom:10px;
           padding-left : 0px;
       }

       .properties {
           width : 200px;
           border-bottom-style : outset;
           border-radius : 10%;
           padding-right : 40px;
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
               background-color: #4da6ff;
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

            @media screen and (max-width: 800px) {
                .left, .right {
                       width: 100%;
                 }
                 .left {
                       height: 127%;
                 }
                 .right {
                       height : 100%;
                 }
            }
     </style>
</head>

<body>
<div class="split left">
    <div class="centeredleft">
        <img alt="Avatar" src="user.png" width="70%">
        <h2><p>${employeeDto.name}</p></h2>
        <p class="properties"><b>Software Engineer ${employeeDto.roleDto.role}</b></p>
        <p class="properties"><b>${employeeDto.dateOfBirth}</b></p>
        <p class="properties"><b>${employeeDto.getPhoneNumber()}</b></p>
        <p class="properties"><b>${employeeDto.getEmailId()}</b></p>
        <a href="update${action}?employeeId=${employeeDto.employeeId}" class="button">Update</a>
        <a href="delete${action}?employeeId=${employeeDto.employeeId}" class="button">Delete</a>
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