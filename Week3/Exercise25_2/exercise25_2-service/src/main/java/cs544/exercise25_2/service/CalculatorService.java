package cs544.exercise25_2.service;

public class CalculatorService implements ICalculator {

    private int value = 0;

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public synchronized int calc(char operator, int number) {
        if (operator == '+')
            value = value + number;
        if (operator == '-')
            value = value - number;
        if (operator == '*')
            value = value * number;
        if (operator == '/')
            value = value / number;
        return value;
    }
}
