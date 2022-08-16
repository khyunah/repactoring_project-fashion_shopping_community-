<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="community_like_modal.jsp"%>

<div class="community-container">
	<div class="cmsomaincon">
		<div class="cmsocon">
			<%@ include file="add_community_board.jsp"%>
		</div>
	</div>
</div>
<div class="commu-profile-box">
	<img class="card-img-top commu-profile-img" src="/upload/${principal.user.imageUrl}" alt="Card image" onerror="this.src='/image/noImage.png'" />
	<div class="card-body commu-profile-text-box">
		<c:choose>
			<c:when test="${principal.user.oauth eq 'KAKAO'}">
				<h5 class="card-title" id="commu-profile-username">${principal.user.name}</h5>
			</c:when>
			<c:otherwise>
				<h5 class="card-title" id="commu-profile-username">${principal.user.username}</h5>
			</c:otherwise>
		</c:choose>
		﻿ <a href="/community/user/${principal.user.id}" class="commu-profile-btn commu-profile-btn-my">MY SOCIAL</a> 
		<a href="/board/write" class="commu-profile-btn commu-profile-btn-write-community-board">WRITE</a>
		<a href="" class="commu-profile-btn commu-profile-btn-top">TOP</a>
	</div>
</div>

<input id="pageNumber" type="hidden" value="${communityBoardList.number}">

<script>
	history.scrollRestoration = "manual"
</script>
<script src="/js/commu.js"></script>
<script src="/js/social_scroll.js"></script>

</body>

</html>