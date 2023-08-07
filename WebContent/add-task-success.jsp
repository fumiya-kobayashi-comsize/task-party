<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.TaskShowBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>add task success</title>
</head>
<body>
<% TaskShowBean task=(TaskShowBean)session.getAttribute("taskShow"); %>
	<div align="center">
		<h1>タスクの登録に成功しました</h1><hr>
	<h3>次のタスクを登録しました</h3>
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
	<form action="menu.jsp" method="POST">
		<input type="submit" value="メニューへ">
	</form>
	</div>
</body>
</html>