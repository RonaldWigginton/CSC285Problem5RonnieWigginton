/*
    Author: Ronnie Wigginton
    Project:

 */


public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Equation 1 Parse:\n");
        String equation1 = "A@(2*(A-C*D))+(9*B/(2*C+1)-B*3)+E%(F-A)#";
        String equation2 = "B*(3@(A-D)%(B-C@D))+4@D*2#";
        String matrixEqation = "2*A-3*((B-2*C)/(A+3)-B*3)#";
       Parser Equation1Parse = new Parser(equation1);
        System.out.println("Starting Equation 2 Parse:\n");
       Parser Equation2Parse = new Parser(equation2);
        System.out.println("Starting Matrix Equation Parse:\n");
       MatrixParser matrixParser = new MatrixParser(matrixEqation);
    }
}
