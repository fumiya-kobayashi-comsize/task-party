<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.List, java.time.LocalDate, model.entity.UserBean, model.entity.TaskBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>従業員カレンダー早見表</title>
<link rel="stylesheet" href="static/css/calendar.css">
</head>
<%
	List<UserBean> userList=(List)session.getAttribute("user_list");
	List<boolean[]> isEmptyTaskWeekLists = (List)session.getAttribute("is_empty_list");
	LocalDate date = (LocalDate)request.getAttribute("date");
%>
<body>
	<table>
		<tr>
			<th rowspan="2">従業員</th>
			<th colspan="7"><%=date.getMonthValue() %>月</th>
		</tr>
		<tr>
			<%
				for(int i = 0; i < 7; i++){
			%>
			<th><%=date.plusDays(i).getDayOfMonth() %><br><%=date.plusDays(i).getDayOfWeek() %></th>
			<%
				}
			%>
		</tr>
		<%
			for(int i = 0; i < userList.size(); i++){
		%>
		<tr>
			<td><%=userList.get(i).getUserName() %></td>
			<%
			for(int j = 0; j < 7; j++){
			%>
			<td><%=isEmptyTaskWeekLists.get(i)[j] %></td>
			<%
			}
			%>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>