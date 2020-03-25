/*
    Author: Ronnie Wigginton
    Project:

 */


public class Main {
    public static void main(String[] args) {

        String equation1 = "A@(2*(A-C*D))+(9*B/(2*C+1)-B*3)+E%(F-A)#";
        String equation2 = "B*(3@(A-D)%(B-C@D))+4@D*2#";
        Parser parser = new Parser(equation1);
    }
}
