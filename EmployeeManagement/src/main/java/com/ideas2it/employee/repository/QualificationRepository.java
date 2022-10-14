package com.ideas2it.employee.repository;

import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Integer> {
    Optional<Qualification> findByQualification(String qualification);
}
