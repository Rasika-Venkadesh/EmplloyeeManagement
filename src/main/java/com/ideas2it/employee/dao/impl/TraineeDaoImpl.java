//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.dao.impl;

import com.ideas2it.employee.customException.TrainerNotFoundException;
import com.ideas2it.employee.dao.interf.TraineeDao_Interf;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
public class TraineeDaoImpl implements TraineeDao_Interf {
    private static List<Trainee> trainees = new ArrayList();
    private static final SessionFactory factory = (new Configuration()).configure("hibernate/properties/hibernate.cfg.xml").buildSessionFactory();

    public TraineeDaoImpl() {
    }

    @Override
    public void insertTrainee(Trainee trainee) {
        new ArrayList<>();
        List<Integer> validTrainersId = new ArrayList<>();
        List<Integer> invalidTrainerId = new ArrayList<>();
        Set<Trainer> assignedTrainersForTrainee = new HashSet<>();
        Session session = null;
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Role.class);
            List<Role> roleResult = criteria.add(Restrictions.eq("role", trainee.getEmployee().getRole().getRole())).list();
            if (roleResult.size() > 0) {
                trainee.getEmployee().setRole((Role)roleResult.get(0));
            }
            criteria = session.createCriteria(Qualification.class);
            List<Qualification> qualificationResult = criteria.add(Restrictions.eq("qualification", trainee.getEmployee().getQualification().getQualification())).list();
            if (qualificationResult.size() > 0) {
                trainee.getEmployee().setQualification((Qualification)qualificationResult.get(0));
            }
            List<Trainer> trainers = session.createCriteria(Trainer.class).list();
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
            trainee.setTrainers(assignedTrainersForTrainee);
            session.saveOrUpdate(trainee);
            transaction.commit();
            if (invalidTrainerId.size() > 0) {
                throw new TrainerNotFoundException("Invalid Trainer Id...\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }

    }

    @Override
    public List<Trainee> retrieveTrainee() {
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(Trainee.class);
        return criteria.list();
    }

    @Override
    public boolean validateTraineeById(int traineeId) {
        for(int i = 0; i < this.retrieveTrainee().size(); ++i) {
            if (traineeId == ((Trainee)this.retrieveTrainee().get(i)).getEmployee().getEmployeeId()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Trainee retrieveTraineeById(int traineeId) {
        Trainee trainee = new Trainee();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Trainee.class);
        criteria.add(Restrictions.eq("employee.employeeId", traineeId));
        List<Trainee> results = criteria.list();
        if (results.size() > 0) {
            trainee = (Trainee) results.get(0);
        }
        session.close();
        return trainee;
    }

    @Override
    public void updateTrainee(Trainee trainee) {
        Session session = null;
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.merge(trainee);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public boolean deleteTrainee(int traineeId) {
        boolean isTraineeDeleted = false;
        Session session = null;
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            Criteria cr = session.createCriteria(Trainee.class);
            cr.add(Restrictions.eq("employee.employeeId", traineeId));
            List<Trainee> res = cr.list();
            if (res.size() > 0) {
                session.remove(res.get(0));
                isTraineeDeleted = true;
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return isTraineeDeleted;
    }
}
