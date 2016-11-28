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
public class Integrators {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Test t = new Test();

        t.run();

        System.out.println("5   Steps: " + Test.integrateTrapeze(-5, 5, 5));
        System.out.println("10  Steps: " + Test.integrateTrapeze(-5, 5, 10));
        System.out.println("50  Steps: " + Test.integrateTrapeze(-5, 5, 50));

        System.out.println("100 Steps: " + Test.integrateTrapeze(-5d, 5d, 100));
        System.out.println("Simpson Rule: " + Test.integrateSimpson(-5d, 5d));


    }

     static double fun(double x) {

        double y = Math.pow(x, 3) + Math.pow(x, 2) + x + 3d;
        return y;
    }



}