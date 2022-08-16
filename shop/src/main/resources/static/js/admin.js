function clickList(target) {
	let list = target.parentNode;
	let trs = list.getElementsByTagName("tr");

	// 선택안된 
	var backColor = "#212529";
	var textColor = "#ffffff";
	// 선택 된
	var orgBColor = "black";
	var orgTColor = "#ffffff";

	let th_value = [];

	for (var i = 0; i < trs.length; i++) {
		if (trs[i] != target) {
			trs[i].style.backgroundColor = backColor;
			trs[i].style.color = textColor;
		} else {
			trs[i].style.backgroundColor = orgBColor;
			trs[i].style.color = orgTColor;
			var td = trs[i].getElementsByTagName("td");
			for (let j = 0; j < td.length; j++) {
				th_value[j] = td[0].innerText;
			}
		}
	}
	var id = th_value[0];
	$("#admin-object-id").text(id);
}

$(document).ready(function() {
	let column = $("#column").val();
	if(column != ''){
		$("#sel1").val(column).prop("selected", true);
	} else {
		$("#sel1").val('선택').prop("selected", true);
	}
	
	if(column == 'ID') {
		$('#keyword').attr("type", "number");
	} else if (column == 'OAUTH' || column == 'GENDER') {
		$('#keyword').attr("readonly", true);
	} else if (column == 'CATEGORY') {
		$('#keyword').attr("readonly", true);
		addCategory($("#keyword").val());
	}
	
});

function addCategory(keyword) {
	var categoryList = ['OUTER', 'SHIRTS', 'PANTS', 'SKIRT', 'ONEPIECE', 'SHOES', 'ACCESSORY'];
	var option = "";
	$.each(categoryList, function(index, category){
		if(category == keyword){
			option += `<option selected>${category}</option>`;
		} else {
			option += `<option>${category}</option>`;
		}
	});

	var selectBox = 
		`<select class="form-control categorySelectBox" onchange="chooseCategory(this)">
			<option>선택</option>
			${option}
		</select>`;
		
	$(".columnBox").append(selectBox);
}

function chooseUserColumn(target) {
	$(".oauthSelectBox").remove();
	$('#keyword').attr("type", "text");
	$('#keyword').attr("readonly", false);
	$('#keyword').val('');
	
	let column = target.options[target.selectedIndex].text;
	$("#column").val(column);

	if (column == 'OAUTH') {
		$('#keyword').attr("readonly", true);
		addSelectBoxOauth();
	} else if(column == 'ID'){
		$('#keyword').attr("type", "number");
	} 
}

function chooseShoppingColumn(target){
	$('#keyword').attr("type", "text");
	$('#keyword').attr("readonly", false);
	$(".genderSelectBox").remove();
	$(".categorySelectBox").remove();
	$('#keyword').val('');
	
	let column = target.options[target.selectedIndex].text;
	$("#column").val(column);

	if (column == 'GENDER') {
		$('#keyword').attr("readonly", true);
		addSelectBoxGender();
	} else if(column == 'ID'){
		$('#keyword').attr("type", "number");
	} else if (column == 'CATEGORY') {
		$('#keyword').attr("readonly", true);
		addSelectBoxCategory();
	}
}

function chooseColumn(target) {
	$('#keyword').attr("type", "text");
	$('#keyword').val('');
	
	let column = target.options[target.selectedIndex].text;
	$("#column").val(column);
	
	if(column == 'ID'){
		$('#keyword').attr("type", "number");
	}
}

function addSelectBoxOauth() {
	let selectBox = `
			<select class="form-control oauthSelectBox" onchange="chooseOauth(this)">
  			  <option>선택</option>
  			  <option>ORIGIN</option>
  			  <option>KAKAO</option>
  			</select>
		`;
	$(".columnBox").append(selectBox);
}

function addSelectBoxGender(){
	let selectBox = `
			<select class="form-control genderSelectBox" onchange="chooseGender(this)">
  			  <option>선택</option>
  			  <option>MAN</option>
  			  <option>WOMAN</option>
  			</select>
		`;
	$(".columnBox").append(selectBox);
}

function addSelectBoxCategory(){
	let selectBox = `
			<select class="form-control categorySelectBox" onchange="chooseCategory(this)">
  			  <option>선택</option>
  			  <option>OUTER</option>
  			  <option>SHIRTS</option>
  			  <option>PANTS</option>
  			  <option>SKIRT</option>
  			  <option>ONEPIECE</option>
  			  <option>SHOES</option>
  			  <option>ACCESSORY</option>
  			</select>
		`;
	$(".columnBox").append(selectBox);
}

