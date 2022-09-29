package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.service.interf.TraineeService_Interf;
import com.ideas2it.employee.dao.interf.TraineeDao_Interf;
import com.ideas2it.employee.dao.impl.TraineeDaoImpl;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.customException.TraineeNotFoundException;
import com.ideas2it.employee.customException.BadRequestException;
import com.ideas2it.employee.utility.DateUtil;
import com.ideas2it.employee.utility.NumberUtil;
import com.ideas2it.employee.utility.StringUtil;
import com.ideas2it.employee.common.CommonUtil;
import com.ideas2it.employee.common.constant.Gender;
import com.ideas2it.employee.common.constant.BloodGroup;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * <p>
 * Trainee Service class holds all bussiness logics and get values 
 * from DAO class and pass it to Controller class.
 * </p>
 * @author Rasika Venkadesh
 * @version 1.0
 **/
public class TraineeServiceImpl implements TraineeService_Interf {
    private Logger logger = LogManager.getLogger(TraineeServiceImpl.class);
    private TraineeDao_Interf traineeDao = new TraineeDaoImpl();
    private Scanner scanner = new Scanner(System.in); 

    /**
     * <p>
     * This method is used to get Trainee Object
     * </p> 
     *  
     * @return {@link List} return Object
     **/
    public List<Trainee> getTrainees() {         
        List<Trainee> trainees = traineeDao.retrieveTrainee();
        return trainees;
    } 

    /**
     * <p>
     * This method is used to pass Valid Trainee object to Trainee DAO class.
     * </p> 
     * @param Trainee object 
     * @return {@link void} return nothing
     **/      
    public void addTraineeDetails(Trainee trainee) {        
        traineeDao.insertTrainee(trainee); 
        logger.debug("Successfully Trainee is added.....\n");              
    }

    /**
     * <p>
     * This method is used to validate trainee id. 
     * </p> 
     * @param traineeid 
     * @return {@link boolean} return true or false
     **/
    public boolean validateTraineeId(int traineeId) throws TraineeNotFoundException {
        boolean isValidId = false;
        List<Trainee> trainees = getTrainees();
        for (int i = 0; i < trainees.size(); i++) {
            if (traineeId == (trainees.get(i).getEmployee().getEmployeeId())) {
                isValidId = true;
                return isValidId;                        
            } else {
                throw new TraineeNotFoundException("TraineeId is Not Found");
            }
       }
       return isValidId; 
    } 

    /**
     * <p>
     * This method is used to get Trainee Object based on id. 
     * </p> 
     * @param traineeid 
     * @return {@link Trainee} return Object
     **/
    public Trainee getTraineeById(int traineeId) throws TraineeNotFoundException {
        Trainee trainee = traineeDao.retrieveTraineeById(traineeId);
        if (trainee == null) {
            throw new TraineeNotFoundException("Trainee not found...");
        }
        return trainee;
    } 
 
    /**
     * <p>
     * This method is used to pass modified Trainee object to 
     * Trainee DAO class for updation.
     * </p> 
     * @param Trainee object
     * @return {@link void} return nothing
     **/
   public void  modifyTraineeDetails(Trainee trainee) {     
        traineeDao.insertTrainee(trainee);                
    } 

    /**
     * <p>
     * This method is used to pass Trainee object to 
     * Trainee DAO class for deletion based on id.
     * </p> 
     * 
     * @param traineeid 
     * @return {@link void} return nothing
     **/        
    public void removeTraineeDetails(int traineeId) {
        if (traineeDao.deleteTrainee(traineeId)) {
            logger.debug("Trainee is deleted.....");
        } else {
            throw new TraineeNotFoundException("Trainee not found...");
        }
    } 

