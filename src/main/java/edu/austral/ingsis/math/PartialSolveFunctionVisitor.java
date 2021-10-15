package edu.austral.ingsis.math;

public class PartialSolveFunctionVisitor implements FunctionVisitor {
    Function result;

    @Override
    public void visitConstant(Constant constant) {
        Operand operand = constant.getOperand();
        Double value = constant.getValue();
        if (operand == null){
            result = constant;
            return;
        }
        switch (operand){
            case SQUAREROOT: {
                result = new Constant(Math.sqrt(value));
                return;
            }
            case ABSOLUTE: {
                result = new Constant(Math.abs(value));
            }
        }
    }

    @Override
    public void visitVariable(Variable variable) {
        Operand operand = variable.getOperand();
        Double value = variable.getValue();
        if(value != null){
            visitConstant(new Constant(value, operand));
        }else result = variable;
    }

    @Override
    public void visitExpression(Expression expression) {
        Function leftSide = expression.getLeftSide();
        Function rightSide = expression.getRightSide();
        Operand operand = expression.getOperand();
        Operand secondOperand = expression.getSecondOperand();
        switch (operand){
            case ADD:{
                leftSide.acceptVisitor(this);
                Function lResult = this.result;
                rightSide.acceptVisitor(this);
                Function rResult = this.result;
                if(lResult.isConstant() && rResult.isConstant()) result = new Constant( ((Constant)lResult).getValue() + ((Constant)rResult).getValue(), secondOperand);
                else if(lResult.isConstant() && !rResult.isComposite()){
                    if(((Variable)rResult).getValue() != null) result = new Constant( ((Constant)lResult).getValue() + ((Variable)rResult).getValue(), secondOperand);
                    else result = new Expression(lResult, operand, secondOperand, rResult);
                }
                else if(!lResult.isComposite() && rResult.isConstant()){
                    if(((Variable)lResult).getValue() != null) result = new Constant( ((Variable)lResult).getValue() + ((Constant)rResult).getValue(), secondOperand);
                    else result = new Expression(lResult, operand, secondOperand, rResult);
                }else if(!lResult.isComposite() && !rResult.isComposite()){
                    if(((Variable)lResult).getValue() != null && ((Variable)rResult).getValue() != null) result = new Constant( ((Variable)lResult).getValue() + ((Variable)rResult).getValue(), secondOperand);
                    else result = new Expression(lResult, operand, secondOperand, rResult);
                }
                else result = new Expression(lResult, operand, secondOperand, rResult);
                break;
            }
            case SUBTRACT:{
                leftSide.acceptVisitor(this);
                Function lResult = this.result;
                rightSide.acceptVisitor(this);
                Function rResult = this.result;
                if(lResult.isConstant() && rResult.isConstant()) result = new Constant( ((Constant)lResult).getValue() - ((Constant)rResult).getValue(), secondOperand);
                else if(lResult.isConstant() && !rResult.isComposite()){
                    if(((Variable)rResult).getValue() != null) result = new Constant( ((Constant)lResult).getValue() - ((Variable)rResult).getValue(), secondOperand);
                    else result = new Expression(lResult, operand, secondOperand, rResult);
                }
                else if(!lResult.isComposite() && rResult.isConstant()){
                    if(((Variable)lResult).getValue() != null) result = new Constant( ((Variable)lResult).getValue() - ((Constant)rResult).getValue(), secondOperand);
                    else result = new Expression(lResult, operand, secondOperand, rResult);
                }else if(!lResult.isComposite() && !rResult.isComposite()){
                    if(((Variable)lResult).getValue() != null && ((Variable)rResult).getValue() != null) result = new Constant( ((Variable)lResult).getValue() - ((Variable)rResult).getValue(), secondOperand);
                    else result = new Expression(lResult, operand, secondOperand, rResult);
                }
                else result = new Expression(lResult, operand, secondOperand, rResult);
                break;
            }
            case MULTIPLY:{
                leftSide.acceptVisitor(this);
                Function lResult = this.result;
                rightSide.acceptVisitor(this);
                Function rResult = this.result;
                if(lResult.isConstant() && rResult.isConstant()) result = new Constant( ((Constant)lResult).getValue() * ((Constant)rResult).getValue(), secondOperand);
                else if(lResult.isConstant() && !rResult.isComposite()){
                    if(((Variable)rResult).getValue() != null) result = new Constant( ((Constant)lResult).getValue() * ((Variable)rResult).getValue(), secondOperand);
                    else result = new Expression(lResult, operand, secondOperand, rResult);
                }
                else if(!lResult.isComposite() && rResult.isConstant()){
                    if(((Variable)lResult).getValue() != null) result = new Constant( ((Variable)lResult).getValue() * ((Constant)rResult).getValue(), secondOperand);
                    else result = new Expression(lResult, operand, secondOperand, rResult);
                }else if(!lResult.isComposite() && !rResult.isComposite()){
                    if(((Variable)lResult).getValue() != null && ((Variable)rResult).getValue() != null) result = new Constant( ((Variable)lResult).getValue() * ((Variable)rResult).getValue(), secondOperand);
                    else result = new Expression(lResult, operand, secondOperand, rResult);
                }
                else result = new Expression(lResult, operand, secondOperand, rResult);
                break;
            }
            case DIVIDE:{
                leftSide.acceptVisitor(this);
                Function lResult = this.result;
                rightSide.acceptVisitor(this);
                Function rResult = this.result;
                if(lResult.isConstant() && rResult.isConstant()) result = new Constant( ((Constant)lResult).getValue() / ((Constant)rResult).getValue(), secondOperand);
                else if(lResult.isConstant() && !rResult.isComposite()){
                    if(((Variable)rResult).getValue() != null) result = new Constant( ((Constant)lResult).getValue() / ((Variable)rResult).getValue(), secondOperand);
                    else result = new Expression(lResult, operand, secondOperand, rResult);
                }
                else if(!lResult.isComposite() && rResult.isConstant()){
                    if(((Variable)lResult).getValue() != null) result = new Constant( ((Variable)lResult).getValue() / ((Constant)rResult).getValue(), secondOperand);
                    else result = new Expression(lResult, operand, secondOperand, rResult);
                }else if(!lResult.isComposite() && !rResult.isComposite()){
                    if(((Variable)lResult).getValue() != null && ((Variable)rResult).getValue() != null) result = new Constant( ((Variable)lResult).getValue() / ((Variable)rResult).getValue(), secondOperand);
                    else result = new Expression(lResult, operand, secondOperand, rResult);
                }
                else result = new Expression(lResult, operand, secondOperand, rResult);
                break;
            }
            case POWER:{
                leftSide.acceptVisitor(this);
                Function lResult = this.result;
                rightSide.acceptVisitor(this);
                Function rResult = this.result;
                if(lResult.isConstant() && rResult.isConstant()) result = new Constant( Math.pow(((Constant)lResult).getValue() , ((Constant)rResult).getValue()), secondOperand);
                else if(lResult.isConstant() && !rResult.isComposite()){
                    if(((Variable)rResult).getValue() != null) result = new Constant( Math.pow(((Constant)lResult).getValue() , ((Variable)rResult).getValue()), secondOperand);
                    else result = new Expression(lResult, operand, secondOperand, rResult);
                }
                else if(!lResult.isComposite() && rResult.isConstant()){
                    if(((Variable)lResult).getValue() != null) result = new Constant( Math.pow(((Variable)lResult).getValue() , ((Constant)rResult).getValue()),secondOperand);
                    else result = new Expression(lResult, operand, secondOperand, rResult);
                }else if(!lResult.isComposite() && !rResult.isComposite()){
                    if(((Variable)lResult).getValue() != null && ((Variable)rResult).getValue() != null) result = new Constant( Math.pow(((Variable)lResult).getValue() , ((Variable)rResult).getValue()), secondOperand);
                    else result = new Expression(lResult, operand, secondOperand, rResult);
                }
                else result = new Expression(lResult, operand, secondOperand, rResult);
                break;
            }
        }
        if(secondOperand != null){
            switch (secondOperand){
                case SQUAREROOT:{
                    if(result.isConstant()) result = new Constant(Math.sqrt(((Constant)result).getValue()));
                    else if(!result.isComposite() && ((Variable)result).getValue() != null) result = new Constant(Math.sqrt(((Variable)result).getValue()));
                    break;
                }
                case ABSOLUTE:{
                    if(result.isConstant()) result = new Constant(Math.abs(((Constant)result).getValue()));
                    else if(!result.isComposite() && ((Variable)result).getValue() != null) result = new Constant(Math.abs(((Variable)result).getValue()));
                    break;
                }
            }
        }
    }

    public Function getResult() {
        return result;
    }
}
