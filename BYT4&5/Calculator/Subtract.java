public class Subtract implements IOperation {
    private IOperation nextOperation;

    @Override
    public void setNextOperation(IOperation nextOperation) {
        this.nextOperation = nextOperation;
    }

    @Override
    public void calculate(Expression expression) {
        if(expression.operation.equals("-")){
            System.out.println(expression.firstValue +"-"+ expression.secondValue+ " = " + (expression.firstValue-expression.secondValue)  );
        }else{
            System.out.println("Please enter a operation");
        }
    }
}
