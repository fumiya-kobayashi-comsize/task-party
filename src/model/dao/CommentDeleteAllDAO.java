package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentDeleteAllDAO {
	/**
	 * コメント オール削除メソッド
	 * @author 根上
	 * @param commentId
	 * @returnコメントを削除できたかを確認する変数count
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int deleteAllComment(int taskId) throws ClassNotFoundException, SQLException {
		int count = 0;
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement("DELETE FROM t_comment WHERE task_id = ?")){
			pstmt.setInt(1, taskId);
			count = pstmt.executeUpdate();
		}
		return count;
	}
}
