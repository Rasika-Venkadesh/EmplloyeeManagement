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
        if (traineeDao.validateTraineeById(traineeId)) {
            isValidId = true;
            return isValidId;
        } else {
            throw new TraineeNotFoundException("TraineeId is Not Found");
        }
    } 

    /**
     * <p>
     * This method is used to get Trainee Object based on id. 
     * </p> 
     * @param traineeid 
     * @return {@link Trainee} return Object
     **/
    public Trainee getTraineeById(int traineeId) {
        return traineeDao.retrieveTraineeById(traineeId);
    } 
 
    /**
     * <p>
     * This method is used to pass modified Trainee object to 
     * Trainee DAO class for updation.
     * </p> 
     * @param Trainee object
     * @return {@link void} return nothing
     **/
   public void  modifyTraineeDetails(Trainee updateTrainee, int updateChoice) {     
        traineeDao.updateTrainee(updateTrainee, updateChoice);                
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
        traineeDao.deleteTrainee(traineeId);
        logger.debug("Trainee is deleted.....");
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
    
        String name = tempTrainee.getName();          
        if (!StringUtil.isValidName(name)) { 
            invalidOption.add(1);            
            invalidOptionDetails += ++no + ") Name is invalid...Name should not contain digits and special characters\n"; 
        }
                    
        String dateOfBirth = tempTrainee.getDateOfBirth().toString();
	if (DateUtil.computePeriod(tempTrainee.getDateOfBirth(), LocalDate.now()) < 18 && 
                                                tempTrainee.getDateOfBirth() != LocalDate.now()) {
	    invalidOptionDetails += ++no + ") Invalid Date of Birth...Employee age should be 18 years old\n";

            invalidOption.add(2);            
	}

        String dateOfJoin = tempTrainee.getDateOfJoin().toString();
        if (DateUtil.computeDays(tempTrainee.getDateOfJoin(), LocalDate.now()) <= 1) {
	    invalidOptionDetails += ++no + ") Invalid Date of Join...It is a Future Date\n";
            invalidOption.add(3);            
	}
    
        String gender = tempTrainee.getGender();
        do {
            switch(gender) {
            case "1":
                gender = Gender.MALE.gender;
                break;

            case "2":
                gender = Gender.FEMALE.gender;
                break;

            case "3":
                gender = Gender.OTHERS.gender;
                break;

            default:
                invalidOption.add(4);            
	        invalidOptionDetails += ++no + ") Gender is invalid...Choice should be from 1 to 3...\n";           
            }
        } while (null == gender);
                
        String tempNumber = String.valueOf(tempTrainee.getPhoneNumber());
        long phoneNumber = Long.valueOf(tempNumber);           
        if (! NumberUtil.validNumberCheck(tempNumber,10)) {
            invalidOption.add(5);            
	    invalidOptionDetails += ++no + ") Mobile Number is invalid...Number should contain 10 digits\n";            
        }
         
        String emailId = tempTrainee.getEmailId();            
        if (! StringUtil.isValidEmailId(emailId)) {
            invalidOption.add(6);            
            invalidOptionDetails += ++no + ") EmailId is invalid...\n";          
        }
	    
        String tempSalary = String.valueOf(tempTrainee.getSalary());
        double salary = Double.valueOf(tempSalary);
        if (! NumberUtil.validSalaryCheck(salary, 7)) {
            invalidOption.add(7);
            invalidOptionDetails += ++no + ") Please provide valid Salary Amount...Salary digit should be more than 4 digits\n";
        } 
        
        String tempAadhar = String.valueOf(tempTrainee.getAadharId());

        long aadharId = Long.valueOf(tempAadhar);        
        if (! NumberUtil.validNumberCheck(tempAadhar, 16)) {
            invalidOption.add(8);            
            invalidOptionDetails += ++no + ") Aadhar Id is invalid...Id should contain 16 digits\n";  
        }
        
        String bloodGroup = null;
        do {
            bloodGroup = tempTrainee.getBloodGroup();
            switch(bloodGroup) {
            case "1":
                bloodGroup = BloodGroup.A_POSITIVE.bloodGroup;
                break;

            case "2":
                bloodGroup = BloodGroup.B_POSITIVE.bloodGroup;
                break;

            case "3":
                bloodGroup = BloodGroup.O_POSITIVE.bloodGroup;
                break;

            case "4":
                bloodGroup = BloodGroup.AB_POSITIVE.bloodGroup;
                break;

            case "5":
                bloodGroup = BloodGroup.A_NEGATIVE.bloodGroup;
                break;

            case "6":
                bloodGroup = BloodGroup.B_NEGATIVE.bloodGroup;
                break;

            case "7":
                bloodGroup = BloodGroup.O_NEGATIVE.bloodGroup;
                break;

            case "8":
                bloodGroup = BloodGroup.AB_NEGATIVE.bloodGroup;
                break;
            
            default:
                invalidOptionDetails += ++no + ") Invalid BloodGroup...Choice sholud be from 1 to 8.....\n";
                invalidOption.add(9);                       
            }
        } while (null == bloodGroup);

        Qualification qualification = tempTrainee.getQualification(); 
        int traineeId = tempTrainee.getTraineeId();
        int trainingPeriod = tempTrainee.getTrainingPeriod();
        Role role = tempTrainee.getRole();
        List<Integer> trainersId = tempTrainee.getTrainersId();
        
        if (invalidOption.size() == 0) {
            int id = CommonUtil.employeeId++;  	
            Employee employee = new Employee(name , dateOfBirth, dateOfJoin, gender, phoneNumber, emailId , salary, aadharId, bloodGroup, qualification, role);                            
            Trainee trainee = new Trainee(employee,trainingPeriod, trainersId);
            addTraineeDetails(trainee);
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
   public boolean validateTraineePhoneNumber(long phoneNumber,Trainee trainee, int updateChoice) {
    	String tempNumber = Long.toString(phoneNumber);
        boolean isValidNumber = true;
    	if (! NumberUtil.validNumberCheck(tempNumber,10)) {
            isValidNumber = false;
    	} else {
    	    trainee.getEmployee().setPhoneNumber(phoneNumber);
    	    modifyTraineeDetails(trainee, updateChoice);
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
   public boolean validateTraineeEmailId(String emailId, Trainee trainee, int updateChoice) {
        boolean isValidEmail = true; 
        if (! StringUtil.isValidEmailId(emailId)) {
            isValidEmail = false;
        } else {
            trainee.getEmployee().setEmailId(emailId);
            modifyTraineeDetails(trainee,updateChoice);
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
    public boolean validateTraineeAadharId(long aadhar,Trainee trainee, int updateChoice) {
    	String temp = Long.toString(aadhar);
        boolean isValidAadhar = true;
    	if (! NumberUtil.validNumberCheck(temp,16)) {
    	    isValidAadhar = false;
        
    	} else {
    	    trainee.getEmployee().setAadharId(aadhar);
    	    modifyTraineeDetails(trainee, updateChoice);
            logger.debug("\n\tSuccessfully AadharId is updated in Trainee.....\n");
        }
    	return isValidAadhar;        
    } 
  
}
