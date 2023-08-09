package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.UserBean;

public class MakeListDAOTest {
	/**
	 * 全てのカテゴリーテーブルを取得するメソッドのテスト
	 */
	@Test
	void selectAllCategory() {
		List<CategoryBean> categoryList = null;
		MakeListDAO listDAO = new MakeListDAO();

		//ファイル読み込みで使用する３つのクラス
		FileInputStream fi = null;
		InputStreamReader is = null;
		BufferedReader br = null;

		try {
			categoryList = listDAO.selectAllCategory();
			//読み込みファイルのインスタンス生成
			//ファイル名を指定する
			fi = new FileInputStream("task_db.m_category.csv");
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
					assertEquals(Integer.parseInt(data[0]), categoryList.get(i - 1).getCategoryId());
					assertEquals(data[1], categoryList.get(i - 1).getCategoryName());
				}
				//行数のインクリメント
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

	/**
	 * 全てのユーザーテーブルを取得するメソッドのテスト
	 */
	@Test
	void selectAllUser() {
		List<UserBean> userList = null;
		MakeListDAO listDAO = new MakeListDAO();

		//ファイル読み込みで使用する３つのクラス
		FileInputStream fi = null;
		InputStreamReader is = null;
		BufferedReader br = null;

		try {
			userList = listDAO.selectAllUser();
			//読み込みファイルのインスタンス生成
			//ファイル名を指定する
			fi = new FileInputStream("task_db.m_user.csv");
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
					assertEquals(data[0], userList.get(i - 1).getUserId());
					assertEquals(data[2], userList.get(i - 1).getUserName());
				}
				//行数のインクリメント
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

	/**
	 * 全てのステータスを取得するメソッドのテスト
	 */
	@Test
	void selectAllStatus() {
		List<StatusBean> statusList = null;
		MakeListDAO listDAO = new MakeListDAO();

		//ファイル読み込みで使用する３つのクラス
		FileInputStream fi = null;
		InputStreamReader is = null;
		BufferedReader br = null;

		try {
			statusList = listDAO.selectAllStatus();
			//読み込みファイルのインスタンス生成
			//ファイル名を指定する
			fi = new FileInputStream("task_db.m_status.csv");
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
					assertEquals(data[0], statusList.get(i - 1).getStatusCode());
					assertEquals(data[1], statusList.get(i - 1).getStatusName());
				}
				//行数のインクリメント
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