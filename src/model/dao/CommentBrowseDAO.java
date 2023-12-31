package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.entity.CommentBean;

/**
 * 引数のタスクのコメントをすべて取得するための
 * データベースに接続するDAO
 * @author Koseki
 *
 */
public class CommentBrowseDAO {

	/**
	 * 選択したタスクからそのタスクに応じたコメントを表示させるためのメソッド
	 * @return 引数のタスクIDに応じたコメントのリスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<CommentBean> TaskComment(int taskId) throws ClassNotFoundException, SQLException{
		//空のリスト作成
		List<CommentBean> commentList = new ArrayList<CommentBean>();

		//SQL文の作成
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("  t1.comment_id ");
		sb.append(", t1.task_id ");
		sb.append(", t2.user_id ");
		sb.append(", t1.comment ");
		sb.append(", t1.update_datetime ");
		sb.append(", t2.user_name ");
		sb.append(" FROM ");
		sb.append("  t_comment t1 ");
		sb.append(" JOIN ");
		sb.append("  m_user t2 ");
		sb.append(" ON ");
		sb.append("  t1.user_id = t2.user_id ");
		sb.append(" WHERE");
		sb.append("  t1.task_id = ? ");
		sb.append(" ORDER BY t1.comment_id ");
		String sql = sb.toString();

		//SQLconnect
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			//プレースホルダ
			pstmt.setInt(1, taskId);
			//一覧を取得
			ResultSet res = pstmt.executeQuery();

			//リストにコメントリストを入れる
			while(res.next()) {
				int commentId= res.getInt("comment_id");
				int task = res.getInt("task_id");
				String userId = res.getString("user_id");
				String userName = res.getString("user_name");
				String com = res.getString("comment");
				LocalDateTime date = res.getTimestamp("update_datetime").toLocalDateTime();
				DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

				CommentBean comment = new CommentBean();
				comment.setCommentId(commentId);
				comment.setTaskId(task);
				comment.setCommentUser(userId);
				comment.setCommentUserName(userName);
				comment.setCommentContent(com);
				comment.setCommentDate(date.format(f));

				commentList.add(comment);
			}
		}
		return commentList;
	}
}
