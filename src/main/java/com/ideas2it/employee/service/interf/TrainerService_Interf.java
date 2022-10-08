//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.service.interf;

import com.ideas2it.employee.model.Trainer;
import java.util.List;

public interface TrainerService_Interf {
    List<Trainer> getTrainers();

    void addTrainerDetails(Trainer var1);

    boolean validateTrainerId(int var1);

    Trainer getTrainerById(int var1);

    void modifyTrainerDetails(Trainer var1);

    void removeTrainerDetails(int var1);

    List<Integer> validateAndAddTrainerDetails(Trainer var1);

    boolean validateTrainerPhoneNumber(long var1, Trainer var3);

    boolean validateTrainerEmailId(String var1, Trainer var2);

    boolean validateTrainerAadhar(long var1, Trainer var3);
}
