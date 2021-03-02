<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" 
integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<title>Insert title here</title>
</head>
<body>

<table class="table table-striped table-bordered table-hover">
       
      		<tr>
			<td>번호</td>
			<td>이름</td>
			<td>제목</td>
			<td>날짜</td>
			<td>히트</td>
		</tr>
		<c:forEach items="${list}" var="dto">
		<tr>
			<td>${dto.bId}</td>
			<td>${dto.bName}</td>
			<td>
				<c:forEach begin="1" end="${dto.bIndent}">-</c:forEach>
				<a href="board/content_view?bId=${dto.bId}">${dto.bTitle}</a></td>
			<td>${dto.bDate}</td>
			<td>${dto.bHit}</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5"> <a href="write_view">글작성</a> </td>
		</tr>
	</table>
	<div>
	<ul class="pagination justify-content-center">
			<c:if test="${pageMaker.prev}">
			<span>
				<a href="board${pageMaker.makeQuery(pageMaker.startPage - 1) }">«</a>
			</span>
			</c:if>
			
			<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
				<c:out value="${pageMaker.cri.pageNum == idx?'':''}" /> 
				<span>
					<a href="board${pageMaker.makeQuery(idx)}">${idx}</a>
				</span>
			</c:forEach>
			
			<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
			<span>
				<a href="board${pageMaker.makeQuery(pageMaker.endPage +1) }">»</a>
			</span>
			</c:if><br>
	</ul>
	</div>
		
</body>
</html>