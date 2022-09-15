package com.ideas2it.employee.controller;

import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.service.impl.TrainerServiceImpl;
import com.ideas2it.employee.service.interf.TrainerService_Interf;
import com.ideas2it.employee.service.impl.TraineeServiceImpl;
import com.ideas2it.employee.service.interf.TraineeService_Interf;
import com.ideas2it.employee.common.constant.Constant;
import com.ideas2it.employee.utility.DateUtil;
import com.ideas2it.employee.utility.NumberUtil;
import com.ideas2it.employee.customException.TraineeNotFoundException;
import com.ideas2it.employee.customException.TrainerNotFoundException;
import com.ideas2it.employee.customException.BadRequestException;
import com.ideas2it.employee.common.CommonUtil;

import java.util.Scanner; 
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


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
public class EmployeeController {
    private Logger logger = LogManager.getLogger(EmployeeController.class);
    private Scanner scanner = new Scanner(System.in);
    private TraineeService_Interf traineeService = new TraineeServiceImpl(); 
    private TrainerService_Interf trainerService = new TrainerServiceImpl();
    private DateTimeFormatter format = DateTimeFormatter.ofPattern(Constant.dateTimeFormat);
                      
    public static void main (String args[]) {
        EmployeeController employeeController = new EmployeeController();
        employeeController.choiceSelection();
    }

    /**
     * <p>
     * choiceSelection method will show the option to add,update,delete,display   
     * employee
     *  </p>
     *
     * @return {@link void} return nothing 
     **/
    private void choiceSelection() {
        int choice ;
         
        do {
            System.out.println("\nWelcome to Employee Details " + "\n");
            System.out.println("\t TRAINER PORTAL \n");
            System.out.println("a) Press 1 for Add Trainer details " + "\n" +
                        "b) Press 2 for View Trainer details " + "\n" +
                        "c) Press 3 for Update Trainer details " + "\n" +
                        "d) Press 4 for Delete Trainer details " + "\n");
            System.out.println("\n\t TRAINEE PORTAL \n");
            System.out.println("e) Press 5 for Add Trainee details" + "\n" +
                        "f) Press 6 for View Trainee details" + "\n" +
                        "g) Press 7 for Update Trainee details" + "\n" +
                        "h) Press 8 for Delete Trainee details" + "\n" + "\n" +
                        "\tPress 9 for Exit" + "\n");
            try {    
                choice = scanner.nextInt(); 
            } catch (InputMismatchException e) {
                logger.info("\nInvalid option....Press 1 to 8 to process.....");
                scanner = new Scanner(System.in);                                
                choice = 0;                
            } 
         
	    switch (choice) {
	    case 1 : 
	        logger.info("\n\t Enter Trainer Details to add....");
                getEmployeeDetails(choice);
    		break;

	    case 2 : 
                displayTrainerDetails();
    		break;

            case 3: 
                updateTrainer();
                break;

            case 4: 
                deleteTrainer();
                break;

            case 5: 
	        logger.info("\n\t Enter Trainee Details to add....");
    		getEmployeeDetails(choice);
     		break;

            case 6: 
	        displayTraineeDetails();
 		break;

            case 7: 
                updateTrainee();
                break;

	    case 8: 
                deleteTrainee();
		break;         
                        
            case 9: 
                logger.info("\nExited.....");
                System.exit(0);
                
            default:                                            
                logger.info("\nDo you want to continue again ?...Press Y to continue and N to exit....");
                String tempOption = scanner.next();
                if (tempOption.equalsIgnoreCase("y")) { 
                    choice = 1;                          
                }           
            }          
        } while (choice >= 1 && choice <= 9);            
    }

