package studentmanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import studentmanagement.model.Batch;
import studentmanagement.service.BatchInterface;

/* This class accesses MySQL Database (specifically, the batches table inside of it) 
 * by using PreparedStatements to make user changes permanent in the database. 
 */
public class BatchDAO implements BatchInterface{
	
	private static String jdbcURL = "jdbc:mysql://localhost:3306/teacherassistant?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "riddhi";

	//static prepared statement
	private static final String INSERT_BATCHES_SQL = "INSERT INTO batches" + "  (day, start_time, end_time, homework, level, lesson_plan,is_deleted) VALUES "
			+ " (?,?, ?, ?,?,?,?);";


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
	
	/*Uses the PreparedStatement INSERT_BATCHES_SQL and replace all of the ? with 
	 * actual values to execute in MySQL. 
	 */
	public void insertBatch(Batch batch) throws SQLException {
		// try-with-resource statement will automatically close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BATCHES_SQL)) {
			preparedStatement.setString(1, batch.getDay());
			preparedStatement.setTime(2, batch.getStartTime());
			preparedStatement.setTime(3, batch.getEndTime());
			preparedStatement.setString(4, batch.getHomework());
			preparedStatement.setInt(5, batch.getLevel());
			preparedStatement.setString(6, batch.getLessonPlan());
			preparedStatement.setBoolean(7, batch.getIsDeleted());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	
	public boolean updateBatch(Batch batch) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BATCHES_SQL);) {
			preparedStatement.setString(1, batch.getDay());
			preparedStatement.setTime(2, batch.getStartTime());
			preparedStatement.setTime(3, batch.getEndTime());
			preparedStatement.setString(4, batch.getHomework());
			preparedStatement.setInt(5, batch.getLevel());
			preparedStatement.setString(6, batch.getLessonPlan());
			preparedStatement.setBoolean(7, batch.getIsDeleted());
			preparedStatement.setInt(8, batch.getBatchId());

			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	

	//get all batch's information from MySQL to be able to display it
	public static List<Batch> selectAllBatches() {

		List<Batch> batch = new ArrayList<>();
		// Establishing a Connection
		try (Connection connection = getConnection();

				//Creating a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BATCHES);) {
			//Executing the query
			ResultSet rs = preparedStatement.executeQuery();

			//Processing the ResultSet object
			while (rs.next()) {
				int id = rs.getInt("batch_id");
				String day = rs.getString("day");
				Time startTime = rs.getTime("start_time");
				Time endTime = rs.getTime("end_time");
				String homework = rs.getString("homework");
				int level = rs.getInt("level");
				String lessonPlan = rs.getString("lesson_plan");
				boolean isDeleted = rs.getBoolean("is_deleted");
				batch.add(new Batch(id, day, startTime, endTime, homework, level, lessonPlan, isDeleted));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return batch;
	}

	
	//access the database to update the boolean (to true) isDeleted for a specific batch
	public boolean deleteBatch(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_BATCHES_SQL);) {
			statement.setBoolean(1, true);
			statement.setInt(2, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	//access the database to update the boolean (to false) isDeleted for a specific batch
	public boolean restoreBatch(int id) throws SQLException {
		boolean rowRestored;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_BATCHES_SQL);) {
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
	
}
