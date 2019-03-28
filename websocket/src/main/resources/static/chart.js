var stompClient = null;
var demoChart = null;

var option = {
	xAxis : {
		type : 'category',
		data : [ 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun' ]
	},
	yAxis : {
		type : 'value'
	},
	series : [ {
		data : [ 820, 932, 901, 934, 1290, 1330, 1320 ],
		type : 'line'
	} ]
};

function connect() {
	var socket = new SockJS('/websocket-endpoint');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/linechart', function(res) {
			updateChart(JSON.parse(res.body).data);
		});
	});
}

function updateChart(data) {
	var label = option.xAxis.data;
	var tmp = label.shift();
	label.push(tmp);
	
	var series0 = option.series[0].data;
	series0.shift();
	series0.push(data);
	
	demoChart.setOption(option);
}

$(function() {
	demoChart = echarts.init(document.getElementById('main'));
	demoChart.setOption(option);

	connect();
});