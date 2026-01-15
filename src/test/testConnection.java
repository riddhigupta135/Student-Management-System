import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import studentmanagement.dao.StudentDAO;
import studentmanagement.model.Student;

public class testConnection {

	@Test
	public void connectionTest() {
	
			try {
				Connection connection = StudentDAO.getConnection();
				System.out.println("Got connection:"+connection);
			} catch (Exception e) {
				System.out.println("Did not connection:");
				e.printStackTrace();
			}
		
	}
	
	
	@Test
	public void selectAllStudentTest() {
	
			try {
				List<Student> students = StudentDAO.selectAllStudents();
				System.out.println("Got students:"+students.size());
			} catch (Exception e) {
				System.out.println("Did not students:");
				e.printStackTrace();
			}
		
	}
	
	
//	@Test
//	public void insertStudentTest() {
//	
//			try {
//				Connection connection = StudentDAO.insertStudent(null);
//				System.out.println("Got connection:"+connection);
//			} catch (Exception e) {
//				System.out.println("Did not connection:");
//				e.printStackTrace();
//			}
//		
//	}


}
