<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.BoardVO"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript"  src="${pageContext.request.contextPath}/httpRequest.js"></script>
<script type="text/javascript" >
function deletePro(){
	var flag = confirm("삭제 하시겠습니까?");
	if(flag){
		location.href="DispatcherServlet?command=deletecontent"+
				"&pno=${requestScope.content.p_no}";
	}
}

function updatePro(){
	var flag = confirm("수정 하시겠습니까?");
	if(flag){
		location.href="DispatcherServlet?command=content"
				+"&pno=${requestScope.content.p_no}"
				+"&isdetail=false";
	}
}

/*///////////////////////////////////////////////////////////////////////////////// */
//페이지 로드(시작)되자 마자 실행될 함수 등록
//페이지가 시작되자마자 db에 있는 모든 글을 읽어와 화면에 출력하는 동작 수행
//ajax로 controller에 articleList요청
	
	window.onload=function(){
		likeList();
		
	}
	function list(){
		var param = "command=articleList&p_no="+${requestScope.content.p_no};
		sendRequest("${pageContext.request.contextPath}/DispatcherServlet", 
				param, listResult, "POST");
	}
	
	//위 ajax요청의 결과처리 함수
	function listResult(){
		if(httpRequest.readyState==4){
			if(httpRequest.status==200){
				
				//ajax 결과인 json을 받아온다.
				//결과는 []형태의 배열로 eval()이 이를 배열로 변환해줌. 즉 commentList는 모든 글이 저장된 배열
				var commentList = eval("(" + httpRequest.responseText + ")");
				//모든 글들을 출력할 영역인 id가 commentList인 div 객체를 읽어와
				var listDiv = document.getElementById('commentList');
				//배열 commentList의 길이만큼 반복함
				for(i=0;i<commentList.length;i++){
					//함수 makeCommentView()를 호출하여 글 하나를 출력할 div를 생성하여 글 내용을 출력함
					var commentDiv = makeCommentView(commentList[i]);
					//위에서 글 하나 출력한 div를 id가 commentList인 div의 자식으로 추가
					listDiv.appendChild(commentDiv);
				}
			}
		}
	}

	function like(){
		var params = "command=likeaddsub&p_no="+${requestScope.content.p_no };
		sendRequest("${pageContext.request.contextPath}/DispatcherServlet", params ,likeResult,"POST");
	}
	 function likeList(){
		var params = "command=likelist&p_no="+${requestScope.content.p_no };
		sendRequest("${pageContext.request.contextPath}/DispatcherServlet", params ,likeResult,"POST");
	} 
	function likeResult(){
		if(httpRequest.readyState==4){
			if(httpRequest.status==200){
				var comment = eval("(" + httpRequest.responseText + ")");
				var likeDiv = document.getElementById('like');
				var like = '<strong>'+comment.like+'</strong>';
				likeDiv.innerHTML = like;
				//alert(comment.likestate);
				var likeimg = document.getElementById('likeimg');
				
				if(comment.likestate=="true"){
					//alert("1"+comment.likestate);
					likeimg.src = "img/like2.PNG";
				}else{
					//alert("2"+comment.likestate);
					likeimg.src = "img/like1.PNG";
				}
				 
				


			}
		}
	}
	
	//새글을 등록한는 함수
	//작성폼에 입력한 이름, 내용을 파라메터로하여 서버에 비동기 요청
	function addComment() {
		var content = document.addForm.content.value;
		var param = "command=addArticle&p_no="+${requestScope.content.p_no }+"&content="+encodeURIComponent(content)+"";
		sendRequest("${pageContext.request.contextPath}/DispatcherServlet", 
				param, addResult1, "POST");
	}
	
	//새글 등록의 결과 함수
	function addResult1(){	
		if(httpRequest.readyState==4){
			if(httpRequest.status==200){
				
				//새로 추가된 글을 json객체로 받음. 이를 eval() 함수로 객체로 변환하여 comment에 저장
				var comment = eval("(" + httpRequest.responseText + ")");
				//모든 글들을 출력할 영역인 id가 commentList인 div 객체를 읽어와
				var listDiv = document.getElementById('commentList');
				//함수 makeCommentView()를 호출하여 글 하나를 출력할 div를 생성하여 글 내용을 출력함
				var commentDiv = makeCommentView(comment);
				//위에서 글 하나 출력한 div를 id가 commentList인 div의 자식으로 추가
				listDiv.appendChild(commentDiv);
				
				//새글 등록폼을 초기화
		
				document.addForm.content.value = '';
				
				alert("등록했습니다!["+comment.num+"]");
			}
		}
	}
	//
	//글 하나를 출력할 div를 생성하여 리턴하는 함수
	function makeCommentView(comment) {
		//div를 하나 생성한다.
		var commentDiv = document.createElement('div');
		
		//생성한 div의 id를 c글번호로 지정한다.
		commentDiv.setAttribute('id', 'c'+comment.num);
		
		//생성한 div에 출력할 내용을 만든다. 작성자/글내용/수정버튼/삭제버튼
		//수정버튼을 클릭하면 viewUpdateForm()를 호출하고 삭제 버튼을 클릭하면 confirmDeletion()를 호출한다.
		
		var a = "${login.id}";
		var b = comment.uid;
		var html = '<strong>'+comment.uid+'</strong><br/>'+
			comment.content.replace(/\n/g, '\n<br/>')+'<br/>';

		var html2 = '<input type="button" value="수정" '
				+ 'onclick="viewUpdateForm(' + comment.num + ')"/>'
				+ '<input type="button" value="삭제" '
				+ 'onclick="confirmDeletion(' + comment.num + ')"/>';

		//위에서 작성한 내용을 div에 출력한다.
		if(a==b){
			commentDiv.innerHTML = html+html2;
		}else{
			commentDiv.innerHTML = html;
		}
		
		//div의 css 클래스를 comment로 지정
		commentDiv.className = "comment";
		//생성한 div 리턴
		return commentDiv;
	}

	//수정폼을 출력하기위해 수정할 글의 원본 내용을 비동기로 요청
	function viewUpdateForm(num) {
		var param = "command=editForm&r_no=" + num;
		sendRequest("${pageContext.request.contextPath}/DispatcherServlet",
				param, viewUpdateForm2, "POST");
	}

	//수정폼 요청의 결과함수
	function viewUpdateForm2() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				//수정 원본 글을 json으로 받아와 eval()함수로 객체로 변환하고 이를 comment에 저장
				var comment = eval("(" + httpRequest.responseText + ")");
				//원본 글을 출력하는 div객체를 읽어옴
				var commentDiv = document.getElementById('c' + comment.num);
				//수정폼을 출력하는 div객체를 읽어옴
				var updateFormDiv = document.getElementById('commentUpdate');
				//수정폼을 원래의 위치 즉 새글등록 폼 밑에서 잘라내 원본글 div의 자식으로 추가
				if (updateFormDiv.parentNode != commentDiv) {
					updateFormDiv.parentNode.removeChild(updateFormDiv);
					commentDiv.appendChild(updateFormDiv);
				}

				//수정폼에 디폴트로 결과로 가져온 원본글의 내용을 출력한다.
				document.updateForm.id.value = comment.num;
				document.updateForm.content.value = comment.content;
				//수정폼을 보이게 설정
				updateFormDiv.style.display = '';
			}
		}
	}

	//수정취소버튼을 누르면 호출되는 함수
	//원본글의 자식으로 되어있는 수정폼 div 객체를 잘라내 원래 위치로 되돌리고 다시 안보이게 설정
	function cancelUpdate() {
		var updateFormDiv = document.getElementById('commentUpdate');
		updateFormDiv.style.display = 'none';
		updateFormDiv.parentNode.removeChild(updateFormDiv);
		document.documentElement.appendChild(updateFormDiv);
	}

	//수정버튼을 누르면 호출되는 함수로 수정완료를 수행
	function updateComment() {
		//수정폼의 내용 즉 글번호, 이름, 내용을 파라메터로 지정하여 컨트롤러에 수정완료를 요청
		var num = document.updateForm.id.value;
		var content = encodeURIComponent(document.updateForm.content.value);
		var params = "command=edit&r_no=" + num + "&content=" + content;
		sendRequest("${pageContext.request.contextPath}/DispatcherServlet",
				params, updateResult, "POST");
	}

	//수정완료요청의 결과함수
	function updateResult() {
		if (httpRequest.readyState == 4) {
			alert("httpRequest.readyState=4");
			if (httpRequest.status == 200) {
				alert("httpRequest.status=200");
				alert(httpRequest.responseText);
				//수정된 글을 json으로 받음. 이를 eval()로 객체로 변환하여 comment에 저장
				var comment = eval("(" + httpRequest.responseText + ")");
				//모든 글들을 출력할 영역인 id가 commentList인 div 객체를 읽어와
				var listDiv = document.getElementById('commentList');
				//함수 makeCommentView()를 호출하여 수정된 글 하나를 출력할 div를 생성하여 글 내용을 출력함
				var newCommentDiv = makeCommentView(comment);
				//수정되기 전의 글내용을 출력했던 div를 새로 변경된 글내용을 출력한div로 대체
				var oldCommentDiv = document.getElementById('c' + comment.num);
				listDiv.replaceChild(newCommentDiv, oldCommentDiv);
			}
		}
	}

	//삭제버튼을 누루면 호출되는 함수로 삭제의사를 물어본다. 확인을 누르면 del()을 호출
	function confirmDeletion(num) {
		var flag = confirm("정말 삭제하겠습니까?");
		if (flag) {
			del(num);
		} else {
			alert("취소되었습니다.");
		}
	}

	//컨트롤러에 글 하나 삭제 요청. 파라메터로 글번호 전달
	function del(num) {
		var params = "command=del&r_no=" + num;
		sendRequest("${pageContext.request.contextPath}/DispatcherServlet",
				params, delResult, "POST");
	}

	//삭제요청 결과 함수
	function delResult() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				//삭제된 글의 글번호를 json형태로 받아옴
				var obj = eval("(" + httpRequest.responseText + ")");
				var num = obj.num;
				//하나의 글을 출력하는 div의 id가 c글번호로 되어 있으므로 삭제된 글의 번호로 해당 글이 출력된 div를 찾아 삭제한다.
				var delDiv = document.getElementById('c' + num);
				delDiv.parentNode.removeChild(delDiv);
			}
		}
	}
