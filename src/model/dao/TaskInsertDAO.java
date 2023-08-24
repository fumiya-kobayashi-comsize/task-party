package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.TaskBean;

/**
 * タスク登録メソッドを格納
 * @author Negami
 *
 */
public class TaskInsertDAO {

	/**
	 * 新しくタスクを登録するためのメソッド
	 * @param taskBean
	 * @return	insert成否判定のためのcount
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int insertTask(TaskBean taskBean) throws SQLException, ClassNotFoundException {
		int count = 0;

		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO t_task ");
		sb.append(" (task_name,category_id,start_date,limit_date");
		sb.append(" ,user_id,status_code,memo) ");
		sb.append("  VALUES(?,?,?,?,?,?,?)");
		String sql = sb.toString();

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, taskBean.getTaskName());
			pstmt.setInt(2, taskBean.getCategoryId());
			if (taskBean.getStartDate() != null) {
				pstmt.setDate(3, Date.valueOf(taskBean.getStartDate()));
			} else {
				pstmt.setDate(3, null);
			}
			if (taskBean.getLimitDate() != null) {
				pstmt.setDate(4, Date.valueOf(taskBean.getLimitDate()));
			} else {
				pstmt.setDate(4, null);
			}
			pstmt.setString(5, taskBean.getUserId());
			pstmt.setString(6, taskBean.getStatusCode());
			pstmt.setString(7, taskBean.getMemo());

			count = pstmt.executeUpdate();
		}

		return count;
	}
}
