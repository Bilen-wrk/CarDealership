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
    ItemsRepository itemRepository;

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

    @GetMapping("/newItem")
    public String addItem(Model model){
        model.addAttribute("item", new Item());
        model.addAttribute("count", categoryRepository.count());
        model.addAttribute("categories", categoryRepository.findAll());
        return "itemform";
    }
    //saving after form is done
    @PostMapping("/savecatform")
    public String saveCatForm(@ModelAttribute("category") Category category){
        categoryRepository.save(category);
        return "redirect:/newItem";
    }

    //saving after form is done
    @PostMapping("/saveitemform")
    public String saveCatForm(@ModelAttribute("item") Item item){
        itemRepository.save(item);
        return "redirect:/";
    }

    //changes
    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable ("id") long id, Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("item", itemRepository.findById(id).get());
        return "detailitem";
    }

    @RequestMapping("/update/{id}")
    public String delete(@PathVariable ("id") long id, Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("item", itemRepository.findById(id).get());
        return "updateitem";
    }

    @RequestMapping("/delete/{id}")
    public String update(@PathVariable("id") long id){
        itemRepository.delete(itemRepository.findById(id).get());
        return "redirect:/";
    }

}
