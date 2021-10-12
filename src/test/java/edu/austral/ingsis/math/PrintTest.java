package edu.austral.ingsis.math;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PrintTest {

    /**
     * Case 1 + 6
     */
    @Test
    public void shouldPrintFunction1() {
        final String expected = "1 + 6";
        Function function = new Expression(new Variable(1d), List.of(Operand.ADD), new Variable(6d));

        final String result = function.print();

        assertThat(result, equalTo(expected));
    }

    /**
     * Case 12 / 2
     */
    @Test
    public void shouldPrintFunction2() {
        final String expected = "12 / 2";
        Function function = new Expression(new Variable(12d), List.of(Operand.DIVIDE), new Variable(2d));
        final String result = function.print();


        assertThat(result, equalTo(expected));
    }

    /**
     * Case (9 / 2) * 3
     */
    @Test
    public void shouldPrintFunction3() {
        final String expected = "(9 / 2) * 3";
        Function function = new Expression(new Expression(new Variable(9d), List.of(Operand.DIVIDE), new Variable(2d)), List.of(Operand.MULTIPLY), new Variable(3d));
        final String result = function.print();

        assertThat(result, equalTo(expected));
    }

    /**
     * Case (27 / 6) ^ 2
     */
    @Test
    public void shouldPrintFunction4() {
        final String expected = "(27 / 6) ^ 2";
        Function function = new Expression(new Expression(new Variable(27d), List.of(Operand.DIVIDE), new Variable(6d)), List.of(Operand.POWER), new Variable(2d));
        final String result = function.print();

        assertThat(result, equalTo(expected));
    }

    /**
     * Case |value| - 8
     */
    @Test
    public void shouldPrintFunction6() {
        final String expected = "|value| - 8";
        Function function = null;
        try {
            function = new Expression(new Variable("value", 8d, Operand.ABSOLUTE), List.of(Operand.SUBTRACT), new Variable(8d));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert function != null;
        final String result = function.print();

        assertThat(result, equalTo(expected));
    }

    /**
     * Case |value| - 8
     */
    @Test
    public void shouldPrintFunction7() {
        final String expected = "|value| - 8";
        Function function = null;
        try {
            function = new Expression(new Variable("value", -8d, Operand.ABSOLUTE), List.of(Operand.SUBTRACT), new Variable(8d));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert function != null;
        final String result = function.print();

        assertThat(result, equalTo(expected));
    }

    /**
     * Case (5 - i) * 8
     */
    @Test
    public void shouldPrintFunction8() {
        final String expected = "(5 - i) * 8";
        Function function = new Expression(new Expression(new Variable(5d), List.of(Operand.SUBTRACT), new Variable("i", 2d)), List.of(Operand.MULTIPLY), new Variable(8d));
        final String result = function.print();

        assertThat(result, equalTo(expected));
    }
}
