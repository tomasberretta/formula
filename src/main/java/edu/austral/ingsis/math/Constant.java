package edu.austral.ingsis.math;

import java.text.DecimalFormat;

public class Constant implements Function {
    Double value;
    Operand operand;

    public Constant(Double value, Operand operand) {
        if(operand != Operand.SQUAREROOT && operand != Operand.ABSOLUTE && operand != null) throw new RuntimeException("Invalid operand for constant");
        this.value = value;
        this.operand = operand;
    }

    public Constant(Double value) {
        this.value = value;
    }

    @Override
    public String print(String prev) {
        if(operand != null && operand == Operand.ABSOLUTE){
            return "|"+ prev + new DecimalFormat("#.##").format(value) + "|";
        }else if(operand != null && operand == Operand.SQUAREROOT){
            return "sqrt("+ prev + new DecimalFormat("#.##").format(value)+ ")";
        }else {
            return prev + new DecimalFormat("#.##").format(value);
        }
    }

    @Override
    public String print() {
        return print("");
    }

    public Double getValue() {
        return value;
    }

    public Operand getOperand() {
        return operand;
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public boolean isConstant() {
        return true;
    }

    @Override
    public void acceptVisitor(FunctionVisitor functionVisitor) {
        functionVisitor.visitConstant(this);
    }
}
