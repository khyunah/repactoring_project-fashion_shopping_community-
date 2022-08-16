let pageNumber = $("#pageNumber").val();

function showMyLike(){
	$(".ms-board-line-box").remove();
	pageNumber = -1;
	$("#nowPage").val('myLike');
	xhr_ms();
}

function showMySocial(){
	$(".ms-board-line-box").remove();
	pageNumber = -1;
	$("#nowPage").val('mySocial');
	xhr_();
}

window.addEventListener('scroll',function(){ 
	if(Math.floor($(window).scrollTop() + $(window).height()) == $(document).height() - 1) {
		console.log("aaaaaaaaa");
		let nowPage = $("#nowPage").val();
		if(nowPage == 'mySocial') {
			xhr_();
		} else {
			xhr_ms();
		}
   }
});

let xhr_ = function() {
	const xhr = new XMLHttpRequest();
	const method = "GET";
	let userId = $("#userId").val();
	console.log(pageNumber);
	let url = `http://localhost:9090/community/user/${userId}/add?page=${pageNumber + 1}`;

	pageNumber++;
	$("#pageNumber").val(pageNumber);

	// 요청을 초기화 합니다.
	xhr.open(method, url);

	// onreadystatechange 이벤트를 이용해 요청에 대한 응답 결과를 처리합니다.
	xhr.onreadystatechange = function(event) {
		const { target } = event;

		if (target.readyState === XMLHttpRequest.DONE) {
			const { status } = target;

			if (status === 0 || (status >= 200 && status < 400)) {
				// 요청이 정상적으로 처리 된 경우
				console.log('성공');
				$(".ms-board-container").append(xhr.responseText);
			} else {
				// 에러가 발생한 경우
				console.log('실패');
			}
		}
	};

	// 서버에 요청을 보냅니다.
	xhr.send();

}

let xhr_ms = function(){
	const xhr = new XMLHttpRequest();
	const method = "GET";
	let userId = $("#userId").val()
	const url = `http://localhost:9090/community/my-like/${userId}?page=${pageNumber + 1}`;
	pageNumber++;
	$("#pageNumber").val(pageNumber);
	
	// 요청을 초기화 합니다.
	xhr.open(method, url);
	
	// onreadystatechange 이벤트를 이용해 요청에 대한 응답 결과를 처리합니다.
	xhr.onreadystatechange = function (event) {
	    const { target } = event;

	    if (target.readyState === XMLHttpRequest.DONE) {
	        const { status } = target;
	        
	        if (status === 0 || (status >= 200 && status < 400)) {
	            // 요청이 정상적으로 처리 된 경우
	            console.log('성공');
	            $(".ms-board-container").append(xhr.responseText);
	        } else {
	            // 에러가 발생한 경우
	            console.log('실패');
	        }
	    }
	};

// 서버에 요청을 보냅니다.
xhr.send();

}
