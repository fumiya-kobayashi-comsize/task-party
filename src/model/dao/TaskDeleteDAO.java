package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * データベースからタスクを削除するDAO
 * @author Arakawa
 *
 */
public class TaskDeleteDAO {
	/**
	 * 引数にしたタスクIDのタスクをデータベースから削除し、削除回数を戻り値として返すメソッド
	 * @param タスクID
	 * @return 削除回数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int deleteTask(int taskId) throws ClassNotFoundException, SQLException {
		int count = 0;
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement("DELETE FROM t_task WHERE task_id = ?")){
			pstmt.setInt(1, taskId);
			count = pstmt.executeUpdate();
		}
		return count;

	}
}