    /**
     * <p>
     * This method get required informations from the user, then validations     
     * done in service, invalid details are returned from service, again the values  
     * inputed from the user until all validations are true.
     * </p>
     * 
     * @param choice   
     * @return {@link void} return nothing
     **/
    private void getEmployeeDetails(int choice) { 
        int experience = 0;
        int trainingPeriod = 0;
        List<Integer> trainersId = new ArrayList<Integer>();
        int numberOfTrainers = 0;

        logger.info("Enter Your Name ");
        String name = scanner.next();

	String tempDate;
        LocalDate dfDateOfBirth = null;
	boolean isValidDate = false;
	do {
	    logger.info("\nEnter Your Date Of Birth , Required Format is d/M/yyyy ");	    
            tempDate = scanner.next();
	    try {
		dfDateOfBirth = LocalDate.parse(tempDate, format);                
		isValidDate = true;
	    } catch (DateTimeParseException e) {
		logger.error("Invalid Date Format");
	    }
	} while (!isValidDate);
        String dateOfBirth = dfDateOfBirth.toString();

	LocalDate dfDateOfJoin  = null;
        isValidDate = false;
	do {
	    logger.info("\nEnter Your Joining Date , Required Format is d/M/yyyy ");
	    tempDate = scanner.next();
	    try {
		dfDateOfJoin = LocalDate.parse(tempDate, format);
		isValidDate = true;
	    } catch (DateTimeParseException e) {
		logger.error("Invalid Date Format");
	    }
	} while (!isValidDate);
        String dateOfJoin = dfDateOfJoin.toString(); 
 
	            
        logger.info("\nEnter GenderChoice \n\t1. Male \n\t2.Female \n\t3. Others");
        String gender = scanner.next();
                 
        logger.info("\nEnter Your PhoneNumber ");
        long phoneNumber = 0;
        try {
            phoneNumber = scanner.nextLong();
        } catch (InputMismatchException e) {                        
            scanner = new Scanner(System.in);
        }

        logger.info("\nEnter Your Email Id ");
        String emailId = scanner.next();

        logger.info("\nEnter Your Salary ");
        double salary = 0.0;
        try {
            salary = scanner.nextDouble();
        } catch (InputMismatchException e){
            scanner = new Scanner(System.in);
        }
	
        logger.info("\nEnter Your Aadhar Id ");
        long aadharId = 0;
        try {
            aadharId = scanner.nextLong();
        } catch (InputMismatchException e) {
            scanner = new Scanner(System.in);
        }
         
        logger.info("\nEnter your BloodGroup \n\t1. A+ \n\t2. B+ \n\t3. O+ " +
                           "\n\t4. AB+ \n\t5. A- \n\t6. B- \n\t7. O- \n\t8. AB- ");
        String bloodGroup = scanner.next();

        Qualification qualification = new Qualification();
        logger.info("\nEnter your Qualification");        
        qualification.setQualification(scanner.next());
        
        Role role = null;
        if (choice == 1) {
            logger.info("\nEnter your Experience in year");
            experience  = scanner.nextInt();           
        } else if (choice == 5) {
            logger.info("\nEnter your Training Period in year");
            trainingPeriod  = scanner.nextInt();
            logger.info("\nEnter Number of Trainers :");
            try {
	        numberOfTrainers = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner = new Scanner(System.in);
            }	    
            trainersId = fetchTrainersId(numberOfTrainers);            
        }
                                 
        List<Integer> errorList = new ArrayList<Integer>();        
        do {
            errorList.clear();
            int id = 1;
            try {
                if (choice == 1) {
                    role = new Role();
                    role.setRole("Trainer");
                    
                    Trainer trainer = new Trainer(name, dateOfBirth, dateOfJoin, gender,
                                                  phoneNumber, emailId, salary, aadharId, bloodGroup, qualification,experience,role);
                    errorList = trainerService.validateAndAddTrainerDetails(trainer);
                } else if (choice == 5) {
                    role = new Role();
                    role.setRole("Trainee");
                    Trainee trainee = new Trainee(name, dateOfBirth, dateOfJoin, gender,
                                                  phoneNumber, emailId, salary, aadharId, bloodGroup, qualification,trainingPeriod,role, trainersId);
                    errorList = traineeService.validateAndAddTraineeDetails(trainee);
                } 
            } catch (BadRequestException e) {
                logger.error(e.getMessage());
                errorList = e.errors;
            }
                                                
            if (errorList.size() > 0) {
                for (Integer eachOption : errorList) {                                    
                    switch (eachOption) {
                    case 1:
                        logger.info("\nEnter Your Name ");
                        name = scanner.next();
                        break;
        
                    case 2:                        
	                dfDateOfBirth = null;
	                isValidDate = false;
	                do {
	                    logger.info("\nEnter Your Date Of Birth , Required Format is d/M/yyyy ");	    
                            tempDate = scanner.next();
	                    try {
		                dfDateOfBirth = LocalDate.parse(tempDate, format);                
		                isValidDate = true;
	                    } catch (DateTimeParseException e) {
		                logger.warn("Invalid Date Format");
	                    }
	                } while (!isValidDate);
                        dateOfBirth = dfDateOfBirth.toString();	                                                                    
                        break;
        
                    case 3:                        
	                dfDateOfJoin  = null;
                        isValidDate = false;
	                do {
	                    logger.info("\nEnter Your Joining Date , Required Format is d/M/yyyy ");
	                    tempDate = scanner.next();
	                    try {
		                dfDateOfJoin = LocalDate.parse(tempDate, format);
		                isValidDate = true;
	                    } catch (DateTimeParseException e) {
		                logger.warn("Invalid Date Format");
	                    }
	                } while (!isValidDate);
                        dateOfJoin = dfDateOfJoin.toString();	                     
                        break;
        	
                    case 4:            
                        logger.info("\nEnter Your Gender Choice");
                        gender = scanner.next();
                        break;   
        
                    case 5:
                        logger.info("\nEnter Your PhoneNumber ");                        
                        try {
                            phoneNumber = scanner.nextLong();
                        } catch (InputMismatchException e) {                        
                            scanner = new Scanner(System.in);
                        }                       
                        break;                      

                    case 6:
                        logger.info("\nEnter Your Email Id ");
                        emailId = scanner.next();
                        break;
                        
                    case 7:
                        logger.info("\nEnter Your Salary ");
                        try {
                            salary = scanner.nextDouble();
                        } catch (InputMismatchException e){
                            scanner = new Scanner(System.in);
                        }                 
                        break;
                        
                    case 8:
                        logger.info("\nEnter Your Aadhar Id ");
                        try {
                            aadharId = scanner.nextLong();
                        } catch (InputMismatchException e) {
                            scanner = new Scanner(System.in);
                        }                          
                        break;
                         
                    case 9:
                        logger.info("\nEnter Your BloodGroup Choice");
                        bloodGroup = scanner.next();
                        break;

                    case 10:
                        logger.info("\nEnter Your Qualification");
                        qualification = new Qualification();
                        qualification.setQualification(scanner.next());
                        break;

                   case 11:
                       logger.info("\nEnter Number of Trainers :");
                       try {
	                   numberOfTrainers = scanner.nextInt();
                       } catch (InputMismatchException e) {
                           scanner = new Scanner(System.in);
                       }	    
                       trainersId = fetchTrainersId(numberOfTrainers);
                       break;
                    }
                }        
            } 
        } while (errorList.size() != 0);
    }

