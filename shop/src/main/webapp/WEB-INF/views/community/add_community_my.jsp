<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
   <sec:authentication property="principal" var="principal" />
 </sec:authorize>

<div class="ms-board-line-box">
	<c:forEach var="commu" items="${communityBoardList.content}" varStatus="status">
		<c:if test="${status.index < 3}">
			<div class="ms-board-img-size-box">
		         <div class="ms-board-img-box">
		         	<a href="/community/${commu.id}">
		         		<img src="/upload/${commu.imageUrl}" class="ms-board-img">
		         	</a>
		         </div>
		     </div>
		</c:if>
	</c:forEach>
</div>

<div class="ms-board-line-box">
	<c:forEach var="commu" items="${communityBoardList.content}" varStatus="status">
		<c:if test="${status.index > 2 && status.index < 6}">
			<div class="ms-board-img-size-box">
		         <div class="ms-board-img-box">
		         	<a href="/community/${commu.id}">
		             	<img src="/upload/${commu.imageUrl}" class="ms-board-img">
		            </a>
		         </div>
		     </div>
		</c:if>
	</c:forEach>
</div>

<div class="ms-board-line-box">
	<c:forEach var="commu" items="${communityBoardList.content}" varStatus="status">
		<c:if test="${status.index > 5 && status.index < 9}">
			<div class="ms-board-img-size-box">
		         <div class="ms-board-img-box">
		         	<a href="/community/${commu.id}">
		            	<img src="/upload/${commu.imageUrl}" class="ms-board-img">
		            </a>
		         </div>
		     </div>
		</c:if>
	</c:forEach>
</div>
