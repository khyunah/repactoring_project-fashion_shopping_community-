<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="community_like_modal.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
   <sec:authentication property="principal" var="principal" />
 </sec:authorize>

<c:forEach var="communityBoard" items="${communityBoardList.content}">
	<div class="cmct">
		<input id="pageLast" type="hidden" value="${communityBoardList.last}">

		<div class="commu-social-btn-box">
			<c:if test="${communityBoard.user.id == principal.user.id}">
				<button type="button" class="btn-up commu-btn commu-social-btn" id="commu-social-btn-update"
					onclick="location.href='/board/${communityBoard.id}/update_form'">update</button>
				<button type="button" class="btn-up commu-btn commu-social-btn" id="commu-social-btn-delete"
					onclick="commu.boardDelete(${communityBoard.id})">delete</button>
			</c:if>
		</div>
		<div class="commu-social-container">

			<input id="userId" type="hidden" value="${principal.user}" /> 
			<input id="communityBoardId" type="hidden" value="${communityBoard.id}" />

			<div class="commu-container commu-container-social">
				<div>

					<div class="commu-social-img-box">
						<a href="/community/${communityBoard.id}">
							<img class="commu-social-img" alt="" onerror="this.src='/image/noImage.png'" src="/upload/${communityBoard.imageUrl}" />
						</a>
					</div>

					<div class="commu-social-main-container">
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
								<button onclick="likeList(${communityBoard.id})" type="button" class="btn like-count-button" data-toggle="modal" data-target="#myModal">
									<span class="likeCount-text">좋아요 </span>
									<span id="likeCount-${communityBoard.id}" class="likeCount-text" >${communityBoard.likeCount}</span>
									<span class="likeCount-text"> 개</span>
								</button>
							</div>


						</div>

						<hr class="commu-social-hr-goodlook-line" />

						<div style="height: 10px; margin-bottom: 10px"></div>

						<div>
							<div class="commu-social-div-title-container">
								<h4 class="commu-title">${communityBoard.title}</h4>
							</div>
							<div style="height: 10px"></div>
							<div class="commu-social-div-title-container">
								<a href="/community/user/${communityBoard.user.id}">
									<span class="commu-social-p-username commu-text">${communityBoard.user.username}</span>
								</a>
							</div>
						</div>

						<div style="height: 40px"></div>

						<div>
							<div class="commu-social-div-content-container csdcc-${communityBoard.id}" onclick="more()">
								<span class="commu-social-span-content commu-text">${communityBoard.content}</span>
							</div>
						</div>

						<div style="height: 50px"></div>

						<div>
							<div class="commu-reply-firstline">
								<div>
									<i class="fa-regular fa-comments fa-lg commu-reply-icon"></i>
									<a href="/community/${communityBoard.id}" class="reply-count-button">
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
								
								<button type="button" class="btn-up commu-btn" id="commu-btn-insert"
									onclick="commu.insertReplyMain(${communityBoard.id}, ${principal.user.id})">up</button>
							</div>

							<div>
								<input type="text" placeholder="한 마디" class="commu-social-input-reply commu-social-input" id="commu-input-reply-${communityBoard.id}" />
							</div>
						</div>
					</div>
					<div class="commu-detail-reply-container-${communityBoard.id}">
						
					</div>
				</div>
			</div>
		</div>
	</div>

</c:forEach>
