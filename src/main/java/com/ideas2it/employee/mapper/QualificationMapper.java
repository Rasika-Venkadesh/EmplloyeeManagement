package com.ideas2it.employee.mapper;

import com.ideas2it.employee.dto.QualificationDto;
import com.ideas2it.employee.model.Qualification;

public class QualificationMapper {

    public static QualificationDto convertQualificationToQualificationDto(Qualification qualification) {
        QualificationDto qualificationDto = new QualificationDto();
        if(qualification != null) {
            if(qualification.getQualificationId() != 0) {
                qualificationDto.setQualificationId(qualification.getQualificationId());
            }
            qualificationDto.setQualification(qualification.getQualification());
        }
        return qualificationDto;
    }

    public static Qualification convertQualificationDtoToQualification(QualificationDto qualificationDto) {
        Qualification qualification = new Qualification();
        if(qualificationDto != null) {
            if(qualificationDto.getQualificationId() != 0) {
                qualification.setQualificationId(qualificationDto.getQualificationId());
            }
            qualification.setQualification(qualificationDto.getQualification());
        }
        return  qualification;
    }


}
