public class Divide implements IOperation {
    private IOperation nextOperation;

    @Override
    public void setNextOperation(IOperation nextOperation) {
        this.nextOperation = nextOperation;
    }

    @Override
    public void calculate(Expression expression) {
        if(expression.operation.equals("/")){
            System.out.println(expression.firstValue +"/"+ expression.secondValue+ " = " + (expression.firstValue/expression.secondValue)  );
        }else{
            nextOperation.calculate(expression);
        }
    }
}
