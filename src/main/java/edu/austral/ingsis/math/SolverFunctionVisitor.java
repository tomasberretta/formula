package edu.austral.ingsis.math;

import java.io.IOException;

public class SolverFunctionVisitor implements FunctionVisitor {
    Double result;
    @Override
    public void visitConstant(Constant constant) {
        Operand operand = constant.getOperand();
        Double value = constant.getValue();
        if (operand == null){
            result = value;
            return;
        }
        switch (operand){
            case SQUAREROOT: {
                result = Math.sqrt(value);
                return;
            }
            case ABSOLUTE: {
                result = Math.abs(value);
            }
        }
    }

    @Override
    public void visitVariable(Variable variable) {
        Operand operand = variable.getOperand();
        Double value = variable.getValue();
        visitConstant(new Constant(value, operand));
    }

    @Override
    public void visitExpression(Expression expression) {
        double result = 0;
        Function leftSide = expression.getLeftSide();
        Function rightSide = expression.getRightSide();
        Operand operand = expression.getOperand();
        Operand secondOperand = expression.getSecondOperand();
        switch (operand){
            case ADD:{
                leftSide.acceptVisitor(this);
                Double lResult = this.result;
                rightSide.acceptVisitor(this);
                Double rResult = this.result;
                result = lResult + rResult;
                break;
            }
            case SUBTRACT:{
                leftSide.acceptVisitor(this);
                Double lResult = this.result;
                rightSide.acceptVisitor(this);
                Double rResult = this.result;
                result = lResult - rResult;
                break;
            }
            case MULTIPLY:{
                leftSide.acceptVisitor(this);
                Double lResult = this.result;
                rightSide.acceptVisitor(this);
                Double rResult = this.result;
                result = lResult * rResult;
                break;
            }
            case DIVIDE:{
                leftSide.acceptVisitor(this);
                Double lResult = this.result;
                rightSide.acceptVisitor(this);
                Double rResult = this.result;
                result = lResult / rResult;
                break;
            }
            case POWER:{
                leftSide.acceptVisitor(this);
                double lResult = this.result;
                rightSide.acceptVisitor(this);
                double rResult = this.result;
                result = Math.pow(lResult, rResult);
                break;
            }
        }
        if(secondOperand != null){
            switch (secondOperand){
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
        this.result = result;
    }

    public Double getResult() {
        return result;
    }

}
