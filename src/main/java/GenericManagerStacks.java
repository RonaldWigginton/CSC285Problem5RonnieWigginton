import java.util.ArrayList;

public class GenericManagerStacks<T> {
    protected ArrayList<T> myStack;
    protected int number;
    public GenericManagerStacks() {
        number = 0;
        myStack = new ArrayList<T>(100);
    }
    public int getNumber(){
        return number;
    }
    public int pushnode(T x){
        myStack.add(number,x);
        number++;
        return number;
    }
    public T popnode() {
        T nodeval;

        nodeval = myStack.get(number - 1);
        myStack.remove(number-1);
        number--;
        return nodeval;
    }
    public T peeknode(){
        T nodeval;
        nodeval = myStack.get(number-1);
        return nodeval;
    }
    boolean staclEmpty() {
        return (number == 0);
    }
}


