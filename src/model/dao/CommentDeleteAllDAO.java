package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 特定のtask_idを外部キーとして所有するコメントを全て削除するメソッドを格納するクラス
 * @author Negami
 *
 */
public class CommentDeleteAllDAO {
	/**
	 * 特定のtask_idを外部キーとして所有するコメントを全て削除するメソッド
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