    /**
     * <p>
     * This method is used to validate details which is get from Controller class.
     * Valid details are hold in Trainee object, invalid objects are return to Controller class.
     * </p> 
     *
     * @param  Trainee object
     * @return {@link List} return invalidOptions
     **/
    public List<Integer> validateAndAddTraineeDetails(Trainee tempTrainee) {
        List<Integer> invalidOption = new ArrayList<Integer>();
        String invalidOptionDetails = "\n\tInvalid Details of Employee\n";
        int no = 0; 
    
        String name = tempTrainee.getEmployee().getName();          
        if (!StringUtil.isValidName(name)) { 
            invalidOption.add(1);            
            //invalidOptionDetails += ++no + ") Name is invalid...Name should not contain digits and special characters\n"; 
        }
                    
        String dateOfBirth = tempTrainee.getEmployee().getDateOfBirth().toString();
	if (DateUtil.computePeriod(tempTrainee.getEmployee().getDateOfBirth(), LocalDate.now()) < 18 && 
                                                tempTrainee.getDateOfBirth() != LocalDate.now()) {
	    //invalidOptionDetails += ++no + ") Invalid Date of Birth...Employee age should be 18 years old\n";

            invalidOption.add(2);            
	}

        String dateOfJoin = tempTrainee.getEmployee().getDateOfJoin().toString();
        if (DateUtil.computeDays(tempTrainee.getEmployee().getDateOfJoin(), LocalDate.now()) <= 1) {
	    //invalidOptionDetails += ++no + ") Invalid Date of Join...It is a Future Date\n";
            invalidOption.add(3);            
	}
    
        String gender = tempTrainee.getEmployee().getGender();
        do {
            
            switch(gender) {
            case "Male":
                gender = Gender.MALE.gender;
                break;

            case "Female":
                gender = Gender.FEMALE.gender;
                break;

            case "Others":
                gender = Gender.OTHERS.gender;
                break;

            default:
                invalidOption.add(4);            
	        //invalidOptionDetails += ++no + ") Gender is invalid...Choice should be from 1 to 3...\n";           
            }
        } while (null == gender);
                
        String tempNumber = String.valueOf(tempTrainee.getEmployee().getPhoneNumber());
        long phoneNumber = Long.valueOf(tempNumber);           
        if (! NumberUtil.validNumberCheck(tempNumber,10)) {
            invalidOption.add(5);            
	   invalidOptionDetails += ++no + ") Mobile Number is invalid...Number should contain 10 digits\n";            
        }
         
        String emailId = tempTrainee.getEmployee().getEmailId();            
        if (! StringUtil.isValidEmailId(emailId)) {
            invalidOption.add(6);            
            //invalidOptionDetails += ++no + ") EmailId is invalid...\n";          
        }
	    
        String tempSalary = String.valueOf(tempTrainee.getEmployee().getSalary());
        double salary = Double.valueOf(tempSalary);
        if (! NumberUtil.validSalaryCheck(salary, 7)) {
            invalidOption.add(7);
            //invalidOptionDetails += ++no + ") Please provide valid Salary Amount...Salary digit should be more than 4 digits\n";
        } 
        
        String tempAadhar = String.valueOf(tempTrainee.getEmployee().getAadharId());

        long aadharId = Long.valueOf(tempAadhar);        
        if (! NumberUtil.validNumberCheck(tempAadhar, 16)) {
            invalidOption.add(8);            
            //invalidOptionDetails += ++no + ") Aadhar Id is invalid...Id should contain 16 digits\n";  
        }
        
        String bloodGroup = "";
        do {
            bloodGroup = tempTrainee.getEmployee().getBloodGroup();
            switch(bloodGroup) {
            case "A_Positive":
                bloodGroup = BloodGroup.A_POSITIVE.bloodGroup;
                break;

            case "B_Positive":
                bloodGroup = BloodGroup.B_POSITIVE.bloodGroup;
                break;

            case "AB_Positive":
                bloodGroup = BloodGroup.O_POSITIVE.bloodGroup;
                break;

            case "O_Positive":
                bloodGroup = BloodGroup.AB_POSITIVE.bloodGroup;
                break;

            case "A_Negative":
                bloodGroup = BloodGroup.A_NEGATIVE.bloodGroup;
                break;

            case "B_Negative":
                bloodGroup = BloodGroup.B_NEGATIVE.bloodGroup;
                break;

            case "AB_Negative":
                bloodGroup = BloodGroup.O_NEGATIVE.bloodGroup;
                break;

            case "O_Negative":
                bloodGroup = BloodGroup.AB_NEGATIVE.bloodGroup;
                break;
            
            default:
                //invalidOptionDetails += ++no + ") Invalid BloodGroup...Choice sholud be from 1 to 8.....\n";
                invalidOption.add(9);                       
            }
        } while (null == bloodGroup);

        Qualification qualification = tempTrainee.getEmployee().getQualification(); 

        int traineeId = tempTrainee.getTraineeId();
        int trainingPeriod = tempTrainee.getTrainingPeriod();
        Role role = tempTrainee.getEmployee().getRole();
        List<Integer> trainersId = tempTrainee.getTrainersId();
        
        if (invalidOption.size() == 0) {
            int id = CommonUtil.employeeId++;  	
            //Employee employee = new Employee(name , dateOfBirth, dateOfJoin, gender, phoneNumber, emailId , salary, aadharId, bloodGroup, qualification, role);                            
            //Trainee trainee = new Trainee(employee,trainingPeriod, trainersId);
            if (tempTrainee.getEmployee().getEmployeeId() > 0) { 
                addTraineeDetails(tempTrainee);                         
            } else {
                modifyTraineeDetails(tempTrainee);
            }   
            
        } else {
            throw new BadRequestException(invalidOption, invalidOptionDetails);
        }
        return invalidOption;           
    }

