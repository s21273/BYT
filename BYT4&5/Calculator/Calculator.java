public class Calculator {
    private static Multiply multiply = new Multiply();
    private static Divide   divide = new Divide();
    private static Addition addition = new Addition();
    private static Subtract subtract = new Subtract();

    static
    {
        multiply.setNextOperation(divide);
        divide.setNextOperation(addition);
        addition.setNextOperation(subtract);
    }

    public static void Calculation(String mathExpression){
        String[] expressionArr = mathExpression.split("\\s");
        Expression expression = new Expression(Double.parseDouble(expressionArr[0]),Double.parseDouble(expressionArr[2]),expressionArr[1]);
        multiply.calculate(expression);
    }

    public static void main(String[] args) {
        Calculation("7 * 13");
        Calculation("7 / 13");
        Calculation("7 + 13");
        Calculation("7 - 13");
    }
}