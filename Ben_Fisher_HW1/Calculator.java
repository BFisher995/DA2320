package Ben_Fisher_HW1;

import java.io.*;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        InfixToPostfixConverter converter;
        PostfixEvaluator evaluator;
        converter = new InfixToPostfixConverter();
        evaluator = new PostfixEvaluator();

        try {
            Scanner sc = new Scanner(new File(args[0]));
            PrintStream ps = new PrintStream(
                new FileOutputStream(
                new File(args[1])));
            String infixString;

            while (sc.hasNext()) {
                System.out.println("reading");
                infixString = sc.nextLine(); // read next line of input file
                converter.convertToPostfix(infixString);
                String postfixString = converter.getPostfixString();
                int ans = evaluator.evaluate(postfixString);
                String outString = infixString + " converts to "+ postfixString + " evaluates to " + ans;
                ps.println(outString); // print String to output file
                System.out.println(outString); // print String to screen
            }
        } catch (IOException io) {
            System.out.println(io);
        } catch (InvalidCharacterException e) {
            System.out.println(e);
        } finally { // executed if any catch blocks
            System.exit(1); // are executed
        }
    }
}
