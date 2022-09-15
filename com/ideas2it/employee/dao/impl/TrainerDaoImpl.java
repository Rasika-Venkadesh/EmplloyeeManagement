package com.ideas2it.employee.dao.impl;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.dao.interf.TrainerDao_Interf;

import java.util.List;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Projections;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;



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
    private static SessionFactory factory;

    /**
     * <p>
     * This method is used to insert trainer object into database using JDBC.
     * </p> 
     * @param Trainer Object
     * @return {@link void} return nothing
     *
     **/
    public void insertTrainer(Trainer trainer) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
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
            session.save(trainer);
            transaction.commit();
            System.out.println("Employee added Succesfully");
            session.close();
        } catch (HibernateException hibernateException) {
            System.out.println(hibernateException);
            System.out.println(hibernateException.getMessage());
            hibernateException.printStackTrace();
        }
    }
            

    /**
     * <p>
     * This method is used to get trainee object from List.
     * </p> 
     *  
     * @return {@link List} return Trainee Object
     **/
    public List<Trainer> retrieveTrainer() {
        factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(Trainer.class);
        List results = criteria.list();
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
	try {
            factory = new Configuration().configure().buildSessionFactory();
	} catch (HibernateException hibernateException) {
            System.out.println(hibernateException);
            System.out.println(hibernateException.getMessage());
            hibernateException.printStackTrace();
        }
	Session session = factory.openSession();
	Transaction transaction = session.beginTransaction();
	Criteria cr = session.createCriteria(Trainer.class);
        cr.add(Restrictions.eq("employee.employeeId",trainerId));
        List<Trainer> results = cr.list();
	if (results.size() > 0) {
	    trainer = results.get(0);
	}
        transaction.commit();
        //session.close();
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
    public void updateTrainer(Trainer updateTrainer, int updateChoice) {
        Session session = null;
	try {
            factory = new Configuration().configure().buildSessionFactory();	  
	} catch (HibernateException hibernateException) {
            System.out.println(hibernateException);
            System.out.println(hibernateException.getMessage());
            hibernateException.printStackTrace();
	} 
        session = factory.openSession();
	Transaction transaction = session.beginTransaction();
	session.merge(updateTrainer);
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
        try {
            factory = new Configuration().configure().buildSessionFactory();
	} catch (HibernateException hibernateException) {
            System.out.println(hibernateException.getMessage());
            hibernateException.printStackTrace();
        }
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