//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.customException;

import java.util.ArrayList;
import java.util.List;

public class BadRequestException extends RuntimeException {
    public List<Integer> errors = new ArrayList();

    public BadRequestException(List<Integer> errors, String message) {
        super(message);
        this.errors = errors;
    }

    public BadRequestException(String message) {
        super(message);
    }
}
