package edu.austral.ingsis.math;

import org.junit.Test;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ListVariablesTest {

    /**
     * Case 1 + 6
     */
    @Test
    public void shouldListVariablesFunction1() {
        Function function = new Expression(new Constant(1d), Operand.ADD, new Constant(6d));
        GetVariablesFunctionVisitor solver = new GetVariablesFunctionVisitor();
        function.acceptVisitor(solver);
        final List<String> result = solver.getResult();

        assertThat(result, empty());
    }

    /**
     * Case 12 / div
     */
    @Test
    public void shouldListVariablesFunction2() {
        Function function = new Expression(new Constant(12d), Operand.DIVIDE, new Variable("div", 4d));
        GetVariablesFunctionVisitor solver = new GetVariablesFunctionVisitor();
        function.acceptVisitor(solver);
        final List<String> result = solver.getResult();

        assertThat(result, containsInAnyOrder("div"));
    }

    /**
     * Case (9 / x) * y
     */
    @Test
    public void shouldListVariablesFunction3() {
        Function function = new Expression(new Expression(new Constant(9d), Operand.DIVIDE, new Variable("x", 3d)), Operand.MULTIPLY, new Variable("y", 4d));
        GetVariablesFunctionVisitor solver = new GetVariablesFunctionVisitor();
        function.acceptVisitor(solver);
        final List<String> result = solver.getResult();

        assertThat(result, containsInAnyOrder("x", "y"));
    }

    /**
     * Case (27 / a) ^ b
     */
    @Test
    public void shouldListVariablesFunction4() {
        Function function = new Expression(new Expression(new Constant(27d), Operand.DIVIDE, new Variable("a", 9d)), Operand.POWER, new Variable("b", 3d));
        GetVariablesFunctionVisitor solver = new GetVariablesFunctionVisitor();
        function.acceptVisitor(solver);
        final List<String> result = solver.getResult();

        assertThat(result, containsInAnyOrder("a", "b"));
    }

    /**
     * Case z ^ (1/2)
     */
    @Test
    public void shouldListVariablesFunction5() {
        Function function = new Expression(new Variable("z", 36d), Operand.POWER,new Expression(new Constant(1d), Operand.DIVIDE, new Constant( 2d)));
        GetVariablesFunctionVisitor solver = new GetVariablesFunctionVisitor();
        function.acceptVisitor(solver);
        final List<String> result = solver.getResult();

        assertThat(result, containsInAnyOrder("z"));
    }

    /**
     * Case |value| - 8
     */
    @Test
    public void shouldListVariablesFunction6() {
        Function function = new Expression(new Variable("value", 8d, Operand.ABSOLUTE), Operand.SUBTRACT, new Constant(8d));
        GetVariablesFunctionVisitor solver = new GetVariablesFunctionVisitor();
        function.acceptVisitor(solver);
        final List<String> result = solver.getResult();

        assertThat(result, containsInAnyOrder("value"));
    }

    /**
     * Case |value| - 8
     */
    @Test
    public void shouldListVariablesFunction7() {
        Function function = new Expression(new Variable("value", 8d, Operand.ABSOLUTE), Operand.SUBTRACT, new Constant(8d));
        GetVariablesFunctionVisitor solver = new GetVariablesFunctionVisitor();
        function.acceptVisitor(solver);
        final List<String> result = solver.getResult();

        assertThat(result, containsInAnyOrder("value"));
    }

    /**
     * Case (5 - i) * 8
     */
    @Test
    public void shouldListVariablesFunction8() {
        Function function = new Expression(new Expression(new Constant(5d), Operand.SUBTRACT, new Variable("i", 2d)), Operand.MULTIPLY, new Constant(8d));
        GetVariablesFunctionVisitor solver = new GetVariablesFunctionVisitor();
        function.acceptVisitor(solver);
        final List<String> result = solver.getResult();

        assertThat(result, containsInAnyOrder("i"));
    }
}
