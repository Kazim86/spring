var chart;
var graph = {id:[],value:[],time:[],date:[]};
var stompClient = null;

document.addEventListener("DOMContentLoaded", function(){
    InitGraph();
});
function view(){
    if(document.getElementById("graph").style.display == 'none'){
        document.getElementById("graph").style.display = '';
        document.getElementById("table").style.display = 'none';
        document.getElementById("select").style.display = 'none';
        document.getElementById("buttonConn").style.display = '';
        document.getElementById("buttonClos").style.display = '';
    } else {
        document.getElementById("graph").style.display = 'none';
        document.getElementById("table").style.display = '';
        document.getElementById("select").style.display = '';
        document.getElementById("buttonConn").style.display = 'none';
        document.getElementById("buttonClos").style.display = 'none';
    }
}
function InitGraph(){
    chart = new Chart(document.getElementById('chart'),{
        type: 'line',
        data: {labels: [], datasets:[{label:'TEST',backgroundColor:'rgb(255, 99, 132)',borderColor:'rgb(255, 99, 132)',data:[],}]},
        options: {}
    });
}
function addValueGraph(obj){
    if(chart.data.labels.length == 20){
        chart.data.labels.shift();
        chart.data.datasets[0].data.shift();
    }
    chart.data.labels.push(obj.id)
    chart.data.datasets[0].data.push(obj.value)
    chart.update();
}

function conn(){
    var socket = new SockJS('register');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/message', function(value) {
            addValueGraph(JSON.parse(value.body));
        });
    });
    document.getElementById("buttonGraph").style.display = 'none';
    document.getElementById("buttonConn").style.display = 'none';
}
function clos(){
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
    document.getElementById("buttonGraph").style.display = '';
    document.getElementById("buttonConn").style.display = '';
}