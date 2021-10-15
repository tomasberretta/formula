package edu.austral.ingsis.math;

import java.util.ArrayList;
import java.util.List;

public class GetVariablesFunctionVisitor implements FunctionVisitor {
    List<String> result = new ArrayList<>();

    @Override
    public void visitConstant(Constant constant) {
    }

    @Override
    public void visitVariable(Variable variable) {
        result.add(variable.getName());
    }

    @Override
    public void visitExpression(Expression expression) {
        Function leftSide = expression.getLeftSide();
        Function rightSide = expression.getRightSide();

        if(leftSide.isComposite()) visitExpression((Expression) leftSide);
        else{
            if (leftSide.isConstant()) visitConstant((Constant) leftSide);
            else visitVariable((Variable) leftSide);
        }
        if(rightSide.isComposite()) visitExpression((Expression) rightSide);
        else{
            if (rightSide.isConstant()) visitConstant((Constant) rightSide);
            else visitVariable((Variable) rightSide);
        }
    }

    public List<String> getResult() {
        return result;
    }

}
