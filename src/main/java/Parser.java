import java.util.LinkedList;

public class Parser {
    LinkedList<Operatorobj> operatorStack = new LinkedList<Operatorobj>();
    LinkedList<Integer> operandStack = new LinkedList<Integer>();
    char[] operands = {'A','B','C','D','E','F',0,1,2,3,4,5,6,7,8,9};
    int [] operandsValue = {8,12,2,3,15,4,0,1,2,3,4,5,6,7,8,9};
    char[] operators= {'@','*','/','+','-',')','(','#'};
    int [] operatorsValue = {3,2,2,1,1,99,-99,-100};



    Parser(String equation){
        char[] equationChars = equation.toCharArray();

        System.out.println("pushing # on operand Stack with value " +findVal('#',operators,operatorsValue));
        // will throw out of Bounds if not in array... I know it will be in there every time - Ronnie
        Operatorobj operator = new Operatorobj('#',findVal('#',operators,operatorsValue));
        operatorStack.push(operator);

        int exValue,tempValue,oprior;
        for (int i = 0; i <equationChars.length ; i++) {
            System.out.println("Parsing " + equationChars[i]);
            if(equationChars[i]=='#'){
                while(operatorStack.peekFirst().operator!='#'){
                    popEvalueAndPush(operatorStack,operandStack);
                }
                exValue = operandStack.pop();
                System.out.println("The Value for this expression is " + exValue +" !!!");
            }

            if(((equationChars[i]>='0') &&(equationChars[i]<='9')) || ((equationChars[i]>= 'A') && (equationChars[i]<='Z'))){ // if its an operand
                System.out.println(equationChars[i] + " is an Operand!");
                tempValue = findVal(equationChars[i],operands,operandsValue);
                if(tempValue==-99){
                    System.out.println("no value in table for " + equationChars[i]);
                }
                System.out.println("Pushing " + equationChars[i] + " on the stack");
                operandStack.push(tempValue);
            }
            else { // its and operator
                System.out.println(equationChars[i] + " is an Operator!");
                if(equationChars[i]=='('){
                    System.out.println("Pushing " + equationChars[i] + " on the stack");
                    Operatorobj pnodeo = new Operatorobj(equationChars[i],-99);
                    operatorStack.push(pnodeo);
                }
                else {
                    if(equationChars[i]==')'){
                        while((operatorStack.peekFirst().operator!='(')){
                            popEvalueAndPush(operatorStack,operandStack);
                            operatorStack.pop();
                        }
                    }
                    else { // not a ( or a )
                        oprior = findVal(equationChars[i],operators,operatorsValue);
                        System.out.println("peeking at top of the stack" + (operatorStack.peekFirst().priority));
                        while(oprior<=(operatorStack.peekFirst().priority)){
                            popEvalueAndPush(operatorStack,operandStack);
                            System.out.println("pushing Operator " +equationChars[i] + " with Priority " + oprior);
                            Operatorobj pnodeo = new Operatorobj(equationChars[i],oprior);
                            operatorStack.push(pnodeo);
                        }
                    }
                }
            }
        }
        while((operatorStack.peekFirst()).operator!='#'){
            popEvalueAndPush(operatorStack,operandStack);
            exValue = operandStack.pop();
            System.out.println("THe value for this expression is " + exValue);
        }
    }

public static void popEvalueAndPush(LinkedList<Operatorobj> x,LinkedList<Integer> y){ // start of popEvalueAndPush
        int a,b,c;
        char operandx;
        operandx = (x.pop().getOperator());
        a=y.pop();
        b=y.pop();
        System.out.println("in pop Evaluate " + b + operandx + a);
        c = IntEval(b,operandx,a);
        y.push(c);
        return;
} // end of popEvalueAndPush

public static int IntEval(int oper1, char oper, int oper2){ // start of IntEval Method
    int result = -99;
        switch(oper){
            case '@' :
                if(oper2 == 0){
                    result = 1;
                }
                else {
                    result = oper1;
                    for (int i = 0; i < oper2; i++) {
                        result = result + oper1;
                    }
                    System.out.println(oper1 + "^" + oper2 +" = " + result);
                }
            case '+' :
                result = oper1 + oper2;
                System.out.println(oper1 + " + " + oper2 +" = " + result);
            case '-' :
                result = oper1 - oper2;
                System.out.println(oper1 + " - " + oper2 +" = " + result);
            case '*' :
                result = oper1 * oper2;
                System.out.println(oper1 + " * " + oper2 +" = " + result);
            case '/' :
                if(oper1 != 0){
                    result = oper1/oper2;
                    System.out.println(oper1 +"/" + oper2 +" = "+result);
                }
                else {
                    System.out.println("Divide by 0 not allowed!!");
                    return -99;
                }
        }
        return result;
} // end of IntEval method


    public int findVal(char c, char [] keyTable, int [] valueTable) { // start of findVal method
        int value = -99;
        for (int i = 0; i < keyTable.length; i++) {
            if (keyTable[i] == c) {
                value= valueTable[i];
                System.out.println(c +" value is " + value);
            }
        }
        return value;
    }
} // end of findVal
