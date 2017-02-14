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
public class Test {

    public void run() {

        for (int i = -5; i <= 5; i++) {
            System.out.println("i: " + i + "; f(x): " + f((double) (i)));
        }

    }

    static double integrateTrapeze(double a, double b, int N) {
        double h = (b - a) / N;              // step size
        double sum = 0.5 * (f(a) + f(b));    // area
        for (int i = 1; i < N; i++) {
            double x = a + h * i;
            sum = sum + f(x);
        }

        return sum * h;
    }

    public static double integrateSimpson(double a, double b) {
        int N = 10000;                    // precision parameter
        double h = (b - a) / (N - 1);     // step size

        // 1/3 terms
        double sum = 1.0 / 3.0 * (f(a) + f(b));

        // 4/3 terms
        for (int i = 1; i < N - 1; i += 2) {
            double x = a + h * i;
            sum += 4.0 / 3.0 * f(x);
        }

        // 2/3 terms
        for (int i = 2; i < N - 1; i += 2) {
            double x = a + h * i;
            sum += 2.0 / 3.0 * f(x);
        }

        return sum * h;
    }



    static double f(double x) {

        double y = Math.pow(x, 3) + Math.pow(x, 2) + x + 3d;
        return y;
    }

    public interface MathOperation {
        double execute(double x);
    }

    public class powerSeries implements MathOperation {

        @Override
        public double execute(double x) {
            return Math.pow(x, 3) + Math.pow(x, 2) + x + 3d;
        }
    }
}
