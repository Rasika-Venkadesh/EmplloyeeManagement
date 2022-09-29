package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.service.interf.TrainerService_Interf;
import com.ideas2it.employee.dao.interf.TrainerDao_Interf;
import com.ideas2it.employee.dao.impl.TrainerDaoImpl;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.utility.DateUtil;
import com.ideas2it.employee.utility.NumberUtil;
import com.ideas2it.employee.utility.StringUtil;
import com.ideas2it.employee.common.CommonUtil;
import com.ideas2it.employee.common.constant.Gender;
import com.ideas2it.employee.common.constant.BloodGroup;
import com.ideas2it.employee.customException.TrainerNotFoundException;
import com.ideas2it.employee.customException.BadRequestException;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * <p>
 * Trainer Service class holds all bussiness logics and get values 
 * from DAO class and pass it to Controller class.
 * </p>
 * @author Rasika Venkadesh
 * @version 1.0
 **/
public class TrainerServiceImpl implements TrainerService_Interf {
    private Logger logger = LogManager.getLogger(TrainerServiceImpl.class);
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
        List<Trainer> trainers = getTrainers();
        for (int i = 0; i < trainers.size(); i++) {
            if (trainerId == (trainers.get(i).getEmployee().getEmployeeId())) {
                isValidId = true;
                return isValidId;                        
            } else {
                throw new TrainerNotFoundException("TrainerId is Not Found");
            }
       }
       return isValidId;        

    }

    /**
     * <p>
     * This method is used to get Trainer Object based on id. 
     * </p> 
     * @param trainerid 
     * @return {@link Trainer} return Object
     **/
    public Trainer getTrainerById(final int trainerId) throws TrainerNotFoundException {
        Trainer trainer = trainerDao.retrieveTrainerById(trainerId);
        if (trainer == null) {
            throw new TrainerNotFoundException("Trainer not found...");
        }
        return trainer;
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
    public void  modifyTrainerDetails(Trainer trainer) {     
        trainerDao.insertTrainer(trainer);             
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
    public void removeTrainerDetails(final int trainerId) {
         if (trainerDao.deleteTrainer(trainerId)) {         
             logger.debug("\n\tTrainer is deleted.....\n");
         } else {
             throw new TrainerNotFoundException("Trainer not Found...");
         }
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
    public List<Integer> validateAndAddTrainerDetails(Trainer trainer) {
        List<Integer> invalidOption = new ArrayList<Integer>();
        String invalidOptionDetails = "\n\tInvalid Details of Employee\n";
        int no = 0;

        String name = trainer.getEmployee().getName();          
        if (!StringUtil.isValidName(name)) { 
            invalidOption.add(1);
        }             
     	
        String dateOfBirth = trainer.getEmployee().getDateOfBirth().toString();
	if (DateUtil.computePeriod(trainer.getEmployee().getDateOfBirth(), LocalDate.now()) < 18) {
	    //invalidOptionDetails += ++no + ") Invalid Date of Birth...Employee age should be 18 years old\n";
            invalidOption.add(2);            
	}       
	
        String dateOfJoin = trainer.getEmployee().getDateOfJoin().toString();
        if (DateUtil.computeDays(trainer.getEmployee().getDateOfJoin(), LocalDate.now()) <= 1) {
	    //invalidOptionDetails += ++no + ") Invalid Date of Join...It is a Future Date\n";
            invalidOption.add(3);            
	}
	
        String gender = trainer.getEmployee().getGender();
            


        String tempNumber = String.valueOf(trainer.getEmployee().getPhoneNumber()); 
        long phoneNumber = Long.valueOf(tempNumber);           
        if (! NumberUtil.validNumberCheck(tempNumber,10)) {
            invalidOption.add(5);
	    //invalidOptionDetails += ++no + ") Mobile Number is invalid...Number should contain 10 digits\n";            
        }               
         
        String emailId = trainer.getEmployee().getEmailId();            
        if (! StringUtil.isValidEmailId(emailId)) {
            invalidOption.add(6);
            //invalidOptionDetails += ++no + ") EmailId is invalid...\n";          
        } 
                    
        String tempSalary = String.valueOf(trainer.getEmployee().getSalary());
        double salary = Double.valueOf(tempSalary);
        if (! NumberUtil.validSalaryCheck(Double.valueOf(tempSalary), 7)) {
            invalidOption.add(7);
           // invalidOptionDetails += ++no + ") Please provide valid Salary Amount...Salary digit should be more than 4 digits\n";
        } 
                    	
        String tempAadhar = String.valueOf(trainer.getEmployee().getAadharId());
        long aadharId = Long.valueOf(tempAadhar);        
        if (! NumberUtil.validNumberCheck(tempAadhar, 16)) {
            invalidOption.add(8);
           // invalidOptionDetails += ++no + ") Aadhar Id is invalid...Id should contain 16 digits\n" ;   
        }                 
                    
        String bloodGroup = trainer.getEmployee().getBloodGroup();
        
        Qualification qualification = trainer.getEmployee().getQualification();    

        int trainerId = trainer.getTrainerId();
        int experience = trainer.getExperience();
        Role role = trainer.getEmployee().getRole();
        if (invalidOption.size() == 0) {
            int id = CommonUtil.employeeId++;
            //Employee employee = new Employee(name , dateOfBirth, dateOfJoin, gender, phoneNumber, emailId , salary, aadharId, bloodGroup, qualification, role);  
            //Trainer trainer1 = new Trainer(employee, experience); 
            if (trainer.getEmployee().getEmployeeId() > 0) { 
                addTrainerDetails(trainer);                         
            } else {
                modifyTrainerDetails(trainer);
            }   
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
    public boolean validateTrainerPhoneNumber(long phoneNumber,Trainer trainer) {
    	String tempNumber = Long.toString(phoneNumber);
        boolean isValidNumber = true;
    	if (! NumberUtil.validNumberCheck(tempNumber,10)) {
    	    isValidNumber = false;
    	} else {
    	    trainer.getEmployee().setPhoneNumber(phoneNumber);
    	    modifyTrainerDetails(trainer);
            logger.debug("\n\tSuccessfully PhoneNumber is updated in Trainer.....\n");
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
    public boolean validateTrainerEmailId(String emailId, Trainer trainer) {
        boolean isValidEmail = true; 
        if (! StringUtil.isValidEmailId(emailId)) {
            isValidEmail = false;
        } else {
            trainer.getEmployee().setEmailId(emailId);
            modifyTrainerDetails(trainer);
            logger.debug("\n\tSuccessfully EmailId is updated in Trainer.....\n");
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
    public boolean validateTrainerAadhar(long aadhar,Trainer trainer) {
    	String temp = Long.toString(aadhar);
        boolean isValidAadhar = true;
    	if (! NumberUtil.validNumberCheck(temp,16)) {
    	    isValidAadhar = false;
    	} else {
    	    trainer.getEmployee().setAadharId(aadhar);
    	    modifyTrainerDetails(trainer);
            logger.debug("\n\tSuccessfully AadharId is updated in Trainer.....\n");
        }
        return isValidAadhar;
    }
 
}