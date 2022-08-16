<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="user-header-line" style="background-color: #453675;"><h2 class="user-header-text">J o i n</h2></div>

<div style="height: 40px;"></div>

<form class="user-form" action="/security/join-user" method="post"
	onsubmit="return checkPassword()">

	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}">

	<div class="user-input-container">
		<label class="user-input-label"> <span class="label-txt">ENTER YOUR ID</span> 
			<input type="text" class="input" name="username" id="username" maxlength="16" oninput="handleOnInput(this)" required>
			<div class="line-box">
				<div class="line"></div>
			</div>
		</label>

		<div>
			<span class="user-check-span" id="checkIdResult"></span>
		</div>
		<div class="user-empty-box"></div>
		<div class="user-empty-box"></div>
		<div class="user-empty-box"></div>

        <div>
            <button type="button" id="btn-checkUsername" class="user-btn" style="background-color: #453675;">check id</button>
        </div>

		<div class="user-empty-box"></div>

		<div class="user-input-container">
			<div>
				<label class="user-input-label"> <span class="label-txt">ENTER YOUR PASSWORD</span> 
					<input id="password" type="password" class="input" name="password"
						pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$" required>
					<div class="line-box">
						<div class="line"></div>
					</div>
				</label> 
				
				<label class="user-input-label"> <span class="label-txt">PASSWORD CHECK</span> 
					<input id="passwordCheck" type="password" class="input" required>
					<div class="line-box">
						<div class="line"></div>
					</div>
				</label>
			</div>
			<span class="user-check-span" id="checkPasswordResult"></span>
			<div><span class="user-join-info-span">* 비밀번호는 영문 , 숫자, 특수문자를 꼭 포함하여 8 ~ 16 자 </span></div>
			
		</div>

		<div class="user-empty-box"></div>
		<div class="user-empty-box"></div>
		<div class="user-empty-box"></div>

		<div class="user-input-container">
			<div>
				<label class="user-input-label"> <span class="label-txt">ENTER YOUR NAME</span>
					<input type="text" class="input" name="name" maxlength="20" required>
					<div class="line-box">
						<div class="line"></div>
					</div>
				</label> <label class="user-input-label"> <span class="label-txt">ENTER YOUR EMAIL</span> 
					<input type="email" class="input" id="email" name="email" required>
					<div class="line-box">
						<div class="line"></div>
					</div>
				</label>
			</div>
		</div>

		<div class="user-empty-box"></div>

		<div class="user-input-container">
			<div>
				<label class="user-input-label"> <span class="label-txt">ENTER YOUR ADDRESS</span> 
				<input type="text" class="input" name="address" required>
					<div class="line-box">
						<div class="line"></div>
					</div>
				</label> 
				<label class="user-input-label"> <span class="label-txt">ENTER YOUR PHONE</span> 
					<input type="number" class="input" name="phoneNumber" min="10" maxlength="11" required>
					<div class="line-box">
						<div class="line"></div>
					</div>
				</label>
			</div>
		</div>

		<div class="user-empty-box"></div>
		<div class="user-empty-box"></div>
		<div class="user-empty-box"></div>


        <button type="submit" id="btn-join" class="user-btn" style="background-color: #453675;">sign up</button>
    </div>

</form>

<script>
	$(document).ready(function() {

		$("#username").keyup(function() {
			$("#checkIdResult").text(null);
		});

		$("#password, #passwordCheck").keyup(function() {
			let password = $("#password").val();
			let passwordCheck = $("#passwordCheck").val();
			if (password == "" && passwordCheck == "") {
				$("#checkPasswordResult").text("");
			} else if (password == passwordCheck) {
				$("#checkPasswordResult").text("비밀번호가 일치합니다.");
			} else if (password != passwordCheck) {
				$("#checkPasswordResult").text("비밀번호가 일치하지 않습니다.");
			}
		});

		$("#btn-checkUsername").bind("click", function() {
			let token = $("meta[name='_csrf']").attr("content");
			let header = $("meta[name='_csrf_header']").attr("content");

			let data = {
				username : $("#username").val()
			}

			console.log(username);
			$.ajax({
				beforeSend : function(xhr) {
					xhr.setRequestHeader(header, token)
				},
				type : "POST",
				url : "/security/join-usernameCheck",
				data : JSON.stringify(data),
				contentType : "application/json; charset=utf-8",
				dataType : "json"
			}).done(function(response) {
				console.log(response)
				if (response.data != null) {
					$("#checkIdResult").text("사용 불가능");
				} else {
					$("#checkIdResult").text("사용 가능");
				}
			}).fail(function(error) {

			});
		});

	});

	function checkPassword() {
		var email = $("#email").val();
		var exptext = new RegExp(/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/);

		if (exptext.test(email) == false) {
			alert("이메일형식이 올바르지 않습니다.");
			$("#email").focus();
			return false;
		}
		if ($("#checkIdResult").text() == "사용 불가능" || $("#checkIdResult").text() == "") {
			alert("아이디 중복확인을 해주세요.");
			return false;
		} else if ($("#checkPasswordResult").text() == "비밀번호가 일치하지 않습니다." || $("#checkPasswordResult") == "") {
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		} else {
			return true;
		}
	}
	
	function handleOnInput(e)  {
		e.value = e.value.replace(/[^A-Za-z0-9-_.]/ig, '')
	}
	
</script>
<script> history.scrollRestoration = "manual" </script>
</body>

</html>