</script>

</head>
<body>

<a href="index.jsp">메인으로</a>
<a href="DispatcherServlet?command=allview">전체글보기</a>
<table border=1>
	<thead>
		<tr>
		<td>글번호</td><td>제목</td><td>Section</td><td>Magazine</td><td>작성자</td>
		</tr>
	</thead>
	<tbody>
		<tr >
		<td >${requestScope.content.p_no }</td>
		<td >${requestScope.content.title }</td>
		<td >${requestScope.content.s_name }</td>
		<td >${requestScope.content.m_name }</td>
		<td >${requestScope.content.u_id }</td>
		</tr>
	</tbody>
</table>
<table border=1>
	<thead>
		<tr>
		<td>내용</td>
		</tr>
	</thead>
	<tbody>
		<tr>
		<td width="400" height="400">
		<img src="/milktank/ViewController?pno=${requestScope.content.p_no }" width="300" height="200"/></img>
		<pre>${requestScope.content.content }</pre></td>
		</tr>
	</tbody>
</table>
<div id="like"><strong> <%-- ${requestScope.content.like } --%></strong></div>
<input type="image" src="" id="likeimg" value="like1" onclick="return like()"><br/>
<c:if test="${login.id eq content.u_id }">
<input type="button" value="수정" onclick="return updatePro()">
<input type="button" value="삭제" onclick="return deletePro()">
</c:if>
<hr>
<div id="commentList"></div>
<div id="commentAdd">
	<form action="" name="addForm">
	
	내용: <textarea name="content" cols="20" rows="2"></textarea><br/>
	<input type="button" value="등록" onclick="addComment()"/>
	</form>
</div>

<div id="commentUpdate" style="display: none">
	<form action="" name="updateForm">
	<input type="hidden" name="id" value=""/>
	내용: <textarea name="content" cols="20" rows="2"></textarea><br/>
	<input type="button" value="등록" onclick="updateComment()"/>
	<input type="button" value="취소" onclick="cancelUpdate()"/>
	</form>
</div>
</body>
</html>