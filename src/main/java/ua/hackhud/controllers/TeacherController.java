package ua.hackhud.controllers;

import ua.hackhud.models.Teacher;
import ua.hackhud.repositories.TeacherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping
    public String listTeachers(Model model) {
        model.addAttribute("teachers", teacherRepository.findAll());
        return "teachers/index";
    }

    @GetMapping("/new")
    public String showTeacherForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teachers/new";
    }

    @PostMapping
    public String createTeacher(@Valid @ModelAttribute("teacher") Teacher teacher,
                                BindingResult result) {
        if (result.hasErrors()) {
            return "teachers/new";
        }
        teacherRepository.save(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/{id}/edit")
    public String editTeacherForm(@PathVariable Long id, Model model) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid teacher Id:" + id));
        model.addAttribute("teacher", teacher);
        return "teachers/edit";
    }

    @PostMapping("/{id}")
    public String updateTeacher(@PathVariable Long id,
                                @Valid @ModelAttribute("teacher") Teacher teacher,
                                BindingResult result) {
        if (result.hasErrors()) {
            return "teachers/edit";
        }
        teacher.setId(id);
        teacherRepository.save(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/{id}/delete")
    public String deleteTeacher(@PathVariable Long id) {
        teacherRepository.deleteById(id);
        return "redirect:/teachers";
    }
}