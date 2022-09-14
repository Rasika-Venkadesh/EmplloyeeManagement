package com.ideas2it.employee.service.impl;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.dao.impl.TrainerDaoImpl;
import com.ideas2it.employee.dao.interf.TrainerDao_Interf;
import com.ideas2it.employee.service.interf.TrainerService_Interf;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.utility.*;
import com.ideas2it.employee.customException.TrainerNotFoundException;
import com.ideas2it.employee.customException.BadRequestException;
import com.ideas2it.employee.common.CommonUtil;
import com.ideas2it.employee.common.constant.Gender;
import com.ideas2it.employee.common.constant.BloodGroup;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;


/**
 * <p>
 * Trainer Service class holds all bussiness logics and get values 
 * from DAO class and pass it to Controller class.
 * </p>
 * @author Rasika Venkadesh
 * @version 1.0
 **/
public class TrainerServiceImpl implements TrainerService_Interf {
    private TrainerDao_Interf trainerDao = new TrainerDaoImpl();

    private Scanner scanner = new Scanner(System.in);       
    
    /**
     * <p>
     * This method is used to get Trainer Object
     * </p> 
     *  
     * @return {@link List} return Object
     **/  
    public List<Trainer> getTrainers() {         
        List<Trainer> trainers = trainerDao.retrieveTrainer();
        return trainers;
    } 
    
    /**
     * <p>
     * This method is used to pass Valid Trainer object to Trainer DAO class.
     * </p> 
     *
     * @param Trainer object 
     * @return {@link void} return nothing
     **/       
    public void addTrainerDetails(Trainer trainer) {        
        trainerDao.insertTrainer(trainer); 
        System.out.println("\n\tSuccessfully Trainer is added.....\n");             
    } 

    /**
     * <p>
     * This method is used to validate trainer id. 
     * </p> 
     * @param trainerid 
     * @return {@link boolean} return true or false
     **/
    public boolean validateTrainerId(int trainerId) throws TrainerNotFoundException {
        boolean isValidId = false;
        if (trainerDao.validateTrainerById(trainerId)){
           isValidId = true;
           return isValidId;
        } else {           
            throw new TrainerNotFoundException("TrainerId is Not Found");            
        }
    }

    /**
     * <p>
     * This method is used to get Trainer Object based on id. 
     * </p> 
     * @param trainerid 
     * @return {@link Trainer} return Object
     **/
    public Trainer getTrainerById(int trainerId) {
        return trainerDao.retrieveTrainerById(trainerId);
    }

    /**
     * <p>
     * This method is used to pass modified Trainer object to 
     * Trainer DAO class for updation.
     * </p> 
     *
     * @param Trainer object
     * @return {@link void} return nothing
     **/ 
    public void  modifyTrainerDetails(Trainer updateTrainer, int updateChoice) {     
        trainerDao.updateTrainer(updateTrainer, updateChoice);             
    }
     
    /**
     * <p>
     * This method is used to pass Trainer object to 
     * Trainer DAO class for deletion based on id.
     * </p> 
     * 
     * @param trainerid 
     * @return {@link void} return nothing
     **/   
    public void removeTrainerDetails(int trainerId) {
         trainerDao.deleteTrainer(trainerId);
         System.out.println("\n\tTrainer is deleted.....\n");
    } 

