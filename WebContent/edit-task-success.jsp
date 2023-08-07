<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "model.entity.TaskShowBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>edit task</title>
</head>
<body>
<%TaskShowBean task = (TaskShowBean)request.getAttribute("task"); %>
	<div align="center">
		<h1>タスクの編集に成功しました</h1><hr>

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
			<td>
				<%if(task.getLimitDate() != null){ %>
					<%=task.getLimitDate() %>
				<%} %>
			</td>
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