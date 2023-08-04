<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List,model.entity.CategoryBean,model.entity.UserBean,model.entity.StatusBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%List<CategoryBean> categoryList=(List)session.getAttribute("categoryList");
	List<UserBean> userList=(List)session.getAttribute("userList");
	List<StatusBean> statusList=(List)session.getAttribute("statusList");%>
	<div align="center">
		<h1>タスク登録画面</h1>
		<hr>
		<form action="TaskAddServlet" method="POST">
			<table border="1">
				<tr>
					<td>タスク名</td>
					<td><input type="text" name="taskName" maxlength="50"></td>
				</tr>
				<tr>
					<td>カテゴリ名</td>
					<td>

					<select name="categoryId">
						<%for(CategoryBean category:categoryList ){ %>
							<option value=<%=category.getCategoryId() %>><%=category.getCategoryName() %></option>
						<%} %>
					</select>
					</td>
				</tr>
				<tr>
					<td>期限</td>
					<td><input type="date" name="limitDate"></td>
				</tr>
				<tr>
					<td>担当者情報</td>
					<td>
					<select name="userId">
						<%for(UserBean user:userList ){ %>
							<option value=<%=user.getUserId() %>><%=user.getUserName() %></option>
						<%} %>
					</select>
					</td>
				</tr>
				<tr>
					<td>ステータス</td>
					<td>
						<select name="statusCode">
						<%for(StatusBean status:statusList ){ %>
							<option value=<%=status.getStatusCode() %>><%=status.getStatusName() %></option>
						<%} %>
					</select>
					</td>
				</tr>
				<tr>
					<td>メモ</td>
					<td><input type="text" name="memo" maxlength="100"></td>
				</tr>
			</table>
			<input type="submit" value="登録">
		</form>
	</div>
</body>
</html>