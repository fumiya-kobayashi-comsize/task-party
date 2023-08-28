<%@page import="java.time.DayOfWeek"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.List, java.time.LocalDate, model.entity.UserBean,
    model.entity.TaskBean, java.time.format.DateTimeFormatter, java.time.format.TextStyle, java.util.Locale"%>
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
	LocalDate date = (LocalDate)session.getAttribute("date");
	Locale locale = Locale.JAPANESE;
%>
<body>
	<div class="date_changer">
	<div class="inline">
	<form action="EmployeeCalendarServlet" method="post">
	<input type = "hidden" name = "date_change" value = prev>
	<input type = "submit" value = "PREV">
	</form>
	<%=date.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日(E)", locale)) %>
	<form action="EmployeeCalendarServlet" method="post">
	<input type = "hidden" name = "date_change" value = next>
	<input type = "submit" value = "NEXT">
	</form>
	</div>
	</div>
	<table>
		<tr>
			<th rowspan="2">従業員</th>
			<th colspan="7"><%=date.format(DateTimeFormatter.ofPattern("yyyy年MM月", locale)) %></th>
		</tr>
		<tr>
			<%
				for(int i = 0; i < 7; i++){
					if(date.plusDays(i).getDayOfWeek() == DayOfWeek.SATURDAY){
			%>
			<th class = "saturday"><%=date.plusDays(i).getDayOfMonth() %><br>(<%=date.plusDays(i).getDayOfWeek().getDisplayName(TextStyle.SHORT, locale) %>)</th>
			<%
					}else if(date.plusDays(i).getDayOfWeek() == DayOfWeek.SUNDAY){
			%>
			<th class = "sunday"><%=date.plusDays(i).getDayOfMonth() %><br>(<%=date.plusDays(i).getDayOfWeek().getDisplayName(TextStyle.SHORT, locale) %>)</th>
			<%
					}else{
			%>
			<th><%=date.plusDays(i).getDayOfMonth() %><br>(<%=date.plusDays(i).getDayOfWeek().getDisplayName(TextStyle.SHORT, locale) %>)</th>
			<%
					}
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
				if(isEmptyTaskWeekLists.get(i)[j]){
			%>
			<td>〇</td>
			<%
				}else{
			%>
			<td>×</td>
			<%
				}
			}
			%>
		</tr>
		<%
			}
		%>
	</table>
	<form action="menu.jsp" method="get">
		<input type="submit" value="メニューへ">
	</form>
</body>
</html>