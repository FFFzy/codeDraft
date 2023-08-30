import java.util.Scanner;

public class test2 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("请分别输入两个圆的半径:");
        double radius1 = input.nextDouble();
        double radius2 = input.nextDouble();

        ComparableCircle circle1 = new ComparableCircle(radius1);
        ComparableCircle circle2 = new ComparableCircle(radius2);

        int i = circle1.comparaTo(circle2);
        if (i > 0)
            System.out.println("Area：circle1 > circle2");
        else if (i == 0)
            System.out.println("Area：circle1 = circle2");
        else
            System.out.println("Area：circle1 < circle2");
    }
}

//ComparableCircle类
class ComparableCircle extends Circle implements Comparable<ComparableCircle> {
    public ComparableCircle(double radius){
        super(radius);
    }

    public int comparaTo(ComparableCircle o){
        if(getArea() > o.getArea())
            return 1;
        else if(getArea() == o.getArea())
            return 0;
        else
            return -1;
    }
}

//接口Comparable
interface Comparable<ComparableCircle>{
    public int comparaTo(ComparableCircle o);
}

//Circle类
class Circle{
    public static final double PI = 3.14159;
    private double radius;

    public Circle(){
        radius=1.0;
    }

    public Circle(double radius){
        this.radius=radius;
    }

    public double getRadius(){
        return radius;
    }

    public void setRadius(double radius){
        this.radius=radius;
    }

    public double getArea(){
        return PI*radius*radius;
    }

    public double getPerimeter(){
        return PI*radius*2;
    }
}
