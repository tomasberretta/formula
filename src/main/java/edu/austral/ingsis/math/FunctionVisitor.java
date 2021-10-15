package edu.austral.ingsis.math;

public interface FunctionVisitor{
    void visitConstant(Constant constant);
    void visitVariable(Variable variable);
    void visitExpression(Expression expression);
}
