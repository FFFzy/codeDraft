import java.util.Scanner;

public class text3 {
    public static void main(String[] args){
        double a,b,c;
        System.out.println("Please enter the number of a、b、c：");
        Scanner input = new Scanner(System.in);
        a = input.nextDouble();
        b = input.nextDouble();
        c = input.nextDouble();

        QuadraticEquation quadraticEquation = new QuadraticEquation(a,b,c);
        System.out.println("Discriminant:"+quadraticEquation.getDiscriminant());
        if (quadraticEquation.getDiscriminant()>0)
            System.out.println("Root1:"+quadraticEquation.getRoot1()+" Root2:"+quadraticEquation.getRoot2());
        else if (quadraticEquation.getDiscriminant()==0)
            System.out.println("Only one root:"+quadraticEquation.getRoot1());
        else System.out.println("The equation has no roots");
    }
}

class QuadraticEquation{
    private double a;
    private double b;
    private double c;

    QuadraticEquation(double newA,double newB,double newC){
        a = newA;
        b = newB;
        c = newC;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    double getDiscriminant(){
        return b*b-4*a*c;
    }

    double getRoot1(){
        if (getDiscriminant()>=0) {
            double r1 = (-b + Math.sqrt(getDiscriminant())) / (2 * a);
            return r1;
        }
        else return  0;
    }

    double getRoot2(){
        if (getDiscriminant()>=0) {
            double r2 = (-b - Math.sqrt(getDiscriminant())) / (2 * a);
            return r2;
        }
        else  return 0;
    }
}