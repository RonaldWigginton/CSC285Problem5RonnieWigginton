import java.util.Arrays;

public class MatrixParser {

    GenericManagerStacks<Operatorobj> operatorStack = new GenericManagerStacks<Operatorobj>();
    GenericManagerStacks operandStack = new GenericManagerStacks();
    char[] operands = {'A', 'B', 'C', 'D', 'E', 'F', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}; // each operand shares the same index with its values aka int [][] in the [][][]
    int[][][] operandsValue = {{{1, 2}, {3, 4}}, {{6, 6}, {8, 8}}, {{1, 2}, {2, 1}}, {{3, 3}, {3, 3}}, {{15, 15}, {15, 15}},
            {{4, 4}, {4, 4}}, {{0, 0}, {0, 0}}, {{1, 1}, {1, 1}}, {{2, 2}, {2, 2}}, {{3, 3}, {3, 3}}, {{4, 4}, {4, 4}}, {{5, 5}, {5, 5}}, {{6, 6}, {6, 6}}, {{7, 7}, {7, 7}}, {{8, 8}, {8, 8}}, {{9, 9}, {9, 9}}}; // I have made constants into int[][] for simplicity
    char[] operators = {'@', '*', '/', '+', '-', ')', '(', '%', '#'}; // heres the same operators
    int[] operatorsValue = {3, 2, 2, 1, 1, 99, -99, 2, -100};        /// with their same values


    MatrixParser(String equation) { // start of the Matrix Parser constructor ... probably could of made a default const and a method but ohh well
        char[] express = equation.toCharArray(); // turn our string to an char array to iterate through
        System.out.println(operandsValue[0][0]);
        Operatorobj operator = new Operatorobj('#', findVal('#', operators, operatorsValue));
        operatorStack.pushnode(operator);

        int[][] exValue; // since we are dealing with int[][] everything will be in int[][]
        int oprior;
        int[][] ivalu;
        int i = 0;

        while (express[i] != '#') {
            System.out.println("Parsing " + express[i]);


            if (((express[i] >= '0') && (express[i] <= '9')) || ((express[i] >= 'A') && (express[i] <= 'Z'))) { // if its an operand
                System.out.println(express[i] + " is an Operand!");

                ivalu = findOperandVal(express[i], operands, operandsValue);
                if (ivalu[0][0] == -99) {
                    System.out.println("no value in table for " + express[i]);
                }
                System.out.println("pushing node " + express[i] + " on the stack");
                operandStack.pushnode(ivalu);

            } else { // its and operator
                System.out.println(express[i] + " is an Operator!");

                if (express[i] == '(') {
                    System.out.println("pushing node " + express[i] + " on the stack");
                    Operatorobj pnodeo = new Operatorobj(express[i], -99);
                    operatorStack.pushnode(pnodeo);
                } else if (express[i] == ')' && !operatorStack.staclEmpty()) {
                    while ((operatorStack.peeknode().operator != '(')) {
                        popEvalueAndpushnode(operatorStack, operandStack);

                    }
                    operatorStack.popnode();
                } // end of this is right parenthesis
                else { // not a ( or a )
                    System.out.println("DEBUG: " + express[i]);
                    oprior = findVal(express[i], operators, operatorsValue);
                    System.out.println("peeking at top of the stack" + (operatorStack.peeknode().priority));
                    while (oprior <= (operatorStack.peeknode().priority)) {
                        popEvalueAndpushnode(operatorStack, operandStack);
                    }
                    System.out.println("pushing node Operator " + express[i] + " with Priority " + oprior);
                    Operatorobj pnodeo = new Operatorobj(express[i], oprior);
                    operatorStack.pushnode(pnodeo);
                } // end of is not a ( or )
            }// end of operator stack

            i++;

        } // end of while express loop
        while ((operatorStack.peeknode()).operator != '#') {
            popEvalueAndpushnode(operatorStack, operandStack);
        }
        exValue = (int[][]) operandStack.popnode();
        System.out.println("The value for this expression is " + exValue);
        System.out.println(Arrays.deepToString(exValue));

    } // end of Matrix Parser

    public static void popEvalueAndpushnode(GenericManagerStacks<Operatorobj> x, GenericManagerStacks y) { // start of popEvalueAndpushnode
        int[][] a, b, c;
        char operandx;
        operandx = (x.popnode().getOperator());
        a = (int[][]) y.popnode(); // scine we are using int [][] we have to do some casting
        b = (int[][]) y.popnode();
        System.out.println("in pop Evaluate " + Arrays.deepToString(b) + operandx + Arrays.deepToString(a));
        c = MatrixMatrixEval(b, operandx, a); // custom matrix matrix evaluator
        y.pushnode(c);
        return;

    } // end of popEvalueAndpushnode


    public static int[][] MatrixMatrixEval(int[][] oper1, char oper, int[][] oper2) { // start of Matrix Matrix eval Method

        int[][] result = new int[oper1.length][oper1[0].length];
        switch (oper) {
            case '+':
                for (int i = 0; i < oper1.length; i++) {
                    for (int j = 0; j < oper1[0].length; j++) {
                        result[i][j] = oper1[i][j] + oper2[i][j];
                    }
                }
                System.out.println(Arrays.deepToString(oper1) + " + " + Arrays.deepToString(oper2) + " = " + Arrays.deepToString(result));
                return result;

            case '-':
                for (int i = 0; i < oper1.length; i++) {
                    for (int j = 0; j < oper1[0].length; j++) {
                        result[i][j] = oper1[i][j] - oper2[i][j];
                    }
                }
                System.out.println(Arrays.deepToString(oper1) + " - " + Arrays.deepToString(oper2) + " = " + Arrays.deepToString(result));
                return result;

            case '*':
                for (int i = 0; i < oper1.length; i++) {
                    for (int j = 0; j < oper1[0].length; j++) {
                        result[i][j] = oper1[i][j] * oper2[i][j];
                    }
                }
                System.out.println(Arrays.deepToString(oper1) + " * " +Arrays.deepToString(oper2) + " = " + Arrays.deepToString(result));
                return result;

            case '/':
                for (int i = 0; i < oper1.length; i++) {
                    for (int j = 0; j < oper1[0].length; j++) {
                        if (oper2[i][j] == 0) {
                            System.out.println("cant Divide by 0!!");
                            System.exit(-1); // exit because Error
                        }
                        result[i][j] = oper1[i][j] / oper2[i][j];
                    }
                }
                System.out.println( Arrays.deepToString(oper1) + " / " + Arrays.deepToString(oper2) + " = " + Arrays.deepToString(result));
                return result;
        }
        return result;

    } // end of MatrixConstantEval method


    public int findVal(char c, char[] keyTable, int[] valueTable) { // start of findVal method
        int value = -99;
        for (int i = 0; i < keyTable.length; i++) {
            if (keyTable[i] == c) {
                value = valueTable[i];
                System.out.println(c + " value is " + value);
            }
        }
        return value;
    }

    public int[][] findOperandVal(char c, char[] keyTable, int[][][] valueTable) { // start of findoperandval method
        int[][] value = new int[2][2];
        value[0][0] = -99;
        for (int i = 0; i < keyTable.length; i++) {         // we needed another way to find our int[][] so We search though a int[][][] where the index is shared with the given char in its array
            if (keyTable[i] == c) {
                value = valueTable[i];
                System.out.println(c + " value is " + Arrays.deepToString(value));
            }
        }
        return value; // this value is really just the pointer to our int [][]
    } // end of findOperandVal

}


