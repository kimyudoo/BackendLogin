<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<body>
		책 리스트 보기<br><br>
		<table>
			<tr>
				<td colspan=2>
					<form action="/search">
						<input type="text" name="searchText">
						<input type="submit" value="검색">
					</form>	
				</td>
			</tr>
			<c:forEach items="${bookList}" var="item">
				<tr>
					<td>도서명</td>
					<td>${item.name}</td>
				</tr>
				<tr>
					<td>저자
					</td>
					<td>${item.author}</td>
				</tr>
				<tr>
					<td>발행일
					</td>
					<td>${item.publishDate}</td>
				</tr>
				<tr>
					<td>ISBN</td>
					<td>${item.isbn}</td>
				</tr>
				<tr>
					<td>상세 정보</td>
					<td>${item.info}</td>
				</tr>
				<tr>
					<td>삭제하기</td>
					<td>
						<form action="/deleteBook">
							<input type="hidden" name="id" value="${item.id}">
							<input type="submit" value="삭제">
						</form>
					</td>
				<tr>
					<td bgcolor="black" height="5px" colspan=2> </td>
				</tr>
			</c:forEach>
		</table>
		<input type="button" value="홈으로" onclick="javascript:location.replace('/')">
	</body>
</html>