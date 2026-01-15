package studentmanagement.service;

import java.sql.SQLException;
import studentmanagement.model.Student;

/* This class is an interface with prepared statements (used for MYSQL data base) as
 * variables and important method headers executed by Student DAO. 
 */

public interface StudentInterface {
	public static final String INSERT_STUDENTS_SQL = "INSERT INTO students" + "  (name, email, phone, address, is_fee_due,fee_due_after, batch_id, is_deleted) VALUES "
			+ " (?, ?, ?,?,?,?,?,?);";
	public static final String SELECT_ALL_STUDENTS = "select * from students";
	public static final String DELETE_STUDENTS_SQL = "update students set is_deleted = ? where student_id = ?;";
	public static final String UPDATE_STUDENTS_SQL = "update students set name = ?,email= ?, phone=?, address=?, is_fee_due=?,fee_due_after=?, batch_id=? where student_id = ?;";
	public static final String UPDATE_STUDENT_ATTENDANCE_SQL = "update students set is_fee_due=?, fee_due_after=? where student_id = ?;";
	public static final String UPDATE_IS_FEE_DUE = "update students set is_fee_due=? where student_id = ?;";
	
	public abstract void insertStudent(Student student) throws SQLException;
	public abstract boolean updateStudent(Student student) throws SQLException;
	public abstract boolean deleteStudent(int id) throws SQLException;
	public abstract boolean updateStudentAttendance(int id, int feeDueAfter, boolean isFeeDue) throws SQLException;
	public abstract boolean updateIsFeeDue(int id) throws SQLException;
	public abstract boolean restoreStudent(int id) throws SQLException;
	
}
