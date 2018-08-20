<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<script type="text/javascript">
window.onload = function(){
	if(${su} > 0) {
		alert("작성하신 글 저장");
		location.href="boardList.do?pg=1";
	} else {
		alert("글 저장 실패~");
		location.href="boardWriteForm.do";
	}	
}
</script>
</head>
<body>



</body>
</html>


















