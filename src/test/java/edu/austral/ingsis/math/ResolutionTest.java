package edu.austral.ingsis.math;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ResolutionTest {

    /**
     * Case 1 + 6
     */
    @Test
    public void shouldResolveSimpleFunction1() {
        Function function = new Expression(new Variable(1d), List.of(Operand.ADD), new Variable(6d));
        final Double result = function.solve();

        assertThat(result, equalTo(7d));
    }

    /**
     * Case 12 / 2
     */
    @Test
    public void shouldResolveSimpleFunction2() {

        Function function = new Expression(new Variable(12d), List.of(Operand.DIVIDE), new Variable(2d));
        final Double result = function.solve();

        assertThat(result, equalTo(6d));
    }

    /**
     * Case (9 / 2) * 3
     */
    @Test
    public void shouldResolveSimpleFunction3() {
        Function function = new Expression(new Expression(new Variable(9d), List.of(Operand.DIVIDE), new Variable(2d)), List.of(Operand.MULTIPLY), new Variable(3d));
        final Double result = function.solve();

        assertThat(result, equalTo(13.5d));
    }

    /**
     * Case (27 / 6) ^ 2
     */
    @Test
    public void shouldResolveSimpleFunction4() {

        Function function = new Expression(new Expression(new Variable(27d), List.of(Operand.DIVIDE), new Variable(6d)), List.of(Operand.POWER), new Variable(2d));
        final Double result = function.solve();

        assertThat(result, equalTo(20.25d));
    }

    /**
     * Case 36 ^ (1/2)
     */
    @Test
    public void shouldResolveSimpleFunction5() {
        Function function = new Expression(new Variable(36d), List.of(Operand.POWER), new Expression(new Variable(1d), List.of(Operand.DIVIDE), new Variable(2d)));
        final Double result = function.solve();

        assertThat(result, equalTo(6d));
    }

    /**
     * Case |136|
     */
    @Test
    public void shouldResolveSimpleFunction6() {

        Function function = null;
        try {
            function = new Variable("a", 136d, Operand.ABSOLUTE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert function != null;
        final Double result = function.solve();

        assertThat(result, equalTo(136d));
    }

    /**
     * Case |-136|
     */
    @Test
    public void shouldResolveSimpleFunction7() {
        Function function = null;
        try {
            function = new Variable("a", -136d, Operand.ABSOLUTE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert function != null;
        final Double result = function.solve();

        assertThat(result, equalTo(136d));
    }

    /**
     * Case (5 - 5) * 8
     */
    @Test
    public void shouldResolveSimpleFunction8() {
        Function function = new Expression(new Expression(new Variable(5d), List.of(Operand.SUBTRACT), new Variable(5d)), List.of(Operand.MULTIPLY), new Variable(8d));
        final Double result = function.solve();

        assertThat(result, equalTo(0d));
    }
}
