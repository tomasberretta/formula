package edu.austral.ingsis.math;

import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ListVariablesTest {

    /**
     * Case 1 + 6
     */
    @Test
    public void shouldListVariablesFunction1() {
        Function function = new Expression(new Variable(1d), List.of(Operand.ADD), new Variable(6d));
        final List<String> result = function.getVariables();


        assertThat(result, empty());
    }

    /**
     * Case 12 / div
     */
    @Test
    public void shouldListVariablesFunction2() {
        Function function = new Expression(new Variable(12d), List.of(Operand.DIVIDE), new Variable("div", 4d));
        final List<String> result = function.getVariables();


        assertThat(result, containsInAnyOrder("div"));
    }

    /**
     * Case (9 / x) * y
     */
    @Test
    public void shouldListVariablesFunction3() {
        Function function = new Expression(new Expression(new Variable(9d), List.of(Operand.DIVIDE), new Variable("x", 3d)), List.of(Operand.MULTIPLY), new Variable("y", 4d));
        final List<String> result = function.getVariables();

        assertThat(result, containsInAnyOrder("x", "y"));
    }

    /**
     * Case (27 / a) ^ b
     */
    @Test
    public void shouldListVariablesFunction4() {
        Function function = new Expression(new Expression(new Variable(27d), List.of(Operand.DIVIDE), new Variable("a", 9d)), List.of(Operand.POWER), new Variable("b", 3d));
        final List<String> result = function.getVariables();

        assertThat(result, containsInAnyOrder("a", "b"));
    }

    /**
     * Case z ^ (1/2)
     */
    @Test
    public void shouldListVariablesFunction5() {
        Function function = new Expression(new Variable("z", 36d), List.of(Operand.POWER),new Expression(new Variable(1d), List.of(Operand.DIVIDE), new Variable( 2d)));
        final List<String> result = function.getVariables();

        assertThat(result, containsInAnyOrder("z"));
    }

    /**
     * Case |value| - 8
     */
    @Test
    public void shouldListVariablesFunction6() {
        Function function = null;
        try {
            function = new Expression(new Variable("value", 8d, Operand.ABSOLUTE), List.of(Operand.SUBTRACT), new Variable(8d));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert function != null;
        final List<String> result = function.getVariables();

        assertThat(result, containsInAnyOrder("value"));
    }

    /**
     * Case |value| - 8
     */
    @Test
    public void shouldListVariablesFunction7() {
        Function function = null;
        try {
            function = new Expression(new Variable("value", 8d, Operand.ABSOLUTE), List.of(Operand.SUBTRACT), new Variable(8d));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert function != null;
        final List<String> result = function.getVariables();

        assertThat(result, containsInAnyOrder("value"));
    }

    /**
     * Case (5 - i) * 8
     */
    @Test
    public void shouldListVariablesFunction8() {
        Function function = new Expression(new Expression(new Variable(5d), List.of(Operand.SUBTRACT), new Variable("i", 2d)), List.of(Operand.MULTIPLY), new Variable(8d));
        final List<String> result = function.getVariables();

        assertThat(result, containsInAnyOrder("i"));
    }
}
