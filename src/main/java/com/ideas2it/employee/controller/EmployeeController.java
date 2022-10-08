//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.controller;

import com.ideas2it.employee.customException.BadRequestException;
import com.ideas2it.employee.customException.TraineeNotFoundException;
import com.ideas2it.employee.customException.TrainerNotFoundException;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.service.impl.TraineeServiceImpl;
import com.ideas2it.employee.service.impl.TrainerServiceImpl;
import com.ideas2it.employee.service.interf.TraineeService_Interf;
import com.ideas2it.employee.service.interf.TrainerService_Interf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmployeeController extends HttpServlet {
    private final Logger logger = LogManager.getLogger(EmployeeController.class);
    @Autowired
    private TraineeService_Interf traineeService;
    @Autowired
    private TrainerService_Interf trainerService;

    public EmployeeController() {
    }

    @GetMapping ("/")
        public String index() {
        return "index";
    }

    @GetMapping("/trainerForm")
    public ModelAndView trainerForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trainer", new Trainer());
        modelAndView.addObject("action", "trainerRegister");
        modelAndView.setViewName("trainerRegister");
        return modelAndView;
    }

    @GetMapping("/traineeForm")
    public ModelAndView traineeForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trainee", new Trainee());
        modelAndView.addObject("action", "traineeRegister");
        modelAndView.setViewName("traineeRegister");
        return modelAndView;
    }

    @RequestMapping(value = "/trainerRegister")
        public String addTrainer(@ModelAttribute Trainer trainer, RedirectAttributes rm) {
            trainerService.validateAndAddTrainerDetails(trainer);
            if (trainer.getEmployee().getEmployeeId() > 0) {
                rm.addFlashAttribute("message", "Trainer Registered Successfully");
            } else {
                rm.addFlashAttribute("message", "Trainer Updated Successfully");
            }
        return "index";
    }

    @RequestMapping(value = "/traineeRegister")
    public String addTrainee(@ModelAttribute Trainee trainee, RedirectAttributes rm) {
        traineeService.validateAndAddTraineeDetails(trainee);
        if (trainee.getEmployee().getEmployeeId() > 0) {
            rm.addFlashAttribute("message", "Trainee Registered Successfully");
        } else {
            rm.addFlashAttribute("message", "Trainee Updated Successfully");
        }
        return "index";
    }


    @GetMapping(value = "/showTrainer")
    public ModelAndView viewTrainer() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("Trainers", trainerService.getTrainers());
        mav.setViewName("viewTrainer");
        return mav;
    }

    @GetMapping(value = "/showTrainee")
    public ModelAndView viewTrainee() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("Trainees", traineeService.getTrainees());
        mav.setViewName("viewTrainee");
        return mav;
    }

    @GetMapping("/updateTrainer")
    public ModelAndView updateTrainer(@RequestParam ("trainerId") int trainerId, Model model) {
        ModelAndView mav = new ModelAndView("registerOrUpdateTrainer");
        Trainer trainer = trainerService.getTrainerById(trainerId);
        mav.addObject("trainer", trainer);
        mav.addObject("action", "updateTrainer");
        mav.setViewName("registerOrUpdateTrainer");
        return mav;
    }

    @GetMapping("/updateTrainee")
    public ModelAndView updateTrainee(@RequestParam ("traineeId") int traineeId, Model model) {
        ModelAndView mav = new ModelAndView("registerOrUpdateTrainee");
        Trainee trainee = traineeService.getTraineeById(traineeId);
        mav.addObject("trainee", trainee);
        mav.addObject("action", "updateTrainee");
        mav.setViewName("registerOrUpdateTrainee");
        return mav;
    }

    @GetMapping("/deleteTrainer")
    public String deleteTrainer(@RequestParam("trainerId") int trainerId, Model model) {
        trainerService.removeTrainerDetails(trainerId);
        String message = "Trainer" + trainerId  + " deleted successfully...";
        model.addAttribute("message", message);
        return "index";
    }

    @GetMapping("/deleteTrainee")
    public String deleteTrainee(@RequestParam("traineeId") int traineeId, Model model) {
        traineeService.removeTraineeDetails(traineeId);
        String message = "Trainee" + traineeId  + " deleted successfully...";
        model.addAttribute("message", message);
        return "index";
    }
}
