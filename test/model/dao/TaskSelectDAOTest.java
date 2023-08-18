package model.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import model.entity.TaskBean;
import model.entity.TaskShowBean;

class TaskSelectDAOTest {

	@Test
	void testSelectTaskShow() {
		TaskSelectDAO dao = new TaskSelectDAO();
		TaskShowBean ts = new TaskShowBean();
		try {
			ts = dao.selectTaskShow(1);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertEquals(1, ts.getTaskId());
		assertEquals("サンプルタスク", ts.getTaskName());
		assertEquals("新商品A:開発プロジェクト", ts.getCategoryName());
		assertEquals(LocalDate.of(2023, 9, 11), ts.getLimitDate());
		assertEquals("管理者ユーザー", ts.getUserName());
		assertEquals("未着手", ts.getStatusName());
		assertEquals("サンプルメモ", ts.getMemo());
	}

	@Test
	void testSelectTask() {
		TaskSelectDAO dao = new TaskSelectDAO();
		TaskBean task = new TaskBean();
		try {
			task = dao.selectTask(1);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertEquals(1, task.getTaskId());
		assertEquals("サンプルタスク", task.getTaskName());
		assertEquals(1, task.getCategoryId());
		assertEquals(LocalDate.of(2023, 9, 11), task.getLimitDate());
		assertEquals("admin", task.getUserId());
		assertEquals("00", task.getStatusCode());
		assertEquals("サンプルメモ", task.getMemo());
	}

}
