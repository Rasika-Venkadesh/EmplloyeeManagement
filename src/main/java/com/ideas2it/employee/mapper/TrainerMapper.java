package com.ideas2it.employee.mapper;

import com.ideas2it.employee.dto.TrainerDto;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;


public class TrainerMapper {

    public static TrainerDto convertTrainerToTrainerDto(Trainer trainer) {
        TrainerDto trainerDto = new TrainerDto();
        if (trainer != null) {
            if (trainer.getEmployeeId() != 0) {
                trainerDto.setEmployeeId(trainer.getEmployeeId());
                trainerDto.setExperience(trainer.getExperience());
                trainerDto.setName(trainer.getName());
                trainerDto.setDateOfBirth(trainer.getDateOfBirth());
                trainerDto.setDateOfJoin(trainer.getDateOfJoin());
                trainerDto.setGender(trainer.getGender());
                trainerDto.setPhoneNumber(trainer.getPhoneNumber());
                trainerDto.setEmailId(trainer.getEmailId());
                trainerDto.setSalary(trainer.getSalary());
                trainerDto.setAadharId(trainer.getAadharId());
                trainerDto.setBloodGroup(trainer.getBloodGroup());
                trainerDto.setQualificationDto(QualificationMapper.convertQualificationToQualificationDto(trainer.getQualification()));
                trainerDto.setRoleDto(RoleMapper.convertRoleToRoleDto(trainer.getRole()));
                for (Trainee trainee : trainer.getTrainees()) {
                    trainerDto.setTraineeNames(Collections.singletonList(trainee.getName()));
                }
                trainerDto.setTrainees(trainer.getTrainees());
                trainerDto.setTraineesId(trainer.getTraineesId());
            }
        }
        return trainerDto;
    }

    public static Trainer convertTrainerDtoToTrainer(TrainerDto trainerDto) {
        Trainer trainer = new Trainer();
        if (trainerDto != null) {
            if (trainerDto.getEmployeeId() != 0) {
                trainer.setEmployeeId(trainerDto.getEmployeeId());
                trainer.setExperience(trainerDto.getExperience());
                trainer.setName(trainerDto.getName());
                trainer.setDateOfBirth(trainerDto.getDateOfBirth());
                trainer.setDateOfJoin(trainerDto.getDateOfJoin());
                trainer.setGender(trainerDto.getGender());
                trainer.setPhoneNumber(trainerDto.getPhoneNumber());
                trainer.setEmailId(trainerDto.getEmailId());
                trainer.setSalary(trainerDto.getSalary());
                trainer.setAadharId(trainerDto.getAadharId());
                trainer.setBloodGroup(trainerDto.getBloodGroup());
                trainer.setQualification(QualificationMapper.convertQualificationDtoToQualification(trainerDto.getQualificationDto()));
                trainer.setRole(RoleMapper.convertRoleDtoToRole(trainerDto.getRoleDto()));
                trainer.setExperience(trainerDto.getExperience());
                trainer.setTrainees(trainerDto.getTrainees());
                trainer.setTraineesId(trainerDto.getTraineesId());
                System.out.println(trainer);
            } else {
                trainer.setExperience(trainerDto.getExperience());
                trainer.setName(trainerDto.getName());
                trainer.setDateOfBirth(trainerDto.getDateOfBirth());
                trainer.setDateOfJoin(trainerDto.getDateOfJoin());
                trainer.setGender(trainerDto.getGender());
                trainer.setPhoneNumber(trainerDto.getPhoneNumber());
                trainer.setEmailId(trainerDto.getEmailId());
                trainer.setSalary(trainerDto.getSalary());
                trainer.setAadharId(trainerDto.getAadharId());
                trainer.setBloodGroup(trainerDto.getBloodGroup());
                trainer.setQualification(QualificationMapper.convertQualificationDtoToQualification(trainerDto.getQualificationDto()));
                trainer.setRole(RoleMapper.convertRoleDtoToRole(trainerDto.getRoleDto()));
                trainer.setExperience(trainerDto.getExperience());
                trainer.setTrainees(trainerDto.getTrainees());
                trainer.setTraineesId(trainerDto.getTraineesId());
            }
        }
        return trainer;
    }
}
