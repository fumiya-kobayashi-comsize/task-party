package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.CommentBean;

/**
 * t_commentテーブルにコメントを登録するメソッドを格納するクラス
 * @author Negami
 *
 */
public class CommentPostDAO {

	/**
	 * t_commentテーブルにコメントを登録するメソッド
	 * @param comment
	 * @returnコメントが投稿できたかを判定する変数 count
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int postComment(CommentBean comment) throws ClassNotFoundException, SQLException {
		int count=0;
		String sql="INSERT INTO t_comment (task_id,user_id,comment)VALUES(?,?,?)";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, comment.getTaskId());
			pstmt.setString(2, comment.getCommentUser());
			pstmt.setString(3, comment.getCommentContent());

			count = pstmt.executeUpdate();
		}
		return count;
	}
}
