package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.entity.TaskBean;

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
	public List<TaskBean> SelectAll() throws ClassNotFoundException, SQLException {

		//空のリスト作成
		List<TaskBean> taskList = new ArrayList<TaskBean>();

		//sql文
		String sql = "SELECT * FROM t_task ORDER BY task_id";

		//SQL接続
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){

			//一覧を取得
			ResultSet res = pstmt.executeQuery();

			//リストに一覧を挿入
			while(res.next()) {
				int taskId = res.getInt("task_id");
				String taskName = res.getString("task_name");
				int categoryId = res.getInt("category_id");
				Date limitDate = res.getDate("limit_date");
				String userId = res.getString("user_id");
				String statusCode = res.getString("status_code");
				String memo = res.getString("memo");
				Date createDate = res.getDate("create_datetime");
				Date updateDate = res.getDate("update_datetime");

				TaskBean task = new TaskBean();
				task.setTaskId(taskId);
				task.setTaskName(taskName);
				task.setCategoryId(categoryId);
				task.setLimitDate(limitDate);
				task.setUserId(userId);
				task.setStatusCode(statusCode);
				task.setMemo(memo);
				task.setCreateDatetime(createDate);
				task.setUpdateDatetime(updateDate);

				taskList.add(task);
			}
		}
		return taskList;

	}

}
