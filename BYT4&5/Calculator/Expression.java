public class Expression {
    public String operation;
    public double firstValue;
    public double secondValue;

    public Expression(double firstValue, double secondValue, String operation){
        this.operation = operation;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }
}
