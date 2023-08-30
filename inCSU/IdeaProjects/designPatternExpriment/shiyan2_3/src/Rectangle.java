public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("绘制了一个长方形");
    }

    @Override
    public void erase() {
        System.out.println("擦除了一个长方形");
    }
}
