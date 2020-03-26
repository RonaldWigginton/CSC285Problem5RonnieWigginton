public class Operatorobj { // operator obj Class
    protected char operator; // char for oper
    protected int priority; // priority in order of operations

    public Operatorobj(char opert, int pri){
        this.operator = opert;
        this.priority = pri;
    }

    public int getPriority() {
        return priority;
    } // returns the priority

    public char getOperator() { // returns our operator
        return operator;
    }
} // end of OperatorobjClass
