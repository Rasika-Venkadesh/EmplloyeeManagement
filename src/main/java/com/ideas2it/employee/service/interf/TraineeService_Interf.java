//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.service.interf;

import com.ideas2it.employee.model.Trainee;
import java.util.List;

public interface TraineeService_Interf {
    List<Trainee> getTrainees();

    void addTraineeDetails(Trainee var1);

    boolean validateTraineeId(int var1);

    Trainee getTraineeById(int var1);

    void modifyTraineeDetails(Trainee var1);

    void removeTraineeDetails(int var1);

    List<Integer> validateAndAddTraineeDetails(Trainee var1);

    boolean validateTraineePhoneNumber(long var1, Trainee var3);

    boolean validateTraineeEmailId(String var1, Trainee var2);

    boolean validateTraineeAadharId(long var1, Trainee var3);
}
