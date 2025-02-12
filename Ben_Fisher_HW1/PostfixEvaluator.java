package Ben_Fisher_HW1;

import java.util.Stack;

public class PostfixEvaluator {
    
    private String postfixString;

    public int evaluate(String postfixString){
        Stack<Integer> ints = new Stack<>();
        int ans = 0;
        for(Character c : postfixString.toCharArray()){
            if(isOperand(c)){
                ints.push(Character.getNumericValue(c));
            }
            else if(isOperator(c)){
                int res = performOp(c, ints.pop(), ints.pop());
                ints.push(res);
            }
        }
        ans = ints.pop();
        return ans;
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

    private static int performOp(char op, int val1, int val2){
        if(op == '+'){
            return val2+val1;
        }
        else if(op == '-'){
            return val2-val1;
        }
        else if(op == '*'){
            return val2*val1;
        }
        else if(op == '/'){
            return val2/val1;
        }
        else{
            System.out.println("Invalid Operator");
            return -10;
        }
    }
/*  Math Test:
    public static void main(String[] args) {
        String s = new String("3-2+1");
        InfixToPostfixConverter i = new InfixToPostfixConverter();
        i.convertToPostfix(s);
        System.out.println(i.getPostfixString());
        PostfixEvaluator evaluator = new PostfixEvaluator();
        int ans = evaluator.evaluate(i.getPostfixString());
        System.out.println(ans);
    }*/
}
