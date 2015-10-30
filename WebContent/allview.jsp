<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border=1>
	
	<thead>
	
	<tr>
	<td>글번호2345</td><td>제목</td><td>사진</td>
	</tr>
	
	</thead>
	
	<tbody>
	<c:set var="i" value="0"></c:set>
	<c:forEach items="${requestScope.allview }" var="allview">
	
	<tr>
	<c:set var="i" value="${i+1 }"></c:set>
	<%-- <td>${i } </td> --%>
	<td>${allview.p_no }</td>
	<td><a href="DispatcherServlet?command=content&pno=${allview.p_no }&isdetail=true">${allview.title }</a></td>
	<td><img src="${allview.path}" width="300" height="200"/></img></td>
	</tr>
	
	</c:forEach>
	</tbody>
	
	</table>
<a href="insert.jsp">글쓰기</a>
<a href="index.jsp">메인으로</a>

</body>
</html>