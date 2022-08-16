let pageSize = $("#pageSize").val();
let pageNumber = $("#pageNumber").val();

window.addEventListener('scroll',function(){ 
console.log($(window).scrollTop() + $(window).height());
console.log($(document).height() - 1);
	if(Math.floor($(window).scrollTop() + $(window).height()) == $(document).height() - 1) {
       xhr_();
   }
});

let xhr_ = function(){
	const xhr = new XMLHttpRequest();
	const method = "GET";
	const url = `http://localhost:9090/index/index-add?page=${pageNumber + 1}`;
	pageNumber++;
	$("#pageNumber").val(pageNumber);
	
	// 요청을 초기화 합니다.
	xhr.open(method, url);
	
	// onreadystatechange 이벤트를 이용해 요청에 대한 응답 결과를 처리합니다.
	xhr.onreadystatechange = function (event) {
	    const { target } = event;
	    
	    console.log(target);
	
	    if (target.readyState === XMLHttpRequest.DONE) {
	        const { status } = target;
	        
	        if (status === 0 || (status >= 200 && status < 400)) {
	            // 요청이 정상적으로 처리 된 경우
	            console.log('성공');
	            $(".leftCards").append(xhr.responseText);
	        } else {
	            // 에러가 발생한 경우
	            console.log('실패');
	        }
	    }
	};

// 서버에 요청을 보냅니다.
xhr.send();

}