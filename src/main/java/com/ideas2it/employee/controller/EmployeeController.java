package com.ideas2it.employee.controller;

import com.ideas2it.employee.dto.TraineeDto;
import com.ideas2it.employee.dto.TrainerDto;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.service.TraineeService;
import com.ideas2it.employee.service.TrainerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServlet;
/**
 * <p>
 * EmployeeController class  will communicate with user and get the options to
 * add,display,update and delete (CRUD) object in the list of trainer and
 * trainee
 * </p>
 *
 * @author Rasika Venkadesh
 * @version 1.0
 **/

@Controller
public class EmployeeController extends HttpServlet {
    private final Logger logger = LogManager.getLogger(EmployeeController.class);
    public final TraineeService traineeService;
    public final TrainerService trainerService;

    @Autowired
    public EmployeeController(TraineeService traineeService, TrainerService trainerService) {
        this.traineeService = traineeService;
        this.trainerService = trainerService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/trainerForm")
    public ModelAndView trainerForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trainerDto", new TrainerDto());
        modelAndView.addObject("action", "trainerRegister");
        modelAndView.setViewName("trainerRegister");
        return modelAndView;
    }

    @GetMapping("/traineeForm")
    public ModelAndView traineeForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("traineeDto", new TraineeDto());
        modelAndView.addObject("action", "traineeRegister");
        modelAndView.addObject("trainers", trainerService.getTrainers());
        modelAndView.setViewName("traineeRegister");
        return modelAndView;
    }

    @RequestMapping(value = "/trainerRegister")
    public String addTrainer(@ModelAttribute TrainerDto trainerDto, RedirectAttributes rm) {
        trainerService.validateAndAddTrainerDetails(trainerDto);
        if (trainerDto.getEmployeeId() > 0) {
            rm.addFlashAttribute("message", "Trainer Registered Successfully");
        } else {
            rm.addFlashAttribute("message", "Trainer Updated Successfully");
        }
        return "index";
    }

    @RequestMapping(value = "/traineeRegister")
    public String addTrainee(@ModelAttribute TraineeDto traineeDto, RedirectAttributes rm) {
        traineeService.validateAndAddTraineeDetails(traineeDto);
        if (traineeDto.getEmployeeId() > 0) {
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

    @GetMapping(value = "/viewPage")
    public ModelAndView viewPage(@RequestParam("trainerId") int trainerId,Model model) {
        ModelAndView mav = new ModelAndView("viewPage");
        mav.addObject("employeeDto", trainerService.getTrainerId(trainerId));
        mav.addObject("action", "Trainer");
        mav.setViewName("viewPage");
        return mav;
    }

    @GetMapping(value = "/viewTraineePage")
    public ModelAndView viewTraineePage(@RequestParam("traineeId") int traineeId,Model model) {
        ModelAndView mav = new ModelAndView("viewPage");
        mav.addObject("employeeDto", traineeService.getTraineeById(traineeId));
        mav.addObject("action", "Trainee");
        //mav.setViewName("viewPage");
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
    public ModelAndView updateTrainer(@RequestParam ("employeeId") int trainerId, Model model) {
        ModelAndView mav = new ModelAndView("registerOrUpdateTrainer");
        TrainerDto trainerDto = trainerService.getTrainerId(trainerId);
        mav.addObject("trainerDto", trainerDto);
        mav.addObject("action", "Trainer");
        mav.setViewName("registerOrUpdateTrainer");
        return mav;
    }

    @GetMapping("/updateTrainee")
    public ModelAndView updateTrainee(@RequestParam ("employeeId") int traineeId, Model model) {
        ModelAndView mav = new ModelAndView("registerOrUpdateTrainee");
        TraineeDto traineeDto = traineeService.getTraineeById(traineeId);
        mav.addObject("traineeDto", traineeDto);
        mav.addObject("action", "Trainee");
        mav.addObject("trainers", trainerService.getTrainers());
        mav.setViewName("registerOrUpdateTrainee");
        return mav;
    }

    @GetMapping("/deleteTrainer")
    public String deleteTrainer(@RequestParam("employeeId") int trainerId, Model model) {
        trainerService.removeTrainerDetails(trainerId);
        String message = "Trainer" + trainerId  + " deleted successfully...";
        model.addAttribute("message", message);
        return "index";
    }

    @GetMapping("/deleteTrainee")
    public String deleteTrainee(@RequestParam("employeeId") int traineeId, Model model) {
        traineeService.removeTraineeDetails(traineeId);
        String message = "Trainee" + traineeId  + " deleted successfully...";
        model.addAttribute("message", message);
        return "index";
    }
}


