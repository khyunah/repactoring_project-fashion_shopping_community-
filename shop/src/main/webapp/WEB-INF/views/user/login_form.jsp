<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="user-header-line" style="background-color: #453675;"><h2 class="user-header-text">L o g i n</h2></div>
<div style="height: 40px;"></div>
<form class="user-form" action="/security/login-user" method="post">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

	<div class="user-input-container">
		<label class="user-input-label">
			<span class="label-txt">ENTER YOUR ID</span>
			<input type="text" class="input" name="username" id="username" value="aa" required>
			<div class="line-box">
			  <div class="line" ></div>
			</div>
		</label>
	</div>

	<div class="user-empty-box"></div>

	<div class="user-input-container">
		<label class="user-input-label"> <span class="label-txt">ENTER
				YOUR PASSWORD</span> <input id="password" type="password" class="input" value="aa11!!aaaaa"
			name="password" required>
			<div class="line-box">
				<div class="line"></div>
			</div>
		</label>
	</div>

	<c:if test="${not empty error}">
		<span class="user-check-span">${errorMessage}</span>
	</c:if>

	<div style="margin-top: 50px;">
		<button type="submit" id="btn-login" class="user-btn" style="background-color: #453675;">login</button>
		<a class="user-kakao-login" href="https://kauth.kakao.com/oauth/authorize?client_id=0d6bcf296d67c35ad944b2a3d38df9be&redirect_uri=http://localhost:9090/security/kakao/callback&response_type=code">
			<img src="/image/kakao_login.png" width="86" height="50">
		</a>
	</div>

</form>
<script> history.scrollRestoration = "manual" </script>
</body>
</html>
