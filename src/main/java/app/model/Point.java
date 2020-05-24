package app.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Objects;


public class Point implements Serializable, Cloneable {

    private Double x;
    private Double r;
    private BigDecimal y;
    private String result;
    private String unique;

    public Point() { }

    public Point(Double x, BigDecimal y, Double r, String result, String unique) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
        this.unique = unique;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null) return false;
        if (getClass() != otherObject.getClass()) return false;
        Point other = (Point) otherObject;
        return Objects.equals(x, other.getX())
                && Objects.equals(y, other.getY())
                && Objects.equals(r, other.getR())
                && Objects.equals(result, other.getResult())
                && Objects.equals(unique, other.getUnique());
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, r, result, unique);
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
}
