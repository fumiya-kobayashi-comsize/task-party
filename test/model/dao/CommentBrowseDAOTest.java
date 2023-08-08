package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.entity.CommentBean;

class CommentBrowseDAOTest {

	@Test
	void testTaskComment1() {
		List<CommentBean> commentList = new ArrayList<CommentBean>();
		CommentBrowseDAO commentBrowseDAO = new CommentBrowseDAO();

		//ファイル読み込みで使用する３つのクラス
		FileInputStream fi = null;
		InputStreamReader is = null;
		BufferedReader br = null;

		try {
			commentList = commentBrowseDAO.TaskComment(1);
			//読み込みファイルのインスタンス生成
			//ファイル名を指定する
			fi = new FileInputStream("t_comment.task_id=1.csv");
			is = new InputStreamReader(fi);
			br = new BufferedReader(is);

			//読み込み行
			String line;

			//読み込み行数の管理
			int i = 0;

			//1行ずつ読み込みを行う
			while ((line = br.readLine()) != null) {
				//先頭行は列名
				if (i != 0) {
					//カンマで分割した内容を配列に格納する
					String[] data = line.split(",");
					assertEquals(Integer.parseInt(data[0]), commentList.get(i - 1).getCommentId());
					assertEquals(Integer.parseInt(data[1]), commentList.get(i - 1).getTaskId());
					assertEquals(data[2], commentList.get(i - 1).getCommentUser());
					assertEquals(data[3], commentList.get(i - 1).getCommentContent());
					assertEquals(data[4], commentList.get(i - 1).getCommentDate());
				}
				i++;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	void testTaskComment2() {
		List<CommentBean> commentList = new ArrayList<CommentBean>();
		CommentBrowseDAO commentBrowseDAO = new CommentBrowseDAO();

		//ファイル読み込みで使用する３つのクラス
		FileInputStream fi = null;
		InputStreamReader is = null;
		BufferedReader br = null;

		try {
			commentList = commentBrowseDAO.TaskComment(2);
			//読み込みファイルのインスタンス生成
			//ファイル名を指定する
			fi = new FileInputStream("t_comment.task_id=2.csv");
			is = new InputStreamReader(fi);
			br = new BufferedReader(is);

			//読み込み行
			String line;

			//読み込み行数の管理
			int i = 0;

			//1行ずつ読み込みを行う
			while ((line = br.readLine()) != null) {
				//先頭行は列名
				if (i != 0) {
					//カンマで分割した内容を配列に格納する
					String[] data = line.split(",");
					assertEquals(Integer.parseInt(data[0]), commentList.get(i - 1).getCommentId());
					assertEquals(Integer.parseInt(data[1]), commentList.get(i - 1).getTaskId());
					assertEquals(data[2], commentList.get(i - 1).getCommentUser());
					assertEquals(data[3], commentList.get(i - 1).getCommentContent());
					assertEquals(data[4], commentList.get(i - 1).getCommentDate());
				}
				i++;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
