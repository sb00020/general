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
class NewTests {

    public NewTests() {
    }

    double trapeze(double min, double max, int steps, Function.DoubleOperation cubic) {

        double h = (max - min) / steps;              // step size
        double sum = 0.5 * (cubic.operation(min) + cubic.operation(max));    // area
        for (int i = 1; i < steps; i++) {
            double x = min + h * i;
            sum = sum + cubic.operation(x);
        }

        return sum * h;
    }

    double simpson(double min, double max, int precision, Function.DoubleOperation cubic) {
             
        double h = (max - min) / (precision - 1);     // step size

        // 1/3 terms
        double sum = 1.d / 3.d * (cubic.operation(min) + cubic.operation(max));

        // 4/3 terms
        for (int i = 1; i < precision - 1; i += 2) {
            double x = min + h * i;
            sum += 4.d / 3.d * cubic.operation(x);
        }

        // 2/3 terms
        for (int i = 2; i < precision - 1; i += 2) {
            double x = min + h * i;
            sum += 2.d / 3.d * cubic.operation(x);
        }

        return sum * h;
    }

    double test(Function.DoubleOperation cubic, double d) {

        return cubic.operation(d);

    }

}
