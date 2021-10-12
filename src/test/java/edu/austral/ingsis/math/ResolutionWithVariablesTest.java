package edu.austral.ingsis.math;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ResolutionWithVariablesTest {

    /**
     * Case 1 + x where x = 3
     */
    @Test
    public void shouldResolveFunction1() {
        Function function = new Expression(new Variable(1d), List.of(Operand.ADD), new Variable("x", 3d));
        final Double result = function.solve();

        assertThat(result, equalTo(4d));
    }

    /**
     * Case 12 / div where div = 4
     */
    @Test
    public void shouldResolveFunction2() {
        Function function = new Expression(new Variable(12d), List.of(Operand.DIVIDE), new Variable("div", 4d));
        final Double result = function.solve();

        assertThat(result, equalTo(3d));
    }

    /**
     * Case (9 / x) * y where x = 3 and y = 4
     */
    @Test
    public void shouldResolveFunction3() {
        Function function = new Expression(new Expression(new Variable(9d), List.of(Operand.DIVIDE), new Variable("x", 3d)), List.of(Operand.MULTIPLY), new Variable("y", 4d));
        final Double result = function.solve();

        assertThat(result, equalTo(12d));
    }

    /**
     * Case (27 / a) ^ b where a = 9 and b = 3
     */
    @Test
    public void shouldResolveFunction4() {
        Function function = new Expression(new Expression(new Variable(27d), List.of(Operand.DIVIDE), new Variable("a", 9d)), List.of(Operand.POWER), new Variable("b", 3d));
        final Double result = function.solve();

        assertThat(result, equalTo(27d));
    }

    /**
     * Case z ^ (1/2) where z = 36
     */
    @Test
    public void shouldResolveFunction5() {
        Function function = new Expression(new Variable("z", 36d), List.of(Operand.POWER),new Expression(new Variable(1d), List.of(Operand.DIVIDE), new Variable( 2d)));
        final Double result = function.solve();

        assertThat(result, equalTo(6d));
    }

    /**
     * Case |value| - 8 where value = 8
     */
    @Test
    public void shouldResolveFunction6() {
        Function function = null;
        try {
            function = new Expression(new Variable("value", 8d, Operand.ABSOLUTE), List.of(Operand.SUBTRACT), new Variable(8d));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert function != null;
        final Double result = function.solve();

        assertThat(result, equalTo(0d));
    }

    /**
     * Case |value| - 8 where value = -8
     */
    @Test
    public void shouldResolveFunction7() {
        Function function = null;
        try {
            function = new Expression(new Variable("value", -8d, Operand.ABSOLUTE), List.of(Operand.SUBTRACT), new Variable(8d));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert function != null;
        final Double result = function.solve();

        assertThat(result, equalTo(0d));
    }

    /**
     * Case (5 - i) * 8 where i = 2
     */
    @Test
    public void shouldResolveFunction8() {
        Function function = new Expression(new Expression(new Variable(5d), List.of(Operand.SUBTRACT), new Variable("i", 2d)), List.of(Operand.MULTIPLY), new Variable(8d));
        final Double result = function.solve();

        assertThat(result, equalTo(24d));
    }
}
