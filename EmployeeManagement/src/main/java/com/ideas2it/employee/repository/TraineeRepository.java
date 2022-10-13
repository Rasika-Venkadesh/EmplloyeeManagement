package com.ideas2it.employee.repository;

import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TraineeRepository extends JpaRepository<Trainee, Integer> {
}
