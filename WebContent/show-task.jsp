<%@page import="model.entity.TaskShowBean"%>
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
<%List<TaskShowBean> taskList = (List<TaskShowBean>)session.getAttribute("taskList"); %>
<h2>タスク一覧</h2>
<hr>
<table>
	<tr>
		<th>タスク名</th>
		<th>カテゴリ情報</th>
		<th>期限</th>
		<th>担当者情報</th>
		<th>ステータス情報</th>
		<th>メモ</th>
	</tr>
	<%for(TaskShowBean task : taskList){ %>
	<tr>
		<td><%=task.getTaskName() %></td>
		<td><%=task.getCategoryName() %></td>
		<td>
			<%if(task.getLimitDate()!=null){ %>
				<%=task.getLimitDate() %>
			<% }%>
		</td>

		<td><%=task.getUserName() %></td>
		<td><%=task.getStatusName() %></td>
		<td><%=task.getMemo() %></td>
		<td>
			<form action = "TaskEditServlet" method = "GET">
				<input type = "hidden" name = "task_id" value = <%=task.getTaskId() %>>
				<input type = "submit" value = 編集>
			</form>
		</td>
		<td>
			<form action = "TaskDeleteServlet" method = "GET">
				<input type = "hidden" name = "task_id" value = <%=task.getTaskId() %>>
				<input type = "submit" value = 削除>
			</form>
		</td>
		<td>
			<form action = "CommentBrowseServlet" method = "POST">
				<input type = "hidden" name = "task_id" value = <%=task.getTaskId() %>>
				<input type = "submit" value = コメント閲覧>
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