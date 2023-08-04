<%@page import="model.entity.TaskBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%List<TaskBean> taskList = (List<TaskBean>)request.getAttribute("taskList"); %>
<h2>タスク一覧</h2>
<hr>
<table>
	<tr>
		<th>タスクID</th>
		<th>タスク名</th>
		<th>カテゴリID</th>
		<th>期限</th>
		<th>ユーザID</th>
		<th>ステータスコード</th>
		<th>メモ</th>
		<th>登録日時</th>
		<th>更新日時</th>
	</tr>
	<%for(TaskBean task : taskList){ %>
	<tr>
		<td><%=task.getTask_id() %></td>
		<td><%=task.getTask_name() %></td>
		<td><%=task.getCategory_id() %></td>
		<td><%=task.getLimit_date() %></td>
		<td><%=task.getUser_id() %></td>
		<td><%=task.getStatus_code() %></td>
		<td><%=task.getMemo() %></td>
		<td><%=task.getCreate_datetime() %></td>
		<td><%=task.getUpdate_datetime() %></td>
		<td>
			<form action = "TaskEditServlet" method = "POST">
				<input type = "submit" value = 編集>
			</form>
		</td>
		<td>
			<form action = "TaskDeleteServlet" method = "POST">
				<input type = "submit" value = 削除>
			</form>
		</td>
	</tr>
	<%} %>
</table>
	<form action="menu.jsp">
		<input type = "submit" value = "メニューに戻る">
	</form>
</body>
</html>