    public List<Integer> fetchTrainersId(int numberOfTrainers) {
        List<Integer> assignedTrainersId = new ArrayList<>();
	for (int i = 1; i <= numberOfTrainers; i++) {
	    logger.info("Enter your TrainersId");
	    assignedTrainersId.add(scanner.nextInt());
	}
	return assignedTrainersId;
    }
       
    /**
     * <p>
     * It displays the Trainer details stored as Object along with Trainer count. 
     * If the list is empty, it shows an message.
     * </p>
     *
     * @return {@link void} return nothing 
     **/
    private void displayTrainerDetails() {
        logger.info("\n\t Displaying Trainer Details.... \n");
        int trainerSize = trainerService.getTrainers().size();
        if (trainerSize < 1) {
            logger.warn("\t No Data Found... Please update Trainer Details..." + "\n");
        } else {
            logger.info("TRAINER'S COUNT : " + trainerSize );
            CommonUtil.displayTrainerFormat();
            for (Trainer eachtTrainer : trainerService.getTrainers()) {
                logger.info(eachtTrainer);        
            }
        }        
    } 

    /**
     * <p>
     * It will call the display method to display Trainee details stored as Object along with Trainee count. 
     * If the list is empty, it shows an message.
     * </p>
     *
     * @return {@link void} return nothing 
     **/
    private void displayTraineeDetails() {
        logger.info("\n\t Displaying Trainee Details.... \n");
        int traineeSize = traineeService.getTrainees().size();
        if (traineeSize < 1) {
            logger.warn("\t No Data Found... Please update Trainee Details..." + "\n");
        } else {
            logger.info("TRAINEE'S Count : " + traineeSize );
            CommonUtil.displayTraineeFormat();        
            for (Trainee eachtTrainee : traineeService.getTrainees()) {            
                logger.info(eachtTrainee);
            } 
        } 
    } 
          
