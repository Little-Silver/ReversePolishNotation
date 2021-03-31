package sample;

public class ReversePolishNotation {

    char[] arr = {'$', '0', '0', '0', '0', '0', '0', '0', '0', '0'};

    /**
     * judge the first char is a legal operator
     *
     * @param charStr the first char
     * @return if is one of +,-,*,/ return true then return false
     */
    private static boolean isOperatorSymbol(char charStr) {
        return switch (charStr) {
            case '+', '-', '*', '/' -> true;
            default -> false;
        };
    }

    /**
     * do calculate with two operate number
     *
     * @param n1       left operate num
     * @param n2       right operate num
     * @param operator the operator. eg: +,-,*,/
     * @return the result after calculating
     * @throws Exception
     */
    private static int doCalcWithTwoNum(int n1, int n2, char operator) throws Exception {
        switch (operator) {
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            case '*':
                return n1 * n2;
            case '/':
                if (n2 == 0) {
                    throw new IllegalArgumentException("Division by 0");
                }
                return n1 / n2;
            default:
                throw new Exception("can not do calculate, the operator is illegal");
        }
    }

    public int calculate(String input) throws Exception {
        init();
        isInputValid(input);
        return fillArr(input);
    }

/*
    private int fillArr(String input) throws Exception {

        input = input.replaceAll("\\s", "");

        Stack stack = new Stack();
        char c;
        int pos = 0;
        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            if(i == 0){

                if (!Character.isDigit(c)) {
                    System.out.println("Doesn't start with a digit!");
                    throw new IllegalArgumentException("ERROR");
                }
                else{
                    stack.pushChar(c);
                }
            }
            else{
                if(Character.isDigit(c))
                {
                    if (Character.isDigit(arr[pos]) || arr[pos] == '$')
                    {
                        pos++;
                        arr[pos] = c;
                        stack.pushChar(c);
                    }
                    else
                    {
                        throw new IllegalArgumentException("ERROR");
                    }
                }
                else if (isOperatorSymbol(c)){
                    if (Character.isDigit(arr[pos]))
                    {
                        int previous = stack.pop();
                        int beforePrevious = stack.pop();

                        int result = doCalcWithTwoNum(beforePrevious, previous, c);
                        stack.push(result);

                        arr[pos] = '0';
                        pos--;
                    }
                    else if (pos == 0 && arr[pos] == '$')
                    {
                        System.out.println("Not valid");
                        throw new IllegalArgumentException("ERROR");
                    }
                    else{
                        System.out.println("Not valid");
                        throw new IllegalArgumentException("ERROR");
                    }
                }
                else{
                    System.out.println("Invalid Input-Value");
                    throw new IllegalArgumentException("ERROR");
                }
            }

        }
        if (pos == 0){
            System.out.println("\nSuccess");
            int result = stack.pop();
            System.out.printf("\nResult: %d", result);
            return result;
        }
        return -1;
    }
    */

    private void isInputValid(String input) {
        int digitCounter = 0;
        int operatorCounter = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                digitCounter++;
            } else if (isOperatorSymbol(c)) {
                if (i == 0) {
                    throw new IllegalArgumentException("ERROR");
                }
                operatorCounter++;
            }
        }
        if (digitCounter - 1 != operatorCounter) {
            throw new IllegalArgumentException("ERROR");
        }

    }

    private void init() {
        arr[0] = '$';
        for (int i = 1; i < arr.length; i++) {
            arr[i] = 0;
        }
    }

    private int fillArr(String input) throws Exception {

        input = input.replaceAll("\\s", "");

        Stack stack = new Stack();
        char c;
        int pos = 0;
        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            if (i == 0) {
                stack.pushChar(c);
            } else {
                if (Character.isDigit(c)) {
                    pos++;
                    stack.pushChar(c);
                } else if (isOperatorSymbol(c)) {

                    int previous = stack.pop();
                    int beforePrevious = stack.pop();
                    int result = doCalcWithTwoNum(beforePrevious, previous, c);
                    stack.push(result);
                    pos--;
                }
            }
        }
        if (pos == 0) {
            System.out.println("\nSuccess");
            int result = stack.pop();
            System.out.printf("\nResult: %d", result);
            return result;
        }
        else{
            throw new IllegalArgumentException("ERROR");
        }
    }

}
