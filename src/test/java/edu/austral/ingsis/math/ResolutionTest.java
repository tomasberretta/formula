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
        Function function = new Expression(new Constant(1d), Operand.ADD, new Constant(6d));
        SolverFunctionVisitor solver = new SolverFunctionVisitor();
        function.acceptVisitor(solver);
        final Double result = solver.getResult();

        assertThat(result, equalTo(7d));
    }

    /**
     * Case 12 / 2
     */
    @Test
    public void shouldResolveSimpleFunction2() {
        Function function = new Expression(new Constant(12d), Operand.DIVIDE, new Constant(2d));
        SolverFunctionVisitor solver = new SolverFunctionVisitor();
        function.acceptVisitor(solver);
        final Double result = solver.getResult();

        assertThat(result, equalTo(6d));
    }

    /**
     * Case (9 / 2) * 3
     */
    @Test
    public void shouldResolveSimpleFunction3() {
        Function function = new Expression(new Expression(new Constant(9d), Operand.DIVIDE, new Constant(2d)), Operand.MULTIPLY, new Constant(3d));
        SolverFunctionVisitor solver = new SolverFunctionVisitor();
        function.acceptVisitor(solver);
        final Double result = solver.getResult();

        assertThat(result, equalTo(13.5d));
    }

    /**
     * Case (27 / 6) ^ 2
     */
    @Test
    public void shouldResolveSimpleFunction4() {
        Function function = new Expression(new Expression(new Constant(27d), Operand.DIVIDE, new Constant(6d)), Operand.POWER, new Constant(2d));
        SolverFunctionVisitor solver = new SolverFunctionVisitor();
        function.acceptVisitor(solver);
        final Double result = solver.getResult();
        assertThat(result, equalTo(20.25d));
    }

    /**
     * Case 36 ^ (1/2)
     */
    @Test
    public void shouldResolveSimpleFunction5() {
        Function function = new Expression(new Constant(36d), Operand.POWER, new Expression(new Constant(1d), Operand.DIVIDE, new Constant(2d)));
        SolverFunctionVisitor solver = new SolverFunctionVisitor();
        function.acceptVisitor(solver);
        final Double result = solver.getResult();

        assertThat(result, equalTo(6d));
    }

    /**
     * Case |136|
     */
    @Test
    public void shouldResolveSimpleFunction6() {
        Function function = new Constant( 136d, Operand.ABSOLUTE);
        SolverFunctionVisitor solver = new SolverFunctionVisitor();
        function.acceptVisitor(solver);
        final Double result = solver.getResult();

        assertThat(result, equalTo(136d));
    }

    /**
     * Case |-136|
     */
    @Test
    public void shouldResolveSimpleFunction7() {
        Function function = new Constant( -136d, Operand.ABSOLUTE);
        SolverFunctionVisitor solver = new SolverFunctionVisitor();
        function.acceptVisitor(solver);
        final Double result = solver.getResult();

        assertThat(result, equalTo(136d));
    }

    /**
     * Case (5 - 5) * 8
     */
    @Test
    public void shouldResolveSimpleFunction8() {
        Function function = new Expression(new Expression(new Constant(5d), Operand.SUBTRACT, new Constant(5d)), Operand.MULTIPLY, new Constant(8d));

        SolverFunctionVisitor solver = new SolverFunctionVisitor();
        function.acceptVisitor(solver);
        final Double result = solver.getResult();
        assertThat(result, equalTo(0d));
    }
}
