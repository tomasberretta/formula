package edu.austral.ingsis.math;

public class Expression implements Function {
    Function leftSide;
    Operand operand;
    Function rightSide;
    Operand secondOperand;

    public Expression(Function leftSide, Operand operand, Function rightSide) {
        if(operand == Operand.SQUAREROOT || operand == Operand.ABSOLUTE || operand == null) throw new RuntimeException("Invalid operand for expression");
        this.leftSide = leftSide;
        this.operand = operand;
        this.rightSide = rightSide;
    }
    public Expression(Function leftSide, Operand operand, Operand secondOperand, Function rightSide) {
        if(operand == Operand.SQUAREROOT || operand == Operand.ABSOLUTE || operand == null) throw new RuntimeException("Invalid operand for expression");
        if(secondOperand != Operand.SQUAREROOT && secondOperand != Operand.ABSOLUTE && secondOperand != null) throw new RuntimeException("Invalid second operand for expression");
        this.leftSide = leftSide;
        this.operand = operand;
        this.secondOperand = secondOperand;
        this.rightSide = rightSide;
    }

    @Override
    public String print(String prev) {
        if(leftSide.isComposite()){
            if (((Expression)leftSide).getSecondOperand() == null) prev = "("+leftSide.print(prev) +")";
            else if (((Expression)leftSide).getSecondOperand() == Operand.ABSOLUTE) prev = "|"+leftSide.print(prev) +"|";
            else if (((Expression)leftSide).getSecondOperand() == Operand.SQUAREROOT) prev = "sqrt("+leftSide.print(prev) +")";
        }
        else prev = leftSide.print(prev);

        StringBuilder prevBuilder = new StringBuilder(prev);
        switch (operand){
            case ADD:{
                prevBuilder.append(" + ");
                break;
            }
            case SUBTRACT:{
                prevBuilder.append(" - ");
                break;
            }
            case MULTIPLY:{
                prevBuilder.append(" * ");
                break;
            }
            case DIVIDE:{
                prevBuilder.append(" / ");
                break;
            }
            case POWER:{
                prevBuilder.append(" ^ ");
                break;
            }
        }
        prev = prevBuilder.toString();
        if(rightSide.isComposite()){
            if (((Expression)rightSide).getSecondOperand() == null) prev = prev + "("+rightSide.print() +")";
            else if (((Expression)rightSide).getSecondOperand() == Operand.ABSOLUTE) prev = prev + "|"+rightSide.print() +"|";
            else if (((Expression)rightSide).getSecondOperand() == Operand.SQUAREROOT) prev = prev + "sqrt("+rightSide.print() +")";
        }
        else prev = rightSide.print(prev);
        return prev;
    }

    @Override
    public String print() {
        return print("");
    }

    public Function getLeftSide() {
        return leftSide;
    }

    public Operand getOperand() {
        return operand;
    }

    public Operand getSecondOperand() {
        return secondOperand;
    }

    public Function getRightSide() {
        return rightSide;
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public boolean isConstant() {
        return false;
    }

    @Override
    public void acceptVisitor(FunctionVisitor functionVisitor) {
        functionVisitor.visitExpression(this);
    }
}
