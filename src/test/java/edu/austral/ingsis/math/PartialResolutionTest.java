package edu.austral.ingsis.math;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PartialResolutionTest {

    /**
     * Case 1 + x where x = 3
     */
    @Test
    public void shouldResolveFunction1() {
        Function function = new Expression(new Constant(1d), Operand.ADD, new Variable("x", 3d));
        PartialSolveFunctionVisitor solver = new PartialSolveFunctionVisitor();
        function.acceptVisitor(solver);
        final Function result = solver.getResult();
        assert result.isConstant();
        assertThat(((Constant)result).getValue(), equalTo(4d));
    }

    /**
     * Case 12 / div
     */
    @Test
    public void shouldResolveFunction2() {
        final String expected = "12 / div";
        Function function = new Expression(new Constant(12d), Operand.DIVIDE, new Variable("div"));
        PartialSolveFunctionVisitor solver = new PartialSolveFunctionVisitor();
        function.acceptVisitor(solver);
        final Function result = solver.getResult();
        final String resultToString = result.print();
        assertThat(resultToString, equalTo(expected));
    }

    /**
     * Case (9 / x) * y where x = 3
     */
    @Test
    public void shouldResolveFunction3() {
        final String expected = "3 * y";
        Function function = new Expression(new Expression(new Constant(9d), Operand.DIVIDE, new Variable("x", 3d)), Operand.MULTIPLY, new Variable("y"));
        PartialSolveFunctionVisitor solver = new PartialSolveFunctionVisitor();
        function.acceptVisitor(solver);
        final Function result = solver.getResult();
        final String resultToString = result.print();
        assertThat(resultToString, equalTo(expected));
    }

    /**
     * Case (27 / a) ^ b where a = 9
     */
    @Test
    public void shouldResolveFunction4() {
        final String expected = "3 ^ b";
        Function function = new Expression(new Expression(new Constant(27d), Operand.DIVIDE, new Variable("a", 9d)), Operand.POWER, new Variable("b"));
        PartialSolveFunctionVisitor solver = new PartialSolveFunctionVisitor();
        function.acceptVisitor(solver);
        final Function result = solver.getResult();
        final String resultToString = result.print();
        assertThat(resultToString, equalTo(expected));
    }

    /**
     * Case z ^ (1/2)
     */
    @Test
    public void shouldResolveFunction5() {
        final String expected = "z ^ 0.5";
        Function function = new Expression(new Variable("z"), Operand.POWER, new Expression(new Constant(1d), Operand.DIVIDE, new Constant(2d)));
        PartialSolveFunctionVisitor solver = new PartialSolveFunctionVisitor();
        function.acceptVisitor(solver);
        final Function result = solver.getResult();
        final String resultToString = result.print();
        assertThat(resultToString, equalTo(expected));
    }

    /**
     * Case |value| - 8
     */
    @Test
    public void shouldResolveFunction6() {
        final String expected = "|value| - 8";
        Function function = new Expression(new Variable("value", Operand.ABSOLUTE), Operand.SUBTRACT, new Constant(8d));
        PartialSolveFunctionVisitor solver = new PartialSolveFunctionVisitor();
        function.acceptVisitor(solver);
        final Function result = solver.getResult();
        final String resultToString = result.print();
        assertThat(resultToString, equalTo(expected));
    }

    /**
     * Case |value| - 8 where value = -8
     */
    @Test
    public void shouldResolveFunction7() {
        final String expected = "|value| - 8";
        Function function = new Expression(new Variable("value", Operand.ABSOLUTE), Operand.SUBTRACT, new Constant(8d));
        PartialSolveFunctionVisitor solver = new PartialSolveFunctionVisitor();
        function.acceptVisitor(solver);
        final Function result = solver.getResult();
        final String resultToString = result.print();
        assertThat(resultToString, equalTo(expected));
    }

    /**
     * Case (x - ((5 - i) * 8)) * 8 where i = 2
     */
    @Test
    public void shouldResolveFunction8() {
        final String expected = "(x - 24) * 8";
        Function function = new Expression(new Expression(new Variable("x"), Operand.SUBTRACT, new Expression(new Expression(new Constant(5d), Operand.SUBTRACT, new Variable("i", 2d)), Operand.MULTIPLY, new Constant(8d))), Operand.MULTIPLY, new Constant(8d));
        PartialSolveFunctionVisitor solver = new PartialSolveFunctionVisitor();
        function.acceptVisitor(solver);
        final Function result = solver.getResult();
        final String resultToString = result.print();
        assertThat(resultToString, equalTo(expected));
    }

    /**
     * Case (x - sqrt((5 + i) * 8)) * 8 where i = 3
     */
    @Test
    public void shouldResolveFunction9() {
        final String expected = "(x - 8) * 8";
        Function function = new Expression(new Expression(new Variable("x"), Operand.SUBTRACT, new Expression(new Expression(new Constant(5d), Operand.ADD, new Variable("i", 3d)), Operand.MULTIPLY, Operand.SQUAREROOT, new Constant(8d))), Operand.MULTIPLY, new Constant(8d));
        PartialSolveFunctionVisitor solver = new PartialSolveFunctionVisitor();
        function.acceptVisitor(solver);
        final Function result = solver.getResult();
        final String resultToString = result.print();
        assertThat(resultToString, equalTo(expected));
    }
}
