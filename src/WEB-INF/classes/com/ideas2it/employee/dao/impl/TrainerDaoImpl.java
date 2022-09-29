package com.ideas2it.employee.dao.impl;

import com.ideas2it.employee.dao.interf.TrainerDao_Interf;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Qualification;

import java.util.List;
import java.util.ArrayList;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Projections;
import org.hibernate.cfg.Configuration;


/**
 * <p>
 * TraineeDao class  will communicate with Trainee service class.It holds 
 * List of trainees. 
 * </p> 
 * @author Rasika Venkadesh
 * @version 1.0
 **/
public class TrainerDaoImpl implements TrainerDao_Interf {
    private static List<Trainer> trainers = new ArrayList<Trainer>();
    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    /**
     * <p>
     * This method is used to insert trainer object into database using Hibernate.
     * </p> 
     * @param Trainer Object
     * @return {@link void} return nothing
     *
     **/
    public void insertTrainer(Trainer trainer) {
        try {
            
            Session session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Role.class);
	    criteria.add(Restrictions.eq("role", trainer.getEmployee().getRole().getRole()));
	    List<Role> roleResult = criteria.list();
	    if (roleResult.size() > 0) {
	        trainer.getEmployee().setRole(roleResult.get(0));
	    }
	    criteria = session.createCriteria(Qualification.class);
	    criteria.add(Restrictions.eq("qualification", trainer.getEmployee().getQualification().getQualification()));
	    List<Qualification> qualificationResult = criteria.list();
	    if (qualificationResult.size() > 0) {
	        trainer.getEmployee().setQualification(qualificationResult.get(0));
	    }
            session.saveOrUpdate(trainer);
            transaction.commit();
            session.close();
        } catch (HibernateException hibernateException) {
            System.out.println(hibernateException);
            System.out.println(hibernateException.getMessage());
            hibernateException.printStackTrace();
        }
    }
            
    /**
     * <p>
     * This method is used to get trainee object list from database.
     * </p> 
     *  
     * @return {@link List} return List of Trainee Objects
     **/
    public List<Trainer> retrieveTrainer() {
        //factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(Trainer.class);
        List results = criteria.list();
	//session.close();
        return results;            
    }

    /**
     * <p>
     * This method is used to validate trainee object from List by traineeid.
     * </p> 
     * @param trainerid
     * @return {@link boolean} return true or false
     *
     **/
    public boolean validateTrainerById(int trainerId) {
        //List<Trainer> trainers = new ArrayList();
        for (int i = 0; i < retrieveTrainer().size(); i++) {
            if (trainerId == (retrieveTrainer().get(i).getEmployee().getEmployeeId())) {
                return true;  
            }
        }
        return false;
    }

    /**
     * <p>
     * This method is used to retrieve trainer object from List by trainerid.
     * If no object is matches with id, it returns null.
     * </p> 
     * @param trainerid
     * @return {@link object} return object or null
     *
     **/
    public Trainer retrieveTrainerById(int trainerId) {
        Trainer trainer = new Trainer();
	Session session = factory.openSession();
	Transaction transaction = session.beginTransaction();
	Criteria cr = session.createCriteria(Trainer.class);
        cr.add(Restrictions.eq("employee.employeeId",trainerId));
        List<Trainer> results = cr.list();
	if (results.size() > 0) {
	    trainer = results.get(0);
	}
        transaction.commit();
        session.close();
	return trainer;     
    }

    
    /**
     * <p>
     * This method is used to update trainer object into List.
     * </p> 
     * @param Trainer Object
     * @return {@link void} return nothing
     *
     **/
    public void updateTrainer(Trainer trainer) {
        Session session = null;
        session = factory.openSession();
	Transaction transaction = session.beginTransaction();
	session.merge(trainer);
        transaction.commit();
        session.close();
    }

    /**
     * <p>
     * This method is used to delete trainer object from database based on given id.
     * </p> 
     * @param Trainer id
     * @return {@link void} return nothing
     *
     **/    
    public boolean deleteTrainer(int trainerId) { 
        boolean isTrainerDeleted = false;       
	Session session = factory.openSession();
	Transaction transaction = session.beginTransaction();
        Criteria cr = session.createCriteria(Trainer.class);
        cr.add(Restrictions.eq("employee.employeeId",trainerId));
        List<Trainer> result = cr.list();
        if(result.size() > 0) {
            session.remove(result.get(0));
            isTrainerDeleted = true;
	 }	
	transaction.commit();
	session.close();
        return isTrainerDeleted;
    }
     
}