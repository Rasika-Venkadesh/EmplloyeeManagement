//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.common.CommonUtil;
import com.ideas2it.employee.customException.BadRequestException;
import com.ideas2it.employee.customException.TrainerNotFoundException;
import com.ideas2it.employee.dao.impl.TrainerDaoImpl;
import com.ideas2it.employee.dao.interf.TrainerDao_Interf;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.service.interf.TrainerService_Interf;
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
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl implements TrainerService_Interf {
    private Logger logger = LogManager.getLogger(TrainerServiceImpl.class);
    @Autowired
    private TrainerDao_Interf trainerDao;
    private Scanner scanner;

    public TrainerServiceImpl() {

        this.scanner = new Scanner(System.in);
    }

    public List<Trainer> getTrainers() {
        List<Trainer> trainers = this.trainerDao.retrieveTrainer();
        return trainers;
    }

    public void addTrainerDetails(Trainer trainer) {
        this.trainerDao.insertTrainer(trainer);
    }

    public boolean validateTrainerId(int trainerId) throws TrainerNotFoundException {
        boolean isValidId = false;
        List<Trainer> trainers = this.getTrainers();
        int i = 0;
        if (i < trainers.size()) {
            if (trainerId == ((Trainer)trainers.get(i)).getEmployee().getEmployeeId()) {
                isValidId = true;
                return isValidId;
            } else {
                throw new TrainerNotFoundException("TrainerId is Not Found");
            }
        } else {
            return isValidId;
        }
    }

    public Trainer getTrainerById(int trainerId) throws TrainerNotFoundException {
        Trainer trainer = this.trainerDao.retrieveTrainerById(trainerId);
        if (trainer == null) {
            throw new TrainerNotFoundException("Trainer not found...");
        } else {
            return trainer;
        }
    }

    public void modifyTrainerDetails(Trainer trainer) {
        this.trainerDao.insertTrainer(trainer);
    }

    public void removeTrainerDetails(int trainerId) {
        trainerDao.deleteTrainer(trainerId) ;
            this.logger.debug("\n\tTrainer is deleted.....\n");

    }

    public List<Integer> validateAndAddTrainerDetails(Trainer trainer) {
        List<Integer> invalidOption = new ArrayList();
        String invalidOptionDetails = "\n\tInvalid Details of Employee\n";
        //int no = false;
        String name = trainer.getEmployee().getName();
        if (!StringUtil.isValidName(name)) {
            invalidOption.add(1);
        }

        String dateOfBirth = trainer.getEmployee().getDateOfBirth().toString();
        if (DateUtil.computePeriod(trainer.getEmployee().getDateOfBirth(), LocalDate.now()) < 18) {
            invalidOption.add(2);
        }

        String dateOfJoin = trainer.getEmployee().getDateOfJoin().toString();
        if (DateUtil.computeDays(trainer.getEmployee().getDateOfJoin(), LocalDate.now()) <= 1) {
            invalidOption.add(3);
        }

        String gender = trainer.getEmployee().getGender();
        String tempNumber = String.valueOf(trainer.getEmployee().getPhoneNumber());
        long phoneNumber = Long.valueOf(tempNumber);
        if (!NumberUtil.validNumberCheck(tempNumber, 10)) {
            invalidOption.add(5);
        }

        String emailId = trainer.getEmployee().getEmailId();
        if (!StringUtil.isValidEmailId(emailId)) {
            invalidOption.add(6);
        }

        String tempSalary = String.valueOf(trainer.getEmployee().getSalary());
        double salary = Double.valueOf(tempSalary);
        if (!NumberUtil.validSalaryCheck(Double.valueOf(tempSalary), 7)) {
            invalidOption.add(7);
        }

        String tempAadhar = String.valueOf(trainer.getEmployee().getAadharId());
        long aadharId = Long.valueOf(tempAadhar);
        if (!NumberUtil.validNumberCheck(tempAadhar, 16)) {
            invalidOption.add(8);
        }

        String bloodGroup = trainer.getEmployee().getBloodGroup();
        Qualification qualification = trainer.getEmployee().getQualification();
        int trainerId = trainer.getTrainerId();
        int experience = trainer.getExperience();
        Role role = trainer.getEmployee().getRole();
        if (invalidOption.size() == 0) {
            int var10000 = CommonUtil.employeeId++;
            if (trainer.getEmployee().getEmployeeId() > 0) {
                this.addTrainerDetails(trainer);
            } else {
                this.modifyTrainerDetails(trainer);
            }

            return invalidOption;
        } else {
            throw new BadRequestException(invalidOption, invalidOptionDetails);
        }
    }

    public boolean validateTrainerPhoneNumber(long phoneNumber, Trainer trainer) {
        String tempNumber = Long.toString(phoneNumber);
        boolean isValidNumber = true;
        if (!NumberUtil.validNumberCheck(tempNumber, 10)) {
            isValidNumber = false;
        } else {
            trainer.getEmployee().setPhoneNumber(phoneNumber);
            this.modifyTrainerDetails(trainer);
            this.logger.debug("\n\tSuccessfully PhoneNumber is updated in Trainer.....\n");
        }

        return isValidNumber;
    }

    public boolean validateTrainerEmailId(String emailId, Trainer trainer) {
        boolean isValidEmail = true;
        if (!StringUtil.isValidEmailId(emailId)) {
            isValidEmail = false;
        } else {
            trainer.getEmployee().setEmailId(emailId);
            this.modifyTrainerDetails(trainer);
            this.logger.debug("\n\tSuccessfully EmailId is updated in Trainer.....\n");
        }

        return isValidEmail;
    }

    public boolean validateTrainerAadhar(long aadhar, Trainer trainer) {
        String temp = Long.toString(aadhar);
        boolean isValidAadhar = true;
        if (!NumberUtil.validNumberCheck(temp, 16)) {
            isValidAadhar = false;
        } else {
            trainer.getEmployee().setAadharId(aadhar);
            this.modifyTrainerDetails(trainer);
            this.logger.debug("\n\tSuccessfully AadharId is updated in Trainer.....\n");
        }

        return isValidAadhar;
    }
}
