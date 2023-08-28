package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.TaskBean;

/**
 * データベースのタスクを更新するDAO
 * @author Arakawa
 *
 */
public class TaskUpdateDAO {
	/**
	 * 引数にしたTaskBeanの値にタスクを更新するメソッド
	 * @param TaskBean
	 * @return 更新回数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int updateTask(TaskBean task) throws ClassNotFoundException, SQLException {
		int count = 0;
		String sql = "UPDATE t_task SET task_name = ?, category_id = ?, start_date=?, limit_date = ?, user_id = ?, status_code = ?, memo = ? WHERE task_id = ?";
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, task.getTaskName());
			pstmt.setInt(2, task.getCategoryId());
			if (task.getStartDate() != null) {
				pstmt.setDate(3, Date.valueOf(task.getStartDate()));
			} else {
				pstmt.setDate(3, null);
			}
			if (task.getLimitDate() != null) {
				pstmt.setDate(4, Date.valueOf(task.getLimitDate()));
			} else {
				pstmt.setDate(4, null);
			}
			pstmt.setString(5, task.getUserId());
			pstmt.setString(6, task.getStatusCode());
			pstmt.setString(7, task.getMemo());
			pstmt.setInt(8, task.getTaskId());
			count = pstmt.executeUpdate();
		}
		return count;
	}
}
