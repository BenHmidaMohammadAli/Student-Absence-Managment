package de.tekup.studentsabsence.controllers;

import de.tekup.studentsabsence.entities.Student;
import de.tekup.studentsabsence.entities.Subject;
import de.tekup.studentsabsence.entities.Group;
import de.tekup.studentsabsence.services.GroupService;
import de.tekup.studentsabsence.services.StudentService;
import de.tekup.studentsabsence.services.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.AttributedString;
import  java.util.List ;
import org.springframework.ui.Model;

@Controller
@AllArgsConstructor
public class FrontController {
    private final StudentService studentService;
    private final SubjectService subjectService;
    private final GroupService groupService;


    @GetMapping({"", "/"})
    public String index(Model model) {

        List<Group> groups = groupService.getAllGroups();
        List<Student> students = studentService.getAllStudents();
        List<Subject> subjects = subjectService.getAllSubjects();

        List<Group> groupsList = groupService.getAllGroups();
        List<Student> studentsList = studentService.getAllStudents ();
        List<Subject> subjectList = subjectService.getAllSubjects ();


        int NumberOfStudent =0 ;
        int NumberOfSubject =0 ;
        int NumberOfGroup =0 ;

        for (Student i :students){
            NumberOfStudent ++ ;
        }
        for (Subject i :subjects){
            NumberOfSubject ++ ;
        }
        for (Group i :groups){
            NumberOfGroup ++ ;
        }


        model.addAttribute("NumberOfStudent", NumberOfStudent);
        model.addAttribute("NumberOfSubject", NumberOfSubject);
        model.addAttribute("NumberOfGroup", NumberOfGroup);

        model.addAttribute("groups", groupsList);
        model.addAttribute("students", studentsList);
        model.addAttribute("subjects", subjectList);

        return "index";
    }

}
