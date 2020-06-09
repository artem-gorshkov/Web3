package app;

import app.beans.CanvasBean;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CanvasTests {
    private static final double EPS = 1e-5;
    private static CanvasBean bean;

    private String getMsg(double x, BigDecimal y) {
        return "Failure in point (" + x + ", " + y + ")";
    }

    @BeforeClass
    public static void init() {
        bean = new CanvasBean();
    }

    @Test
    public void test_point_of_origin() {
        double x = 0d;
        BigDecimal y = newY("0");
        double r = 1;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(getMsg(x, y), ans);
    }

    @Test
    public void test_point_top_right_on_bound() {
        double x = 1d;
        BigDecimal y = newY("0");
        double r = 2;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(getMsg(x, y), ans);
    }

    @Test
    public void test_point_right_on_bound() {
        double x = 1.5d;
        BigDecimal y = newY("0");
        double r = 1.5;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(getMsg(x, y), ans);
    }

    @Test
    public void test_point_top_right_out_of_bound() {
        double x = 1;
        BigDecimal y = newY("1.5");
        double r = 2;
        boolean ans = bean.findResult(x, y, r);
        assertFalse(getMsg(x, y), ans);
    }

    @Test
    public void test_point_top_on_bound() {
        double x = 0;
        BigDecimal y = newY("1.5");
        double r = 1.5;
        boolean ans = bean.findResult(x, y, r);
        assertFalse(getMsg(x, y), ans);
    }

    @Test
    public void test_point_top_right_inside_of_bound() {
        double x = 0.7;
        BigDecimal y = newY(0.3);
        double r = 1;
        boolean ans = bean.findResult(x, y, r);
        assertFalse(getMsg(x, y), ans);
    }

    @Test
    public void test_point_top_left_() {
        double x = -0.5;
        BigDecimal y = newY(1d);
        double r = 3;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(getMsg(x, y), ans);
    }

    @Test
    public void test_point_top_on_bound_2() {
        double x = 0d;
        BigDecimal y = newY(1d);
        double r = 2;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(getMsg(x, y), ans);
    }

    @Test
    public void test_point_top_left_on_bound() {
        double x = -1.25;
        BigDecimal y = newY(0);
        double r = 2.5;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(getMsg(x, y), ans);
    }

    @Test
    public void test_point_top_left_inside_of_bound() {
        double x = -0.5;
        BigDecimal y = newY(0.5);
        double r = 3;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(getMsg(x, y), ans);
    }

    @Test
    public void test_point_top_left_inside_of_bound_2() {
        double x = -1;
        BigDecimal y = newY(0.5);
        double r = 3;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(getMsg(x, y), ans);
    }


    @Test
    public void test_point_top_left_out_of_bound() {
        double x = -1;
        BigDecimal y = newY(0.6);
        double r = 3;
        boolean ans = bean.findResult(x, y, r);
        assertFalse(getMsg(x, y), ans);
    }

    @Test
    public void test_point_top_left_out_of_bound_2() {
        double x = -2;
        BigDecimal y = newY(3);
        double r = 2;
        boolean ans = bean.findResult(x, y, r);
        assertFalse(getMsg(x, y), ans);
    }

    @Test
    public void test_point_down_left_out_of_bound() {
        double x = -1;
        BigDecimal y = newY(-1);
        double r = 2;
        boolean ans = bean.findResult(x + EPS, y, r);
        assertFalse(getMsg(x, y), ans);
    }


    @Test
    public void test_point_down_left_out_of_bound_2() {
        double x = -1.1;
        BigDecimal y = newY(-1.3);
        double r = 1;
        boolean ans = bean.findResult(x, y, r);
        assertFalse(getMsg(x, y), ans);
    }

    @Test
    public void test_point_down_left_inside_of_bound() {
        double x = -1;
        BigDecimal y = newY(-1);
        double r = 3;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(getMsg(x, y), ans);
    }

    @Test
    public void test_point_down_left_on_bound() {
        double x = -0.5;
        BigDecimal y = newY(-1.41);
        double r = 3;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(getMsg(x, y), ans);
    }

    @Test
    public void test_point_down_right_inside_of_bound() {
        double x = 1.3;
        BigDecimal y = newY(-0.1);
        double r = 2.5;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(getMsg(x, y), ans);
    }

    @Test
    public void test_point_down_right_on_bound() {
        double x = 0d;
        BigDecimal y = newY(-0.5d);
        double r = 1;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(getMsg(x, y), ans);
    }

    @Test
    public void test_point_down_right_out_of_bound() {
        double x = 3;
        BigDecimal y = newY(-6d);
        double r = 1;
        boolean ans = bean.findResult(x, y, r);
        assertFalse(getMsg(x, y), ans);
    }

    @Test
    public void test_point_down_right_out_of_bound_2() {
        double x = 1d;
        BigDecimal y = newY(-1.6);
        double r = 1.5d;
        boolean ans = bean.findResult(x, y, r);
        assertFalse(getMsg(x, y), ans);
    }

    @Test
    public void test_point_down_right_out_of_bound_3() {
        BigDecimal y = newY(-1.6);
        double x = 1d;
        double r = 1.5d;
        boolean ans = bean.findResult(x, y, r);
        assertFalse(getMsg(x, y), ans);
    }

    @Test
    public void test_point_down_right_on_bound_2() {
        BigDecimal y = newY(-0.75);
        double x = 1.5d;
        double r = 1.5d;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(getMsg(x, y), ans);
    }

    private BigDecimal newY(String str) {
        return new BigDecimal(str);
    }

    private BigDecimal newY(double d) {
        return new BigDecimal(d);
    }
}
