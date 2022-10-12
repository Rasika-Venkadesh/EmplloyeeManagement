package com.ideas2it.employee.controller;

import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.service.impl.TraineeServiceImpl;
import com.ideas2it.employee.service.impl.TrainerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import java.util.Optional;


@Controller
public class EmployeeController extends HttpServlet {
    private final Logger logger = LogManager.getLogger(EmployeeController.class);

    @Autowired
    public TraineeServiceImpl traineeService;
    @Autowired
    public TrainerServiceImpl trainerService;

    @GetMapping("/")
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
        if (trainer.getEmployeeId() > 0) {
            rm.addFlashAttribute("message", "Trainer Registered Successfully");
        } else {
            rm.addFlashAttribute("message", "Trainer Updated Successfully");
        }
        return "index";
    }

    @RequestMapping(value = "/traineeRegister")
    public String addTrainee(@ModelAttribute Trainee trainee, RedirectAttributes rm) {
        traineeService.validateAndAddTraineeDetails(trainee);
        if (trainee.getEmployeeId() > 0) {
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
        Optional<Trainer> trainer = trainerService.getTrainerId(trainerId);
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


