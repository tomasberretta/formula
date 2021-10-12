package edu.austral.ingsis.math;

import java.util.ArrayList;
import java.util.List;

public class Expression implements Function {
    Function leftSide;
    List<Operand> operands;
    Function rightSide;

    public Expression(Function leftSide, List<Operand> operands, Function rightSide) {
        this.leftSide = leftSide;
        this.operands = operands;
        this.rightSide = rightSide;
    }

    @Override
    public Double solve() {
        double result = 0;
        for (Operand operand : operands) {
            switch (operand){
                case ADD:{
                    result = leftSide.solve() + rightSide.solve();
                    break;
                }
                case SUBTRACT:{
                    result = leftSide.solve() - rightSide.solve();
                    break;
                }
                case MULTIPLY:{
                    result = leftSide.solve() * rightSide.solve();
                    break;
                }
                case DIVIDE:{
                    result = leftSide.solve() / rightSide.solve();
                    break;
                }
                case POWER:{
                    result = Math.pow(leftSide.solve(), rightSide.solve());
                    break;
                }
                case SQUAREROOT:{
                    result = Math.sqrt(result);
                    break;
                }
                case ABSOLUTE:{
                    result = Math.abs(result);
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public List<String> getVariables(List<String> variables) {
        variables = leftSide.getVariables(variables);
        variables = rightSide.getVariables(variables);
        return variables;
    }

    @Override
    public List<String> getVariables() {
        return getVariables(new ArrayList<>());
    }

    @Override
    public String print(String prev) {
        if (leftSide.isComposite()) prev = "("+leftSide.print(prev) +")";
        else prev = leftSide.print(prev);
        StringBuilder prevBuilder = new StringBuilder(prev);
        for (Operand operand : operands) {
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
                case SQUAREROOT:{
                    prevBuilder.insert(0," sqrt( ");
                    prevBuilder.append(" ) ");
                    break;
                }
                case ABSOLUTE:{
                    prevBuilder.insert(0," | ");
                    prevBuilder.append(" | ");
                    break;
                }
            }
        }
        prev = prevBuilder.toString();
        if (rightSide.isComposite()) prev = "("+ rightSide.print(prev) +")";
        else prev = rightSide.print(prev);
        return prev;
    }

    @Override
    public String print() {
        return print("");
    }

    @Override
    public boolean isComposite() {
        return true;
    }
}
