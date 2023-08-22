package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.entity.TaskBean;
import model.entity.TaskShowBean;


/**
 * タスク選択DAO
 * @author arakawa
 *
 */
public class TaskSelectDAO {
	/**
	 * @param タスクID
	 * @return TaskShowBean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public TaskShowBean selectTaskShow(int taskId) throws ClassNotFoundException, SQLException{
		TaskShowBean taskShow = new TaskShowBean();
		StringBuilder sqlSb = new StringBuilder();
		sqlSb.append("SELECT ");
		sqlSb.append("  t1.task_id ");
		sqlSb.append(", t1.task_name ");
		sqlSb.append(", t2.category_name ");
		sqlSb.append(", t1.start_date ");
		sqlSb.append(", t1.limit_date ");
		sqlSb.append(", t3.user_name ");
		sqlSb.append(", t4.status_name ");
		sqlSb.append(", t1.memo ");
		sqlSb.append("FROM ");
		sqlSb.append(" task_db.t_task t1 ");
		sqlSb.append("LEFT JOIN");
		sqlSb.append(" task_db.m_category t2 ");
		sqlSb.append("ON ");
		sqlSb.append(" t1.category_id = t2.category_id ");
		sqlSb.append("LEFT JOIN ");
		sqlSb.append(" task_db.m_user t3 ");
		sqlSb.append("ON ");
		sqlSb.append(" t1.user_id = t3.user_id ");
		sqlSb.append("LEFT JOIN ");
		sqlSb.append(" task_db.m_status t4 ");
		sqlSb.append("ON ");
		sqlSb.append("t1.status_code = t4.status_code ");
		sqlSb.append("WHERE t1.task_id = ?");
		String sql = sqlSb.toString();

		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, taskId);
			ResultSet res = pstmt.executeQuery();
			LocalDate startDate = null;
			LocalDate limitDate = null;
			if(res.next()) {
				taskShow.setTaskId(taskId);
				taskShow.setTaskName(res.getString("task_name"));
				taskShow.setCategoryName(res.getString("category_name"));
				if (res.getDate("start_date") != null) {
					startDate = res.getDate("start_date").toLocalDate();
				}
				if (res.getDate("limit_date") != null) {
					limitDate = res.getDate("limit_date").toLocalDate();
				}
				taskShow.setStartDate(startDate);
				taskShow.setLimitDate(limitDate);
				taskShow.setUserName(res.getString("user_name"));
				taskShow.setStatusName(res.getString("status_name"));
				if(res.getString("memo") != null) {
					taskShow.setMemo(res.getString("memo"));
				} else {
					taskShow.setMemo("");
				}
			}
		}
		return taskShow;


	}

	public TaskBean selectTask(int taskId) throws ClassNotFoundException, SQLException {
		TaskBean task = new TaskBean();
		String sql = "SELECT * FROM t_task WHERE task_id = ?";
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, taskId);
			ResultSet res = pstmt.executeQuery();
			LocalDate startDate = null;
			LocalDate limitDate = null;
			if(res.next()) {
				task.setTaskId(taskId);
				task.setTaskName(res.getString("task_name"));
				task.setCategoryId(res.getInt("category_id"));
				if (res.getDate("start_date") != null) {
					startDate = res.getDate("start_date").toLocalDate();
				}
				if (res.getDate("limit_date") != null) {
					limitDate = res.getDate("limit_date").toLocalDate();
				}
				task.setStartDate(startDate);
				task.setLimitDate(limitDate);
				task.setUserId(res.getString("user_id"));
				task.setStatusCode(res.getString("status_code"));
				if(res.getString("memo") != null) {
					task.setMemo(res.getString("memo"));
				} else {
					task.setMemo("");
				}
			}
		}

		return task;
	}

	/**
	 * 着手中タスク一覧
	 * @author arakawa
	 * @param userId
	 * @return List<TaskBean>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<TaskBean> selectProgressTask(String userId) throws ClassNotFoundException, SQLException {
		List<TaskBean> taskList = new ArrayList<>();
		String sql = "SELECT * FROM t_task WHERE user_id = ? AND status_code = '50'";
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, userId);
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				TaskBean task = new TaskBean();
				LocalDate startDate = null;
				LocalDate limitDate = null;
				task.setTaskId(res.getInt("task_id"));
				task.setTaskName(res.getString("task_name"));
				task.setCategoryId(res.getInt("category_id"));
				if (res.getDate("start_date") != null) {
					startDate = res.getDate("start_date").toLocalDate();
				}
				if (res.getDate("limit_date") != null) {
					limitDate = res.getDate("limit_date").toLocalDate();
				}
				task.setStartDate(startDate);
				task.setLimitDate(limitDate);
				task.setUserId(userId);
				task.setStatusCode(res.getString("status_code"));
				if(res.getString("memo") != null) {
					task.setMemo(res.getString("memo"));
				} else {
					task.setMemo("");
				}
				taskList.add(task);
			}
		}

		return taskList;
	}

	public List<TaskBean> selectProgressTask() throws ClassNotFoundException, SQLException {
		List<TaskBean> taskList = new ArrayList<>();
		String sql = "SELECT * FROM t_task WHERE status_code = '50'";
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				TaskBean task = new TaskBean();
				LocalDate startDate = null;
				LocalDate limitDate = null;
				task.setTaskId(res.getInt("task_id"));
				task.setTaskName(res.getString("task_name"));
				task.setCategoryId(res.getInt("category_id"));
				if (res.getDate("start_date") != null) {
					startDate = res.getDate("start_date").toLocalDate();
				}
				if (res.getDate("limit_date") != null) {
					limitDate = res.getDate("limit_date").toLocalDate();
				}
				task.setStartDate(startDate);
				task.setLimitDate(limitDate);
				task.setUserId(res.getString("user_id"));
				task.setStatusCode(res.getString("status_code"));
				if(res.getString("memo") != null) {
					task.setMemo(res.getString("memo"));
				} else {
					task.setMemo("");
				}
				taskList.add(task);
			}
		}

		return taskList;
	}

	/**
	 * 着手中タスク（引数にしたしたタスクを除く）一覧
	 * @author Arakawa
	 * @param userId
	 * @param taskId
	 * @return List<TaskBean>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<TaskBean> selectOtherProgressTask(String userId, int taskId) throws ClassNotFoundException, SQLException {
		List<TaskBean> taskList = new ArrayList<>();
		String sql = "SELECT * FROM t_task WHERE user_id = ? AND status_code = '50' AND NOT task_id = ?";
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, userId);
			pstmt.setInt(2, taskId);
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				TaskBean task = new TaskBean();
				LocalDate startDate = null;
				LocalDate limitDate = null;
				task.setTaskId(res.getInt("task_id"));
				task.setTaskName(res.getString("task_name"));
				task.setCategoryId(res.getInt("category_id"));
				if (res.getDate("start_date") != null) {
					startDate = res.getDate("start_date").toLocalDate();
				}
				if (res.getDate("limit_date") != null) {
					limitDate = res.getDate("limit_date").toLocalDate();
				}
				task.setStartDate(startDate);
				task.setLimitDate(limitDate);
				task.setUserId(userId);
				task.setStatusCode(res.getString("status_code"));
				if(res.getString("memo") != null) {
					task.setMemo(res.getString("memo"));
				} else {
					task.setMemo("");
				}
				taskList.add(task);
			}
		}

		return taskList;
	}
}