    /**
     * <p>
     * This method is used to validate PhoneNumber to update in Trainee Object.
     * If data is valid, it is updated into Trainee Object.
     * </p> 
     * 
     * @param phoneNumber
     * @param trainee 
     * @param updateChoice
     * @return {@link boolean} return true or false
     **/
   public boolean validateTraineePhoneNumber(long phoneNumber,Trainee trainee) {
    	String tempNumber = Long.toString(phoneNumber);
        boolean isValidNumber = true;
    	if (! NumberUtil.validNumberCheck(tempNumber,10)) {
            isValidNumber = false;
    	} else {
    	    trainee.getEmployee().setPhoneNumber(phoneNumber);
    	    modifyTraineeDetails(trainee);
            logger.debug("\n\tSuccessfully PhoneNumber is updated in Trainee.....\n");
        }
    	return isValidNumber;
    } 

    /**
     * <p>
     * This method is used to validate Emailid to update in Trainee Object.
     * If data is valid, it is updated into Trainee Object.
     * </p> 
     *  
     * @param emailId
     * @param trainee
     * @param updateChoice
     * @return {@link boolean} return true or false
     **/ 
   public boolean validateTraineeEmailId(String emailId, Trainee trainee) {
        boolean isValidEmail = true; 
        if (! StringUtil.isValidEmailId(emailId)) {
            isValidEmail = false;
        } else {
            trainee.getEmployee().setEmailId(emailId);
            modifyTraineeDetails(trainee);
            logger.debug("\n\tSuccessfully emailId is updated in Trainee.....\n");
        }
        return isValidEmail;
    } 

    /**
     * <p>
     * This method is used to validate AadharId to update in Trainee Object.
     * If data is valid, it is updated into Trainee Object.
     * </p> 
     *
     * @param aadhar 
     * @param trainee 
     * @param updateChoice
     * @return {@link boolean} return true or false
     **/
    public boolean validateTraineeAadharId(long aadhar,Trainee trainee) {
    	String temp = Long.toString(aadhar);
        boolean isValidAadhar = true;
    	if (! NumberUtil.validNumberCheck(temp,16)) {
    	    isValidAadhar = false;
        
    	} else {
    	    trainee.getEmployee().setAadharId(aadhar);
    	    modifyTraineeDetails(trainee);
            logger.debug("\n\tSuccessfully AadharId is updated in Trainee.....\n");
        }
    	return isValidAadhar;        
    } 
  
}
