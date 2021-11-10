public interface IOperation {
    public void setNextOperation(IOperation operationHandler);
    public abstract void calculate(Expression expression);
}