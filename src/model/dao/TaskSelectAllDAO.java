package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.TaskBean;

/**
 * @author 小関
 * タスク一覧を取得するDAO
 *
 */
public class TaskSelectAllDAO {

	/**
	 * @return タスク一覧のリスト
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<TaskBean> SelevtAll() throws ClassNotFoundException, SQLException {

		//空のリスト作成
		List<TaskBean> taskList = new ArrayList<TaskBean>();

		//sql文
		String sql = "SELECT * FROM t_task ORDER BY task_id";

		//SQL接続
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){

			//結果を取得
			ResultSet res = pstmt.executeQuery();

			while(res.next()) {

			}

		}
		return taskList;

	}

}
