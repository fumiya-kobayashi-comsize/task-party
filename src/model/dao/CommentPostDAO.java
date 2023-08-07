package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.CommentBean;

public class CommentPostDAO {

	public int postComment(CommentBean comment) throws ClassNotFoundException, SQLException {
		int count=0;
		String sql="INSERT INTO t_task (task_id,user_id,comment)VALUES(?,?,?)";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, comment.getTaskId());
			pstmt.setString(2, comment.getCommentUser());
			pstmt.setString(3, comment.getCommentContent());
		}
		return count;
	}
}
