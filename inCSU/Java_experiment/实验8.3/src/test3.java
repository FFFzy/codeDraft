public class test3 {
    public static void main(String[] args){
        GeometricObject[] geometricObject = {new Circle(), new Square(), new Square(), new Square(), new Triangle()};
        for(int i=0; i<geometricObject.length; i++){
            if(geometricObject[i] instanceof Colorable){
                System.out.print("GeometricObject " + (i+1) + " : ");
                ((Colorable)geometricObject[i]).howToColor();
            }
        }
    }
}

class Circle extends GeometricObject{
    private double radius;

    public Circle(){
    }
}

class Triangle extends GeometricObject{
    private double side1, side2, side3;

    public Triangle(){
    }
}

//Square类
class Square extends GeometricObject implements Colorable{
    private double sideLength;

    public Square(){
        sideLength=1.0;
    }

    public Square(double sideLength){
        this.sideLength = sideLength;
    }

    public void howToColor(){
        System.out.println("给所有的四条边着色");
    }

    public double getSideLength(){
        return sideLength;
    }

    public void setSideLength(double sideLength){
        this.sideLength = sideLength;
    }
}

//Colorable接口
interface Colorable{
    public void howToColor();
}

//GeometricObject类
class GeometricObject {
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
}





