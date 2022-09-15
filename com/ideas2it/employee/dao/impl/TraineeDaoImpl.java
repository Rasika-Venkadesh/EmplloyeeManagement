package com.ideas2it.employee.dao.impl;

import com.ideas2it.employee.dao.interf.TraineeDao_Interf;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.customException.TraineeNotFoundException;
import com.ideas2it.employee.customException.TrainerNotFoundException;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;


/**
 * <p>
 * TraineeDaoImpl class  will communicate with Trainee service class and database.
 * </p> 
 * @author Rasika Venkadesh
 * @version 1.0
 **/
public class TraineeDaoImpl implements TraineeDao_Interf {
    private static List<Trainee> trainees = new ArrayList<Trainee>();
    private static SessionFactory factory;

    /**
     * <p>
     * This method is used to insert trainee object into database.
     * </p> 
     * @param Trainee Object
     * @return {@link void} return nothing
     *
     **/
    public void insertTrainee(Trainee trainee) {
        List<Trainer> trainers = new ArrayList<>();
        List<Integer> validTrainersId = new ArrayList<>();
        List<Integer> invalidTrainerId = new ArrayList<>();
        Set<Trainer> assignedTrainersForTrainee = new HashSet<>();
	Session session = null;

        try {
            factory = new Configuration().configure().buildSessionFactory();
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Role.class);
            List<Role> roleResult = criteria.add(Restrictions.eq("role", trainee.getEmployee().getRole().getRole())).list();
            if(roleResult.size() > 0) {
                trainee.getEmployee().setRole(roleResult.get(0));
            }
            criteria = session.createCriteria(Qualification.class);
            List<Qualification> qualificationResult = criteria.add(Restrictions.eq("qualification", trainee.getEmployee()
                                                      .getQualification().getQualification())).list();
            if(qualificationResult.size() > 0) {
                trainee.getEmployee().setQualification(qualificationResult.get(0)); 
            }

            trainers = session.createCriteria(Trainer.class).list();
            boolean isValidTrainerId = false;
            for (int trainerId : trainee.getTrainersId()) {        
                for (Trainer trainer : trainers) {
                    if (trainer.getEmployee().getEmployeeId() == trainerId) {
                        assignedTrainersForTrainee.add(trainer);
                        validTrainersId.add(trainerId);
                        isValidTrainerId = true;
                        break;
                    }       
                }
                if (!isValidTrainerId) {
                    invalidTrainerId.add(trainerId);
                }   
            }
            trainee.setTrainersId(validTrainersId);
            System.out.println(trainee.getTrainersId()+ "trainersId");
            System.out.println(assignedTrainersForTrainee + "sdnfdsafkakf");
            trainee.setTrainers(assignedTrainersForTrainee);
            System.out.println(assignedTrainersForTrainee);
            session.save(trainee);
            transaction.commit();
            if (invalidTrainerId.size() > 0) {
                throw new TrainerNotFoundException("Invalid Trainer Id...\n");
            }	   
        } catch (HibernateException hibernateException) {
            System.out.println(hibernateException.getMessage());
            hibernateException.printStackTrace();
        } finally {
	    session.close();
	}
    }

    
    /**
     * <p>
     * This method is used to get trainees from database.
     * </p> 
     *  
     * @return {@link List} return Trainee Object
     **/
    public List<Trainee> retrieveTrainee() {
        factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(Trainee.class);
        List results = criteria.list();
        return results;                
    }

    /**
     * <p>
     * This method is used to validate trainee object from List by traineeid.
     * </p> 
     * @param traineeid
     * @return {@link boolean} return true or false
     *
     **/
    public boolean validateTraineeById(int traineeId) {
        for (int i = 0; i < retrieveTrainee().size(); i++) {
            if (traineeId == (retrieveTrainee().get(i).getEmployee().getEmployeeId())) {
                return true;  
            }
        }
        return false;
    } 

    /**
     * <p>
     * This method is used to retrieve trainee object from database by traineeid.
     * If no object is matches with id, it throws exception.
     * </p> 
     * @param traineeid
     * @return {@link object} return object or null
     *
     **/
    public Trainee retrieveTraineeById(int traineeId) {
        Trainee trainee = new Trainee();
	try {
            factory = new Configuration().configure().buildSessionFactory();
	} catch (HibernateException hibernateException) {
            System.out.println(hibernateException);
            System.out.println(hibernateException.getMessage());
            hibernateException.printStackTrace();
        }
	Session session = factory.openSession();
	Transaction transaction = session.beginTransaction();
	Criteria cr = session.createCriteria(Trainee.class);
        cr.add(Restrictions.eq("employee.employeeId",traineeId));
        List<Trainee> results = cr.list();
	if (results.size() > 0) {
	    trainee = results.get(0);
	}
	transaction.commit();
	session.close();
	return trainee;     
    }

    /**
     * <p>
     * This method is used to update trainee object into List.
     * </p> 
     * @param Trainee Object
     * @return {@link void} return nothing
     *
     **/
    public void updateTrainee(Trainee updateTrainee, int updateChoice) {
        Session session = null;
	try {
            factory = new Configuration().configure().buildSessionFactory();
	    session = factory.openSession();
	    Transaction transaction = session.beginTransaction();
	    List<Trainer> trainerResults = session.createCriteria(Trainer.class).list();
	    List<Integer> trainersId = updateTrainee.getTrainersId();
	    Set<Trainer> trainers = new HashSet<>();
	    for (int i=0; i < trainerResults.size(); i++) {
	        for (int j=0; j < trainersId.size(); j++) {
	            if (trainersId.get(j) == trainerResults.get(i).getEmployee().getEmployeeId()) {
		        trainers.add(trainerResults.get(i));
	            }
	        }
	    }
	    updateTrainee.setTrainers(trainers);
	    session.merge(updateTrainee);
	    transaction.commit();	  
	} catch (HibernateException hibernateException) {
            System.out.println(hibernateException);
            System.out.println(hibernateException.getMessage());
            hibernateException.printStackTrace();
	} finally {
	    session.close();
	}	
    }

   /**
     * <p>
     * This method is used to delete trainee object from DATABASE based on given id.
     * </p> 
     * @param Trainer id
     * @return {@link void} return nothing
     *
     **/       
    public void deleteTrainee(int traineeId) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
	} catch (HibernateException hibernateException) {
            System.out.println(hibernateException);
            System.out.println(hibernateException.getMessage());
            hibernateException.printStackTrace();
        }
	Session session = factory.openSession();
	Transaction transaction = session.beginTransaction();
        Criteria cr = session.createCriteria(Trainee.class);
        cr.add(Restrictions.eq("employee.employeeId",traineeId));
        List<Trainee> res = cr.list();
        if(res.size() > 0) {
            session.remove(res.get(0));
	 }	
	transaction.commit();
	session.close();
    }
        

                 

}