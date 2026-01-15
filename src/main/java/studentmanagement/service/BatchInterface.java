package studentmanagement.service;

import java.sql.SQLException;
import studentmanagement.model.Batch;

/* This class is an interface with prepared statements (used for MYSQL data base) as
 * variables and important method headers executed by Batch DAO. 
 */

public interface BatchInterface {
	public static final String SELECT_ALL_BATCHES = "select * from batches";
	public static final String DELETE_BATCHES_SQL = "update batches set is_deleted = ? where batch_id = ?;";
	public static final String UPDATE_BATCHES_SQL = "update batches set day=?, start_time=?, end_time=?, homework=?, level=?, lesson_plan=?,is_deleted=? where batch_id = ?;";

	public abstract void insertBatch(Batch batch) throws SQLException;
	public abstract boolean updateBatch(Batch batch) throws SQLException;
	public abstract boolean deleteBatch(int id) throws SQLException;
	public abstract boolean restoreBatch(int id) throws SQLException;
}
