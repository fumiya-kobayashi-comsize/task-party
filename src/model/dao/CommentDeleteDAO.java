package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author 根上
 *
 */
public class CommentDeleteDAO {

	/**
	 * コメント 削除メソッド
	 * @author 根上
	 * @param commentId
	 * @returnコメントを削除できたかを確認する変数count
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int deleteComment(int commentId) throws ClassNotFoundException, SQLException {
		int count = 0;
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement("DELETE FROM t_comment WHERE comment_id = ?")){
			pstmt.setInt(1, commentId);
			count = pstmt.executeUpdate();
		}
		return count;

	}
}
