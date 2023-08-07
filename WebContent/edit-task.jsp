<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List, model.entity.TaskShowBean, model.entity.CategoryBean,
    		model.entity.UserBean, model.entity.StatusBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク編集画面</title>
</head>
<body>
	<%
		List<CategoryBean> categoryList=(List)session.getAttribute("categoryList");
		List<UserBean> userList=(List)session.getAttribute("userList");
		List<StatusBean> statusList=(List)session.getAttribute("statusList");
		TaskShowBean task = (TaskShowBean)session.getAttribute("task");
	%>
	<h2>タスク編集画面</h2>
<hr>
	<h3>次のタスクを編集します。</h3>
<br>
	<form action="TaskEditServlet" method="POST">
		<table>
			<tr>
				<th>タスク名</th>
				<td><input type="text" name="task_name"
					value="<%=task.getTaskName() %>" maxlength="50" required></td>
			</tr>
			<tr>
				<th>カテゴリ情報</th>
				<td><select name="category_id">
				<%
					for(CategoryBean category : categoryList){
						String categoryName = category.getCategoryName();
						if(categoryName.equals(task.getCategoryName())){
				%>
				<option value = "<%=category.getCategoryId() %>" selected><%=categoryName %></option>
				<%
						}else{
				%>
				<option value = "<%=category.getCategoryId() %>"         ><%=categoryName %></option>
				<%
						}
					}
				%>
				</select></td>
			</tr>
			<tr>
				<th>期限</th>
				<td><input type="date" name="limit_date"
					value = "<%=task.getLimitDate()%>"></td>
			</tr>
			<tr>
				<th>担当者情報</th>
				<td><select name="user_id">
				<%
					for(UserBean user:userList){
						String userName = user.getUserName();
						if(userName.equals(task.getUserName())){
				%>
				<option value = "<%=user.getUserId() %>" selected><%=userName %></option>
				<%
						}else{
				%>
				<option value = "<%=user.getUserId() %>"         ><%=userName %></option>
				<%
						}
					}
				%>
				</select></td>
			</tr>
			<tr>
				<th>ステータス情報</th>
				<td><select name="status_code">
				<%
					for(StatusBean status:statusList){
						String statusName = status.getStatusName();
						if(statusName.equals(task.getStatusName())){
				%>
				<option value = "<%=status.getStatusCode() %>" selected><%=statusName %></option>
				<%
						}else{
				%>
				<option value = "<%=status.getStatusCode() %>"         ><%=statusName %></option>
				<%
						}
					}
				%>
				</select></td>
			</tr>
			<tr>
				<th>メモ</th>
				<td><input type="text" name="memo"
					value="<%=task.getMemo() %>" maxlength="100"></td>
			</tr>
		</table>
		<table>
		<tr>
			<td>
					<input type = "hidden" name = "task_id" value = <%=task.getTaskId() %>>
					<input type = "submit" value = "変更する">
			</td>
		</tr>
	</table>
	</form>

</body>
</html>