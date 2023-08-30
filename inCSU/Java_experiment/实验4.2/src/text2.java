public class text2 {
    public static void main(String[] args){
        Circle2D c1 = new Circle2D(2,2,5.5);
        System.out.println("Area:"+c1.getArea()+"\tperimeter:"+c1.getPerimeter()+
                "\nc1.contains(3,3):"+c1.contains(3,3)+
                "\nc1.contains(new Circle2D(4,5,10.5))"+c1.contains(new Circle2D(4,5,10.5))+
                "\nc1.overlaps(new Circle2D(3, 5, 2.3)):"+c1.overlaps(new Circle2D(3, 5, 2.3)));
    }
}

class Circle2D{
    double x,y;
    double radius;

    Circle2D(){
        x = 0;
        y = 0;
        radius = 1;
    }

    Circle2D(double x,double y,double radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    double getArea(){
        return 3.14*radius*radius;
    }

    double getPerimeter(){
        return 2*3.14*radius;
    }

    boolean contains(double x,double y){
        return (((x-this.x)*(x-this.x) + (y-this.y)*(y-this.y)) < (this.radius*this.radius) ) ? true : false;
    }

    boolean contains(Circle2D circle){
        if(this.radius > circle.getRadius()){
            double m = this.radius - circle.getRadius();
            double n = Math.sqrt((circle.getX()-this.x)*(circle.getX()-this.x)+(circle.getY()-this.y)*(circle.getY()-this.y));
            return (n<m) ? true : false;
        }
        else
            return false;
    }

    boolean overlaps(Circle2D circle){
        double n = Math.sqrt((circle.getX()-this.x)*(circle.getX()-this.x)+(circle.getY()-this.y)*(circle.getY()-this.y));
        double m = (this.radius > circle.getRadius()) ? this.radius-circle.getRadius() : circle.getRadius()-this.radius;
        return (n>m && n<(this.radius+circle.getRadius())) ? true : false;
    }
}