package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.common.CommonUtil;
import com.ideas2it.employee.customException.BadRequest;
import com.ideas2it.employee.customException.TrainerNotFoundException;
import com.ideas2it.employee.dto.QualificationDto;
import com.ideas2it.employee.mapper.QualificationMapper;
import com.ideas2it.employee.mapper.TrainerMapper;
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
import com.ideas2it.employee.dto.TrainerDto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public  class TrainerServiceImpl implements TrainerService {
    private final Logger logger = LogManager.getLogger(TrainerServiceImpl.class);
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private QualificationRepository qualificationRepository;

    public TrainerServiceImpl() {
    }

    @Override
    public List<TrainerDto> getTrainers() throws TrainerNotFoundException {
        List<Trainer> trainers = trainerRepository.findAll();
        if (trainers.isEmpty()) {
            throw new TrainerNotFoundException("No Trainers Found");
        } else {
            return trainers.stream().map(TrainerMapper::convertTrainerToTrainerDto).collect(Collectors.toList());
        }
    }

    @Override
    public TrainerDto getTrainerId(int trainerId) throws TrainerNotFoundException {
        TrainerDto trainerDto = null;
        Optional<Trainer> trainer = trainerRepository.findById(trainerId);
        if (trainer.isEmpty()) {
            throw new TrainerNotFoundException("Trainer not found...");
        }
        trainerDto = TrainerMapper.convertTrainerToTrainerDto(trainer.get());
        return trainerDto;
    }

    @Override
    public void removeTrainerDetails(int trainerId) {
        trainerRepository.deleteById(trainerId);
        this.logger.debug("\n\tTrainer is deleted.....\n");
    }

    public List<Integer> validateAndAddTrainerDetails(TrainerDto trainerDto) {
        List<Integer> invalidOption = new ArrayList<>();
        String invalidOptionDetails = "\n\tInvalid Details of Employee\n";
        String name = trainerDto.getName();
        if (!StringUtil.isValidName(name)) {
            invalidOption.add(1);
        }

        String dateOfBirth = trainerDto.getDateOfBirth().toString();
        if (DateUtil.computePeriod(trainerDto.getDateOfBirth(), LocalDate.now()) < 18) {
            invalidOption.add(2);
        }

        String dateOfJoin = trainerDto.getDateOfJoin().toString();
        if (DateUtil.computeDays(trainerDto.getDateOfJoin(), LocalDate.now()) <= 1) {
            invalidOption.add(3);
        }

        String gender = trainerDto.getGender();
        String tempNumber = String.valueOf(trainerDto.getPhoneNumber());
        long phoneNumber = Long.parseLong(tempNumber);
        if (NumberUtil.validNumberCheck(tempNumber, 10)) {
            invalidOption.add(5);
        }

        String emailId = trainerDto.getEmailId();
        if (!StringUtil.isValidEmailId(emailId)) {
            invalidOption.add(6);
        }

        String tempSalary = String.valueOf(trainerDto.getSalary());
        double salary = Double.parseDouble(tempSalary);
        if (NumberUtil.validSalaryCheck(Double.parseDouble(tempSalary), 7)) {
            invalidOption.add(7);
        }

        String tempAadhar = String.valueOf(trainerDto.getAadharId());
        long aadharId = Long.parseLong(tempAadhar);
        if (NumberUtil.validNumberCheck(tempAadhar, 16)) {
            invalidOption.add(8);
        }

        String bloodGroup = trainerDto.getBloodGroup();

       Trainer trainer =  TrainerMapper.convertTrainerDtoToTrainer(trainerDto);
       Optional<Qualification> qualification = qualificationRepository.findByQualification(trainer.getQualification().getQualification());
       qualification.ifPresent(trainer::setQualification);

        Optional<Role> role = roleRepository.findByRole(trainer.getRole().getRole());
        role.ifPresent(trainer::setRole);



        int experience = trainerDto.getExperience();
        if (invalidOption.size() == 0) {
            int var10000 = CommonUtil.employeeId++;
            trainerRepository.save(trainer);
        } else {
            throw new BadRequest(invalidOption, invalidOptionDetails);
        }
        return invalidOption;
    }
}



