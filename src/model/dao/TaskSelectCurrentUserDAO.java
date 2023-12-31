package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * ログインしたユーザーが着手しているタスクの確認、現在日時と期限の差分を返す
 * @author Negami
 * @return 現在日時と期限の差分
 */
public class TaskSelectCurrentUserDAO {

	public int selectCurrentUsersTask(String userId) throws ClassNotFoundException, SQLException {
		int currentUsersLimit = 0;

		StringBuilder sqlSb = new StringBuilder();
		sqlSb.append("SELECT ");
		sqlSb.append("  t1.user_id ");
		sqlSb.append(", t2.start_date ");
		sqlSb.append(", t2.limit_date ");
		sqlSb.append(", t2.status_code");
		sqlSb.append(" FROM ");
		sqlSb.append("  task_db.m_user t1 ");
		sqlSb.append(" LEFT JOIN ");
		sqlSb.append(" task_db.t_task t2 ");
		sqlSb.append(" ON ");
		sqlSb.append(" t1.user_id = t2.user_id ");
		sqlSb.append("WHERE t2.status_code = 50 and t2.user_id = ?");
		String sql = sqlSb.toString();

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			LocalDate currentDate = LocalDate.now();

			pstmt.setString(1, userId);
			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				LocalDate startDate = null;
				LocalDate limitDate = null;
				if (res.getDate("start_date") != null) {
					startDate = res.getDate("start_date").toLocalDate();
				}
				if (res.getDate("limit_date") != null) {
					limitDate = res.getDate("limit_date").toLocalDate();
				}
				// 開始日と期限日が決まっている場合
				if (startDate != null && limitDate != null) {
					if (startDate.isEqual(currentDate) || startDate.isBefore(currentDate)) {
						currentUsersLimit = (int) ChronoUnit.DAYS.between(currentDate, limitDate);
					}
					//期限日のみ決まってる場合
				} else if (startDate == null && limitDate != null) {
					currentUsersLimit = (int) ChronoUnit.DAYS.between(currentDate, limitDate);
				}
			}
		}
		return currentUsersLimit;
	}
}