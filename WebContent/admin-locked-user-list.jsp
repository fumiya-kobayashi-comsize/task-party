<%@page import="model.entity.UserBean"%>
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
	<%
		List<UserBean> lockedUserList = (List<UserBean>) request.getAttribute("lockedUserList");
	%>
	<div align="center">
		<h1>ログインをロックされたユーザー</h1>
		<hr>
		<table>
			<tr>
				<th>ユーザID</th>
				<th>ユーザ名</th>
			</tr>
			<%for(UserBean lockedUser : lockedUserList){ %>
			<tr>
				<td><%=lockedUser.getUserId() %></td>
				<td><%=lockedUser.getUserName() %></td>
				<td>
					<form action = "AdminUnlockUserServlet" method = "POST">
					<input type = "hidden" name = "unlock" value = <%=lockedUser.getUserId() %>>
						<input type = "submit" value = "解除">
					</form>
			</tr>
			<%} %>
		</table>
		<table>
			<tr>
				<th>
					<form action="admin-management.jsp">
						<input type="submit" value="管理者用画面に戻る">
					</form>
				</th>
			</tr>
		</table>
	</div>
</body>
</html>