    /**
     * <p>
     * This method get the id from user, if it is valid then it will call updateTrainerDetails methos.     
     * if not found it shows a message.
     * </p>
     *
     * @return {@link void} return nothing 
     */
   private void updateTrainer() {
       int updateChoice = 0;
       try {
           logger.info("\nPlease Enter your TrainerId ");                
           int trainerId = scanner.nextInt(); 
           if (trainerService.validateTrainerId(trainerId)) { 
               Trainer trainer = trainerService.getTrainerById(trainerId);                            
                do {
                    CommonUtil.displayUpdateChoices();      
                    try {
                        updateChoice = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        scanner = new Scanner(System.in);
                        updateChoice = 0;
                    }
                    switch(updateChoice) {
                    case 1:
                        logger.info("\nEnter new phone number: ");
                        long phoneNumber = 0;
                        try {
                            phoneNumber = scanner.nextLong();
                            while (!trainerService.validateTrainerPhoneNumber(phoneNumber,trainer, updateChoice)) {
                                logger.warn("\nEnter valid phone number: ");
                                phoneNumber = scanner.nextLong();
                            }
                        } catch(InputMismatchException e){
                            scanner = new Scanner(System.in);
                        }                    
                        break;
                   
                    case 2:
                        logger.info("Enter Your Email Id ");
                        String emailId = scanner.next();
                        while (!trainerService.validateTrainerEmailId(emailId,trainer, updateChoice)) {
                            logger.warn("\nEnter valid email: ");
                            emailId = scanner.next();
                        }                    
                        break;
                       
                    case 3:
                        logger.info("\nEnter Your Aadhar Id ");
                        long aadharId = 0;
                        try {
                            aadharId = scanner.nextLong();
                            while (!trainerService.validateTrainerAadhar(aadharId,trainer, updateChoice)) {
                                logger.warn("\nEnter valid AadharID number: ");
                                aadharId = scanner.nextLong();
                            }
                        } catch (InputMismatchException e) {
                            scanner = new Scanner(System.in);
                        }                                     
                        break;

                    case 4:
                        logger.info("You are exited from Trainer Update.....");
                        break;
                    
                    default:
                        logger.error("Invalid choice.....\n");
                        scanner = new Scanner(System.in);
                        updateChoice = 1;                                  
                    }
                } while (updateChoice >= 1 && updateChoice < 4);  
            }
        } catch(InputMismatchException e){
             scanner = new Scanner(System.in);
        } catch (TrainerNotFoundException e) {
            logger.error(e.getMessage());
        }
    } 

