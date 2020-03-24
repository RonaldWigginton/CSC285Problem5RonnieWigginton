import java.util.LinkedList;

public class Parser {
    GenericManagerStacks<Operatorobj> operatorStack = new GenericManagerStacks<Operatorobj>();
    GenericManagerStacks<Integer> operandStack = new GenericManagerStacks<Integer>();
    char[] operands = {'A','B','C','D','E','F','0','1','2','3','4','5','6','7','8','9'};
    int [] operandsValue = {8,12,2,3,15,4,0,1,2,3,4,5,6,7,8,9};
    char[] operators= {'@','*','/','+','-',')','(','%','#'};
    int [] operatorsValue = {3,2,2,1,1,99,-99,2,-100};



    Parser(String equation){
        char[] express = equation.toCharArray();

        //System.out.println("pushnodeing # on operand Stack with value " +findVal('#',operators,operatorsValue));
        // will throw out of Bounds if not in array... I know it will be in there every time - Ronnie
        Operatorobj operator = new Operatorobj('#',findVal('#',operators,operatorsValue));
        operatorStack.pushnode(operator);

        int exValue,ivalu,oprior;
        int i = 0;

        while (express[i]!= '#'){
            System.out.println("Parsing " + express[i]);



            if(((express[i]>='0') &&(express[i]<='9')) || ((express[i]>= 'A') && (express[i]<='Z'))){ // if its an operand
                System.out.println(express[i] + " is an Operand!");
                ivalu = findVal(express[i],operands,operandsValue);
                if(ivalu==-99){
                    System.out.println("no value in table for " + express[i]);
                }
                System.out.println("pushing node " + express[i] + " on the stack");
                operandStack.pushnode(ivalu);
            }
            else { // its and operator
                System.out.println(express[i] + " is an Operator!");

                if (express[i] == '(') {
                    System.out.println("pushnodeing " + express[i] + " on the stack");
                    Operatorobj pnodeo = new Operatorobj(express[i], -99);
                    operatorStack.pushnode(pnodeo);
                } else if (express[i] == ')' && !operatorStack.staclEmpty()) {
                    while (( operatorStack.peeknode().operator != '(') ) {
                        popEvalueAndpushnode(operatorStack, operandStack);

                        operatorStack.popnode();
                    }
                } // end of this is right parenthesis
                else { // not a ( or a )
                    System.out.println("DEBUG: "+express[i]);
                    oprior = findVal(express[i], operators, operatorsValue);
                    System.out.println("peeking at top of the stack" + (operatorStack.peeknode().priority));
                    while (oprior <= (operatorStack.peeknode().priority)) {                                      /// getting an error with operatorStack.peeknode() when stack is 0 trying to peek at number-1
                        popEvalueAndpushnode(operatorStack, operandStack);
                    }
                        System.out.println("pushnodeing Operator " + express[i] + " with Priority " + oprior);
                        Operatorobj pnodeo = new Operatorobj(express[i], oprior);
                        operatorStack.pushnode(pnodeo);


                } // end of is not a ( or )
            }// end of operator stack

                i++;

        } // end of while express loop
        while((operatorStack.peeknode()).operator!='#') {
            popEvalueAndpushnode(operatorStack, operandStack);
        }
            exValue = operandStack.popnode();
            System.out.println("The value for this expression is " + exValue);

    }

public static void popEvalueAndpushnode(GenericManagerStacks<Operatorobj> x,GenericManagerStacks<Integer> y){ // start of popEvalueAndpushnode
        int a,b,c;
        char operandx;
        operandx = (x.popnode().getOperator());
        a=y.popnode();
        b=y.popnode();
        System.out.println("in pop Evaluate " + b + operandx + a);
        c = IntEval(b,operandx,a);
        y.pushnode(c);
        return;
} // end of popEvalueAndpushnode

public static int IntEval(int oper1, char oper, int oper2) { // start of IntEval Method

    int result = 0;
    switch (oper) {
        case '@':
            if (oper2 == 0) {
                return 1;
            } else {
                int temp = 0;
                for (int i = 0; i < oper2; i++) {
                    temp = temp + oper1;
                }
                result = temp;
                System.out.println(oper1 + "^" + oper2 + " = " + result);
                return result;
            }
        case '+':
            result = oper1 + oper2;
            System.out.println(oper1 + " + " + oper2 + " = " + result);
            return result;

        case '-':
            result = oper1 - oper2;
            System.out.println(oper1 + " - " + oper2 + " = " + result);
            return result;

        case '*':
            result = oper1 * oper2;
            System.out.println(oper1 + " * " + oper2 + " = " + result);
            return result;
        case '%':
            result = oper1 % oper2;
            System.out.println(oper1 + " % " + oper2 + " = " + result);
            return result;

        case '/':
            if (oper2 != 0) {
                result = oper1 / oper2;
                System.out.println(oper1 + "/" + oper2 + " = " + result);

              return result;

            } else {
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
