//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.common.CommonUtil;
import com.ideas2it.employee.customException.BadRequestException;
import com.ideas2it.employee.customException.TraineeNotFoundException;
import com.ideas2it.employee.dao.impl.TraineeDaoImpl;
import com.ideas2it.employee.dao.interf.TraineeDao_Interf;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.service.interf.TraineeService_Interf;
import com.ideas2it.employee.utility.DateUtil;
import com.ideas2it.employee.utility.NumberUtil;
import com.ideas2it.employee.utility.StringUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TraineeServiceImpl implements TraineeService_Interf {
    private Logger logger = LogManager.getLogger(TraineeServiceImpl.class);

    @Autowired
    private TraineeDao_Interf traineeDao;
    private Scanner scanner;
    public TraineeServiceImpl() {

        this.scanner = new Scanner(System.in);
    }

    @Override
    @Transactional
    public List<Trainee> getTrainees() {
        List<Trainee> trainees = this.traineeDao.retrieveTrainee();
        return trainees;
    }

    @Override
    @Transactional
    public void addTraineeDetails(Trainee trainee) {
        this.traineeDao.insertTrainee(trainee);
        this.logger.debug("Successfully Trainee is added.....\n");
    }


    public boolean validateTraineeId(int traineeId) throws TraineeNotFoundException {
        boolean isValidId = false;
        List<Trainee> trainees = this.getTrainees();
        int i = 0;
        if (i < trainees.size()) {
            if (traineeId == ((Trainee)trainees.get(i)).getEmployee().getEmployeeId()) {
                isValidId = true;
                return isValidId;
            } else {
                throw new TraineeNotFoundException("TraineeId is Not Found");
            }
        } else {
            return isValidId;
        }
    }


    public Trainee getTraineeById(int traineeId) throws TraineeNotFoundException {
        Trainee trainee = this.traineeDao.retrieveTraineeById(traineeId);
        if (trainee == null) {
            throw new TraineeNotFoundException("Trainee not found...");
        } else {
            return trainee;
        }
    }

    @Override
    @Transactional
    public void modifyTraineeDetails(Trainee trainee) {

        this.traineeDao.insertTrainee(trainee);
    }

    @Override
    @Transactional
    public void removeTraineeDetails(int traineeId) {
        traineeDao.deleteTrainee(traineeId);
    }
            //this.logger.debug("Trainee is deleted.....");
        //} else {
            //throw new TraineeNotFoundException("Trainee not found...");
       // }


    @Override
    @Transactional
    public List<Integer> validateAndAddTraineeDetails(Trainee trainee) {
        List<Integer> invalidOption = new ArrayList();
        String invalidOptionDetails = "\n\tInvalid Details of Employee\n";
        int no = 0;
        String name = trainee.getEmployee().getName();
        if (!StringUtil.isValidName(name)) {
            invalidOption.add(1);
        }

        String dateOfBirth = trainee.getEmployee().getDateOfBirth().toString();
        if (DateUtil.computePeriod(trainee.getEmployee().getDateOfBirth(), LocalDate.now()) < 18 && trainee.getDateOfBirth() != LocalDate.now()) {
            invalidOption.add(2);
        }

        String dateOfJoin = trainee.getEmployee().getDateOfJoin().toString();
        if (DateUtil.computeDays(trainee.getEmployee().getDateOfJoin(), LocalDate.now()) <= 1) {
            invalidOption.add(3);
        }

        String gender = trainee.getEmployee().getGender();
        String tempNumber = String.valueOf(trainee.getEmployee().getPhoneNumber());
        long phoneNumber = Long.valueOf(tempNumber);
        if (!NumberUtil.validNumberCheck(tempNumber, 10)) {
            invalidOption.add(5);
            ++no;
            invalidOptionDetails = invalidOptionDetails + no + ") Mobile Number is invalid...Number should contain 10 digits\n";
        }

        String emailId = trainee.getEmployee().getEmailId();
        if (!StringUtil.isValidEmailId(emailId)) {
            invalidOption.add(6);
        }

        String tempSalary = String.valueOf(trainee.getEmployee().getSalary());
        double salary = Double.valueOf(tempSalary);
        if (!NumberUtil.validSalaryCheck(salary, 7)) {
            invalidOption.add(7);
        }

        String tempAadhar = String.valueOf(trainee.getEmployee().getAadharId());
        long aadharId = Long.valueOf(tempAadhar);
        if (!NumberUtil.validNumberCheck(tempAadhar, 16)) {
            invalidOption.add(8);
        }

        String bloodGroup = trainee.getEmployee().getBloodGroup();
        Qualification qualification = trainee.getEmployee().getQualification();
        int traineeId = trainee.getTraineeId();
        int trainingPeriod = trainee.getTrainingPeriod();
        Role role = trainee.getEmployee().getRole();
        List<Integer> trainersId = trainee.getTrainersId();
        if (invalidOption.size() == 0) {
            int var10000 = CommonUtil.employeeId++;
            if (trainee.getEmployee().getEmployeeId() > 0) {
                this.modifyTraineeDetails(trainee);
            } else {
                this.addTraineeDetails(trainee);
            }

            return invalidOption;
        } else {
            throw new BadRequestException(invalidOption, invalidOptionDetails);
        }
    }

    public boolean validateTraineePhoneNumber(long phoneNumber, Trainee trainee) {
        String tempNumber = Long.toString(phoneNumber);
        boolean isValidNumber = true;
        if (!NumberUtil.validNumberCheck(tempNumber, 10)) {
            isValidNumber = false;
        } else {
            trainee.getEmployee().setPhoneNumber(phoneNumber);
            this.modifyTraineeDetails(trainee);
            this.logger.debug("\n\tSuccessfully PhoneNumber is updated in Trainee.....\n");
        }
        return isValidNumber;
    }

    public boolean validateTraineeEmailId(String emailId, Trainee trainee) {
        boolean isValidEmail = true;
        if (!StringUtil.isValidEmailId(emailId)) {
            isValidEmail = false;
        } else {
            trainee.getEmployee().setEmailId(emailId);
            this.modifyTraineeDetails(trainee);
            this.logger.debug("\n\tSuccessfully emailId is updated in Trainee.....\n");
        }
        return isValidEmail;
    }

    public boolean validateTraineeAadharId(long aadhar, Trainee trainee) {
        String temp = Long.toString(aadhar);
        boolean isValidAadhar = true;
        if (!NumberUtil.validNumberCheck(temp, 16)) {
            isValidAadhar = false;
        } else {
            trainee.getEmployee().setAadharId(aadhar);
            this.modifyTraineeDetails(trainee);
            this.logger.debug("\n\tSuccessfully AadharId is updated in Trainee.....\n");
        }

        return isValidAadhar;
    }
}
