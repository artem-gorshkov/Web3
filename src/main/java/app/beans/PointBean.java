package app.beans;

import java.math.BigDecimal;

public interface PointBean {
    static final String in ="В зоне";
    static final String out ="Не в зоне";
    void addPoint();
    default String findResult(Double x, BigDecimal y, Double r) {
        double Y = Double.parseDouble(y.toString()); //for compare with x and r
        if (x <= 0) {
            if (Y >= 0) {
                if (Y <= x + r / 2) {
                    return in;
                } else {
                    return out;
                }
            } else {
                if (Math.pow(x, 2) + Math.pow(Y, 2) <= Math.pow(r / 2, 2)) {
                    return in;
                } else {
                    return out;
                }
            }
        } else {
            if (Y > 0) {
                return out;
            } else {
                if (x <= r && Y >= -r / 2) {
                    return in;
                } else {
                    return out;
                }
            }
        }
    }
}
