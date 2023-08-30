public class ShapeFactory {
    public static Shape createShape(String type){
        Shape shape = null;
        if(type.equalsIgnoreCase("circle")){
            shape = new Circle();
            System.out.println("初始化圆");
        }
        else if(type.equalsIgnoreCase("triangle")){
            shape = new Triangle();
            System.out.println("初始化三角形");
        }
        else if (type.equalsIgnoreCase("rectangle")){
            shape = new Rectangle();
            System.out.println("初始化长方形");
        }

        return shape;
    }
}
