<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
	<head>
	    <meta charset="UTF-8">
	</head>
	<script>
		var loginok = '<%=(String)session.getAttribute("loginok")%>';
		if(loginok != "" || loginok != "null") {
			alert("로그인이 되어 있지 않습니다.");
			location.replace("/");
		}
	</script>
	<body>
		책 추가하기<br>
		<form name="calc" method="GET" action="/insertbook">
			책 제목 : <input type="text" name="name"><br>
			ISBN : <input type="text" name="isbn"><br>
			저자명 : <input type="text" name="author"><br>
			발행일 : <input type="date" name="date"><br>
			책 정보 : <textarea name="info"></textarea><br>
			<input type="submit" value="책 추가하기">
		</form>
	</body>
</html>