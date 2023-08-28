package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import model.entity.TaskBean;

class TaskInsertDAOTest {

	@Test
	void test1InsertTask() {
		TaskBean task = new TaskBean();
		task.setTaskName("サンプルタスク３");
		task.setCategoryId(1);
		task.setStartDate(LocalDate.of(2023, 8, 1));
		task.setLimitDate(LocalDate.of(2023, 8, 31));
		task.setUserId("arakawa");
		task.setStatusCode("50");
		task.setMemo("サンプルメモ３");
		int count = 0;
		TaskInsertDAO dao = new TaskInsertDAO();
		try {
			count = dao.insertTask(task);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertEquals(1, count);

	}

	@Test
	void test2InsertTask() {
		TaskBean task = new TaskBean();
		task.setTaskName("サンプルタスク４");
		task.setCategoryId(1);
		task.setStartDate(null);
		task.setLimitDate(null);
		task.setUserId("test5");
		task.setStatusCode("00");
		task.setMemo("");
		int count = 0;
		TaskInsertDAO dao = new TaskInsertDAO();
		try {
			count = dao.insertTask(task);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertEquals(1, count);

	}

}
