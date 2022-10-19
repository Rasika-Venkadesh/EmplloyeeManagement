//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.Helper.TrainerHelper;
import com.ideas2it.employee.common.CommonUtil;
import com.ideas2it.employee.customException.BadRequest;
import com.ideas2it.employee.customException.TraineeNotFoundException;
import com.ideas2it.employee.dto.TraineeDto;
import com.ideas2it.employee.mapper.TraineeMapper;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.repository.QualificationRepository;
import com.ideas2it.employee.repository.RoleRepository;
import com.ideas2it.employee.repository.TraineeRepository;
import com.ideas2it.employee.repository.TrainerRepository;
import com.ideas2it.employee.service.TraineeService;
import com.ideas2it.employee.utility.DateUtil;
import com.ideas2it.employee.utility.NumberUtil;
import com.ideas2it.employee.utility.StringUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TraineeServiceImpl implements TraineeService {
    private final Logger logger = LogManager.getLogger(TraineeServiceImpl.class);
    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;
    private final QualificationRepository qualificationRepository;
    private final RoleRepository roleRepository;
    private TrainerHelper trainerHelper;
    public TraineeServiceImpl( TraineeRepository traineeRepository,
                              TrainerHelper trainerHelper,
                              TrainerRepository trainerRepository, QualificationRepository qualificationRepository,
                              RoleRepository roleRepository) {
        this.trainerHelper=trainerHelper;
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
        this.qualificationRepository = qualificationRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public List<TraineeDto> getTrainees()  {
        List<Trainee> trainees =  traineeRepository.findAll();
            return trainees.stream().map(TraineeMapper::convertTraineeToTraineeDto).collect(Collectors.toList());
        }


    @Override
    public TraineeDto getTraineeById(int traineeId) throws TraineeNotFoundException {
        TraineeDto traineeDto = null;
        Optional<Trainee> trainee = traineeRepository.findById(traineeId);
        if (trainee.isEmpty()) {
            throw new TraineeNotFoundException("Trainee not found...");
        }
        traineeDto = TraineeMapper.convertTraineeToTraineeDto(trainee.get());
        return traineeDto;

    }

    @Override
    @Transactional
    public void removeTraineeDetails(int traineeId) {
        traineeRepository.deleteById(traineeId);
    }

    @Override
    @Transactional
    public List<Integer> validateAndAddTraineeDetails(TraineeDto traineeDto) {
        List<Integer> invalidOption = new ArrayList<>();
        String invalidOptionDetails = "\n\tInvalid Details of Employee\n";
        int no = 0;
        String name = traineeDto.getName();
        if (!StringUtil.isValidName(name)) {
            invalidOption.add(1);
        }

        String dateOfBirth = traineeDto.getDateOfBirth().toString();
        if (DateUtil.computePeriod(traineeDto.getDateOfBirth(), LocalDate.now()) < 18 && !Objects.equals(traineeDto.getDateOfBirth(), LocalDate.now())) {
            invalidOption.add(2);
        }

        String dateOfJoin = traineeDto.getDateOfJoin().toString();
        if (DateUtil.computeDays(traineeDto.getDateOfJoin(), LocalDate.now()) <= 1) {
            invalidOption.add(3);
        }

        String gender = traineeDto.getGender();
        String tempNumber = String.valueOf(traineeDto.getPhoneNumber());
        long phoneNumber = Long.parseLong(tempNumber);
        if (NumberUtil.validNumberCheck(tempNumber, 10)) {
            invalidOption.add(5);
        }

        String emailId = traineeDto.getEmailId();
        if (!StringUtil.isValidEmailId(emailId)) {
            invalidOption.add(6);
        }

        String tempSalary = String.valueOf(traineeDto.getSalary());
        double salary = Double.parseDouble(tempSalary);
        if (NumberUtil.validSalaryCheck(salary, 7)) {
            invalidOption.add(7);
        }

        String tempAadhar = String.valueOf(traineeDto.getAadharId());
        long aadharId = Long.parseLong(tempAadhar);
        if (NumberUtil.validNumberCheck(tempAadhar, 16)) {
            invalidOption.add(8);
        }
        String bloodGroup = traineeDto.getBloodGroup();

        Trainee trainee =  TraineeMapper.convertTraineeDtoToTrainee(traineeDto);
        Optional<Qualification> qualification = qualificationRepository.findByQualification(trainee.getQualification().getQualification());
        qualification.ifPresent(trainee::setQualification);

        Optional<Role> role = roleRepository.findByRole(trainee.getRole().getRole());
        role.ifPresent(trainee::setRole);

        int trainingPeriod = traineeDto.getTrainingPeriod();
        List<Integer> trainersId = traineeDto.getTrainersId();
        Set<Trainer> trainersGroup = Set.copyOf(trainerHelper.getAllTrainersById(trainersId));
        trainee.setTrainers(trainersGroup);
        if (invalidOption.size() == 0) {
            int var10000 = CommonUtil.employeeId++;
                traineeRepository.save(trainee);
        } else {
            throw new BadRequest(invalidOption, invalidOptionDetails);
        }
        return invalidOption;
    }

}
