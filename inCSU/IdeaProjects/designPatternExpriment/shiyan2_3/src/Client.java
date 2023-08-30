public class Client {
    public static void main(String args[]) throws UnsupportedShapeException{
        Shape shape;
        shape = ShapeFactory.createShape("triangle");
        shape.draw();
        shape.erase();
    }
}
