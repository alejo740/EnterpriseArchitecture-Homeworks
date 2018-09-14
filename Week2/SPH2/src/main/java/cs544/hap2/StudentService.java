package cs544.hap2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class StudentService {

	@Autowired
	private StudentDAO studentdao;

	public StudentService() {
		//studentdao = new StudentDAO();
	}

	@Transactional(propagation= Propagation.REQUIRES_NEW, readOnly = true)
	public Student getStudent(long studentid) {
		return studentdao.load(studentid);
	}
}
