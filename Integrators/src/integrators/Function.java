/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrators;

/**
 *
 * @author brettsa
 */
public class Function {

    public static void main(String args[]) {
        Function tester = new Function();

        //with type declaration
        MathOperation addition = (int a, int b) -> a + b;

        //with out type declaration
        MathOperation subtraction = (a, b) -> a - b;

        //with return statement along with curly braces
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };

        //without return statement and without curly braces
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));

        //with parenthesis
        GreetingService greetService1 = message
                -> System.out.println("Hello " + message);

        //without parenthesis
        GreetingService greetService2 = (message)
                -> System.out.println("Hello " + message);

        greetService1.sayMessage("Sam");
        greetService2.sayMessage("Ben");

        double xy = 9d;

        DoubleOperation cubic = x -> Math.pow(x, 3) + Math.pow(x, 2) + x + 3d;

        System.out.println("10 ^3 = " + tester.operate(10, cubic));

        NewTests nt = new NewTests();

        for (int i = 1; i < 5; i++) {
            
            double d = nt.test(cubic, (double)i);
            System.out.println("d: " + d);

        }
        
        System.out.println(nt.trapeze(-5d, 5d, 1000, cubic));
        
        System.out.println(nt.simpson(-5d, 5d, 1000000, cubic));
        
    }

    interface MathOperation {

        int operation(int a, int b);
    }

    interface DoubleOperation {

        double operation(double b);
    }

    interface GreetingService {

        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    private static double operate(double a, DoubleOperation mathOperation) {
        return mathOperation.operation(a);
    }
}
