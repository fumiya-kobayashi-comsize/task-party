package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.CategoryBean;

/**
 * タスク登録と既存のカテゴリネーム、ユーザーネーム、ステータスネームを取得
 * @author 根上
 *
 */
public class TaskInsertDAO {

	List<CategoryBean> selectCategoryList() throws SQLException, ClassNotFoundException {
		List<CategoryBean> categoryList = new ArrayList<>();
//		ここから
		String sql = "";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				int categoryId = res.getInt("category_id");
				String categoryName = res.getString("category_name");

				CategoryBean bean = new CategoryBean();
				bean.setCategory_id(categoryId);
				bean.setCategory_name(categoryName);

				categoryList.add(bean);
			}
		}
		return categoryList;
	}

}
