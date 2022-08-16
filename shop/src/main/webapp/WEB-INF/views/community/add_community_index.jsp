<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="community_like_modal.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
   <sec:authentication property="principal" var="principal" />
 </sec:authorize>

<c:forEach var="communityBoard" items="${communityBoardList.content}">
	<input id="communityBoardId" type="hidden" value="${communityBoard.id}" />

	<div class="commu-container">
		<div class="commu-img-box">
			<a href="/community/${communityBoard.id}"> 
			<img class="commu-img" alt="" src="/upload/${communityBoard.imageUrl}"
				onerror="this.src='/image/noImage.png'" />
			</a>
		</div>

		<div class="div-main-container">
			<div id="commu-icon-box-${communityBoard.id}">
				<div>
					<c:set var="myLike" value="0"></c:set>
					<c:forEach var="like" items="${likeList}">
						<c:if test="${like.board.id == communityBoard.id}">
							<c:set var="myLike" value="ok"></c:set>
						</c:if>
					</c:forEach>

					<c:choose>
						<c:when test="${myLike == 'ok'}">
							<i class="fa-solid fa-heart fa-lg" style="color: rgb(240, 81, 115)" onclick="commu.communityLike(${communityBoard.id}, ${communityBoard.likeCount})"></i>
						</c:when>
						<c:otherwise>
							<i style="color: black" id="before-like" class="fa-regular fa-heart fa-lg" onclick="commu.communityLike(${communityBoard.id}, ${communityBoard.likeCount})"></i>
						</c:otherwise>
					</c:choose>
					<button onclick="likeList(${communityBoard.id})"  type="button" class="btn like-count-button" data-toggle="modal" data-target="#myModal">
						<span class="likeCount-text">좋아요 </span>
						<span id="likeCount-${communityBoard.id}" class="likeCount-text" >${communityBoard.likeCount}</span>
						<span class="likeCount-text"> 개</span>
					</button>
				</div>

			</div>

			<hr class="hr-goodlook-line" />

			<div style="height: 10px"></div>

			<div class="div-title-container">
				<span class="commu-text commu-title commu-home-title">${communityBoard.title}</span>
			</div>
			<div class="div-title-container">
				<a href="/community/user/${communityBoard.user.id}">
					<span class="p-username commu-text commu-home-username">${communityBoard.user.username}</span>
				</a>
			</div>

			<div style="height: 20px"></div>

			<div class="div-content-container">
				<span class="span-content commu-home-text">${communityBoard.content}</span>
			</div>
			
			<div class="commu-reply-firstline">
			<div>
				<i class="fa-regular fa-comments fa-lg commu-reply-icon"></i>
				<c:choose>
					<c:when test="${principal.user == null}">
						<a href="/community/${communityBoard.id}" class="reply-count-button" onsubmit="return confirm('로그인이 필요합니다. 로그인 하시겠습니까?')">
					</c:when>
					<c:otherwise>
						<a href="/community/${communityBoard.id}" class="reply-count-button">
					</c:otherwise>
				</c:choose>
				
					<span class="reply-count-span">댓글 </span>
					<c:set var="thisReplyCount" value="0"></c:set>
					<c:forEach var="replyCount" items="${replyCountList}">
						<c:if test="${replyCount.id == communityBoard.id}">
							<c:set var="thisReplyCount" value="${replyCount.count}"></c:set>
						</c:if>
					</c:forEach>
					<span class="reply-count-span">${thisReplyCount}</span>
					<span class="reply-count-span"> 개</span>
					<span class="reply-count-span reply-more-view">( 더보기 )</span>
				</a>
			</div>
		</div>

		</div>
		
		

	</div>
</c:forEach>
