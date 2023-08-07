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
 * コメント表示DAO
 * @author こせき
 *
 */
public class CommentBrowseDAO {

	public List<CommentBean> TaskComment(int taskId) throws ClassNotFoundException, SQLException{
		//空のリスト作成
		List<CommentBean> commentList = new ArrayList<CommentBean>();

		//SQL文の作成
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("  t2.user_name ");
		sb.append(", t1.comment ");
		sb.append(", t1.update_datetime ");
		sb.append(" FROM ");
		sb.append("  t_comment t1 ");
		sb.append(" JOIN ");
		sb.append("  m_user t2 ");
		sb.append(" ON ");
		sb.append("  t1.user_id = t2.user_id ");
		sb.append(" WHERE");
		sb.append("  t1.task_id = " + taskId);
		sb.append(" ORDER BY t1.comment_id ");
		String sql = sb.toString();

		//SQLconnect
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			//一覧を取得
			ResultSet res = pstmt.executeQuery();

			//リストにコメントリストを入れる
			while(res.next()) {
				String user = res.getString("user_name");
				String com = res.getString("comment");
				LocalDateTime date = res.getTimestamp("update_datetime").toLocalDateTime();
				DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

				CommentBean comment = new CommentBean();
				comment.setCommentUser(user);
				comment.setCommentContent(com);
				comment.setCommentDate(date.format(f));

				commentList.add(comment);
			}
		}
		return commentList;
	}
}
