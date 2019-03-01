package ru.bobahe.algorithms.recursion;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestExponentiation {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {3, 3, 27},
                {5, -4, 0.0016},
                {0, 0, 1},
                {0.5, -2, 4}
        });
    }

    private double num;
    private int pow;
    private double result;

    public TestExponentiation(double num, int pow, double result) {
        this.num = num;
        this.pow = pow;
        this.result = result;
    }

    @Test
    public void testExponentiating() {
        Assert.assertEquals(result, Exponentiation.exponentiate(num, pow), 1e-12);
    }
}
