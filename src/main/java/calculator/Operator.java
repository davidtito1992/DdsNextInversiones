package calculator;

public interface Operator {
    Value operate(Value left, Value right);
}