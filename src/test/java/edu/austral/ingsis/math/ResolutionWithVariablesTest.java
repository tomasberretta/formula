package edu.austral.ingsis.math;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ResolutionWithVariablesTest {

    /**
     * Case 1 + x where x = 3
     */
    @Test
    public void shouldResolveFunction1() {
        Function function = new Expression(new Constant(1d), Operand.ADD, new Variable("x", 3d));
        SolverFunctionVisitor solver = new SolverFunctionVisitor();
        function.acceptVisitor(solver);
        final Double result = solver.getResult();
        assertThat(result, equalTo(4d));
    }

    /**
     * Case 12 / div where div = 4
     */
    @Test
    public void shouldResolveFunction2() {
        Function function = new Expression(new Constant(12d), Operand.DIVIDE, new Variable("div", 4d));
        SolverFunctionVisitor solver = new SolverFunctionVisitor();
        function.acceptVisitor(solver);
        final Double result = solver.getResult();

        assertThat(result, equalTo(3d));
    }

    /**
     * Case (9 / x) * y where x = 3 and y = 4
     */
    @Test
    public void shouldResolveFunction3() {
        Function function = new Expression(new Expression(new Constant(9d), Operand.DIVIDE, new Variable("x", 3d)), Operand.MULTIPLY, new Variable("y", 4d));
        SolverFunctionVisitor solver = new SolverFunctionVisitor();
        function.acceptVisitor(solver);
        final Double result = solver.getResult();

        assertThat(result, equalTo(12d));
    }

    /**
     * Case (27 / a) ^ b where a = 9 and b = 3
     */
    @Test
    public void shouldResolveFunction4() {
        Function function = new Expression(new Expression(new Constant(27d), Operand.DIVIDE, new Variable("a", 9d)), Operand.POWER, new Variable("b", 3d));
        SolverFunctionVisitor solver = new SolverFunctionVisitor();
        function.acceptVisitor(solver);
        final Double result = solver.getResult();

        assertThat(result, equalTo(27d));
    }

    /**
     * Case z ^ (1/2) where z = 36
     */
    @Test
    public void shouldResolveFunction5() {
        Function function = new Expression(new Variable("z", 36d), Operand.POWER,new Expression(new Constant(1d), Operand.DIVIDE, new Constant( 2d)));
        SolverFunctionVisitor solver = new SolverFunctionVisitor();
        function.acceptVisitor(solver);
        final Double result = solver.getResult();

        assertThat(result, equalTo(6d));
    }

    /**
     * Case |value| - 8 where value = 8
     */
    @Test
    public void shouldResolveFunction6() {
        Function function = new Expression(new Variable("value", 8d, Operand.ABSOLUTE), Operand.SUBTRACT, new Constant(8d));
        SolverFunctionVisitor solver = new SolverFunctionVisitor();
        function.acceptVisitor(solver);
        final Double result = solver.getResult();

        assertThat(result, equalTo(0d));
    }

    /**
     * Case |value| - 8 where value = -8
     */
    @Test
    public void shouldResolveFunction7() {
        Function function = new Expression(new Variable("value", -8d, Operand.ABSOLUTE), Operand.SUBTRACT, new Constant(8d));
        SolverFunctionVisitor solver = new SolverFunctionVisitor();
        function.acceptVisitor(solver);
        final Double result = solver.getResult();

        assertThat(result, equalTo(0d));
    }

    /**
     * Case (5 - i) * 8 where i = 2
     */
    @Test
    public void shouldResolveFunction8() {
        Function function = new Expression(new Expression(new Constant(5d), Operand.SUBTRACT, new Variable("i", 2d)), Operand.MULTIPLY, new Constant(8d));
        SolverFunctionVisitor solver = new SolverFunctionVisitor();
        function.acceptVisitor(solver);
        final Double result = solver.getResult();

        assertThat(result, equalTo(24d));
    }
}
