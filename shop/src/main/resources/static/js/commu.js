$(document).ready(function () {
	let replyList = JSON.parse(`${replyJsonList}`);
	console.log(replyList);
	
	$.each(replyList, function(index, reply){
		fixTextAreaHeight(reply.id);
	});
});

let commu = {

	init: function() {
		$(document).on('click', ".commu-detail-btn-reply-update-finish", function() {
			commu.finishUpdateReply();
		});

		$("#commu-detail-btn-delete").bind('click', () => {
			this.boardDelete();
		});

		$(".commu-profile-btn-top").bind('click', () => {
			this.goTop();
		});
	},

	// 댓글쓰기
	insertReply: function(communityBoardId, userId) {
		let token = $("meta[name='_csrf']").attr("content");
		let header = $("meta[name='_csrf_header']").attr("content");

		let data = {
			content: $(`#commu-input-reply-${communityBoardId}`).val(),
		}

		if (data.content != '') {
			$.ajax({
				beforeSend: function(xhr) {
					xhr.setRequestHeader(header, token)
				},
				type: "POST",
				url: `/community/reply-insert/${communityBoardId}`,
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			}).done(function(response) {
				addReply(response.data, userId, communityBoardId);
			}).fail(function(error) {
				alert("댓글 작성 실패 !");
			});
		} else {
			alert('내용을 입력해주세요');
		}
	},
	
	// 메인에서 댓글 작성시 하나만 보이게
	insertReplyMain: function(communityBoardId, userId){
		let token = $("meta[name='_csrf']").attr("content");
		let header = $("meta[name='_csrf_header']").attr("content");

		let data = {
			content: $(`#commu-input-reply-${communityBoardId}`).val(),
		}

		if (data.content != '') {
			$.ajax({
				beforeSend: function(xhr) {
					xhr.setRequestHeader(header, token)
				},
				type: "POST",
				url: `/community/reply-insert/${communityBoardId}`,
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			}).done(function(response) {
				addOneReply(response.data, userId, communityBoardId);
			}).fail(function(error) {
				alert("댓글 작성 실패 !");
			});
		} else {
			alert('내용을 입력해주세요');
		}
	},

	// 댓글 삭제
	deleteReply: function(id) {
		let token = $("meta[name='_csrf']").attr("content");
		let header = $("meta[name='_csrf_header']").attr("content");

		$.ajax({
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token)
			},
			type: "DELETE",
			url: `/community/reply-delete/${id}`,
			dataType: "json"
		}).done(function(response) {
			console.log("성공");
			removeReply(id);
		}).fail(function(error) {
			console.log("실패");
		})
	},

	// 댓글 수정버튼 클릭시 
	updateBtnReply: function(id) {
		let btn = $(`.commu-detail-btn-reply-update-${id}`).text();
		console.log(btn);
		if (btn == '수정') {
			changeReply(id);
		} else {
			this.finishUpdateReply(id);
		}
	},

	// 댓글 수정 완료시 
	finishUpdateReply: function(replyId) {
		let token = $("meta[name='_csrf']").attr("content");
		let header = $("meta[name='_csrf_header']").attr("content");

		let data = {
			id: replyId,
			content: $(`#commu-detail-reply-content-${replyId}`).val()
		}
		console.log(data.content);
		$.ajax({
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token)
			},
			type: "PUT",
			url: `/community/reply-update`,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(response) {
			console.log("성공");
			changVieweReply(response.data.id);
		}).fail(function(error) {
			console.log("실패");
		});
	},

	// 좋아요
	communityLike: function(communityBoardId, likeCount) {
		if ($("#userId").val() != '') {
			$.ajax({
				type: "GET",
				url: `/community/check-like/${communityBoardId}`,
				dataType: "json"
			}).done(function(response) {
				changeLikeIcon(response, communityBoardId, likeCount);
			}).fail(function(error) {
				alert('서버오류 ! 다시 실행해주세요.');
			});
		} else {
			if(confirm('로그인이 필요합니다. 로그인 하시겠습니까?')){
				location.href = '/security/login_form';
			}
		}
	},

	// 글 수정하기
	boardUpdate: function() {
		let token = $("meta[name='_csrf']").attr("content");
		let header = $("meta[name='_csrf_header']").attr("content");

		let id = $("#boardId").val();
		let data = {
			title: $("#communityBoardTitle").val(),
			content: $("#communityBoardContent").val()
		}

		$.ajax({
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token)
			},
			type: "PUT",
			url: `/api/board/${id}`,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		})
			.done(function(data) {
				if (data.status) {
					alert("글 수정이 완료 되었습니다");
					location.href = "/";
				}
			})
			.fail(function(error) {
				alert("글 쓰기에 실패하였습니다");
			});
	},

	// 글 삭제하기
	boardDelete: function(boardId) {
		let token = $("meta[name='_csrf']").attr("content");
		let header = $("meta[name='_csrf_header']").attr("content");

		$.ajax({
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token)
			},
			type: "DELETE",
			url: `/api/board/${boardId}`
		})
			.done(function(response) {
				if (response.data != 0) {
					alert("삭제가 완료되었습니다.");
					location.href = "/";
				}
			})
			.fail(function() {
				alert("삭제에 실패했습니다.");
			});
	},

	// 맨위로
	goTop: function() {
		$('html').scrollTop(0);
	},

	// 메인에서 로그인 안하고 카드 선택시 로그인페이지로
	guestCheck: function() {
		location.href = "/security/login_form";
	}
}

