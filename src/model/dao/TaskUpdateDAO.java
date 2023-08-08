package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.TaskBean;

/**
 * タスク更新DAO
 * @author arakawa
 *
 */
public class TaskUpdateDAO {
	/**
	 * @param TaskBean
	 * @return 更新回数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int updateTask(TaskBean task) throws ClassNotFoundException, SQLException {
		int count = 0;
		String sql = "UPDATE t_task SET task_name = ?, category_id = ?, limit_date = ?, user_id = ?, status_code = ?, memo = ? WHERE task_id = ?";
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, task.getTaskName());
			pstmt.setInt(2, task.getCategoryId());
			if (task.getLimitDate() != null) {
				pstmt.setDate(3, Date.valueOf(task.getLimitDate()));
			} else {
				pstmt.setDate(3, null);
			}
			pstmt.setString(4, task.getUserId());
			pstmt.setString(5, task.getStatusCode());
			pstmt.setString(6, task.getMemo());
			pstmt.setInt(7, task.getTaskId());
			count = pstmt.executeUpdate();
		}
		return count;
	}
}
