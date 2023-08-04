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
		<td><%=task.getTaskId() %></td>
		<td><%=task.getTaskName() %></td>
		<td><%=task.getCategoryId() %></td>
		<td><%=task.getLimitDate() %></td>
		<td><%=task.getUserId() %></td>
		<td><%=task.getStatusCode() %></td>
		<td><%=task.getMemo() %></td>
		<td><%=task.getCreateDatetime() %></td>
		<td><%=task.getUpdateDatetime() %></td>
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