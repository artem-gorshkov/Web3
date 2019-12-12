package app.beans;

import javax.faces.annotation.ManagedProperty;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@ManagedBean(name = "point")
@RequestScoped
public class PointBean implements Serializable, Cloneable {

    @ManagedProperty(value = "#{points}")
    private PointsBean pointsBean;
    private double x;
    private double r;
    private BigDecimal y;
    private String result;
    private String unique;

    public PointBean() {
    }

    public PointBean(double x, BigDecimal y, double r, String result, String unique) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
        this.unique = unique;
    }

    public void addPoint() throws SQLException {

        double Y = Double.parseDouble(y.toString().substring(0, Math.min(10, y.toString().length())));
        if (x < 0) {
            if (Y >= 0) {
                if (Y < x + r / 2) {
                    result = "В зоне";
                } else {
                    result = " Не в зоне";
                }
            } else {
                if (Math.pow(x, 2) + Math.pow(Y, 2) <= Math.pow(r / 2, 2)) {
                    result = "В зоне";
                } else {
                    result = " Не в зоне";
                }
            }
        } else {
            if (Y > 0) {
                result = " Не в зоне";
            } else {
                if (x <= r && Y >= -r / 2) {
                    result = "В зоне";
                } else {
                    result = " Не в зоне";
                }
            }
        }
       unique = UUID.randomUUID().toString();
        try {
            PointBean last = pointsBean.getData().getFirst();
            if (!last.equals(this)) //add if not reload page
                pointsBean.addPoint((PointBean) this.clone());
        } catch (NoSuchElementException|NullPointerException e) {
            pointsBean.addPoint((PointBean) this.clone());
            e.printStackTrace();
        }

    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null) return false;
        if (getClass() != otherObject.getClass()) return false;
        PointBean other = (PointBean) otherObject;
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

    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public PointsBean getPointsBean() {
        return pointsBean;
    }

    public void setPointsBean(PointsBean pointsBean) {
        this.pointsBean = pointsBean;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
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
