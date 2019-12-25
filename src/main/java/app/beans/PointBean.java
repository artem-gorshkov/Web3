package app.beans;

import app.model.Point;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.math.BigDecimal;

@ManagedBean(name = "point")
@RequestScoped
public class PointBean implements Serializable {
    private Point point;

    private double x;
    private double r;
    private BigDecimal y;
    private String result;
    private String unique;
}
