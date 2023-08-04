package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.entity.TaskShowBean;

/**
 * @author 小関
 * タスク一覧を取得するDAO
 *
 */
public class TaskSelectAllDAO {

	/**
	 * タスク一覧のリストを作成し、返します。
	 * @return タスク一覧のリスト
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<TaskShowBean> SelectAll() throws ClassNotFoundException, SQLException {

		//空のリスト作成
		List<TaskShowBean> taskList = new ArrayList<TaskShowBean>();

		//sql文
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("  t1.task_id ");
		sb.append(", t1.task_name ");
		sb.append(", t2.category_name ");
		sb.append(", t1.limit_date ");
		sb.append(", t3.user_name ");
		sb.append(", t4.status_name ");
		sb.append(", t1.memo ");
		sb.append("FROM ");
		sb.append(" task_db.t_task t1 ");
		sb.append("LEFT JOIN");
		sb.append(" task_db.m_category t2 ");
		sb.append("ON ");
		sb.append(" t1.category_id = t2.category_id ");
		sb.append("LEFT JOIN ");
		sb.append(" task_db.m_user t3 ");
		sb.append("ON ");
		sb.append(" t1.user_id = t3.user_id ");
		sb.append("LEFT JOIN ");
		sb.append(" task_db.m_status t4 ");
		sb.append("ON ");
		sb.append("t1.status_code = t4.status_code ");
		sb.append("ORDER BY t1.task_id");
		String sql = sb.toString();

		//SQL接続
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){

			//一覧を取得
			ResultSet res = pstmt.executeQuery();

			//リストに一覧を挿入
			while(res.next()) {
				int taskId = res.getInt("task_id");
				String taskName = res.getString("task_name");
				String categoryName = res.getString("category_name");
				LocalDate limitDate = res.getDate("limit_date").toLocalDate();
				String userName = res.getString("user_name");
				String statusName = res.getString("status_name");
				String memo = res.getString("memo");

				TaskShowBean task = new TaskShowBean();
				task.setTaskId(taskId);
				task.setTaskName(taskName);
				task.setCategoryName(categoryName);
				task.setLimitDate(limitDate);
				task.setUserName(userName);
				task.setStatusName(statusName);
				task.setMemo(memo);

				taskList.add(task);
			}
		}
		return taskList;

	}

}
