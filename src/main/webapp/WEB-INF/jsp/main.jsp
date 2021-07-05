<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メインメニュー</title>
</head>
<body>
	<div>
		<h1>今日のニュース</h1>
		<table border=1>
			<tr>
				<th>タイトル</th>
				<th>URL</th>
			</tr>
			<c:forEach var="info" items="${ infos }">
				<tr>
					<td>${ info.url }</td>
					<td>${ info.title }</td>
				</tr>
			</c:forEach>
		</table>

	</div>

</body>
</html>