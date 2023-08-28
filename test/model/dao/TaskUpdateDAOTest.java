package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import model.entity.TaskBean;

class TaskUpdateDAOTest {

	@Test
	void test1UpdateTask() {
		int count = 0;
		TaskBean task = new TaskBean();
		task.setTaskId(2);
		task.setTaskName("サンプルタスク99");
		task.setCategoryId(1);
		task.setStartDate(LocalDate.of(2023, 8, 8));
		task.setLimitDate(LocalDate.of(2023, 9, 1));
		task.setUserId("user");
		task.setStatusCode("99");
		task.setMemo("サンプルメモ99");
		TaskUpdateDAO dao = new TaskUpdateDAO();
		try {
			count = dao.updateTask(task);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertEquals(1, count);
	}

	@Test
	void test2UpdateTask() {
		int count = 0;
		TaskBean task = new TaskBean();
		task.setTaskId(9);
		task.setTaskName("negami着手中タスク");
		task.setCategoryId(1);
		task.setStartDate(null);
		task.setLimitDate(null);
		task.setUserId("negami");
		task.setStatusCode("50");
		task.setMemo("");
		TaskUpdateDAO dao = new TaskUpdateDAO();
		try {
			count = dao.updateTask(task);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertEquals(1, count);
	}
}