    /**
     * <p>
     * This method is used to validate details which is get from Controller class.
     * Valid details are hold in Trainer object, invalid objects are return to Controller class.
     * </p> 
     *
     * @param  Trainer object
     * @return {@link List} return invalidOptions
     **/
    public List<Integer> validateAndAddTrainerDetails(Trainer tempTrainer) {
        List<Integer> invalidOption = new ArrayList<Integer>();
        String invalidOptionDetails = "\n\tInvalid Details of Employee\n";
        int no = 0;

        String name = tempTrainer.getName();          
        if (!StringUtil.isValidName(name)) { 
            invalidOption.add(1);
            invalidOptionDetails += ++no + ") Name is invalid... Name should not contain digits and special caracters\n";
        }             
     
        String dateOfBirth = tempTrainer.getDateOfBirth().toString();
	if (DateUtil.computePeriod(tempTrainer.getDateOfBirth(), LocalDate.now()) < 18) {
	    invalidOptionDetails += ++no + ") Invalid Date of Birth...Employee age should be 18 years old\n";
            invalidOption.add(2);            
	}       
	
        String dateOfJoin = tempTrainer.getDateOfJoin().toString();
        if (DateUtil.computeDays(tempTrainer.getDateOfJoin(), LocalDate.now()) <= 1) {
	    invalidOptionDetails += ++no + ") Invalid Date of Join...It is a Future Date\n";
            invalidOption.add(3);            
	}
	
        String gender = "";
        do {
            gender = tempTrainer.getGender();
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


        String tempNumber = String.valueOf(tempTrainer.getPhoneNumber()); 
        long phoneNumber = Long.valueOf(tempNumber);           
        if (! NumberUtil.validNumberCheck(tempNumber,10)) {
            invalidOption.add(5);
	    invalidOptionDetails += ++no + ") Mobile Number is invalid...Number should contain 10 digits\n";            
        }               
         
        String emailId = tempTrainer.getEmailId();            
        if (! StringUtil.isValidEmailId(emailId)) {
            invalidOption.add(6);
            invalidOptionDetails += ++no + ") EmailId is invalid...\n";          
        } 
                    
        String tempSalary = String.valueOf(tempTrainer.getSalary());
        double salary = Double.valueOf(tempSalary);
        if (! NumberUtil.validSalaryCheck(Double.valueOf(tempSalary), 7)) {
            invalidOption.add(7);
            invalidOptionDetails += ++no + ") Please provide valid Salary Amount...Salary digit should be more than 4 digits\n";
        } 
                    	
        String tempAadhar = String.valueOf(tempTrainer.getAadharId());
        long aadharId = Long.valueOf(tempAadhar);        
        if (! NumberUtil.validNumberCheck(tempAadhar, 16)) {
            invalidOption.add(8);
            invalidOptionDetails += ++no + ") Aadhar Id is invalid...Id should contain 16 digits\n" ;   
        }                 
                    
        String bloodGroup = "";
        do {
            bloodGroup = tempTrainer.getBloodGroup();
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

        Qualification qualification = tempTrainer.getQualification();    

        int trainerId = tempTrainer.getTrainerId();
        int experience = tempTrainer.getExperience();
        Role role = tempTrainer.getRole();
        if (invalidOption.size() == 0) {
            int id = CommonUtil.employeeId++;
            Employee employee = new Employee(name , dateOfBirth, dateOfJoin, gender, phoneNumber, emailId , salary, aadharId, bloodGroup, qualification, role);  
            Trainer trainer = new Trainer(employee, experience);           
            addTrainerDetails(trainer);
        } else {
            throw new BadRequestException(invalidOption, invalidOptionDetails) ;
        }
        return invalidOption;           
    }

    /**
     * <p>
     * This method is used to validate PhoneNumber to update in Trainer Object.
     * If data is valid, it is updated into Trainer Object.
     * </p> 
     * 
     * @param phoneNumber
     * @param trainer 
     * @return {@link boolean} return true or false
     **/
    public boolean validateTrainerPhoneNumber(long phoneNumber,Trainer trainer, int updateChoice) {
    	String tempNumber = Long.toString(phoneNumber);
        boolean isValidNumber = true;
    	if (! NumberUtil.validNumberCheck(tempNumber,10)) {
    	    isValidNumber = false;
    	} else {
    	    trainer.getEmployee().setPhoneNumber(phoneNumber);
    	    modifyTrainerDetails(trainer, updateChoice);
            System.out.println("\n\tSuccessfully PhoneNumber is updated in Trainer.....\n");
        }
    	return isValidNumber; 
    }

    /**
     * <p>
     * This method is used to validate Emailid to update in Trainer Object.
     * If data is valid, it is updated into Trainer Object.
     * </p> 
     * 
     * @param emailId
     * @param trainer 
     * @return {@link boolean} return true or false
     **/
    public boolean validateTrainerEmailId(String emailId, Trainer trainer, int updateChoice) {
        boolean isValidEmail = true; 
        if (! StringUtil.isValidEmailId(emailId)) {
            isValidEmail = false;
        } else {
            trainer.getEmployee().setEmailId(emailId);
            modifyTrainerDetails(trainer, updateChoice);
            System.out.println("\n\tSuccessfully EmailId is updated in Trainer.....\n");
        }
        return isValidEmail;
    }

    /**
     * <p>
     * This method is used to validate AadharId to update in Trainer Object.
     * If data is valid, it is updated into Trainer Object.
     * </p> 
     * 
     * @param aadhar
     * @param trainer 
     * @return {@link boolean} return true or false
     **/
    public boolean validateTrainerAadhar(long aadhar,Trainer trainer, int updateChoice) {
    	String temp = Long.toString(aadhar);
        boolean isValidAadhar = true;
    	if (! NumberUtil.validNumberCheck(temp,16)) {
    	    isValidAadhar = false;
    	} else {
    	    trainer.getEmployee().setAadharId(aadhar);
    	    modifyTrainerDetails(trainer, updateChoice);
            System.out.println("\n\tSuccessfully AadharId is updated in Trainer.....\n");
        }
        return isValidAadhar;
    }
 
}