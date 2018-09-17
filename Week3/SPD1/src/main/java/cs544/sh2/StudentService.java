package cs544.sh2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class StudentService {

    @Autowired
    private StudentDao studentdao;

    public void setStudentdao(StudentDao studentdao) {
        this.studentdao = studentdao;
    }

    @Transactional
    public Student getStudent(long studentid) {
        Student student = studentdao.findById(studentid).orElse(new Student());
        return student;
    }
}
