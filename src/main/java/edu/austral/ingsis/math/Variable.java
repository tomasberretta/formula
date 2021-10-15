package edu.austral.ingsis.math;


import java.text.DecimalFormat;

public class Variable implements Function {
    String name;
    Double value;
    Operand operand;

    public Variable(String name, Double value, Operand operand) {
        if(operand != Operand.SQUAREROOT && operand != Operand.ABSOLUTE && operand != null) throw new RuntimeException("Invalid operand for variable");
        this.name = name;
        this.value = value;
        this.operand = operand;
    }

    public Variable(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public Variable(String name, Operand operand) {
        if(operand != Operand.SQUAREROOT && operand != Operand.ABSOLUTE && operand != null) throw new RuntimeException("Invalid operand for variable");
        this.name = name;
        this.operand = operand;
    }

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public String print(String prev) {
        if(operand != null && operand == Operand.ABSOLUTE){
            if(value == null) return "|"+ prev + name + "|";
            return "|"+ prev + new DecimalFormat("#.##").format(value)+ "|";
        }else if(operand != null && operand == Operand.SQUAREROOT){
            if(value == null) return "sqrt("+ prev + name + ")";
            return "sqrt("+ prev + new DecimalFormat("#.##").format(value) + ")";
        }else {
            if(value == null) return prev + name;
            return prev + new DecimalFormat("#.##").format(value);
        }
    }

    @Override
    public String print() {
        return print("");
    }

    public String getName() {
        return name;
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
        return false;
    }

    @Override
    public void acceptVisitor(FunctionVisitor functionVisitor) {
        functionVisitor.visitVariable(this);
    }
}
