//html 태그를 name 속성으로 제어하는 방법
function checkBoardWrite() {
	if (document.boardWriteForm.subject.value == "") {
		alert("제목을 입력하세요");
		document.boardWriteForm.subject.focus();
	} else if (document.boardWriteForm.content.value == "") {
		alert("내용을 입력하세요");
		document.boardWriteForm.content.focus();
	} else {
		document.boardWriteForm.submit();
	}
}

// html 태그를 id 속성으로 제어하는 방법
function checkBoardWrite1(){
	if(document.getElementById("subject").value == ""){
		alert("제목을 입력하세요1");
		document.getElementById("subject").focus();
	} else if (document.getElementById("content").value == "") {
		alert("내용을 입력하세요1");
		document.getElementById("content").focus();
	} else {
		document.boardWriteForm.submit();	
	}
}
