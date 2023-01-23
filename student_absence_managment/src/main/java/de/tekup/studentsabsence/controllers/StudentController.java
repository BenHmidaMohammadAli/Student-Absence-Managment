package de.tekup.studentsabsence.controllers;

import de.tekup.studentsabsence.entities.Image;
import de.tekup.studentsabsence.entities.Student;
import de.tekup.studentsabsence.services.GroupService;
import de.tekup.studentsabsence.services.ImageService;
import de.tekup.studentsabsence.services.StudentService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final GroupService groupService;
    private final ImageService imageService;

    @GetMapping({"", "/"})
    public String index(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students/index";
    }

    @GetMapping("/add")
    public String addView(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("groups", groupService.getAllGroups());
        return "students/add";
    }

    @PostMapping("/add")
    public String add(@Valid Student student, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("groups", groupService.getAllGroups());
            return "students/add";
        }

        studentService.addStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/{sid}/update")
    public String updateView(@PathVariable Long sid, Model model) {
        model.addAttribute("student", studentService.getStudentBySid(sid));
        model.addAttribute("groups", groupService.getAllGroups());
        return "students/update";
    }

    @PostMapping("/{sid}/update")
    public String update(@PathVariable Long sid, @Valid Student student, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("groups", groupService.getAllGroups());
            return "students/update";
        }

        studentService.updateStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/{sid}/delete")
    public String delete(@PathVariable Long sid) {
        studentService.deleteStudent(sid);
        return "redirect:/students";
    }

    @GetMapping("/{sid}/show")
    public String show(Model model, @PathVariable Long sid) {
        model.addAttribute("student", studentService.getStudentBySid(sid));
        return "students/show";
    }

    @GetMapping("/{sid}/add-image")
    public String addImageView(@PathVariable Long sid, Model model) {
        model.addAttribute("student", studentService.getStudentBySid(sid));
        return "students/add-image";
    }

    @PostMapping("/{sid}/add-image")
    //TODO
    public String addImage() {
        return "redirect:/students";
    }

    @RequestMapping(value = "/{sid}/display-image")
    public void getStudentPhoto(HttpServletResponse response, @PathVariable("sid") long sid) throws Exception {
        Student student = studentService.getStudentBySid(sid);
        Image image = student.getImage();

        if(image != null) {
            response.setContentType(image.getFileType());
            InputStream inputStream = new ByteArrayInputStream(image.getData());
            IOUtils.copy(inputStream, response.getOutputStream());
        }
    }

}
