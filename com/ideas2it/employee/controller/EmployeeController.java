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
                System.out.println("\nInvalid option....Press 1 to 8 to process.....");
                scanner = new Scanner(System.in);                                
                choice = 0;                
            } 
         
	    switch (choice) {
	    case 1 : 
	        System.out.println("\n\t Enter Trainer Details to add....");
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
	        System.out.println("\n\t Enter Trainee Details to add....");
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
                System.out.println("\nExited.....");
                System.exit(0);
                
            default: 
                                           
                System.out.println("\nDo you want to continue again ?...Press Y to continue and N to exit....");
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
        System.out.println("Enter Your Name ");
        String name = scanner.next();

	String tempDate;
        LocalDate dfDateOfBirth = null;
	boolean isValidDate = false;
	do {
	    System.out.println("\nEnter Your Date Of Birth , Required Format is d/M/yyyy ");	    
            tempDate = scanner.next();
	    try {
		dfDateOfBirth = LocalDate.parse(tempDate, format);                
		isValidDate = true;
	    } catch (DateTimeParseException e) {
		System.out.println("Invalid Date Format");
	    }
	} while (!isValidDate);
        String dateOfBirth = dfDateOfBirth.toString();

	LocalDate dfDateOfJoin  = null;
        isValidDate = false;
	do {
	    System.out.println("\nEnter Your Joining Date , Required Format is d/M/yyyy ");
	    tempDate = scanner.next();
	    try {
		dfDateOfJoin = LocalDate.parse(tempDate, format);
		isValidDate = true;
	    } catch (DateTimeParseException e) {
		System.out.println("Invalid Date Format");
	    }
	} while (!isValidDate);
        String dateOfJoin = dfDateOfJoin.toString(); 
 
	            
        System.out.println("\nEnter GenderChoice \n\t1. Male \n\t2.Female \n\t3. Others");
        String gender = scanner.next();
                 
        System.out.println("\nEnter Your PhoneNumber ");
        long phoneNumber = 0;
        try {
            phoneNumber = scanner.nextLong();
        } catch (InputMismatchException e) {                        
            scanner = new Scanner(System.in);
        }

        System.out.println("\nEnter Your Email Id ");
        String emailId = scanner.next();

        System.out.println("\nEnter Your Salary ");
        double salary = 0.0;
        try {
            salary = scanner.nextDouble();
        } catch (InputMismatchException e){
            scanner = new Scanner(System.in);
        }
	
        System.out.println("\nEnter Your Aadhar Id ");
        long aadharId = 0;
        try {
            aadharId = scanner.nextLong();
        } catch (InputMismatchException e) {
            scanner = new Scanner(System.in);
        }
         
        System.out.println("\nEnter your BloodGroup \n\t1. A+ \n\t2. B+ \n\t3. O+ " +
                           "\n\t4. AB+ \n\t5. A- \n\t6. B- \n\t7. O- \n\t8. AB- ");
        String bloodGroup = scanner.next();

        Qualification qualification = new Qualification();
        System.out.println("\nEnter your Qualification");        
        qualification.setQualification(scanner.next());

        int experience = 0;
        int trainingPeriod = 0;
        List<Integer> trainersId = new ArrayList<Integer>();
        int noOfTrainer = 0;
        Role role = null;
        if (choice == 1) {
            System.out.println("\nEnter your Experience in year");
            experience  = scanner.nextInt();
           
        } else if (choice == 5) {
            System.out.println("\nEnter your Training Period in year");
            trainingPeriod  = scanner.nextInt();
            System.out.println("\nEnter Number of Trainers :");
            try {
	        noOfTrainer = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner = new Scanner(System.in);
            }	    
            trainersId = fetchTrainersId(noOfTrainer);            
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
                System.out.println(e.getMessage());
                errorList = e.errors;
            }
                                                
            if (errorList.size() > 0) {
                for (Integer eachOption : errorList) {                                    
                    switch (eachOption) {
                    case 1:
                        System.out.println("\nEnter Your Name ");
                        name = scanner.next();
                        break;
        
                    case 2:                        
	                dfDateOfBirth = null;
	                isValidDate = false;
	                do {
	                    System.out.println("\nEnter Your Date Of Birth , Required Format is d/M/yyyy ");	    
                            tempDate = scanner.next();
	                    try {
		                dfDateOfBirth = LocalDate.parse(tempDate, format);                
		                isValidDate = true;
	                    } catch (DateTimeParseException e) {
		                System.out.println("Invalid Date Format");
	                    }
	                } while (!isValidDate);
                        dateOfBirth = dfDateOfBirth.toString();	                                                                    
                        break;
        
                    case 3:                        
	                dfDateOfJoin  = null;
                        isValidDate = false;
	                do {
	                    System.out.println("\nEnter Your Joining Date , Required Format is d/M/yyyy ");
	                    tempDate = scanner.next();
	                    try {
		                dfDateOfJoin = LocalDate.parse(tempDate, format);
		                isValidDate = true;
	                    } catch (DateTimeParseException e) {
		                System.out.println("Invalid Date Format");
	                    }
	                } while (!isValidDate);
                        dateOfJoin = dfDateOfJoin.toString();	                     
                        break;
        	
                    case 4:            
                        System.out.println("\nEnter Your Gender Choice");
                        gender = scanner.next();
                        break;   
        
                    case 5:
                        System.out.println("\nEnter Your PhoneNumber ");                        
                        try {
                            phoneNumber = scanner.nextLong();
                        } catch (InputMismatchException e) {                        
                            scanner = new Scanner(System.in);
                        }                       
                        break;                      

                    case 6:
                        System.out.println("\nEnter Your Email Id ");
                        emailId = scanner.next();
                        break;
                        
                    case 7:
                        System.out.println("\nEnter Your Salary ");
                        try {
                            salary = scanner.nextDouble();
                        } catch (InputMismatchException e){
                            scanner = new Scanner(System.in);
                        }                 
                        break;
                        
                    case 8:
                        System.out.println("\nEnter Your Aadhar Id ");
                        try {
                            aadharId = scanner.nextLong();
                        } catch (InputMismatchException e) {
                            scanner = new Scanner(System.in);
                        }                          
                        break;
                         
                    case 9:
                        System.out.println("\nEnter Your BloodGroup Choice");
                        bloodGroup = scanner.next();
                        break;

                    case 10:
                        System.out.println("\nEnter Your Qualification");
                        qualification = new Qualification();
                        qualification.setQualification(scanner.next());
                        break;

                   case 11:
                       System.out.println("\nEnter Number of Trainers :");
                       try {
	                   noOfTrainer = scanner.nextInt();
                       } catch (InputMismatchException e) {
                           scanner = new Scanner(System.in);
                       }	    
                       trainersId = fetchTrainersId(noOfTrainer);
                       break;
                    }

                }  
      
            } 

       } while (errorList.size() != 0);

    }

    public List<Integer> fetchTrainersId(int noOfTrainer) {
        List<Integer> assignesTrainersId = new ArrayList<>();
	for (int i = 1; i <= noOfTrainer; i++) {
	    System.out.println("Enter your TrainersId");
	    assignesTrainersId.add(scanner.nextInt());
	}
	return assignesTrainersId;
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
        System.out.println("\n\t Displaying Trainer Details.... \n");
        int trainerSize = trainerService.getTrainers().size();
        if (trainerSize < 1) {
            System.out.println("\t No Data Found... Please update Trainer Details..." + "\n");
        } else {
            System.out.println("TRAINER'S COUNT : " + trainerSize );
            CommonUtil.displayFormat();
            for (Trainer eachtTrainer : trainerService.getTrainers()) {
                System.out.println(eachtTrainer);        
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
        System.out.println("\n\t Displaying Trainee Details.... \n");
        int traineeSize = traineeService.getTrainees().size();
        if (traineeSize < 1) {
            System.out.println("\t No Data Found... Please update Trainee Details..." + "\n");
        } else {
            System.out.println("TRAINEE'S Count : " + traineeSize );
            CommonUtil.displayFormat1();        
            for (Trainee eachtTrainee : traineeService.getTrainees()) {            
                System.out.println(eachtTrainee);
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
        System.out.println("\nPlease Enter your TrainerId ");
        int trainerId = 0;
        try {
            trainerId = scanner.nextInt();                        
        }catch(InputMismatchException e){
            scanner = new Scanner(System.in);
        }
        try {
            if (trainerService.validateTrainerId(trainerId)) {                  
                updateTrainerDetails(trainerId);
            }
        } catch (TrainerNotFoundException e) {
            System.out.println(e.getMessage());
        }
    } 

    /**
     * <p>
     * This method gets choice from user to update particular field. If the choice is valid , 
     * the data which is to be updated, will send to service class to update.
     *  The loop will run until valid choice.
     * </p>
     *
     * @param trainerId
     * @return {@link void} return nothing 
     **/
   private void updateTrainerDetails(int trainerId) {
        int updateChoice = 0;
        Trainer trainer = trainerService.getTrainerById(trainerId); 
        do {
            displayUpdateChoices();      
            try {
                updateChoice = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner = new Scanner(System.in);
                updateChoice = 0;
            }

            switch(updateChoice) {
            case 1:
                System.out.println("\nEnter new phone number: ");
                long phoneNumber = 0;
                try {
                    phoneNumber = scanner.nextLong();
                }catch(InputMismatchException e){
                    scanner = new Scanner(System.in);
                }                    
                while (!trainerService.validateTrainerPhoneNumber(phoneNumber,trainer, updateChoice)) {
                    System.out.println("\nEnter valid phone number: ");
                    try {
                       phoneNumber = scanner.nextLong();
                    }catch(InputMismatchException e){
                       scanner = new Scanner(System.in);
                    }
                }
                break;
                   
            case 2:
                System.out.println("Enter Your Email Id ");
                String emailId = scanner.next();
                while (!trainerService.validateTrainerEmailId(emailId,trainer, updateChoice)) {
                    System.out.println("\nEnter valid email: ");
                    emailId = scanner.next();
                }                    
                break;
                       
            case 3:
                System.out.println("\nEnter Your Aadhar Id ");
                long aadharId = 0;
                try {
                    aadharId = scanner.nextLong();
                } catch (InputMismatchException e) {
                    scanner = new Scanner(System.in);
                } 
                while (!trainerService.validateTrainerAadhar(aadharId,trainer, updateChoice)) {
                    System.out.println("\nEnter valid AadharID number: ");
                    try {
                        aadharId = scanner.nextLong();
                    } catch (InputMismatchException e) {
                        scanner = new Scanner(System.in);
                    } 
                }
                break;

            case 4:
                System.out.println("You are exited from Trainer Update.....");
                break;
                    
            default:
                System.out.println("Invalid choice.....\n");
                scanner = new Scanner(System.in);
                updateChoice = 1;                                  
            }

        } while (updateChoice >= 1 && updateChoice < 4);        

    }

    /**
     * <p>
     * This method get the id from user, if it is valid then it will call updateTraineeDetails methos.     
     * </p>
     *
     * @return {@link void} return nothing 
     */
   private void updateTrainee() {
        System.out.println("\nPlease Enter your TraineeId ");
        int traineeId = 0;
        try {
            traineeId = scanner.nextInt();                        
        } catch (InputMismatchException e) {
            scanner = new Scanner(System.in); 
        }
        try {
            if (traineeService.validateTraineeId(traineeId)) {                  
                updateTraineeDetails(traineeId);
            }
        } catch (TraineeNotFoundException e) {
            System.out.println(e.getMessage());
            scanner = new Scanner(System.in);
        } 
    } 

    /**
     * <p>
     * This method gets choice from user to update particular field. If the choice is valid , 
     * the data which is to be updated, will send to service class to update.
     * The loop will run until valid choice.
     * </p>
     * @param traineeid
     * @return {@link void} return nothing 
     **/    
   private void updateTraineeDetails(int traineeId) {
        int updateChoice = 0; 
        int traineeSize = traineeService.getTrainees().size();
        if (traineeSize == 0) {
            System.out.println("\nNo Trainee is Found...");
        } else {             
            Trainee trainee = traineeService.getTraineeById(traineeId); 
            do {
                displayUpdateChoices();      
                try {
                    updateChoice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    scanner = new Scanner(System.in);
                    updateChoice = 0;
                }

                switch(updateChoice) {
                case 1:
                    System.out.println("\nEnter your PhoneNumber to update");
                    long phoneNumber = 0;
                    try {
                        phoneNumber = scanner.nextLong();
                    } catch (InputMismatchException e) {
                        scanner = new Scanner(System.in);
                    }
                    while(!traineeService.validateTraineePhoneNumber(phoneNumber,trainee, updateChoice)){
                        System.out.println("\nEnter valid phone number: ");
                        try {
                           phoneNumber = scanner.nextLong();
                        } catch(InputMismatchException e) {
                           scanner = new Scanner(System.in);
                        }
                    }
                    break;
                   
                case 2:
                    System.out.println("Enter Your Email Id ");
                    String emailId = scanner.next();
                    while (!traineeService.validateTraineeEmailId(emailId,trainee, updateChoice)) {
                        System.out.println("\nEnter valid email: ");
                        emailId = scanner.next();
                    }
                    break;
                       
                case 3:
                    System.out.println("\nEnter Your Aadhar Id ");
                    long aadharId = 0;
                    try {
                        aadharId = scanner.nextLong();
                    } catch (InputMismatchException e) {
                        scanner = new Scanner(System.in);
                    }                      
                    while (!traineeService.validateTraineeAadharId(aadharId,trainee, updateChoice)) {
                        System.out.println("\nEnter valid AadharId ");
                        try {
                            aadharId = scanner.nextLong();
                        } catch (InputMismatchException e) {
                            scanner = new Scanner(System.in);
                        } 
                    }
                    break;

                case 4:
                    System.out.println("\nYou are exited from Trainee Update.....");
                    break;
                    
                default:
                    System.out.println("\nInvalid choice.....");
                    scanner = new Scanner(System.in);
                    updateChoice = 1;                
                }

            } while (updateChoice >= 1 && updateChoice < 4);
  
        }
       
    } 
  
    /**
     * <p>
     * This method displays choices for updation.
     * </p>
     *
     * @return {@link void} return nothing 
     **/
    private void displayUpdateChoices() {
        System.out.println("Press 1 for Update Phone Number");
        System.out.println("Press 2 for Update EmailId");
        System.out.println("Press 3 for Update Aadhar Id");
        System.out.println("Press 4 to Quit update");
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
        System.out.println("\nPlease Enter your TrainerId ");
        int trainerId = 0;
        try {
            trainerId = scanner.nextInt();                        
        } catch(InputMismatchException e) {
            System.out.println("\nPlease Enter Valid TrainerId, Id should contain digits only"); 
            scanner = new Scanner(System.in);
        }
        try {
            if (trainerService.validateTrainerId(trainerId)) {                  
                trainerService.removeTrainerDetails(trainerId);
            } 
         } catch (TrainerNotFoundException e) {
            System.out.println(e.getMessage());
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
        System.out.println("\nPlease Enter your TraineeId ");
        int traineeId = 0;
        try {
            traineeId = scanner.nextInt();                        
        } catch(InputMismatchException e) {
            System.out.println("\nPlease Enter Valid TraineeId, Id should contain digits only"); 
            scanner = new Scanner(System.in);
        }
        try {
            if (traineeService.validateTraineeId(traineeId)) {                  
                traineeService.removeTraineeDetails(traineeId);
            }
        } catch (TraineeNotFoundException e) {
            System.out.println(e.getMessage());
        } 
    } 
}  
      



 
	    



	