public class text1 {
    public static void main(String[] args){
        Triangle triangle = new Triangle(1,1.5,1);
        triangle.setColor("yellow");
        triangle.setFilled(true);
        System.out.println(triangle.toString());
    }

}

class Triangle extends GeometricObject{
    double side1;
    double side2;
    double side3;

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

    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public double getSide3() {
        return side3;
    }

    public double getArea(){
        double s = ( side1 + side2 + side3 ) / 2.0;
        return Math.sqrt( s * (s-side1) * (s-side2) * (s-side3) );
    }

    public double getPerimeter(){
        return side1+side2+side3;
    }

    @Override
    public String toString() {
        return "Three sides of triangle: side1:"+side1+"\tside2:"+side2+"\tside3:"+side3+"\nperimeter: "+getPerimeter()+"\tarea: "+getArea()+"\ncolor: "+getColor()+" ,and filled: "+isFilled();
    }
}

class GeometricObject{
    private String color = "white";
    private boolean filled = false;
    private java.util.Date dateCreated;

    public GeometricObject(){
        dateCreated = new java.util.Date();
    }

    public GeometricObject(String color, boolean filled){
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

    public String toString(){
        return "created on " + dateCreated + "\ncolor: " + color +" and filled: " + filled;
    }
}