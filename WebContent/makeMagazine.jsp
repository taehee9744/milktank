<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
var m_name = document.getElementById('m_name');
function makeMagazine(){
	
}
</script>
</head>
<body>
<form action="DispatcherServlet?command=makeMagazine&u_no=${login.uno }" method="post">
매거진 이름: <input type="text" id="m_name">
<input type="submit" value="만들기" onclick="return makeMagazine()">
</form>
</body>
</html>