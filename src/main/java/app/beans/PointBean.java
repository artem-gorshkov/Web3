package app.beans;

import java.math.BigDecimal;

public interface PointBean {
    String in = "В зоне";
    String out = "Не в зоне";

    void addPoint();

    default boolean findResult(Double x, BigDecimal y, Double r) {
        double Y = Double.parseDouble(y.toString()); //for compare with x and r
        if (x <= 0) {
            if (Y >= 0) {
                return Y <= x + r / 2;
            } else {
                return Math.pow(x, 2) + Math.pow(Y, 2) <= Math.pow(r / 2, 2);
            }
        } else {
            if (Y > 0) {
                return false;
            } else {
                return x <= r && Y >= -r / 2;
            }
        }
    }
}
