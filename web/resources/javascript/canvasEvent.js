const errorText = "Невозможно определить координаты точки!<br>Укажите R!";
const blue = "#45688E";
const red = "red";
const canvas = document.getElementById("canvas");
const ctx = canvas.getContext("2d");
const width = canvas.getAttribute("width");
const height = canvas.getAttribute("height");
const form = document.getElementById("form");
const hiddenForm = document.getElementById("hiddenForm");

function paintPlot() {
    let rad = height / 40;
    let rad2 = height / 80;
    ctx.fillStyle = "white";
    ctx.fillRect(0, 0, Number(width), Number(height)); //do white canvas
    ctx.fillStyle = blue;
    ctx.fillRect(width / 2, height / 2, 2 / 6 * width, 1 / 6 * height);
    ctx.beginPath();
    ctx.arc(width / 2, height / 2, 1 / 6 * height, 1 / 2 * Math.PI, Math.PI);
    ctx.moveTo(width / 2, height / 2);
    ctx.lineTo(width / 2, 4 / 6 * height);
    ctx.lineTo(2 / 6 * width, height / 2);
    ctx.lineTo(width / 2, height / 2);
    ctx.fill();
    ctx.beginPath();
    ctx.moveTo(width / 2, height / 2);
    ctx.lineTo(2 / 6 * width, height / 2);
    ctx.lineTo(width / 2, 2 / 6 * height);
    ctx.lineTo(width / 2, height / 2);
    ctx.fill();
    ctx.beginPath();
    canvas_arrow(ctx, width / 2, height - rad, width / 2, rad);
    canvas_arrow(ctx, rad, height / 2, width - rad, height / 2);
    ctx.strokeText("X", Number(width) - rad, height / 2 - rad / 2);
    ctx.strokeText("Y", width / 2 + rad / 2, rad);
    addMark("-R", width / 2, 5 / 6 * height);
    addMark("-R/2", width / 2, 4 / 6 * height);
    addMark("R/2", width / 2, 2 / 6 * height);
    addMark("R", width / 2, 1 / 6 * height);
    addMark("R/2", 4 / 6 * width, height / 2);
    addMark("R", 5 / 6 * width, height / 2);
    addMark("-R/2", 2 / 6 * width, height / 2);
    addMark("-R", 1 / 6 * width, height / 2);
    ctx.stroke();

    function addMark(label, x, y) {
        if (x === width / 2) {
            ctx.moveTo(x - rad2, y);
            ctx.lineTo(x + rad2, y);
            ctx.strokeText(label, x + rad, y);
        }
        if (y === height / 2) {
            ctx.moveTo(x, y - rad2);
            ctx.lineTo(x, y + rad2);
            ctx.strokeText(label, x, y - rad);
        }
    }

    function canvas_arrow(context, fromx, fromy, tox, toy) {
        let headlen = 10; // length of head in pixels
        let dx = tox - fromx;
        let dy = toy - fromy;
        let angle = Math.atan2(dy, dx);
        context.moveTo(fromx, fromy);
        context.lineTo(tox, toy);
        context.lineTo(tox - headlen * Math.cos(angle - Math.PI / 6), toy - headlen * Math.sin(angle - Math.PI / 6));
        context.moveTo(tox, toy);
        context.lineTo(tox - headlen * Math.cos(angle + Math.PI / 6), toy - headlen * Math.sin(angle + Math.PI / 6));
    }

}

function setColor(point, r) {
    const x = point['x'];
    const y = point['y'];
    if (x <= 0) {
        if (y >= 0) {
            if (y <= x + r / 2) {
                return red;
            } else {
                return blue;
            }
        } else {
            if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r / 2, 2)) {
                return red;
            } else {
                return blue;
            }
        }
    } else {
        if (y > 0) {
            return blue;
        } else {
            if (x <= r && y >= -r / 2) {
                return red;
            } else {
                return blue;
            }
        }
    }
}

function addDots(r, history) {
    let rad = height / 40;
    let rad2 = height / 80;
    Array.prototype.forEach.call(history, function (point) {
        let x = width / 2 + point['x'] * Math.round(width / 3) / Number(r);
        let y = height / 2 - point['y'] * Math.round(height / 3) / Number(r);
        ctx.fillStyle = setColor(point, r);
        ctx.beginPath();
        ctx.arc(x, y, 3, 0, 2 * Math.PI);
        ctx.fill();
    });
}

let curr_R = null;

function newRad() {
    document.getElementById("checkedR").innerHTML = "<br><br>";
    paintPlot();
    curr_R = event.currentTarget.value;
    addDots(Number(curr_R), JSON.parse(document.getElementById("history").innerHTML));
}

function clickOnCanv() {
    if (curr_R == null) {
        document.getElementById("checkedR").innerHTML = errorText; //click with not choosen R
    } else {
        const x = event.pageX - (canvas.getBoundingClientRect().left + pageXOffset);
        const y = event.pageY - (canvas.getBoundingClientRect().top + pageYOffset);

        const cordX = (x - width / 2) * Number(curr_R) / Math.round(width / 3);
        const cordY = (height / 2 - y) * Number(curr_R) / Math.round(height / 3);

        //draw point
        ctx.fillStyle = setColor({'x': cordX, 'y': cordY}, Number(curr_R));
        ctx.beginPath();
        ctx.arc(x, y, 3, 0, 2 * Math.PI);
        ctx.fill();

        //fill hidden form
        hiddenForm[hiddenForm.id + ":x_canv"].value = cordX;
        hiddenForm[hiddenForm.id + ":y_canv"].value = cordY;
        hiddenForm[hiddenForm.id + ":r_canv"].value = curr_R;
        hiddenForm[hiddenForm.id + ":submitCanvas"].click();
    }
}

function repaintPlot() {
    paintPlot();
    Array.prototype.forEach.call(form[form.id + ":r"], function (elem) {
        if (elem.checked === true) {
            curr_R = elem.value;
        }
    });
    if (curr_R != null)
        addDots(Number(curr_R), JSON.parse(document.getElementById("history").innerHTML));
}

{
    Array.prototype.forEach.call(form[form.id + ":r"], function (elem) {
        if (elem.checked === true) {
            curr_R = elem.value;
        }
    });
    repaintPlot();
}