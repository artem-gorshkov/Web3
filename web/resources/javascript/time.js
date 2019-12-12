window.onload = function(){
    window.setInterval(function(){
        let now = new Date();
        let clock = document.getElementById("time");
        clock.innerHTML = now.toLocaleTimeString();
    }, 5000);
};