package com.ideas2it.employee.mapper;

import com.ideas2it.employee.dto.TraineeDto;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;

import java.util.Collections;

public class TraineeMapper {

    public static TraineeDto convertTraineeToTraineeDto(Trainee trainee) {
        TraineeDto traineeDto = new TraineeDto();
        if (trainee != null) {
            if (trainee.getEmployeeId() != 0) {
                traineeDto.setEmployeeId(trainee.getEmployeeId());
                traineeDto.setName(trainee.getName());
                traineeDto.setDateOfBirth(trainee.getDateOfBirth());
                traineeDto.setDateOfJoin(trainee.getDateOfJoin());
                traineeDto.setGender(trainee.getGender());
                traineeDto.setPhoneNumber(trainee.getPhoneNumber());
                traineeDto.setEmailId(trainee.getEmailId());
                traineeDto.setSalary(trainee.getSalary());
                traineeDto.setAadharId(trainee.getAadharId());
                traineeDto.setBloodGroup(trainee.getBloodGroup());
                traineeDto.setQualificationDto(QualificationMapper.convertQualificationToQualificationDto(trainee.getQualification()));
                traineeDto.setRoleDto(RoleMapper.convertRoleToRoleDto(trainee.getRole()));
                for (Trainer trainer : trainee.getTrainers()) {
                    traineeDto.setTrainerNames(Collections.singletonList(trainer.getName()));
                }
                traineeDto.setTrainers(trainee.getTrainers());
                traineeDto.setTrainersId(trainee.getTrainersId());
                traineeDto.setTrainingPeriod(trainee.getTrainingPeriod());
            }
        }
        return traineeDto;

    }
    public static Trainee convertTraineeDtoToTrainee(TraineeDto traineeDto) {
        Trainee trainee = new Trainee();
        if (traineeDto != null) {
           if(traineeDto.getEmployeeId() != 0) {
               trainee.setEmployeeId(traineeDto.getEmployeeId());
               trainee.setName(traineeDto.getName());
               trainee.setDateOfBirth(traineeDto.getDateOfBirth());
               trainee.setDateOfJoin(traineeDto.getDateOfJoin());
               trainee.setGender(traineeDto.getGender());
               trainee.setPhoneNumber(traineeDto.getPhoneNumber());
               trainee.setEmailId(traineeDto.getEmailId());
               trainee.setSalary(traineeDto.getSalary());
               trainee.setAadharId(traineeDto.getAadharId());
               trainee.setBloodGroup(traineeDto.getBloodGroup());
               trainee.setQualification(QualificationMapper.convertQualificationDtoToQualification(traineeDto.getQualificationDto()));
               trainee.setRole(RoleMapper.convertRoleDtoToRole(traineeDto.getRoleDto()));
               trainee.setTrainersId(traineeDto.getTrainersId());
               trainee.setTrainingPeriod(traineeDto.getTrainingPeriod());
           } else {
               trainee.setName(traineeDto.getName());
               trainee.setDateOfBirth(traineeDto.getDateOfBirth());
               trainee.setDateOfJoin(traineeDto.getDateOfJoin());
               trainee.setGender(traineeDto.getGender());
               trainee.setPhoneNumber(traineeDto.getPhoneNumber());
               trainee.setEmailId(traineeDto.getEmailId());
               trainee.setSalary(traineeDto.getSalary());
               trainee.setAadharId(traineeDto.getAadharId());
               trainee.setBloodGroup(traineeDto.getBloodGroup());
               trainee.setQualification(QualificationMapper.convertQualificationDtoToQualification(traineeDto.getQualificationDto()));
               trainee.setRole(RoleMapper.convertRoleDtoToRole(traineeDto.getRoleDto()));
               trainee.setTrainersId(traineeDto.getTrainersId());
               trainee.setTrainingPeriod(traineeDto.getTrainingPeriod());
           }

        }
        return trainee;
    }


}
