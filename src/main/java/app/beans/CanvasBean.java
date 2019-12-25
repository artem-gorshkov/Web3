package app.beans;

import app.model.Point;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class CanvasBean implements Serializable, PointBean {
    private Double x;
    private Double r;
    private BigDecimal y;
    private String result;
    private String unique;

    private PointsBean points;

    public void addPoint() {
        result = findResult(x,y,r);
        unique = UUID.randomUUID().toString();
        Point point = new Point(x, y, r, result, unique);
        points.addPoint(point);
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
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
