package studentmanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import studentmanagement.model.Student;
import studentmanagement.service.StudentInterface;

/* This class accesses MySQL Database (specifically, the students table inside of it) 
 * by using PreparedStatements to make user changes permanent in the database. 
 */
public class StudentDAO implements StudentInterface{
	
	private static String jdbcURL = "jdbc:mysql://localhost:3306/teacherassistant?serverTimezone=UTC&useLegacyDatetimeCode=false&allowPublicKeyRetrieval=true&useSSL=false";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "riddhi";

	//prepared statement
	private static final String INSERT_STUDENTS_SQL = "INSERT INTO students"
			+ "  (name, email, phone, address, is_fee_due,fee_due_after, batch_id, is_deleted) VALUES "
			+ " (?, ?, ?,?,?,?,?,?);";


	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	/* Uses the PreparedStatement UPDATE_STUDENTS_SQL and replace all of the ?
	 * with actual values to execute it in MySQL. 
	 * inserts a new student into the database
	 */
	
	public void insertStudent(Student student) throws SQLException {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENTS_SQL)) {
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getEmail());
			preparedStatement.setString(3, student.getPhone());
			preparedStatement.setString(4, student.getAddress());
			preparedStatement.setBoolean(5, student.getIsFeeDue());
			preparedStatement.setInt(6, student.getFeeDueAfter());
			preparedStatement.setInt(7, student.getBatchNumber());
			preparedStatement.setBoolean(8, student.getIsDeleted());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	
	
	public boolean updateStudent(Student student) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENTS_SQL);) {
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getEmail());
			preparedStatement.setString(3, student.getPhone());
			preparedStatement.setString(4, student.getAddress());
			preparedStatement.setBoolean(5, student.getIsFeeDue());
			preparedStatement.setInt(6, student.getFeeDueAfter());
			preparedStatement.setInt(7, student.getBatchNumber());
			preparedStatement.setInt(8, student.getStudentId());
			
			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	
	//get all student's information from MySQL to be able to display it
	public static List<Student> selectAllStudents() {
		List<Student> student = new ArrayList<>();
		
		// Establishing a Connection
		try (Connection connection = getConnection();

				// Creating a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);) {
			// Executing the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Processing the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("student_id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				boolean isFeeDue = rs.getBoolean("is_fee_due");
				int feeDueAfter = rs.getInt("fee_due_after");
				int batchNumber = rs.getInt("batch_id");
				boolean isDeleted =  rs.getBoolean("is_deleted");
				
				student.add(new Student(id, name, email, phone, address, batchNumber, isFeeDue,feeDueAfter, isDeleted));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return student;
	}

	//access the database to update the boolean (to true) isDeleted for a specific student
	public boolean deleteStudent(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_STUDENTS_SQL);) {
			statement.setBoolean(1, true);
			statement.setInt(2, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	//access the database to update the boolean (to false) isDeleted for a specific student
	public boolean restoreStudent(int id) throws SQLException {
		boolean rowRestored;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_STUDENTS_SQL);) {
			statement.setBoolean(1, false);
			statement.setInt(2, id);
			rowRestored = statement.executeUpdate() > 0;
		}
		return rowRestored;
	}
	
	private static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

	//access the database to update the boolean isFeeDue and int feeDueAfter for a specific student
	public boolean updateStudentAttendance(int id, int feeDueAfter, boolean isFeeDue) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT_ATTENDANCE_SQL);) {

			preparedStatement.setBoolean(1, isFeeDue);
			preparedStatement.setInt(2, feeDueAfter);
			preparedStatement.setInt(3, id);

			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	//access the database to update the boolean isFeeDue (to false) for a specific student
	public boolean updateIsFeeDue(int id) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_IS_FEE_DUE);) {
			
			preparedStatement.setBoolean(1, false);
			preparedStatement.setInt(2, id);

			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
}
