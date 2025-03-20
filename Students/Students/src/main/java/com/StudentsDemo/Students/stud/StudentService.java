package com.StudentsDemo.Students.stud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }


    public int add(String LName,String FName, String MName,String email,int age, String YrLvl)
    {
        return studentRepo.add(LName,FName, MName,email, age,YrLvl);
    }

    public int save(StudentDisplay studentDisplay)
    {
        return studentRepo.saveStudents(studentDisplay);
    }

    public Optional<StudentDisplay> getStudentID(int StudentID) {
        return studentRepo.findId(StudentID);
    }

    public int upt(int id, StudentDisplay sd)
    {
        return studentRepo.upt(id,sd);
    }
    @Transactional
    public void delete(int id)
    {
        studentRepo.delete(id);
    }
}
