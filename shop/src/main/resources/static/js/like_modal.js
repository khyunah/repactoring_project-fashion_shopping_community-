function likeList(boardId){
	console.log("눌름");
	$.ajax({
		type: "GET",
		url: `/comminity/like-list/${boardId}`,
		dataType: "json"
	}).done(function(response){
		console.log(response);
		addList(response);
	}).fail(function(){
		console.log('실패');
	});
}

function addList(response){
	$(".like-list-item").remove();
	$(".like-null-info").remove();
	
	if(response.length == 0){
		var nullInfo = `<p class="like-null-info h-modal-text">좋아요를 해보세요 !</p>`;
		$(".modal-body").append(nullInfo);
	}
	$.each(response, function(index, likeItem){
		console.log(likeItem.imageUrl);
		var addItem = `<div class="like-list-item-${likeItem.id} like-list-item" onclick="location.href='/community/user/${likeItem.id}'">
	            <div class="circle-img-box-${likeItem.id} circle-img-box">
	            	<c:choose>
	            		<c:when test="${likeItem.imageUrl != null}">
	            			<img src="/upload/${likeItem.imageUrl}" alt="" class="profile-img" onerror="this.src='/image/noImage.png'">
	            		</c:when>
	            		<c:otherwise>
	            			<img src="/image/noImage.png" alt="" class="profile-img">
	            		</c:otherwise>
	            	</c:choose>
	            </div>
	            <div class="like-list-username-box-${likeItem.id} like-list-username-box">
	                <p class="like-list-username">${likeItem.username}</p>
	                <p class="like-list-name">${likeItem.name}</p>
	            </div>
	        </div>`;
		$(".modal-body").append(addItem);
	});
}

