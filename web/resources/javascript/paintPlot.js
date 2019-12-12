const blue = "#45688E";
const red = "red";
const errorText = "Невозможно определить координаты точки!<br>Укажите R!";

{

    const canvas = document.getElementById("canvas");
    const ctx = canvas.getContext("2d");
    const width = canvas.getAttribute("width");
    const height = canvas.getAttribute("height");
    const form = document.forms[1];
    const canvasForm = document.forms[0];
    let history = document.getElementById("history");
    paintPlot(canvas, ctx, width, height);

    window.onload = function () {
        form.reset(); //drop R on new page`
    };

    console.log(form[form.id + ":r_form"]);

    Array.prototype.forEach.call(form[form.id + ":r_form"], function (elem) {
        //elem.oninput doesn't work on helios. Strange!
        elem.onclick = function (event) {
            document.getElementById("checkedR").innerHTML = "<br><br>";
            paintPlot(canvas, ctx, width, height);
            addDots(event.currentTarget.value, history.value); //repaint dots with new radius
        }
    });

    canvas.onclick = function (event) {
        let R = null;

        Array.prototype.forEach.call(form[form.id + ":r_form"], function (elem) {
            if (elem.checked === true) {
                R = elem;
            }
        });
        if (R == null) {
            document.getElementById("checkedR").innerHTML = errorText; //click with not choosen R
        } else {
            const x = event.pageX - (canvas.getBoundingClientRect().left + pageXOffset);
            const y = event.pageY - (canvas.getBoundingClientRect().top + pageYOffset);


            const cordX = (x - width / 2) * Number(R.value) / Math.round(width / 3);
            const cordY = (height / 2 - y) * Number(R.value) / Math.round(height / 3);

            //draw point
            setColor({'x': cordX, 'y': cordY}, Number(R.value));
            ctx.beginPath();
            ctx.arc(x, y, 3, 0, 2 * Math.PI);
            ctx.fill();

            //send request
            canvasForm[canvasForm.id + ":x_canv"].value = cordX;
            canvasForm[canvasForm.id + ":y_canv"].value = cordY;
            canvasForm[canvasForm.id + ":y_canv"].value = R.value;
            console.log(canvasForm[canvasForm.id + ":x_canv"].value);
            console.log(canvasForm[canvasForm.id + ":y_canv"].value);
            console.log(canvasForm[canvasForm.id + ":y_canv"].value);
            canvasForm.submit();
        }
    };
}

function addDots(R, history, canvas) {
    const ctx = canvas.getContext("2d");
    const width = canvas.getAttribute("width");
    const height = canvas.getAttribute("height");
    let rad = height / 40;
    let rad2 = height / 80;
    Array.prototype.forEach.call(history, function (point) {
        setColor(point, R);
        let x = width / 2 + point['x'] * Math.round(width / 3) / Number(R);
        let y = height / 2 - point['y'] * Math.round(height / 3) / Number(R);
        ctx.beginPath();
        ctx.arc(x, y, 3, 0, 2 * Math.PI);
        ctx.fill();
    });
}

function setColor(point, r) {
    const ctx = canvas.getContext("2d");
    const x = point['x'];
    const y = point['y'];
    if (x < 0) {
        if (y >= 0) {
            if (y < x + r / 2) {
                ctx.fillStyle = red;
            } else {
                ctx.fillStyle = blue;
            }
        } else {
            if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r / 2, 2)) {
                ctx.fillStyle = red;
            } else {
                ctx.fillStyle = blue;
            }
        }
    } else {
        if (y > 0) {
            ctx.fillStyle = blue;
        } else {
            if (x <= r && y >= -r / 2) {
                ctx.fillStyle = red;
            } else {
                ctx.fillStyle = blue;
            }
        }
    }
}

function paintPlot(canvas, ctx, width, height) {
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