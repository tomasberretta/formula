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
        Function function = new Expression(new Constant(1d), Operand.ADD, new Constant(6d));
        final String result = function.print();

        assertThat(result, equalTo(expected));
    }

    /**
     * Case 12 / 2
     */
    @Test
    public void shouldPrintFunction2() {
        final String expected = "12 / 2";
        Function function = new Expression(new Constant(12d), Operand.DIVIDE, new Constant(2d));
        final String result = function.print();

        assertThat(result, equalTo(expected));
    }

    /**
     * Case (9 / 2) * 3
     */
    @Test
    public void shouldPrintFunction3() {
        final String expected = "(9 / 2) * 3";
        Function function = new Expression(new Expression(new Constant(9d), Operand.DIVIDE, new Constant(2d)), Operand.MULTIPLY, new Constant(3d));
        final String result = function.print();

        assertThat(result, equalTo(expected));
    }

    /**
     * Case (27 / 6) ^ 2
     */
    @Test
    public void shouldPrintFunction4() {
        final String expected = "(27 / 6) ^ 2";
        Function function = new Expression(new Expression(new Constant(27d), Operand.DIVIDE, new Constant(6d)), Operand.POWER, new Constant(2d));
        final String result = function.print();

        assertThat(result, equalTo(expected));
    }

    /**
     * Case |value| - 8
     */
    @Test
    public void shouldPrintFunction6() {
        final String expected = "|value| - 8";
        Function function = new Expression(new Variable("value", Operand.ABSOLUTE), Operand.SUBTRACT, new Constant(8d));
        final String result = function.print();

        assertThat(result, equalTo(expected));
    }

    /**
     * Case |value| - 8
     */
    @Test
    public void shouldPrintFunction7() {
        final String expected = "|value| - 8";
        Function function = new Expression(new Variable("value", Operand.ABSOLUTE), Operand.SUBTRACT, new Constant(8d));
        final String result = function.print();

        assertThat(result, equalTo(expected));
    }

    /**
     * Case (5 - i) * 8
     */
    @Test
    public void shouldPrintFunction8() {
        final String expected = "(5 - i) * 8";
        Function function = new Expression(new Expression(new Constant(5d), Operand.SUBTRACT, new Variable("i")), Operand.MULTIPLY, new Constant(8d));
        final String result = function.print();

        assertThat(result, equalTo(expected));
    }

    /**
     * Case |5 - i| * 8
     */
    @Test
    public void shouldPrintFunction9() {
        final String expected = "|5 - i| * 8";
        Function function = new Expression(new Expression(new Constant(5d), Operand.SUBTRACT, Operand.ABSOLUTE, new Variable("i")), Operand.MULTIPLY, new Constant(8d));

        final String result = function.print();

        assertThat(result, equalTo(expected));
    }

    /**
     * Case 8 * |5 - i|
     */
    @Test
    public void shouldPrintFunction10() {
        final String expected = "8 * |5 - i|";
        Function function = new Expression(new Constant(8d) , Operand.MULTIPLY, new Expression(new Constant(5d), Operand.SUBTRACT, Operand.ABSOLUTE, new Variable("i")));

        final String result = function.print();

        assertThat(result, equalTo(expected));
    }

    /**
     * Case 8 * ||5 - |5 - i|| - i|
     */
    @Test
    public void shouldPrintFunction11() {
        final String expected = "8 * ||5 - |5 - i|| - i|";
        Function function = new Expression(new Constant(8d) , Operand.MULTIPLY, new Expression(new Expression(new Constant(5d), Operand.SUBTRACT, Operand.ABSOLUTE, new Expression(new Constant(5d), Operand.SUBTRACT, Operand.ABSOLUTE, new Variable("i"))), Operand.SUBTRACT, Operand.ABSOLUTE, new Variable("i")));
        final String result = function.print();

        assertThat(result, equalTo(expected));
    }

    /**
     * Case 8 * sqrt(|5 - sqrt(5 - i)| - i)
     */
    @Test
    public void shouldPrintFunction12() {
        final String expected = "8 * sqrt(|5 - sqrt(5 - i)| - i)";
        Function function = new Expression(new Constant(8d) , Operand.MULTIPLY, new Expression(new Expression(new Constant(5d), Operand.SUBTRACT, Operand.ABSOLUTE, new Expression(new Constant(5d), Operand.SUBTRACT, Operand.SQUAREROOT, new Variable("i"))), Operand.SUBTRACT, Operand.SQUAREROOT, new Variable("i")));

        final String result = function.print();

        assertThat(result, equalTo(expected));
    }


    /**
     * Case 8 * sqrt(sqrt(5 - sqrt(5 - i)) - i)
     */
    @Test
    public void shouldPrintFunction13() {
        final String expected = "8 * sqrt(sqrt(5 - sqrt(5 - i)) - i)";
        Function function = new Expression(new Constant(8d) , Operand.MULTIPLY, new Expression(new Expression(new Constant(5d), Operand.SUBTRACT, Operand.SQUAREROOT, new Expression(new Constant(5d), Operand.SUBTRACT, Operand.SQUAREROOT, new Variable("i"))), Operand.SUBTRACT, Operand.SQUAREROOT, new Variable("i")));

        final String result = function.print();

        assertThat(result, equalTo(expected));
    }
}
