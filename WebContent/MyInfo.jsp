<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="model.BoardVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript"  src="${pageContext.request.contextPath}/httpRequest.js"></script>
<script type="text/javascript">
function get_Magazine(){
	var param = "command=magazine&u_no="+${login.uno};
	sendRequest("${pageContext.request.contextPath}/DispatcherServlet", 
			param, magazineList, "POST");
}

	function magazineList() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var getMagazine = eval("(" + httpRequest.responseText + ")");
				var magazinelist = document.getElementById('magazinelist');
				for(i=0; i<getMagazine.length; i++){
					var list = document.createElement('div');
					list.appendchild(getMagazine[i].magazine);
				}
			}
		}
	}
	
function makeMagazine(){
	var m_name = document.getElementById('m_name').value;
	var param = "command=makeMagazine&u_no="+${login.uno}+"&m_name="+m_name;
	sendRequest("${pageContext.request.contextPath}/DispatcherServlet", 
			param, makeMagazineResult, "POST");
}

function makeMagazineResult(){
	if (httpRequest.readyState == 4) {
		if (httpRequest.status == 200) {
			alert("매거진 등록 완료");
			
		}
	}	
}
	
</script>
</head>
<body>

${login.id }
<table border="1">
	<tr>
		<td><input type="button" value="Magazine" onclick="return get_Magazine()"></td>
		<td>Friend</td>
		<td>Etc</td>
	</tr>
</table>
<div id="magazinelist"></div>
<div id="makeMagazine">
매거진 이름: <input type="text" id="m_name">
<input type="button" value="등록" onclick="return makeMagazine()"></div>
</body>
</html>