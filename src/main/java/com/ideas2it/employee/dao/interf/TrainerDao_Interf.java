//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.dao.interf;

import com.ideas2it.employee.model.Trainer;
import java.util.List;

public interface TrainerDao_Interf {
    List<Trainer> retrieveTrainer();

    void insertTrainer(Trainer var1);

    boolean validateTrainerById(int var1);

    Trainer retrieveTrainerById(int var1);

    void updateTrainer(Trainer var1);

    boolean deleteTrainer(int var1);
}
