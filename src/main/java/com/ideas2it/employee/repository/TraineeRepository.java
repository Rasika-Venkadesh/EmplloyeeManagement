package com.ideas2it.employee.repository;

import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Integer> {
    @Query(value =" select e.e_id,e.e_name,e.phone_number from employee e inner join emp_relation on trainee_id = e.e_id where trainer_id = ?1", nativeQuery = true)
            public Trainee getTraineeDetails(int trainerId);
}
