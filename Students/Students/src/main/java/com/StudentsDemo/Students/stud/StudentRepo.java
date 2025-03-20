package com.StudentsDemo.Students.stud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static java.util.stream.StreamSupport.stream;

@Repository
public class StudentRepo {

    private final JdbcTemplate template;

    @Autowired
    public StudentRepo(JdbcTemplate template) {
        this.template = template;
    }

    private static final Logger log = LoggerFactory.getLogger(StudentRepo.class);

    public int add(String LName, String FName, String MName, String email, int age, String YrLvl )
    {
        System.out.println("SR" + FName);
        String sql = "Insert Into details (LastName,FName,MName,email,age,YrLvl) Values(?,?,?,?,?,?)";

        System.out.println("Attempt: " + sql);
        System.out.println("Last Name: " + LName);
        System.out.println("First Name: " + FName);
        System.out.println("Middle Name: " + MName);
        System.out.println("Email " + email);
        System.out.println("Age: " + age);
        System.out.println("YrLvl: " + YrLvl);
       // log.info("Executing sql:{} with FName ={}",sql,FName);


        int rows = template.update(sql, LName, FName, MName, email,age,YrLvl);
        //log.info("row: {} ", rows);
        return rows;
    }

//    public void trying()
//    {
//        try{
//            String sql = "Select 1";
//            Integer result = template.queryForObject(sql, Integer.class);
//            System.out.println("Test Result: " + result);
//        } catch (Exception e) {
//            System.out.println("failed: " + e.getMessage());
//        }
//    }

    public int saveStudents(StudentDisplay studentDisplay)
    {
        String sql = "Insert Into Details (lname,fname,mname,email,age) Values(?,?,?,?,?)";

        return template.update(sql,
                studentDisplay.getAge(),
                studentDisplay.getLname(),
                studentDisplay.getFname(),
                studentDisplay.getMname(),
                studentDisplay.getEmail());

        //return a;
    }


    public Optional<StudentDisplay> findId(int StudentID) {
        String sql = "SELECT * FROM Details where StudentID=?";  // Make sure this table name matches your database

        return template.query(sql, new Object[]{StudentID}, new StudentRowMapper())
                .stream().findFirst();
    }

    public void delete(int id) {
        String sql = "Delete from details where StudentID = ?";
        template.update(sql, id);

    }


    public static class StudentRowMapper implements RowMapper<StudentDisplay>{

        @Override
        public StudentDisplay mapRow(ResultSet rs, int rowNum) throws SQLException {
            StudentDisplay sd = new StudentDisplay();
            sd.setId(rs.getInt("StudentID"));
            sd.setLname(rs.getString("LastName"));
            sd.setFname(rs.getString("FName"));
            sd.setMname(rs.getString("MName"));
            sd.setEmail(rs.getString("email"));
            sd.setAge(rs.getInt("age"));
            sd.setYrLvl(rs.getString("YrLvl"));

            return sd;
            }
        }
        public int upt(int id, StudentDisplay sd)
        {
            String sql = "Update Details Set LastName=?, FName=?, MName=?, email=?, age=?,YrLvl=? where StudentID=?" ;
            return template.update(sql, sd.getLname(),sd.getFname(),sd.getMname(),sd.getEmail(),sd.getAge(),sd.getYrLvl(),id);
        }
    }