    /**
     * <p>
     * This method get the id from user, if it is valid then it will update TraineeDetails methos.     
     * </p>
     *
     * @return {@link void} return nothing 
     */
    private void updateTrainee() {
       int updateChoice = 0;
       try {
           logger.info("\nPlease Enter your TraineeId ");                
           int traineeId = scanner.nextInt(); 
           if (traineeService.validateTraineeId(traineeId)) { 
               Trainee trainee = traineeService.getTraineeById(traineeId);                            
                do {
                    CommonUtil.displayUpdateChoices();      
                    try {
                        updateChoice = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        scanner = new Scanner(System.in);
                        updateChoice = 0;
                    }
                    switch(updateChoice) {
                    case 1:
                        logger.info("\nEnter new phone number: ");
                        long phoneNumber = 0;
                        try {
                            phoneNumber = scanner.nextLong();
                            while (!traineeService.validateTraineePhoneNumber(phoneNumber, trainee, updateChoice)) {
                                logger.warn("\nEnter valid phone number: ");
                                phoneNumber = scanner.nextLong();
                            }
                        } catch(InputMismatchException e){
                            scanner = new Scanner(System.in);
                        }                    
                        break;
                   
                    case 2:
                        logger.info("Enter Your Email Id ");
                        String emailId = scanner.next();
                        while (!traineeService.validateTraineeEmailId(emailId,trainee, updateChoice)) {
                            logger.warn("\nEnter valid email: ");
                            emailId = scanner.next();
                        }                    
                        break;
                       
                    case 3:
                        logger.info("\nEnter Your Aadhar Id ");
                        long aadharId = 0;
                        try {
                            aadharId = scanner.nextLong();
                            while (!traineeService.validateTraineeAadharId(aadharId,trainee, updateChoice)) {
                                logger.warn("\nEnter valid AadharID number: ");
                                aadharId = scanner.nextLong();
                            }
                        } catch (InputMismatchException e) {
                            scanner = new Scanner(System.in);
                        }                                     
                        break;

                    case 4:
                        logger.info("You are exited from Trainee Update.....");
                        break;
                    
                    default:
                        logger.info("Invalid choice.....\n");
                        scanner = new Scanner(System.in);
                        updateChoice = 1;                                  
                    }
                } while (updateChoice >= 1 && updateChoice < 4);  
            }
        } catch(InputMismatchException e){
             scanner = new Scanner(System.in);
        } catch (TraineeNotFoundException e) {
            logger.error(e.getMessage());
        }
    } 
      
    /**
     * <p>
     * This method get id from user, if it is valid it will send it to service class
     * to perform deletion.
     * 
     * </p>
     *
     * @return {@link void} return nothing 
     **/
    private void deleteTrainer() {
        logger.info("\nPlease Enter your TrainerId ");
        int trainerId = 0;
        try {
            trainerId = scanner.nextInt(); 
            if (trainerService.validateTrainerId(trainerId)) {                  
                trainerService.removeTrainerDetails(trainerId);
            }                                          
        } catch(InputMismatchException e) {
            logger.warn("\nPlease Enter Valid TrainerId, Id should contain digits only"); 
            scanner = new Scanner(System.in);
        } catch (TrainerNotFoundException e) {
            logger.error(e.getMessage());
        }                    
    } 

    /**
     * <p>
     * This method get id from user, if it is valid it will send it to service class
     * to perform deletion.
     * 
     * </p>
     *
     * @return {@link void} return nothing 
     **/
    private void deleteTrainee() {
        logger.info("\nPlease Enter your TraineeId ");
        int traineeId = 0;
        try {
            traineeId = scanner.nextInt(); 
            if (traineeService.validateTraineeId(traineeId)) {                  
                traineeService.removeTraineeDetails(traineeId);
            }                       
        } catch(InputMismatchException e) {
            logger.warn("\nPlease Enter Valid TraineeId, Id should contain digits only"); 
            scanner = new Scanner(System.in);        
        } catch (TraineeNotFoundException e) {
            logger.error(e.getMessage());
        } 
    }

 
}  
      



 
	    



	