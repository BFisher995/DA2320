package Ben_Fisher_HW1;

import java.util.Stack;

public class InfixToPostfixConverter {
    
    private String postfixString, infixString;

    public InfixToPostfixConverter(){}

    public void convertToPostfix(String infixString) throws InvalidCharacterException{
        Stack<Character> chars = new Stack<>();
        StringBuffer str = new StringBuffer();

        for(int i = 0; i < infixString.length(); i++){
            char c = infixString.charAt(i);

            if(isOperand(c)){
                str.append(c);
            }
            else if(isLeftParen(c)){
                chars.push(c);
            }
            else if(isRightParen(c)){
                while (!chars.isEmpty() && chars.peek() != '(') {
                    str.append(chars.pop());
                }
                chars.pop();
            }
            else {
                if(isOperator(c) && chars.isEmpty()){
                    chars.push(c);
                }
                else if(chars.peek() == '(' || precedence(c) > precedence(chars.peek())){
                    chars.push(c);
                }
                else{
                    while(!chars.isEmpty() && chars.peek()!='('){
                        str.append(chars.pop());
                    }
                    chars.push(c);
                }
            }
        }
        while(!chars.isEmpty()){
            str.append(chars.pop());
        }
        postfixString = str.toString();
    }
    
    public String getPostfixString(){
        return postfixString;
    }
    
    private static boolean isOperator(char ch){
        return ch=='+' || ch=='-' || ch=='*' || ch=='/';
    }

    private static boolean isOperand(char ch){
        return ch=='0'|| ch=='1' || ch=='2' || ch=='3' || ch=='4' || ch=='5' || ch=='6' || ch=='7' || ch=='8' || ch=='9';
    }
    
    private static boolean isLeftParen(char ch){
        return ch=='(';
    }
    
    private static boolean isRightParen(char ch){
        return ch==')';
    }
    
    private static int precedence (char ch) throws InvalidCharacterException{
        if(ch=='+' || ch=='-'){
            return 1;
        }
        else if(ch=='*' || ch=='/'){
            return 2;
        }
        else{
            throw new InvalidCharacterException("Invalid Character");
        }
    }

    /*  Converstion Test:
    public static void main(String[] args) {
        String s = new String("732");
        InfixToPostfixConverter i = new InfixToPostfixConverter();
        i.convertToPostfix(s);
        System.out.println(i.getPostfixString());
    }*/
}
