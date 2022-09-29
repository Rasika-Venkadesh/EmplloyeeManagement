package com.ideas2it.employee.controller;

import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.service.impl.TrainerServiceImpl;
import com.ideas2it.employee.service.interf.TrainerService_Interf;
import com.ideas2it.employee.service.impl.TraineeServiceImpl;
import com.ideas2it.employee.service.interf.TraineeService_Interf;
import com.ideas2it.employee.customException.TraineeNotFoundException;
import com.ideas2it.employee.customException.TrainerNotFoundException;
import com.ideas2it.employee.customException.BadRequestException;

import java.util.Scanner;
import java.util.Arrays; 
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.lang.NumberFormatException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;


/**
 * <p>
 * EmployeeController class  will communicate with user and get the options to 
 * add,display,update and delete (CRUD) object in the list of trainer and 
 * trainee
 * </p> 
 *
 * @author Rasika Venkadesh
 * @version 1.0
 **/
public class EmployeeController extends HttpServlet {
    private Logger logger = LogManager.getLogger(EmployeeController.class);
    private TraineeService_Interf traineeService = new TraineeServiceImpl(); 
    private TrainerService_Interf trainerService = new TrainerServiceImpl(); 
    
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {   
	    String task = request.getParameter("action");
	    switch(task) {
	    case "trainerRegister":
	 	addTrainer(request, response);
		break;

            case "showTrainer":
	 	viewTrainer(request, response);
		break;

      	    case "updateTrainer":
	 	updateTrainer(request, response);
		break;

	    case "deleteTrainer":
	 	deleteTrainer(request, response);
		break;
		
	    case "traineeRegister":
	 	addTrainee(request, response);
		break;
	
            case "showTrainee":
	 	viewTrainee(request, response);
		break;

	    case "updateTrainee":
	 	updateTrainee(request, response);
		break;

	    case "deleteTrainee":
	 	deleteTrainee(request, response);
		break; 
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException { 
        doPost(request,response);
    }
    
    public void addTrainer(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
	response.setContentType("text/html"); 
	PrintWriter printWriter = response.getWriter();
        HttpSession session = request.getSession(false);
	String name = request.getParameter("name");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String dateOfJoin = request.getParameter("dateOfJoin");
	String gender = request.getParameter("gender");
       	long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
	String emailId = request.getParameter("emailId");
        double salary = Double.parseDouble(request.getParameter("salary"));         
        long aadharId = Long.parseLong(request.getParameter("aadharId"));
	String bloodGroup = request.getParameter("bloodGroup");
	String quali = request.getParameter("qualification");
        Qualification qualification = new Qualification();
        qualification.setQualification(quali);
	int experience = Integer.parseInt(request.getParameter("experience"));
        String roleString = request.getParameter("role");
        Role role = new Role();
        role.setRole(roleString); 
        Trainer existingTrainer = (Trainer)session.getAttribute("trainer");
	try {
            Employee employee = new Employee(name,dateOfBirth,dateOfJoin,gender,phoneNumber,emailId,
					  salary,aadharId,bloodGroup,qualification,role);
            Trainer trainer = new Trainer(employee,experience);         
            if (existingTrainer == null) {
	        trainerService.validateAndAddTrainerDetails(trainer);
	        printWriter.println("Trainer Added Successfully....."); 
            } else {
	        existingTrainer.getEmployee().setName(name);
                existingTrainer.getEmployee().setDateOfBirth(LocalDate.parse(dateOfBirth));
                existingTrainer.getEmployee().setDateOfJoin(LocalDate.parse(dateOfJoin));
                existingTrainer.getEmployee().setGender(gender);
                existingTrainer.getEmployee().setPhoneNumber(phoneNumber);
                existingTrainer.getEmployee().setEmailId(emailId);
                existingTrainer.getEmployee().setSalary(salary);
                existingTrainer.getEmployee().setAadharId(aadharId);
                existingTrainer.getEmployee().setBloodGroup(bloodGroup);
                existingTrainer.getEmployee().setQualification(qualification);
                existingTrainer.setExperience(experience);
                trainerService.validateAndAddTrainerDetails(existingTrainer);
            }
            printWriter.println("<a href='index.html'>Back to Main Menu</a>");
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/TrainerRegister.html");  
            requestDispatcher.include(request, response);  
	} catch (BadRequestException e) {
	    logger.debug(e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/TrainerRegister.html");  
            requestDispatcher.include(request, response);             
            printWriter.close();
	} 
    }
    
    public void viewTrainer(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        response.setContentType("text/html"); 
	PrintWriter printWriter = response.getWriter();
        List<Trainer> trainers = trainerService.getTrainers();
        if (trainers.size() < 1) {
            printWriter.println(" No Data Found... Please update Trainer Details...");
        } else {
            request.setAttribute("Trainers", trainers);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewTrainer.jsp");
            requestDispatcher.include(request, response);
        }
        printWriter.close();
    }


    public void updateTrainer(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        response.setContentType("text/html"); 
	PrintWriter printWriter = response.getWriter();

	String id = request.getParameter("trainerId");
        int trainerId = Integer.parseInt(id);
	Trainer trainer;
	try {
	    trainer = trainerService.getTrainerById(trainerId);
	    request.setAttribute("trainer", trainer);
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/registerOrUpdateTrainer.jsp?action=updateTrainer"); 
            requestDispatcher.forward(request, response);
	} catch (TrainerNotFoundException e) {
	    logger.error(e.getMessage());
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.html");  
            requestDispatcher.include(request, response);
	}
    
    }

    public void deleteTrainer(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        response.setContentType("text/html"); 
	PrintWriter printWriter = response.getWriter();
        String id = request.getParameter("trainerId");
        int trainerId = Integer.parseInt(id);
        trainerService.removeTrainerDetails(trainerId);
        logger.debug("Trainer " + trainerId + " deleted Successfully");
        printWriter.println("<a href='index.html'>Back to Main Menu</a>");
    }

    public void addTrainee(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        response.setContentType("text/html"); 
	PrintWriter printWriter = response.getWriter();        
        HttpSession session = request.getSession(false);
	String name = request.getParameter("name");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String dateOfJoin = request.getParameter("dateOfJoin");
	String gender = request.getParameter("gender");
       	long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
	String emailId = request.getParameter("emailId");
        double salary = Double.parseDouble(request.getParameter("salary"));         
        long aadharId = Long.parseLong(request.getParameter("aadharId"));
	String bloodGroup = request.getParameter("bloodGroup");
	String quali = request.getParameter("qualification");
        Qualification qualification = new Qualification();
        qualification.setQualification(quali);
	int trainingPeriod = Integer.parseInt(request.getParameter("trainingPeriod"));
        String roleString = request.getParameter("role");
        Role role = new Role();
        role.setRole(roleString);
        String ids = request.getParameter("trainersId");
        String[] stringIds = ids.replaceAll("\\[", "")
                              .replaceAll("]", "")
                              .split(",");
        int[] id = new int[stringIds.length]; 
        for (int i = 0; i < stringIds.length; i++) {
            id[i] = Integer.valueOf(stringIds[i]);
        }
        List<Integer> trainersId = Arrays.stream(id).boxed().toList();
        Trainee existingTrainee = (Trainee)session.getAttribute("trainee");	
	try {
            Employee employee = new Employee(name,dateOfBirth,dateOfJoin,gender,phoneNumber,emailId,
					  salary,aadharId,bloodGroup,qualification,role);
            Trainee trainee = new Trainee(employee,trainingPeriod,trainersId);
            if (existingTrainee == null) {
	        traineeService.validateAndAddTraineeDetails(trainee);
	        printWriter.println("Trainee Added Successfully....."); 
            } else {
	        existingTrainee.getEmployee().setName(name);
                existingTrainee.getEmployee().setDateOfBirth(LocalDate.parse(dateOfBirth));
                existingTrainee.getEmployee().setDateOfJoin(LocalDate.parse(dateOfJoin));
                existingTrainee.getEmployee().setGender(gender);
                existingTrainee.getEmployee().setPhoneNumber(phoneNumber);
                existingTrainee.getEmployee().setEmailId(emailId);
                existingTrainee.getEmployee().setSalary(salary);
                existingTrainee.getEmployee().setAadharId(aadharId);
                existingTrainee.getEmployee().setBloodGroup(bloodGroup);
                existingTrainee.getEmployee().setQualification(qualification);
                existingTrainee.setTrainingPeriod(trainingPeriod);
                existingTrainee.setTrainersId(trainersId);
                traineeService.validateAndAddTraineeDetails(existingTrainee);
            }
            printWriter.println("<a href='index.html'><br>Back to Main Menu<br></a>");
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/TraineeRegister.html");  
            requestDispatcher.include(request, response);  
	} catch (BadRequestException e) {
	    printWriter.println(e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/TraineeRegister.html");  
            requestDispatcher.include(request, response);             
            printWriter.close();
	} 
    }
    
    public void viewTrainee(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        response.setContentType("text/html"); 
	PrintWriter printWriter = response.getWriter();
        List<Trainee> trainees = traineeService.getTrainees();
        if (trainees.size() < 1) {
            printWriter.println(" No Data Found... Please update Trainee Details...");
        } else {
            request.setAttribute("Trainees", trainees);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewTrainee.jsp");
            requestDispatcher.include(request, response);
        }
        printWriter.close();
    }

    public void updateTrainee(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        response.setContentType("text/html"); 
	PrintWriter printWriter = response.getWriter();

	String id = request.getParameter("traineeId");
        int traineeId = Integer.parseInt(id);
	Trainee trainee;
	try {
	    trainee = traineeService.getTraineeById(traineeId);
	    request.setAttribute("trainee", trainee);
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/registerOrUpdateTrainee.jsp?action=updateTrainee"); 
            requestDispatcher.forward(request, response);
	} catch (TraineeNotFoundException e) {
	    logger.error(e.getMessage());
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.html");  
            requestDispatcher.include(request, response);
	}
    
    }

    public void deleteTrainee(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        response.setContentType("text/html"); 
	PrintWriter printWriter = response.getWriter();
        String id = request.getParameter("traineeId");
        int traineeId = Integer.parseInt(id);
        traineeService.removeTraineeDetails(traineeId);
        logger.debug("Trainee " + traineeId + " deleted Successfully");
        printWriter.println("<a href='index.html'>Back to Main Menu</a>");
    }


}  	