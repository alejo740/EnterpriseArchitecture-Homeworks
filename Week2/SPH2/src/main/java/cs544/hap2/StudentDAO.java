package cs544.hap2;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.MANDATORY)
public class StudentDAO {

    @Autowired
    private SessionFactory sf;

    public StudentDAO() {
    }

    public void create(Student student) {
        sf.getCurrentSession().persist(student);
    }

    public Student load(long studentid) {
        return sf.getCurrentSession().get(Student.class, studentid);
    }

    private void init() {
    }
}
