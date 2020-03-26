import java.util.ArrayList;

public class GenericManagerStacks<T> { // Generic Stack manager class
    protected ArrayList<T> myStack; // has a Arraylist with generic Type
    protected int number;               // number used to track elements in array list
    public GenericManagerStacks() { // default constructor
        number = 0;
        myStack = new ArrayList<T>(100);
    }
    public int getNumber(){
        return number;
    } // returns the number
    public int pushnode(T x){ // adds a generic obj to our Array list
        myStack.add(number,x); // add function
        number++; // increases our number of objects
        return number; // return our number
    }
    public T popnode() { // pops top of our array list off the stack
        T nodeval;

        nodeval = myStack.get(number - 1);
        myStack.remove(number-1);
        number--;
        return nodeval;
    }
    public T peeknode(){ // allows us to peek at the object on top of our stack
        T nodeval;
        nodeval = myStack.get(number-1);
        return nodeval;
    }
    boolean staclEmpty() {
        return (number == 0);
    } // lets us know when we are empty
}


