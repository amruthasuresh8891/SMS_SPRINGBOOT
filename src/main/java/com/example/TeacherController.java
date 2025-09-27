package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
	
	@Autowired
    private  TeacherRepository repo;

   
    @GetMapping
    public String list(Model model) {
        model.addAttribute("teachers", repo.findAll());
        return "teachers/index";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teachers/add";
    }

    @PostMapping("/add")
    public String addTeacher(@ModelAttribute Teacher teacher) {
        repo.save(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Teacher teacher = repo.findById(id).orElseThrow();
        model.addAttribute("teacher", teacher);
        return "teachers/edit";
    }

    @PostMapping("/update/{id}")
    public String updateTeacher(@PathVariable Long id, @ModelAttribute Teacher teacher) {
        teacher.setId(id);
        repo.save(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/teachers";
    }
}