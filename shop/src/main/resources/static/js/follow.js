function followList(userId){

	$.ajax({
		type: "GET",
		url: `/comminity/follow-list/${userId}`,
		dataType: "json"
	}).done(function(response){
		console.log(response);
		addList(response);
	}).fail(function(){
		console.log('실패');
	});
}

function followerList(userId){

	$.ajax({
		type: "GET",
		url: `/comminity/follower-list/${userId}`,
		dataType: "json"
	}).done(function(response){
		console.log(response);
		console.log(response);
		addFollowerList(response);
	}).fail(function(){
		console.log('실패');
	});
}

function addList(response){
	$(".follow-list-item").remove();
	$(".follow-null-info").remove();
	
	$(".h-modal-title").text('팔로우');
	
	if(response.length == 0){
		var nullInfo = `<p class="follow-null-info h-modal-text">팔로우가 없습니다 !</p>`;
		$(".modal-body").append(nullInfo);
	}
	$.each(response, function(index, f){
		var addItem = `<div class="follow-list-item-${f.followUser.id} follow-list-item" onclick="location.href='/community/user/${f.followUser.id}'">
				<div class="circle-img-box-${f.followUser.id} circle-img-box">
	            	<c:choose>
	            		<c:when test="${f.followUser.imageUrl != null}">
	            			<img src="/upload/${f.followUser.imageUrl}" alt="" class="profile-img" onerror="this.src='/image/noImage.png'">
	            		</c:when>
	            		<c:otherwise>
	            			<img src="/image/noImage.png" alt="" class="profile-img">
	            		</c:otherwise>
	            	</c:choose>
	            </div>
	            <div class="follow-flex">
		            <div class="follow-list-username-box-${f.followUser.id} follow-list-username-box">
		                <p class="follow-list-username">${f.followUser.username}</p>
		                <p class="follow-list-name">${f.followUser.name}</p>
		            </div>
		            
	            </div>
			</div>
	    </div>`;
		$(".modal-body").append(addItem);
	});
}

function addFollowerList(response){
	$(".follow-list-item").remove();
	$(".follow-null-info").remove();
	
	$(".h-modal-title").text('팔로워');
	
	if(response.length == 0){
		var nullInfo = `<p class="follow-null-info h-modal-text">팔로워가 없습니다 !</p>`;
		$(".modal-body").append(nullInfo);
	}
	$.each(response, function(index, f){
		var addItem = `<div class="follow-list-item-${f.followerUser.id} follow-list-item" onclick="location.href='/community/user/${f.followerUser.id}'">
				<div class="circle-img-box-${f.followerUser.id} circle-img-box">
	            	<c:choose>
	            		<c:when test="${f.followerUser.imageUrl != null}">
	            			<img src="/upload/${f.followerUser.imageUrl}" alt="" class="profile-img" onerror="this.src='/image/noImage.png'">
	            		</c:when>
	            		<c:otherwise>
	            			<img src="/image/noImage.png" alt="" class="profile-img">
	            		</c:otherwise>
	            	</c:choose>
	            </div>
	            <div class="follow-flex">
		            <div class="follow-list-username-box-${f.followerUser.id} follow-list-username-box">
		                <p class="follow-list-username">${f.followerUser.username}</p>
		                <p class="follow-list-name">${f.followerUser.name}</p>
		            </div>
		            
	            </div>
			</div>
	    </div>`;
		$(".modal-body").append(addItem);
	});
}
/* 
<div class="ms-follow-btn-box">
	<c:choose>
		<c:when test="${}">
			
		</c:when>
	</c:choose>
    <button onclick="" class="ms-follow-btn">팔로우</button>
</div>
 */

function follow(followId){
	$.ajax({
		type: "GET",
		url: `/comminity/m-follow/${followId}`,
		dataType: "json"
	}).done(function(response){
		console.log(response);
		addList(response);
	}).fail(function(){
		console.log('실패');
	});
}
