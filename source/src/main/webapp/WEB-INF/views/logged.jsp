<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
	<head>
	    <meta charset="UTF-8">
	</head>
	<body>
		로그인 성공!!<br>
		현재 로그인 ID : ${current} <br>
		<form action="/modify">
			ID : <input type="text" name="id" value="${current}"><br>
			Password : <input type="password" name="password"><br>
			주소 : <input type="text" name="addr" value="${addr}">
			<input type="submit" value="수정하기">
		</form>
		<form action="/logout">
			<input type="submit" value="로그아웃">
		</form>		
		<form action="/deletemember">
			<input type="hidden" name="userid" value="${current}">
			<input type="submit" value="회원탈퇴"> 
		</form>		
		<input type="submit" value="책정보 입력" onClick="javascript:location.replace('/addbook')"> 
		<input type="submit" value="책 리스트 보기" onClick="javascript:location.replace('/list')"> 
	</body>
</html>