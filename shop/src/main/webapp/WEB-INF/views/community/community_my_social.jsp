<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="community_follow_modal.jsp"%>

<input type="hidden" value="${user.id}" id="userId">
<div class="ms-body">
	<div class="ms-all-container">
        <header class="ms-profile-container">
            <div class="ms-img-container">
                <img src="/upload/${user.imageUrl}" onerror="this.src='/image/noImage.png'" class="ms-profile-img">
            </div>
            <div class="ms-profile-text-box">
                <div class="ms-pf-name-box">
                    <p class="ms-pf-username">${user.username}</p>
                    <span class="ms-pf-name">${user.name}</span>
                    <c:choose>
                   		<c:when test="${isFollow}">
                    		<a href="/community/follow-cancle/${user.id}" class="follow-btn">팔로우 취소</a>
                    	</c:when>
                    	<c:when test="${principal.user.id != user.id}">
                    		<a href="/community/follow/${user.id}" class="follow-btn">팔로우</a>
                    	</c:when>
                    	
                    </c:choose>
                    
                </div>
                <div class="ms-pf-follow-box">
                    <div class="ms-pf-board-num-box">
                        <span class="ms-pf-follow-text">게시물</span>
                        <span class="ms-pf-follow-num">${boardCount}</span>
                    </div>
                    <div class="ms-pf-follow-num-box" onclick="followList(${user.id})" data-toggle="modal" data-target="#myModal">
                        <span class="ms-pf-follow-text">팔로우</span>
                        <span class="ms-pf-follow-num">${followCount}</span>
                    </div>
                    <div class="ms-pf-follower-num-box" onclick="followerList(${user.id})" data-toggle="modal" data-target="#myModal">
                        <span class="ms-pf-follow-text">팔로워</span>
                        <span class="ms-pf-follow-num">${followerCount}</span>
                    </div>
                </div>
            </div>
        </header>
        <div class="ms-btn-box">
        	<button onclick="showMySocial()" class="ms-btn">MYSOCIAL</button>
        	<button onclick="showMyLike()" class="ms-btn">MYLIKE</button>
        </div>
        <div class="ms-board-container">
            <%@ include file="add_community_my.jsp"%>
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
<input id="nowPage" type="hidden" value="mySocial">

<script>
	history.scrollRestoration = "manual"
</script>
<script src="/js/follow.js"></script>
<script src="/js/my_social.js"></script>
</body>

</html>