// 댓글 추가 
function addReply(reply, userId, communityBoardId) {
	let childReply = `
		<div id="commu-reply-${reply.id}">
			<div class="commu-detail-reply-firstline-container">
				<div class="commu-reply-profile-div">
					<div class="commu-reply-circle-img-box-${reply.user.id} commu-reply-circle-img-box">
		            	<c:choose>
		            		<c:when test="${reply.user.imageUrl != null}">
		            			<img src="/upload/${reply.user.imageUrl}" alt="" class="commu-reply-circle-img" onerror="this.src='/image/noImage.png'">
		            		</c:when>
		            		<c:otherwise>
		            			<img src="/image/noImage.png" alt="" class="commu-reply-circle-img">
		            		</c:otherwise>
		            	</c:choose>
		            </div>
					<span class="commu-detail-reply-user commu-detail-reply-text">${reply.user.username}</span>
				</div>
				
				<div id="commu-detail-reply-btn-box">
					<c:if test="${reply.user.id == userId}">
						<button onclick="commu.updateBtnReply(${reply.id})" class="commu-detail-btn-reply-update-${reply.id} commu-detail-btn-reply">수정</button>
						<button onclick="commu.deleteReply(${reply.id})" class="commu-detail-btn-reply-delete-${reply.id} commu-detail-btn-reply"> 삭제</button>
					</c:if>
				</div>
			</div>
			<div id="commu-detail-reply-content-box-${reply.id}">
				<textarea id="commu-detail-reply-content-${reply.id}" class="commu-detail-reply-content commu-detail-reply-text" readonly>${reply.content}</textarea>
			</div>
		</div>
	`;

	$(".commu-detail-reply-container").prepend(childReply);
	fixTextAreaHeight(reply.id);
	$(`#commu-input-reply-${communityBoardId}`).val("");
}

// 댓글 불러온것 뿌릴때 태그의 높이 지정해줌
function fixTextAreaHeight(replyId) {
	var textEle = $(`#commu-detail-reply-content-` + replyId);
	textEle.css('height', "auto");
	var textEleHeight = textEle.prop("scrollHeight");
	textEle.css("height", textEleHeight);
}

