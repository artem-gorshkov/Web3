package app;

import app.beans.CanvasBean;
import app.beans.PointBean;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CanvasTests {
    private static final String msg = "fail";
    private static final Random rand = new Random();
    private static PointBean bean;

    @BeforeClass
    public static void init() {
        bean = new CanvasBean();
    }

    @Test
    public void testFirstClass() {
        boolean ans = bean.findResult(0d, newY("0"), randR());
        assertTrue(msg, ans);
    }

    private double randR() {
        int n = rand.nextInt(5);
        double r;
        if (n == 0) {
            r = 1.5;
        } else if (n == 4) {
            r = 2.5;
        } else
            r = (double) n;
        return r;
    }
    private BigDecimal newY(String str) {
        return new BigDecimal(str);
    }
}
