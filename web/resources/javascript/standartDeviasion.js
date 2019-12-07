"use strict"
let script_time = [];

Array.prototype.forEach.call(document.getElementsByClassName("script_time"), el => {
    script_time.push(el.textContent);
});

if (script_time.length !== 0) {
    document.getElementById("stand_deviation").innerHTML = "Время стандартного отклонения работы скрипта: " + calc_stand_deviation(script_time) + " мс.";
}

function calc_stand_deviation(arr) {
    let sum = 0;
    Array.prototype.forEach.call(arr, (el) => {
        sum = sum + parseFloat(el);
    });
    let mid_arifm = sum / arr.length;
    let sum_of_sq = 0;
    Array.prototype.forEach.call(arr, el => {
        sum_of_sq += Math.pow((parseFloat(el) - mid_arifm), 2);
    });
    return Math.round(Math.sqrt(sum_of_sq / arr.length) * Math.pow(10,6)) / Math.pow(10,6);
}