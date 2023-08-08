package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.TaskBean;

/**
 * タスク登録と既存のカテゴリネーム、ユーザーネーム、ステータスネームを取得
 * @author 根上
 *
 */
public class TaskInsertDAO {

	/**
	 * タスク登録メソッド
	 * @author 根上
	 * @param taskBean
	 * @return	insert成否判定のためのcount
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int insertTask(TaskBean taskBean) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO t_task (task_name,category_id,limit_date,user_id,status_code,memo) VALUES(?,?,?,?,?,?)";
		int count = 0;

		/*StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO t_task ");
		sb.append(" (task_name,category_id ");
		if (taskBean.getLimitDate() != null) {
			sb.append(" ,limit_date ");
		}
		sb.append(" ,user_id,status_code ");
		if (!taskBean.getMemo().equals("")) {
			sb.append(",memo");
		}
		sb.append(") VALUES(?,?");
		if (taskBean.getLimitDate() != null) {
			sb.append(",?");
		}
		sb.append(",?,?");
		if (!taskBean.getMemo().equals("")) {
			sb.append(",?");
		}
		sb.append(")");
//		String sql = sb.toString();
*/
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, taskBean.getTaskName());
			pstmt.setInt(2, taskBean.getCategoryId());

			if (taskBean.getLimitDate() != null) {
				pstmt.setDate(3, Date.valueOf(taskBean.getLimitDate()));
			} else {
				pstmt.setDate(3, null);
			}
			pstmt.setString(4, taskBean.getUserId());
			pstmt.setString(5, taskBean.getStatusCode());
			pstmt.setString(6, taskBean.getMemo());

			count = pstmt.executeUpdate();
		}

		return count;
	}
}
