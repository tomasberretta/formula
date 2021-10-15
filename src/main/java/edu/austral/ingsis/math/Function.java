package edu.austral.ingsis.math;

public interface Function {
    String print(String prev);
    String print();
    boolean isComposite();
    boolean isConstant();
    void acceptVisitor(FunctionVisitor functionVisitor);

}
