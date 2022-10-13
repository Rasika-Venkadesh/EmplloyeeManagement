//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.common.CommonUtil;
import com.ideas2it.employee.customException.BadRequest;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.repository.TraineeRepository;
import com.ideas2it.employee.service.TraineeService;
import com.ideas2it.employee.utility.DateUtil;
import com.ideas2it.employee.utility.NumberUtil;
import com.ideas2it.employee.utility.StringUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TraineeServiceImpl implements TraineeService {
    private final Logger logger = LogManager.getLogger(TraineeServiceImpl.class);

    private final TraineeRepository traineeRepository;

    public TraineeServiceImpl(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }


    @Override
    @Transactional
    public List<Trainee> getTrainees() {
        return traineeRepository.findAll();
    }

    @Override
    public Trainee getTraineeById(int traineeId)  {
        return traineeRepository.getReferenceById(traineeId);
    }

    @Override
    @Transactional
    public void removeTraineeDetails(int traineeId) {
        traineeRepository.deleteById(traineeId);
    }

    @Override
    @Transactional
    public List<Integer> validateAndAddTraineeDetails(Trainee trainee) {
        List<Integer> invalidOption = new ArrayList<>();
        String invalidOptionDetails = "\n\tInvalid Details of Employee\n";
        int no = 0;
        String name = trainee.getName();
        if (!StringUtil.isValidName(name)) {
            invalidOption.add(1);
        }

        String dateOfBirth = trainee.getDateOfBirth().toString();
        if (DateUtil.computePeriod(trainee.getDateOfBirth(), LocalDate.now()) < 18 && !Objects.equals(trainee.getDateOfBirth(), LocalDate.now())) {
            invalidOption.add(2);
        }

        String dateOfJoin = trainee.getDateOfJoin().toString();
        if (DateUtil.computeDays(trainee.getDateOfJoin(), LocalDate.now()) <= 1) {
            invalidOption.add(3);
        }

        String gender = trainee.getGender();
        String tempNumber = String.valueOf(trainee.getPhoneNumber());
        long phoneNumber = Long.parseLong(tempNumber);
        if (NumberUtil.validNumberCheck(tempNumber, 10)) {
            invalidOption.add(5);
        }

        String emailId = trainee.getEmailId();
        if (!StringUtil.isValidEmailId(emailId)) {
            invalidOption.add(6);
        }

        String tempSalary = String.valueOf(trainee.getSalary());
        double salary = Double.parseDouble(tempSalary);
        if (NumberUtil.validSalaryCheck(salary, 7)) {
            invalidOption.add(7);
        }

        String tempAadhar = String.valueOf(trainee.getAadharId());
        long aadharId = Long.parseLong(tempAadhar);
        if (NumberUtil.validNumberCheck(tempAadhar, 16)) {
            invalidOption.add(8);
        }

        String bloodGroup = trainee.getBloodGroup();
        Qualification qualification = trainee.getQualification();
        int trainingPeriod = trainee.getTrainingPeriod();
        Role role = trainee.getRole();
        List<Integer> trainersId = trainee.getTrainersId();
        if (invalidOption.size() == 0) {
            int var10000 = CommonUtil.employeeId++;
                traineeRepository.save(trainee);
        } else {
            throw new BadRequest(invalidOption, invalidOptionDetails);
        }
        return invalidOption;
    }

}
