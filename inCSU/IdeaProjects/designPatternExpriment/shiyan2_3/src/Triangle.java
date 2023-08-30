public class Triangle implements Shape {
    @Override
    public void draw() {
        System.out.println("绘制了一个三角形");
    }

    @Override
    public void erase() {
        System.out.println("擦除了一个三角形");
    }
}