// 소셜 메인에서 댓글 추가 
function addOneReply(reply, userId, communityBoardId) {
	$(`#commu-reply-${communityBoardId}`).remove();
	
	let childReply = `
		<div id="commu-reply-${communityBoardId}">
			<div class="commu-detail-reply-firstline-container">
				<div class="commu-reply-profile-div">
					<div class="commu-reply-circle-img-box-${reply.user.id} commu-reply-circle-img-box">
		            	<c:choose>
		            		<c:when test="${reply.user.imageUrl != null}">
		            			<img src="/upload/${reply.user.imageUrl}" alt="" class="commu-reply-circle-img" onerror="this.src='/image/noImage.png'">
		            		</c:when>
		            		<c:otherwise>
		            			<img src="/image/noImage.png" alt="" class="commu-reply-circle-img">
		            		</c:otherwise>
		            	</c:choose>
		            </div>
					<span class="commu-detail-reply-user commu-detail-reply-text">${reply.user.username}</span>
				</div>
				
				<div id="commu-detail-reply-btn-box">
					<c:if test="${reply.user.id == userId}">
						<button onclick="commu.updateBtnReply(${reply.id})" class="commu-detail-btn-reply-update-${reply.id} commu-detail-btn-reply">수정</button>
						<button onclick="commu.deleteReply(${reply.id})" class="commu-detail-btn-reply-delete-${reply.id} commu-detail-btn-reply"> 삭제</button>
					</c:if>
				</div>
			</div>
			<div id="commu-detail-reply-content-box-${reply.id}">
				<textarea id="commu-detail-reply-content-${reply.id}" class="commu-detail-reply-content commu-detail-reply-text" readonly>${reply.content}</textarea>
			</div>
		</div>
	`;

	$(`.commu-detail-reply-container-${communityBoardId}`).append(childReply);
	fixTextAreaHeight(reply.id);
	$(`#commu-input-reply-${communityBoardId}`).val("");
}

// 수정 완료시 화면 랜더링
function changVieweReply(id) {
	$(`#commu-detail-reply-content-${id}`).attr("readonly", true);
	fixTextAreaHeight(id);
	$(`.commu-detail-btn-reply-update-${id}`).text('수정');
}

// 댓글 화면에서 삭제처리 
function removeReply(replyId) {
	$(`#commu-reply-` + replyId).remove();
	$(`#commu-reply`).remove();
}

// 댓글 수정버튼 클릭시 
function changeReply(id) {
	let content = $(`#commu-detail-reply-content-${id}`).val();
	$(`.commu-detail-btn-reply-update-${id}`).text('완료');
	
	document.getElementById(`commu-detail-reply-content-box-${id}`).innerHTML =
		`<textarea id="commu-detail-reply-content-${id}" class="commu-detail-reply-content commu-detail-reply-text">${content}</textarea>`;
	fixTextAreaHeight(id);
}

// 좋아요 아이콘 변경 함수
function changeLikeIcon(response, communityBoardId, likeCount) {
	if (response.data == null) {
		likeCount--;
		document.getElementById(`commu-icon-box-${communityBoardId}`).innerHTML =
			`
				<div>
	        		<i style="color: black" id="before-like" class="fa-regular fa-heart fa-lg" onclick="commu.communityLike(${communityBoardId}, ${likeCount})"></i>
					
					<button onclick="likeList(${communityBoardId})" type="button" class="btn like-count-button" data-toggle="modal" data-target="#myModal">
						<span class="likeCount-text">좋아요 </span>
						<span id="likeCount-${communityBoardId}" class="likeCount-text" >${likeCount}</span>
						<span class="likeCount-text"> 개</span>
					</button>
	            </div>
			`;

	} else {
		likeCount++;
		document.getElementById(`commu-icon-box-${communityBoardId}`).innerHTML =
			`
				<div>
	        		<i class="fa-solid fa-heart fa-lg" style="color: rgb(240, 81, 115)" onclick="commu.communityLike(${communityBoardId}, ${likeCount})"></i>

					<button onclick="likeList(${communityBoardId})" type="button" class="btn like-count-button" data-toggle="modal" data-target="#myModal">
						<span class="likeCount-text">좋아요 </span>
						<span id="likeCount-${communityBoardId}" class="likeCount-text" >${likeCount}</span>
						<span class="likeCount-text"> 개</span>
					</button>
	            </div>
			`;
	}
}

function more(){
	
}

commu.init();
