//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.dao.impl;

import com.ideas2it.employee.dao.interf.TrainerDao_Interf;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Trainer;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TrainerDaoImpl implements TrainerDao_Interf {
    private static final List<Trainer> trainers = new ArrayList<>();
    private static final SessionFactory factory = (new Configuration()).configure("hibernate/properties/hibernate.cfg.xml").buildSessionFactory();
    Session session = null;
    public TrainerDaoImpl() {
    }
    @Override
    public void insertTrainer(Trainer trainer) {
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Role.class);
            criteria.add(Restrictions.eq("role", trainer.getEmployee().getRole().getRole()));
            List<Role> roleResult = criteria.list();
            if (roleResult.size() > 0) {
                trainer.getEmployee().setRole((Role)roleResult.get(0));
            }
            criteria = session.createCriteria(Qualification.class);
            criteria.add(Restrictions.eq("qualification", trainer.getEmployee().getQualification().getQualification()));
            List<Qualification> qualificationResult = criteria.list();
            if (qualificationResult.size() > 0) {
                trainer.getEmployee().setQualification((Qualification)qualificationResult.get(0));
            }
            session.saveOrUpdate(trainer);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public List<Trainer> retrieveTrainer() {
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(Trainer.class);
        return criteria.list();
    }

    @Override
    public boolean validateTrainerById(int trainerId) {
        for(int i = 0; i < this.retrieveTrainer().size(); ++i) {
            if (trainerId == ((Trainer)this.retrieveTrainer().get(i)).getEmployee().getEmployeeId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Trainer retrieveTrainerById(int trainerId) {
        Trainer trainer = new Trainer();
        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria cr = session.createCriteria(Trainer.class);
        cr.add(Restrictions.eq("employee.employeeId", trainerId));
        List<Trainer> results = cr.list();
        if (results.size() > 0) {
            trainer = (Trainer)results.get(0);
        }
        session.close();
        return trainer;
    }

    @Override
    public void updateTrainer(Trainer trainer) {
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.merge(trainer);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public boolean deleteTrainer(int trainerId) {
        boolean isTrainerDeleted = false;
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            Criteria cr = session.createCriteria(Trainer.class);
            cr.add(Restrictions.eq("employee.employeeId", trainerId));
            List<Trainer> result = cr.list();
            if (result.size() > 0) {
                session.remove(result.get(0));
                isTrainerDeleted = true;
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return isTrainerDeleted;
    }
}

