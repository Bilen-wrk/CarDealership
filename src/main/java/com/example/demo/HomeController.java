package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CarRepository carRepository;

    //view the category
    @RequestMapping("/")
    public String homePage(Model model) {
        model.addAttribute("allcat", categoryRepository.findAll());
        return "home";
    }

    //form
    @GetMapping("/newCategory")
    public String addCategory(Model model){
        model.addAttribute("category", new Category());
        return "categoryform";
    }

    @GetMapping("/newCar")
    public String addCar(Model model){
        model.addAttribute("car", new Car());
        model.addAttribute("count", categoryRepository.count());
        model.addAttribute("categories", categoryRepository.findAll());
        return "carform";
    }
    //saving after form is done
    @PostMapping("/savecatform")
    public String saveCatForm(@ModelAttribute("category") Category category){
        categoryRepository.save(category);
        return "redirect:/newCar";
    }

    //saving after form is done
    @PostMapping("/savecarform")
    public String saveCatForm(@ModelAttribute("car") Car car){
        carRepository.save(car);
        return "redirect:/";
    }
    //changes
    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable ("id") long id, Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("car", carRepository.findById(id).get());
        return "detailcar";
    }

    @RequestMapping("/update/{id}")
    public String delete(@PathVariable ("id") long id, Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("car", carRepository.findById(id).get());
        return "updatecar";
    }

    @RequestMapping("/delete/{id}")
    public String update(@PathVariable ("id") long id){
        carRepository.delete(carRepository.findById(id).get());
        return "redirect:/";
    }





}
