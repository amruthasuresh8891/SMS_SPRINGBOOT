package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
    private  StudentRepository repo;



    @GetMapping
    public String list(Model model) {
        model.addAttribute("students", repo.findAll());
        return "students/index";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/add";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student) {
        repo.save(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Student student = repo.findById(id).orElseThrow();
        model.addAttribute("student", student);
        return "students/edit";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute Student student) {
        student.setId(id);
        repo.save(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/students";
    }
}