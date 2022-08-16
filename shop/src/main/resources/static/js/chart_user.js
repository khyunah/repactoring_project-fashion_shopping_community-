var context = $("#myChart");

var chartArea = {
	labels: [],
	dataSets: [],
	render: function() {
		new Chart(context, {
			type: 'line',
			data: {
				labels: dateRange,
				datasets: [{
					label: "가입자 수",
					data: resultData,
					fill: false,
					borderWidth: 1,
					lineTension: 0,
					backgroundColor: "rgba(255, 0, 0, 1)",
					borderColor: "rgba(255, 0, 0, 1)"
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
							fontSize: 13
						}
					}],
				},
				legend: {
					display: false
				}
			}
		});
	},

	joinData: function() {
		$.ajax({
			type: 'GET',
			url: '/admin/graph/join-count/week',
			contentType: 'application/json',
			dataType: 'json'
		}).done(function(response) {
			$.each(response, function(index, obj) {
				chartArea.labels.push(obj.joinDate);
				chartArea.dataSets.push(obj.joinCount);
			});
			checkData();
			setTableData();
			chartArea.render();
		}).fail(function() {
			console.log("실패");
		});
	}

};

var oauthArea = {
	labels: [],
	dataSets: [],
	render: function() {
		new Chart($("#third-chart"), {
			type: 'bar',
			data: {
				labels: oauth,
				datasets: [{
					label: 'OAuth별 가입자 수',
					fill: false,
					data: oauthResultData,
					backgroundColor: [
						'rgba(180, 0, 255, 0.4)',
						'rgba(255, 206, 86, 0.4)'
						],
					borderColor: [
						'rgba(180, 0, 255, 1)',
						'rgba(255, 206, 86, 1)'
						],
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
	},

	oauthData: function() {
		$.ajax({
			type: 'GET',
			url: '/admin/graph/join-count/oauth',
			contentType: 'application/json',
			dataType: 'json'
		}).done(function(response) {
			$.each(response, function(index, obj) {
				oauthArea.labels.push(obj.oauth);
				oauthArea.dataSets.push(obj.oauthCount);
			});
			checkOauth();
			oauthArea.render();
		}).fail(function() {
			console.log("실패");
		});
	}

};

var oauthTodayArea = {
	labels: [],
	dataSets: [],
	render: function() {
		new Chart($("#second-chart"), {
			type: 'bar',
			data: {
				labels: oauth,
				datasets: [{
					label: '오늘 가입자 수',
					fill: false,
					data: oauthTodayResultData,
					backgroundColor: [
						'rgba(180, 0, 255, 0.4)',
						'rgba(255, 206, 86, 0.2)'
						],
					borderColor: [
						'rgba(180, 0, 255, 1)',
						'rgba(255, 206, 86, 1)'
						],
					borderWidth: 1
				}]
			},
			options: {
				responsive: true,
				scales: {
					yAxes: [{
						ticks: {
							beginAtZero: true,
							min: 0,
						}
					}]
				},
				legend: {
					display: false
				}
			}
		});
	},

	oauthTodayData: function() {
		$.ajax({
			type: 'GET',
			url: '/admin/graph/join-count/oauth-today',
			contentType: 'application/json',
			dataType: 'json'
		}).done(function(response) {
			$.each(response, function(index, obj) {
				oauthTodayArea.labels.push(obj.oauth);
				oauthTodayArea.dataSets.push(obj.oauthCount);
			});
			checkTodayOauth();
			oauthTodayArea.render();
		}).fail(function() {
			console.log("실패");
		});
	}

};

var dateRange = [];
var checkDate = [];
var resultData = [];

function checkData() {

	// 날짜 x축 
	for (var i = 6; i >= 0; i--) {
		dateRange.push(moment().subtract(i, 'd').format("MM-DD"));
		checkDate.push(moment().subtract(i, 'd').format("YYYY-MM-DD"));
	}

	var index = 0;
	for (var i = 0; i < checkDate.length; i++) {

		if (chartArea.labels[index] == checkDate[i]) {
			resultData.push(chartArea.dataSets[index]);
			index++;
		} else {
			resultData.push(0);
		}
	}
}

// 회원가입 수 테이블 데이터 세팅
function setTableData(){
	$("#today-count").text(resultData[6]);
	
	var weekCount = 0;
	$.each(chartArea.dataSets, function(index, count){
		weekCount += count;
	});
	$("#week-count").text(weekCount);
}

var oauth = [];
var oauthResultData = [];

function checkOauth() {

	oauth = ['ORIGIN', 'KAKAO'];

	var index = 0;
	for (var i = 0; i < oauth.length; i++) {

		if (oauthArea.labels[index] == oauth[i]) {
			oauthResultData.push(oauthArea.dataSets[index]);
			index++;
		} else {
			oauthResultData.push(0);
		}
	}
}

var oauthTodayResultData = [];

function checkTodayOauth() {

	var index = 0;
	for (var i = 0; i < oauth.length; i++) {

		if (oauthTodayArea.labels[index] == oauth[i]) {
			oauthTodayResultData.push(oauthTodayArea.dataSets[index]);
			index++;
		} else {
			oauthTodayResultData.push(0);
		}
	}
}

chartArea.joinData();
oauthArea.oauthData();
oauthTodayArea.oauthTodayData();
