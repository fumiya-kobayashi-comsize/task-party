<%@page import="model.entity.TaskShowBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%TaskShowBean task = (TaskShowBean)session.getAttribute("task"); %>
	<h2>タスク削除確認画面</h2>
<hr>
	<h3>次のタスクを削除します。</h3>
<br>
	<table>
		<tr>
			<th>タスク名</th>
			<td><%=task.getTaskName() %></td>
		</tr>
		<tr>
			<th>カテゴリ情報</th>
			<td><%=task.getCategoryName() %></td>
		</tr>
		<tr>
			<th>期限</th>
			<td><%=task. getLimitDate()%></td>
		</tr>
		<tr>
			<th>担当者情報</th>
			<td><%=task.getUserName() %></td>
		</tr>
		<tr>
			<th>ステータス情報</th>
			<td><%=task.getStatusName() %></td>
		</tr>
		<tr>
			<th>メモ</th>
			<td><%=task.getMemo()%></td>
		</tr>
	</table>
	<table>
		<tr>
			<td>
				<form action="TaskDeleteServlet" method = "POST">
					<input type = "hidden" name = "task_id" value = <%=task.getTaskId() %>>
					<input type = "submit" value = "削除する">
				</form>
			</td>
			<td>
				<form action="show-task.jsp">
					<input type = "submit" value = "一覧に戻る">
				</form>
		</tr>
	</table>
</body>
</html>