package edu.austral.ingsis.math;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Variable implements Function {
    String name;
    Double value;
    Operand operand;

    public Variable(String name, Double value, Operand operand) throws IOException {
        if(operand != Operand.SQUAREROOT && operand != Operand.ABSOLUTE) throw new IOException("Invalid operand for variable");
        this.name = name;
        this.value = value;
        this.operand = operand;
    }

    public Variable(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public Variable(String name, Operand operand) throws IOException {
        if(operand != Operand.SQUAREROOT && operand != Operand.ABSOLUTE) throw new IOException("Invalid operand for variable");
        this.name = name;
        this.operand = operand;
    }

    public Variable(Double value, Operand operand) throws IOException {
        if(operand != Operand.SQUAREROOT && operand != Operand.ABSOLUTE) throw new IOException("Invalid operand for variable");
        this.value = value;
        this.operand = operand;
    }

    public Variable(Double value) {
        this.value = value;
    }

    @Override
    public Double solve() {
        if (operand == null) return value;
        switch (operand){
            case SQUAREROOT: return Math.sqrt(value);
            case ABSOLUTE: return Math.abs(value);
        }
        return value;
    }

    @Override
    public List<String> getVariables(List<String> variables) {
        if(name != null) variables.add(name);
        return variables;
    }

    @Override
    public List<String> getVariables() {
        return getVariables(new ArrayList<>());
    }

    @Override
    public String print(String prev) {
        if(operand != null && operand == Operand.ABSOLUTE){
            if(name != null) return "|"+ prev + name + "|";
            return "|"+ prev + value.intValue()+ "|";
        }else if(operand != null && operand == Operand.SQUAREROOT){
            if(name != null) return "sqrt("+ prev + name + ")";
            return "sqrt("+ prev + value.intValue()+ ")";
        }else {
            if(name != null) return prev + name;
            return prev + value.intValue();
        }
    }

    @Override
    public String print() {
        return print("");
    }

    @Override
    public boolean isComposite() {
        return false;
    }
}
