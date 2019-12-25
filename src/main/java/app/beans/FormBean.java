package app.beans;

import app.model.Point;

import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class FormBean implements Serializable, PointBean {
    private int inputX;
    private Double outputX = 0.0d;
    private Double r;
    private BigDecimal y;
    private String result;
    private String unique;

    private PointsBean points;

    public void changeSlider(ValueChangeEvent e) {
        outputX = ((double) inputX) / 10.0d;
    }

    public void addPoint() {
        result = findResult(outputX,y,r);
        unique = UUID.randomUUID().toString();
        Point point = new Point(outputX, y, r, result, unique);
        points.addPoint(point);
    }

    public int getInputX() {
        return inputX;
    }

    public void setInputX(int inputX) {
        this.inputX = inputX;
    }

    public Double getOutputX() {
        return outputX;
    }

    public void setOutputX(Double outputX) {
        this.outputX = outputX;
    }


    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

    public PointsBean getPoints() {
        return points;
    }

    public void setPoints(PointsBean points) {
        this.points = points;
    }
}
