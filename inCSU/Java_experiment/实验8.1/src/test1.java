import java.util.Scanner;

public class test1 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean isContinue = true;

        while (isContinue){
            System.out.println("请输入三角形三边的边长：");
            double side1 = input.nextDouble();
            double side2 = input.nextDouble();
            double side3 = input.nextDouble();
            if ((side1+side2>side3) && (side1+side3>side2) && (side2+side3>side1)){
                System.out.println("请输入三角形的颜色：");
                String color = input.next();
                System.out.println("请输入三角形是否被填充(填充了为1，否则为0)：");
                int filled = input.nextInt();
                GeometricObject triangle = new Triangle(side1,side2,side3);
                triangle.setColor(color);
                boolean isFilled;
                if (filled == 1)
                    isFilled = true;
                else isFilled = false;
                triangle.setFilled(isFilled);
                System.out.println(triangle.toString());
                isContinue = false;
            }
            else System.out.println("这三条边不能构成一个三角形，请重新输入：");
        }
    }
}


//GeometricObject类
abstract class GeometricObject {
    private String color = "white";
    private boolean filled;
    private java.util.Date dateCreated;

    protected GeometricObject(){
        dateCreated = new java.util.Date();
    }

    protected GeometricObject(String color, boolean filled){
        dateCreated = new java.util.Date();
        this.color = color;
        this.filled = filled;
    }

    public String getColor(){
        return color;
    }

    public void setColor(String color){
        this.color = color;
    }

    public boolean isFilled(){
        return filled;
    }

    public void setFilled(boolean filled){
        this.filled = filled;
    }

    public java.util.Date getDateCreated(){
        return dateCreated;
    }

    @Override
    public String toString(){
        return "created on " + dateCreated + "\ncolor: " + color +
                " and filled: " + filled;
    }

    public abstract double getArea();

    public abstract double getPerimeter();
}

//Triangle类
class Triangle extends GeometricObject {
    private double side1;
    private double side2;
    private double side3;

    public Triangle(){
        side1 = 1.0;
        side2 = 1.0;
        side3 = 1.0;
    }

    public Triangle(double side1, double side2, double side3){
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public double getSide1(){
        return side1;
    }

    public double getSide2(){
        return side1;
    }

    public double getSide3(){
        return side1;
    }

    public void setSide1(double side1){
        this.side1=side1;
    }

    public void setSide2(double side2){
        this.side2=side2;
    }

    public void setSide3(double side3){
        this.side3=side3;
    }

    public double getArea(){
        double s = ( side1 + side2 + side3 ) / 2.0;
        return Math.sqrt( s * (s-side1) * (s-side2) * (s-side3));
    }

    public double getPerimeter(){
        return side1+side2+side3;
    }

    @Override
    public String toString(){
        return "\n面积: " + getArea() + "\t周长: " + getPerimeter()
                + "\n颜色: " + super.getColor() + "\t是否填充: " + super.isFilled();
    }
}

