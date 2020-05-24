package app;

import app.beans.CanvasBean;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CanvasTests {
    private static final String MSG = "fail";
    private static final double EPS = 1e-5;
    private static final int iterations = 10;
    private static final Random rand = new Random();
    private static CanvasBean bean;

    @BeforeClass
    public static void init() {
        bean = new CanvasBean();
    }

    @Test
    public void quater1test1() {
        double x = 0d;
        BigDecimal y = newY("0");
        double r = 1;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(MSG, ans);
    }

    @Test
    public void quater1test2() {
        double x = 1d;
        BigDecimal y = newY("0");
        double r = 2;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(MSG, ans);
    }

    @Test
    public void quater1test3() {
        double x = 1.5d;
        BigDecimal y = newY("0");
        double r = 1.5;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(MSG, ans);
    }

    @Test
    public void quater1test4() {
        double x = 1;
        BigDecimal y = newY("1.5");
        double r = 2;
        boolean ans = bean.findResult(x, y, r);
        assertFalse(MSG, ans);
    }

    @Test
    public void quater1test5() {
        double x = 0;
        BigDecimal y = newY("1.5");
        double r = 1.5;
        boolean ans = bean.findResult(x, y, r);
        assertFalse(MSG, ans);
    }

    @Test
    public void quater1test6() {
        double x = 0.7;
        BigDecimal y = newY(0.3);
        double r = 1;
        boolean ans = bean.findResult(x, y, r);
        assertFalse(MSG, ans);
    }

    @Test
    public void quater2test1() {
        double x = -0.5;
        BigDecimal y = newY(1d);
        double r = 3;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(MSG, ans);
    }

    @Test
    public void quater2test2() {
        double x = 0d;
        BigDecimal y = newY(1d);
        double r = 2;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(MSG, ans);
    }

    @Test
    public void quater2test3() {
        double x = -1.25;
        BigDecimal y = newY(0);
        double r = 2.5;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(MSG, ans);
    }

    @Test
    public void quater2test4() {
        double x = -0.5;
        BigDecimal y = newY(0.5);
        double r = 3;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(MSG, ans);
    }

    @Test
    public void quater2test5() {
        double x = -1;
        BigDecimal y = newY(0.5);
        double r = 3;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(MSG, ans);
    }


    @Test
    public void quater2test6() {
        double x = -1;
        BigDecimal y = newY(0.6);
        double r = 3;
        boolean ans = bean.findResult(x, y, r);
        assertFalse(MSG, ans);
    }

    @Test
    public void quater2test7() {
        double x = -2;
        BigDecimal y = newY(3);
        double r = 2;
        boolean ans = bean.findResult(x, y, r);
        assertFalse(MSG, ans);
    }

    @Test
    public void quater3test1() {
        double x = -1;
        BigDecimal y = newY(-1);
        double r = 2;
        boolean ans = bean.findResult(x + EPS, y, r);
        assertFalse(MSG, ans);
    }


    @Test
    public void quater3test2() {
        double x = -1.1;
        BigDecimal y = newY(-1.3);
        double r = 1;
        boolean ans = bean.findResult(x, y, r);
        assertFalse(MSG, ans);
    }

    @Test
    public void quater3test3() {
        double x = -1;
        BigDecimal y = newY(-1);
        double r = 3;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(MSG, ans);
    }

    @Test
    public void quater3test4() {
        double x = -0.5;
        BigDecimal y = newY(-1.41);
        double r = 3;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(MSG, ans);
    }

    @Test
    public void quater4test1() {
        double x = 1.3;
        BigDecimal y = newY(-0.1);
        double r = 2.5;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(MSG, ans);
    }

    @Test
    public void quater4test2() {
        double x = 0d;
        BigDecimal y = newY(-0.5d);
        double r = 1;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(MSG, ans);
    }

    @Test
    public void quater4test3() {
        double x = 3;
        BigDecimal y = newY(-6d);
        double r = 1;
        boolean ans = bean.findResult(x, y, r);
        assertFalse(MSG, ans);
    }

    @Test
    public void quater4test4() {
        double x = 1d;
        BigDecimal y = newY(-1.6);
        double r = 1.5d;
        boolean ans = bean.findResult(x, y, r);
        assertFalse(MSG, ans);
    }

    @Test
    public void quater4test5() {
        BigDecimal y = newY(-1.6);
        double x = 1d;
        double r = 1.5d;
        boolean ans = bean.findResult(x, y, r);
        assertFalse(MSG, ans);
    }

    @Test
    public void quater4test6() {
        BigDecimal y = newY(-0.75);
        double x = 1.5d;
        double r = 1.5d;
        boolean ans = bean.findResult(x, y, r);
        assertTrue(MSG, ans);
    }

    private BigDecimal newY(String str) {
        return new BigDecimal(str);
    }

    private BigDecimal newY(double d) {
        return new BigDecimal(d);
    }
}
