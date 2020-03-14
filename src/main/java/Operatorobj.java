public class Operatorobj {
    protected char operator;
    protected int priority;

    public Operatorobj(char opert, int pri){
        this.operator = opert;
        this.priority = pri;
    }

    public int getPriority() {
        return priority;
    }

    public char getOperator() {
        return operator;
    }
} // end of OperatorobjClass
