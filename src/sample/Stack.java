package sample;

public class Stack {

    public static final int ASCII = 48;
    int[] arr = new int[100];
    int lastElementPos = -1;

    public Stack(){
        init();
    }

    public int pop(){
        int removed = arr[lastElementPos];
        arr[lastElementPos] = -1;
        lastElementPos--;
        return removed;
    }

    public void pushChar(int value){
        value -= ASCII;
        lastElementPos++;
        arr[lastElementPos] = value;
    }

    public void push(int value){
        lastElementPos++;
        arr[lastElementPos] = value;
    }

    private void init(){
        for (int i : arr) {
            arr[i] = -1;
        }
    }

}
