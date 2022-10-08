//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.dao.interf;

import com.ideas2it.employee.model.Trainee;
import java.util.List;

public interface TraineeDao_Interf {
    List<Trainee> retrieveTrainee();

    void insertTrainee(Trainee var1);

    boolean validateTraineeById(int var1);

    Trainee retrieveTraineeById(int var1);

    void updateTrainee(Trainee var1);

    boolean deleteTrainee(int var1);
}
