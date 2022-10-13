package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.common.CommonUtil;
import com.ideas2it.employee.customException.BadRequest;
import com.ideas2it.employee.customException.TrainerNotFoundException;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.repository.QualificationRepository;
import com.ideas2it.employee.repository.RoleRepository;
import com.ideas2it.employee.repository.TrainerRepository;
import com.ideas2it.employee.utility.StringUtil;
import com.ideas2it.employee.utility.NumberUtil;
import com.ideas2it.employee.utility.DateUtil;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.service.TrainerService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public  class TrainerServiceImpl implements TrainerService {
    private final Logger logger = LogManager.getLogger(TrainerServiceImpl.class);
    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private QualificationRepository qualificationRepository;

    private Iterable<Integer> trainerId;

    public TrainerServiceImpl() {
    }

    @Override
    public List<Trainer> getTrainers() {
        List<Trainer> trainers = trainerRepository.findAll();
        return trainers;
    }

    @Override
    public Trainer getTrainerId(int trainerId) throws TrainerNotFoundException {
        Optional<Trainer> trainer = trainerRepository.findById(trainerId);
        if (trainer.isEmpty()) {
            throw new TrainerNotFoundException("Trainer not found...");
        }
        Trainer trainer1= trainer.get();
        return trainer1;
    }

    @Override
    public void removeTrainerDetails(int trainerId) {
        trainerRepository.deleteById(trainerId);
        this.logger.debug("\n\tTrainer is deleted.....\n");
    }

    public List<Integer> validateAndAddTrainerDetails(Trainer trainer) {
        List<Integer> invalidOption = new ArrayList<>();
        String invalidOptionDetails = "\n\tInvalid Details of Employee\n";
        String name = trainer.getName();
        if (!StringUtil.isValidName(name)) {
            invalidOption.add(1);
        }

        String dateOfBirth = trainer.getDateOfBirth().toString();
        if (DateUtil.computePeriod(trainer.getDateOfBirth(), LocalDate.now()) < 18) {
            invalidOption.add(2);
        }

        String dateOfJoin = trainer.getDateOfJoin().toString();
        if (DateUtil.computeDays(trainer.getDateOfJoin(), LocalDate.now()) <= 1) {
            invalidOption.add(3);
        }

        String gender = trainer.getGender();
        String tempNumber = String.valueOf(trainer.getPhoneNumber());
        long phoneNumber = Long.parseLong(tempNumber);
        if (NumberUtil.validNumberCheck(tempNumber, 10)) {
            invalidOption.add(5);
        }

        String emailId = trainer.getEmailId();
        if (!StringUtil.isValidEmailId(emailId)) {
            invalidOption.add(6);
        }

        String tempSalary = String.valueOf(trainer.getSalary());
        double salary = Double.parseDouble(tempSalary);
        if (NumberUtil.validSalaryCheck(Double.parseDouble(tempSalary), 7)) {
            invalidOption.add(7);
        }

        String tempAadhar = String.valueOf(trainer.getAadharId());
        long aadharId = Long.parseLong(tempAadhar);
        if (NumberUtil.validNumberCheck(tempAadhar, 16)) {
            invalidOption.add(8);
        }

        String bloodGroup = trainer.getBloodGroup();

       Optional<Qualification> qualification = qualificationRepository.findByQualification(trainer.getQualification().getQualification());
        qualification.ifPresent(trainer::setQualification);

        Optional<Role> role = roleRepository.findByRole(trainer.getRole().getRole());
        role.ifPresent(trainer::setRole);

        //Qualification qualification = trainer.getQualification();
        int experience = trainer.getExperience();
        //Role role = trainer.getRole();
        if (invalidOption.size() == 0) {
            int var10000 = CommonUtil.employeeId++;
            trainerRepository.save(trainer);
        } else {
            throw new BadRequest(invalidOption, invalidOptionDetails);
        }
        return invalidOption;
    }
}



