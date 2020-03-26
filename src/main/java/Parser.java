import java.util.LinkedList;

public class Parser {
    GenericManagerStacks<Operatorobj> operatorStack = new GenericManagerStacks<Operatorobj>();
    GenericManagerStacks operandStack = new GenericManagerStacks();
    char[] operands = {'A','B','C','D','E','F','0','1','2','3','4','5','6','7','8','9'}; // list of operands numbers and variables
    int [] operandsValue = {8,12,2,3,15,4,0,1,2,3,4,5,6,7,8,9}; // their numeric representation                                     Each paired list uses the same index for key and value
    char[] operators= {'@','*','/','+','-',')','(','%','#'};   /// Operators in a array with
    int [] operatorsValue = {3,2,2,1,1,99,-99,2,-100};         // another array for their values in terms of Order of operations



    Parser(String equation){ // start of Constructor
        char[] express = equation.toCharArray(); // turning our string to an char array

        Operatorobj operator = new Operatorobj('#',findVal('#',operators,operatorsValue)); // adding a place holder
        operatorStack.pushnode(operator); // adding it to the stack

        int exValue,ivalu,oprior;
        int i = 0;

        while (express[i]!= '#'){
            System.out.println("Parsing " + express[i]);



            if(((express[i]>='0') &&(express[i]<='9')) || ((express[i]>= 'A') && (express[i]<='Z'))){ // checking if its an Operand
                System.out.println(express[i] + " is an Operand!");
                ivalu = findVal(express[i],operands,operandsValue); // finding the value in the table
                if(ivalu==-99){
                    System.out.println("no value in table for " + express[i]);
                }
                System.out.println("pushing node " + express[i] + " on the stack");
                operandStack.pushnode(ivalu); /// pushing the value on our stack
            }
            else { // its and operator
                System.out.println(express[i] + " is an Operator!");

                if (express[i] == '(') {
                    System.out.println("pushing node " + express[i] + " on the stack");
                    Operatorobj pnodeo = new Operatorobj(express[i], -99);
                    operatorStack.pushnode(pnodeo);                                 // these parenthesis are very special to us
                } else if (express[i] == ')' && !operatorStack.staclEmpty()) {      //we handle them differently
                    while (( operatorStack.peeknode().operator != '(') ) {      // when we get a ) mathematically we need to solve everything till the (.
                        popEvalueAndpushnode(operatorStack, operandStack); // we do this by a popvalue and push function

                    }
                    operatorStack.popnode(); // this is the result from the parenthesis back on the stack
                } // end of this is right parenthesis
                else { // not a ( or a )
                    System.out.println("DEBUG: "+express[i]);
                    oprior = findVal(express[i], operators, operatorsValue);
                    System.out.println("peeking at top of the stack" + (operatorStack.peeknode().priority));
                    while (oprior <= (operatorStack.peeknode().priority)) {
                        popEvalueAndpushnode(operatorStack, operandStack);                          // These all have priority's and get pushed on the stack
                    }                                                                               // until a )
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
        exValue =(Integer) operandStack.popnode(); // changed our linked list to generic so we have to cast to a Integer object
        System.out.println("The value for this expression is " + exValue);

    }

    public static void popEvalueAndpushnode(GenericManagerStacks<Operatorobj> x,GenericManagerStacks<Integer> y){ // start of popEvalueAndpushnode
        int a,b,c;
        char operandx;
        operandx = (x.popnode().getOperator());
        a=y.popnode();
        b=y.popnode();
        System.out.println("in pop Evaluate " + b + operandx + a);      // pops the first to operands and their operator off the stack calculate then back on the stack!
        c = IntEval(b,operandx,a);
        y.pushnode(c);
        return;
    } // end of popEvalueAndpushnode

    public static int IntEval(int oper1, char oper, int oper2) { // start of IntEval Method

        int result = 0;
        switch (oper) {
            case '@':
                if (oper2 == 0) {
                    result = 1;
                    return result;
                } else {
                    result = oper1;
                    for(int i=1; i < oper2; i++){
                        result = result * oper1;
                    }

                    System.out.println(oper1 + "^" + oper2 + " = " + result);   // this does all of the actual math in our equation by taking the two operands and given operator
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
                value= valueTable[i];                       // this takes our equation char and two tables Char and key table returns the value of the given char for our equation
                System.out.println(c +" value is " + value);
            }
        }
        return value;
    }
} // end of findVal
