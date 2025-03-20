package com.StudentsDemo.Students.stud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentAPI {

    private final StudentService studentService;
    private StudentRepo repo;

    @Autowired
    public StudentAPI(StudentService studentService, StudentRepo repo) {
        this.studentService = studentService;
        this.repo = repo;
    }
    @GetMapping("/form")
    public String show()
    {
        return "demo.html";
    }

    //Enrollment Form (Create)
    @GetMapping("/findStudentForm")
    public String showFindStudentForm() {
        return "id.html";
    }

    //Displaying Enrolled Details
    @PostMapping("/update")
    public String update(@RequestParam String LName,
                         @RequestParam String FName,
                         @RequestParam String MName,
                         @RequestParam String email,
                         @RequestParam int age,
                         @RequestParam String YrLvl, Model model) {

        model.addAttribute("LName", LName);
        model.addAttribute("FName", FName);
        model.addAttribute("MName", MName);
        model.addAttribute("email", email);
        model.addAttribute("age", age);
        model.addAttribute("YrLvl", YrLvl);

        int rowsAffected = studentService.add(LName,FName,MName,email,age,YrLvl);

        if(rowsAffected>0)
        {
            model.addAttribute("Added");
        }
        else {
            model.addAttribute("Failed");
        }

        return "nextstep.html";
    }
    //Finding Students (Read)
    @GetMapping("/find")
    public String getStudentById(@RequestParam(value = "StudentID", required = false) Integer studentID, Model model) {
        if (studentID == null) {
            model.addAttribute("error", "Please enter a Student ID.");
            return "id";
        }
        Optional<StudentDisplay> student = studentService.getStudentID(studentID);
        model.addAttribute("student", student.orElse(null));
        model.addAttribute("error", student.isPresent() ? null : "Student not found.");
        return "id";
    }

    //Updating Student's Details (Update)
    @GetMapping("/updating")
    public String showUpdateForm(@RequestParam int id, Model model) {
        Optional<StudentDisplay> student = studentService.getStudentID(id);

        if (student.isPresent()) {
            model.addAttribute("student", student.get());
            return "update.html";  // Show update form
        } else {
            model.addAttribute("error", "Student not found.");
            return "id.html"; // Redirect to search page if not found
        }
    }



    @PostMapping("/updating")
    public String upt(@RequestParam int id,
                      @RequestParam String LName,
                      @RequestParam String FName,
                      @RequestParam String MName,
                      @RequestParam String email,
                      @RequestParam int age,
                      @RequestParam String YrLvl, Model model)
    {
        StudentDisplay sd = new StudentDisplay(id,LName,FName,MName,email,age,YrLvl);
        int result = studentService.upt(id, sd);
        return "redirect:/students/find?StudentID=" + id;
    }
    //Delete
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id, Model model)
    {
        Optional<StudentDisplay> stud = repo.findId(id);
        if (stud.isPresent()) {
            model.addAttribute("student", stud.get());
            return "delete.html"; // Return the delete.html page
        } else {
            model.addAttribute("error", "Student not found!");
            return "id"; // Redirect back to search if student not found
        }
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id, RedirectAttributes redirectAttributes)
    {
        repo.delete(id);
        redirectAttributes.addFlashAttribute("message","Student Deleted");
        return "redirect:/students/find";
    }
}


