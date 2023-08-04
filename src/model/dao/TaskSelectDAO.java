package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.TaskShowBean;


/**
 * タスク選択DAO
 * @author arakawa
 *
 */
public class TaskSelectDAO {
	/**
	 * @param タスクID
	 * @return TaskShowBean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public TaskShowBean selectTask(int taskId) throws ClassNotFoundException, SQLException{
		TaskShowBean taskShow = new TaskShowBean();
		StringBuilder sqlSb = new StringBuilder();
		sqlSb.append("SELECT ");
		sqlSb.append("  t1.task_id ");
		sqlSb.append(", t1.task_name ");
		sqlSb.append(", t2.category_name ");
		sqlSb.append(", t1.limit_date ");
		sqlSb.append(", t3.user_name ");
		sqlSb.append(", t4.status_name ");
		sqlSb.append(", t1.memo ");
		sqlSb.append("FROM ");
		sqlSb.append(" task_db.t_task t1 ");
		sqlSb.append("LEFT JOIN");
		sqlSb.append(" task_db.m_category t2 ");
		sqlSb.append("ON ");
		sqlSb.append(" t1.category_id = t2.category_id ");
		sqlSb.append("LEFT JOIN ");
		sqlSb.append(" task_db.m_user t3 ");
		sqlSb.append("ON ");
		sqlSb.append(" t1.user_id = t3.user_id ");
		sqlSb.append("LEFT JOIN ");
		sqlSb.append(" task_db.m_status t4 ");
		sqlSb.append("ON ");
		sqlSb.append("t1.status_code = t4.status_code ");
		sqlSb.append("WHERE t1.task_id = ?");
		String sql = sqlSb.toString();

		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, taskId);
			ResultSet res = pstmt.executeQuery();
			if(res.next()) {
				taskShow.setTaskId(taskId);
				taskShow.setTaskName(res.getString("task_name"));
				taskShow.setCategoryName(res.getString("category_name"));
				taskShow.setLimitDate(res.getDate("limit_date").toLocalDate());
				taskShow.setUserName(res.getString("user_name"));
				taskShow.setStatusName(res.getString("status_name"));
				taskShow.setMemo(res.getString("memo"));
			}
		}
		return taskShow;


	}
}