function chooseOauth(target) {
	let oauth = target.options[target.selectedIndex].text;
	location.href = `/admin/user/select?keyword=` + oauth + `&column=OAUTH`;
}

function chooseGender(target) {
	let gender = target.options[target.selectedIndex].text;
	location.href = `/admin/shopping/select?keyword=` + gender + `&column=GENDER`;
}

function chooseCategory(target) {
	let category = target.options[target.selectedIndex].text;
	location.href = `/admin/shopping/select?keyword=` + category + `&column=CATEGORY`;
}

function checkColumn() {
	let column = $("#column").val();
	
	if(column == '') {
		alert('분류를 선택해주세요 !');
		return false;
	} 
	return true;
}

let admin = {
	userDelete: function() {
		let token = $("meta[name='_csrf']").attr("content");
		let header = $("meta[name='_csrf_header']").attr("content");
		
		let id = $("#admin-object-id").text();

		if (id != '') {
			let result = confirm('해당 회원을 삭제할까요?');
			if (result) {
				$.ajax({
					beforeSend: function(xhr) {
						xhr.setRequestHeader(header, token)
					},
					type: "DELETE",
					url: `/admin/user/delete/${id}`,
					dataType: "json"
				}).done(function(response) {
					location.href = response.data;
				}).fail(function(error) {
					alert("유저 삭제 실패");
				})
			}
		} else {
			alert("회원을 선택해주세요");
		}

	},

	userChangeRole: function() {
		let token = $("meta[name='_csrf']").attr("content");
		let header = $("meta[name='_csrf_header']").attr("content");
		
		let id = $("#admin-object-id").text();

		if (id != '') {
			let result = confirm('유저에게 권한을 부여할까요?\n[ 확인 - 관리자     취소 - 회원 ]');
			if (result != null) {
				$.ajax({
					beforeSend: function(xhr) {
						xhr.setRequestHeader(header, token)
					},
					type: "PUT",
					url: `/admin/user/change-role/${id}/${result}`,
					dataType: "json"
				}).done(function(response) {
					alert("권한설정 완료");
					location.href = response.data;
				}).fail(function(error) {
					alert("권한설정 실패");
				})
			}
		} else {
			alert("회원을 선택해주세요.");
		}


	},

	communityDelete: function() {
		let token = $("meta[name='_csrf']").attr("content");
		let header = $("meta[name='_csrf_header']").attr("content");
		
		let id = $("#admin-object-id").text();

		if (id != '') {
			let result = confirm('해당 글을 삭제할까요?');
			if (result) {
				$.ajax({
					beforeSend: function(xhr) {
						xhr.setRequestHeader(header, token)
					},
					type: "DELETE",
					url: `/admin/community/delete/${id}`,
					dataType: "json"
				}).done(function(response) {
					location.href = "/admin/community/select?keyword=&column=";
				}).fail(function(error) {
					alert("해당 글 삭제 실패");
				})
			}
		} else {
			alert("게시글을 선택해주세요.");
		}


	},

	shoppingDelete: function() {
		let token = $("meta[name='_csrf']").attr("content");
		let header = $("meta[name='_csrf_header']").attr("content");

		let id = $("#admin-object-id").text();

		if (id != '') {
			let result = confirm('해당 아이템을 삭제할까요?');
			if (result) {
				$.ajax({
					beforeSend: function(xhr) {
						xhr.setRequestHeader(header, token)
					},
					type: "DELETE",
					url: `/admin/shopping-item/delete/${id}`,
					dataType: "json"
				}).done(function(response) {
					history.go(-1);
				}).fail(function(error) {
					alert("해당 상품 삭제 실패");
				})
			}
		} else {
			alert("상품을 선택해주세요.");
		}
	},

	shoppingUpdateForm: function() {
		let id = $("#admin-object-id").text();
		if (id != '') {
			location.href = `/admin/shopping-item/update_form/${id}`;
		} else {
			alert("수정할 상품을 선택해주세요.");
		}
	},

	shoppingDetail: function() {
		let id = $("#admin-object-id").text();
		if (id != '') {
			location.href = `/admin/shopping/item-detail/${id}`;
		} else {
			alert("상품을 선택해주세요.");
		}
	},
	
	communityDetail: function() {
		let id = $("#admin-object-id").text();
		
		if (id != '') {
			location.href = `/admin/community-detail/${id}`;
		} else {
			alert("게시글을 선택해주세요.");
		}
	}
}

