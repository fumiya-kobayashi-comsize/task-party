<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@page import="model.entity.CommentBean"%>
    <%@page import="model.entity.TaskShowBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%String name= (String)session.getAttribute("user_name");
String userId= (String)session.getAttribute("user_id");%>
<%TaskShowBean task = (TaskShowBean)session.getAttribute("task_show"); %>
	<h1>コメント投稿画面</h1><hr>

	<form action="CommentPostServlet"method="POST">
		<table>
			<tr>
				<th>タスク名</th>
				<th>カテゴリ情報</th>
				<th>期限</th>
				<th>担当者情報</th>
				<th>ステータス情報</th>
				<th>メモ</th>
			</tr>
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
			</tr>
			<tr>
				<td>投稿者</td>
				<td><input type="hidden" name="name_id" value=<%=userId %>> <%=name %></td>
			</tr>
			<tr>
				<td>コメント内容</td>
				<td><input type="text" name="comment" value=""></td>
			</tr>
		</table>
		<input type="submit" value="コメントを投稿">
	</form>
	<form action="show-task.jsp"method="POST">
		<input type="submit" value="タスク一覧画面へ">
	</form>
</body>
</html>