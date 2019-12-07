"use strict"
let er = document.getElementById("error");
let textField = document.getElementById("textfieldY");
let Xfield = document.getElementsByName("X");
let Rfield = document.getElementsByName("R");

function valid() {
    let validX = false;
    let validR = false;
    Array.prototype.forEach.call(Xfield, function (button) {
        if (button.checked) validX = true;
    });
    Array.prototype.forEach.call(Rfield, function (button) {
        if (button.checked) validR = true;
    });
    let value = textField.value.substring(0, 10);
    value = value.replace(",", ".");
    if (value === "" || isNaN(value) || value <= -5 || value >= 3) {
        er.innerHTML = "Значение Y должно быть в диапазоне (-5;3)";
        textField.style.borderColor = "red";
        return false;
    }
    if (!validX) {
        er.innerText = "Укажите значение X";
        return false;
    }
    if (!validR) {
        er.innerHTML = "Укажите значение R";
        return false;
    }
    return true;
}
