var dateList = [];
var countList = [];

var monthBoard = {
	monthData: function() {
		$.ajax({
			type: 'GET',
			url: '/admin/graph/commu-board',
			contentType: 'application/json',
			dataType: 'json'
		}).done(function(response) {
			$.each(response, function(index, obj) {
				dateList.push(obj.date);
				countList.push(obj.count);
			});
			checkData();
			monthBoard.setDataToInput();
			renderLine('chart-1', resultDateLabels, '게시글 수', resultCountData, 'rgba(102, 0, 142, 1)');
		}).fail(function() {
			console.log("실패");
		});
	}, 
	
	setDataToInput: function (){
		var monthCount = 0;
		$.each(countList, function(index, count){
			monthCount += count;
		});
		$("#month-count").text(monthCount);
	}

};

var resultDateLabels = [];
var resultCountData = [];

function checkData() {
	
	var date = new Date();
	var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
	var lastday = moment(lastDay).format("DD");
	
	console.log(lastDay);
	console.log(lastday);

	for (var i = 1; i <= lastday; i++) {
		resultDateLabels.push(i);
	}

	var index = 0;
	for (var i = 0; i < resultDateLabels.length; i++) {

		if (dateList[index] == resultDateLabels[i]) {
			resultCountData.push(countList[index]);
			index++;
		} else {
			resultCountData.push(0);
		}
	}
}

// 좋아요 주간 top5 
var heartTop5 = {
	idList: [],
	labels: [],
	usernameList: [],
	countData: [],
	

	heartData: function() {
		$.ajax({
			type: 'GET',
			url: '/admin/graph/commu-heart',
			contentType: 'application/json',
			dataType: 'json'
		}).done(function(response) {
			$.each(response, function(index, obj) {
				heartTop5.idList.push(obj.id);
				heartTop5.labels.push(obj.title);
				heartTop5.usernameList.push(obj.username);
				heartTop5.countData.push(obj.count);
			});
			heartTop5.setDataToInput();
			randerBar('chart-4', heartTop5.idList, '좋아요 수', heartTop5.countData, top5HeartBackColor, top5HeartBorderColor);
		}).fail(function() {
			console.log("실패");
		});
	}, 
	
	setDataToInput: function (){
		$.each(heartTop5.idList, function(index, id){
			$(".chart-heart-tbody").append(
				`<tr class="chart-heart-row-${index + 1}">
			        <td>${id}</td>
			      </tr>`
			);
		});
		$.each(heartTop5.labels, function(index, title){
			$(`.chart-heart-row-${index + 1}`).append(
				`<td class="chart-heart-title-td">${title}</td>`
			);
		});
		$.each(heartTop5.usernameList, function(index, username){
			$(`.chart-heart-row-${index + 1}`).append(
				`<td>${username}</td>`
			)
		});
		$.each(heartTop5.countData, function(index, count){
			$(`.chart-heart-row-${index + 1}`).append(
				`<td>${count}</td>`
			)
		});
	}
};

// 댓글 주간 top5 
var replyTop5 = {
	idList: [],
	labels: [],
	usernameList: [],
	countData: [],
	
	replyData: function() {
		$.ajax({
			type: 'GET',
			url: '/admin/graph/commu-reply',
			contentType: 'application/json',
			dataType: 'json'
		}).done(function(response) {
			$.each(response, function(index, obj) {
				replyTop5.idList.push(obj.id);
				replyTop5.labels.push(obj.title);
				replyTop5.usernameList.push(obj.username);
				replyTop5.countData.push(obj.count);
			});
			replyTop5.setDataToInput();
			randerBar('chart-5', replyTop5.idList, '댓글 수', replyTop5.countData, top5ReplyBackColor, top5ReplyBorderColor);
		}).fail(function() {
			console.log("실패");
		});
	}, 
	
	setDataToInput: function (){
		$.each(replyTop5.idList, function(index, id){
			$(".chart-reply-tbody").append(
				`<tr class="chart-reply-row-${index + 1}">
			        <td>${id}</td>
			      </tr>`
			);
		});
		$.each(replyTop5.labels, function(index, title){
			$(`.chart-reply-row-${index + 1}`).append(
				`<td class="chart-heart-title-td">${title}</td>`
			);
		});
		$.each(replyTop5.usernameList, function(index, username){
			$(`.chart-reply-row-${index + 1}`).append(
				`<td>${username}</td>`
			)
		});
		$.each(replyTop5.countData, function(index, count){
			$(`.chart-reply-row-${index + 1}`).append(
				`<td>${count}</td>`
			)
		});
	}
};

var top5HeartBackColor = [
	'rgba(255, 74, 221, 0.7)',
	'rgba(255, 158, 221, 0.7)',
	'rgba(255, 201, 221, 0.7)'
];

var top5HeartBorderColor = [
	'rgba(255, 74, 221, 1)',
	'rgba(255, 158, 221, 1)',
	'rgba(255, 201, 221, 1)'
];

var top5ReplyBackColor = [
	'rgba(54, 213, 0, 0.7)',
	'rgba(135, 218, 46, 0.7)',
	'rgba(182, 255, 104, 0.7)'
];

var top5ReplyBorderColor = [
	'rgba(54, 213, 0, 1)',
	'rgba(135, 218, 46, 1)',
	'rgba(182, 255, 104, 1)'
];

function randerBar(context, labels, label, data, backColor, borderColor) {
	new Chart($(`#${context}`), {
		type: 'bar',
		data: {
			labels: labels,
			datasets: [{
				label: label,
				fill: false,
				data: data,
				backgroundColor: backColor,
				borderColor: borderColor,
				borderWidth: 1
			}]
		},
		options: {
			responsive: true,
			scales: {
				yAxes: [{
					ticks: {
						beginAtZero: true
					}
				}]
			},
			legend: {
				display: false
			}
		}
	});
};

function renderLine(context, labels, label, data, color) {
	new Chart($(`#${context}`), {
		type: 'line',
		data: {
			labels: labels,
			datasets: [{
				label: label,
				data: data,
				fill: false,
				borderWidth: 1,
				lineTension: 0,
				backgroundColor: color,
				borderColor: color
			}],
		},
		options: {
			responsive: true,
			scales: {
				xAxes: [{
					ticks: {
						maxTicksLimit: 7,
						fontSize: 13
					}
				}],
				yAxes: [{
					ticks: {
						min: 0,
						fontSize: 13,
						beginAtZero: true
					}
				}],
			},
			legend: {
				display: false
			}
		}
	});
}

monthBoard.monthData();
heartTop5.heartData();
replyTop5.replyData();
