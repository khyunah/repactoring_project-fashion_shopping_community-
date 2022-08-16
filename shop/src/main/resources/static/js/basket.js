let index2 = {

	init: function() {
		$("#inputCart").bind("click", () => {
			this.putItemCart();
		});

	},

	putItemCart: function() {
		
		let userid = $('#userId').val();
		
		let token = $("meta[name='_csrf']").attr("content");
		let header = $("meta[name='_csrf_header']").attr("content");
		
		let itemId = $("#inputCart").val();

		let data = {
			itemId: $('#inputCart').val()
		}

		$.ajax({
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token)				
			},
			type: "POST",
			url: `/test/api/cart?itemId=${itemId}`,
			dataType: "json"
		})
			.done(function(data) {
				alert("장바구니에 추가되었습니다.");
			})
			.fail(function(error) {
				if(userid != null) {
					alert("장바구니에 추가하지 못했습니다.");
				} else {
					if(confirm("로그인이 필요한 서비스입니다. 로그인 하시겠습니까?")) {
						location.href="/security/login_form";
					} 
					
				}
				
				
				
			});

	},

}

index2.init();

function basketItemDelete(basket) {
	let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");

	$.ajax({
		beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token)				
		},
		type: "DELETE",
		url: `/test/api/basket/${basket}`,
		dataType: "json"
	}).done(function(res) {
		console.log();
		location.href = `/shop/basket_form/${res.data}`
	}).fail(function() {
		alert("취소 실패");
	});
}

$(document).ready(function(){
	
	// 품절 상품 정리 안내
	if($('#soldoutInput').val()){
		if(confirm('품절된 상품이 있습니다. 정리하시겠습니까?')){
			soldoutDelete();
		}
	}
});

function soldoutDelete() {
	let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");
	
	$.ajax({
		beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token)				
		},
		type: "DELETE",
		url: `/basket-soldout/delete`,
		dataType: "json"
	}).done(function(res) {
		location.href = `/shop/basket_form/${res.data}`
	}).fail(function() {
		alert("취소 실패");
	});
}
