function start(url){
    var xhr = new XMLHttpRequest();
    xhr.open( "GET", url+"gen", false );
    xhr.send();
    return xhr.responseText;
}
function back(){
    window.location.href = '/';
}