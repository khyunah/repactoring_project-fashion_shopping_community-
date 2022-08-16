let token = $("meta[name='_csrf']").attr("content");
let header = $("meta[name='_csrf_header']").attr("content");

let domain;

let index = {
	getDomain: function() {
		$.ajax({
			type: "get",
			url: "/test/api/getDomain",
			dateType: "json",
			success: function(res) {
				domain = res
				index.genderCategory();
				index.init();
			}
		}).fail(function() {
			index.getDomain();
		});
	},

	init: function() {

		$("#btn-save").bind("click", () => {
			this.save();
		});
		$("#gender").change("change", () => {
			this.genderCategory();
		});
		$("#btn-reply-save").bind("click", () => {
			this.replySave();
		});
		$("#admin-shopping-btn-update").bind("click", () => {
			this.updateItem();
		});
	},

	genderCategory: function() {
		if ($("#gender").val() == 'MAN') {
			loadCategory('MAN');
			$("#category").change("change", () => {
				console.log("체인지 카테고리 에서 실행 ");
				categorySize();
			});

		} else if ($("#gender").val() == 'WOMAN') {
			loadCategory('WOMAN');
			$("#category").change("change", () => {
				console.log("체인지 카테고리 에서 실행 ");
				categorySize();
			});
		}

	},

	save: function() {
		let token = $("meta[name='_csrf']").attr("content");
		let header = $("meta[name='_csrf_header']").attr("content");

		// 데이터 가져오기
		let data = {
			category: $("#category").val(),
			content: $("#content").val(),
			gender: $("#gender").val(),
			imageurl: $("#imageurl").val(),
			name: $("#name").val(),
			price: $("#price").val(),
			size: $("#size").val(),
			amount: $("#amount").val()
		}

		console.log("데이터 확인");
		console.log(data);

		$.ajax({
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token)
			},
			type: "POST",
			url: "/api/item",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		})
			.done(function(response) {

				alert("새로운 제품을 추가했습니다.");
				history.back();
				let historyCheck = $("#historyCheck").val();
				if(historyCheck == 'admin'){
					location.href = `/admin/shopping/item-detail/${response.data.id}`;
				} else {
					location.href = `/shop/itemdetail_form/${response.data.id}`;
				}

			})
			.fail(function(error) {
				alert("제품 추가를 실패했습니다.");
			});
	},

	updateItem: function() {
		let token = $("meta[name='_csrf']").attr("content");
		let header = $("meta[name='_csrf_header']").attr("content");

		let id = $("#itemId").val();
		let data = {
			id: id,
			category: $("#category").val(),
			content: $("#content").val(),
			gender: $("#gender").val(),
			imageurl: $("#imageurl").val(),
			name: $("#name").val(),
			price: $("#price").val(),
			size: $("#size").val(),
			amount: $("#amount").val()
		}

		$.ajax({
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token)
			},
			type: "POST",
			url: "/api/item",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(response) {
			if (response.status != null) {
				alert("상품 수정이 완료되었습니다.");
				location.href = `/admin/shopping/item-detail/${id}`;
			}
		}).fail(function() {
			alert("상품 수정에 실패했습니다.");
		});
	}

}
index.getDomain();

function loadCategory(gender) {
	var cell = document.getElementById("category");
	while (cell.hasChildNodes()) {
		cell.removeChild(cell.firstChild);
	}
	let category = domain.genderCategory[gender];

	let name = $("#name").val();

	category.forEach((item) => {
		if (name != null) {
			let itemCategory = $("#itemCategory").val();
			if (item == itemCategory) {
				let option = `<option value="${item}" selected>${item}</option>`;
				$("#category").append(option);
			} else {
				let option = `<option value="${item}">${item}</option>`;
				$("#category").append(option);
			}
		} else {
			let option = `<option value="${item}">${item}</option>`;
			$("#category").append(option);
		}
	});

}

function categorySize() {

	var cell = document.getElementById("size");
	while (cell.hasChildNodes()) {
		cell.removeChild(cell.firstChild);
	}
	let size = domain.categorySize[$("#category").val()];

	size.forEach((item) => {
		let option = `<option value="${item}">${item}</option>`;
		$("#size").append(option);
	});
}

function categoryColor() {
	let size = $("#itemSize").val();
	$("#size").val(size).prop('selected', true);
}

function thisSize() {
	$("#size").bind('click', () => {
		categorySize();
	});
}

if ($("#name").val() != null) {
	categoryColor();
	thisSize();
}

window.onload = function () {
  itemDetailSizeandColor();
};

function itemDetailSizeandColor(){
		
		let data = {
			name :$("#itemname").val(),
			gender: $("#itemgender").val()
		}

		$.ajax({
			type: "POST",
			url: `/test/api/itemdetail`,
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
			dataType: "json"
		}).done(function(data) {
			var sizes = [];
			for(var i = 0; i < data.length; i++){
				if(sizes.indexOf(data[i].size) == -1){
					sizes.push(data[i].size);
					let option =`<option value='${data[i].size}'>${data[i].size}</option>`;
					$("#sizes").append(option);
				}
			}
					
		})
		.fail(function(error) {
		});
}

function reviewWrite(){
	
	
		var cell = document.getElementById("writeReview");
		while (cell.hasChildNodes()) {
		cell.removeChild(cell.firstChild);
	}
	
	
		let option = `
    <div class="form-group">
      <label for="image">image</label>
      <div class="input-group">
        <input type="file" name="file" class="form-control" id="image" required="required" accept=".jpg, .jpeg, .png, .gif">
        <label class="custom-file-label" for="customFile"></label>
      </div>
    </div>

    <div class="form-group mt-3">
      <label for="content">content</label>
      <textarea class="form-control" rows="5" id="content" name="content" placeholder="내용을 입력하세요"></textarea>
    </div>
    <div class="form-group mt-3">
  <button type="submit" id="save" class="btn btn-dark" style="background-color: #453675;">글 쓰기 완료</button>
	</div>
  `;
		$("#writeReview").append(option);
}

function ItemReviewDelete(itemreview, item) {
	let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");

	$.ajax({
		beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token)				
		},
		type: "DELETE",
		url: `/test/api/itemReview/${itemreview}`,
		dataType: "json"
	}).done(function() {
		location.href = `/shop/itemdetail_form/${item}`
	}).fail(function() {
		alert("취소 실패");
	});
}


function updateBtnReview(content, imageUrl) {
	updateReview(content, imageUrl)
	
	
}



function updateReview(content){
	
	
		var cell = document.getElementById("updateReview");
		while (cell.hasChildNodes()) {
		cell.removeChild(cell.firstChild);
	}
	
	
		let option = `
    <div class="form-group">
      <label for="image">image</label>
      <div class="input-group">
        <input id="filetext"type="file" name="file" class="form-control" id="image" accept=".jpg, .jpeg, .png, .gif">
        <label class="custom-file-label" for="customFile"></label>
      </div>
    </div>

    <div class="form-group mt-3">
      <label for="content">content</label>
      <textarea class="form-control" rows="5" id="content" name="content" placeholder="${content}"></textarea>
    </div>
    <div class="form-group mt-3">
  <button type="submit" onclick="ReviewUpdate();" class="btn btn-dark" style="background-color: #453675; ">글 수정 완료</button>
	</div>
  `;
		$("#updateReview").append(option);
}


