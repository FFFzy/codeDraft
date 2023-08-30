public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("绘制了一个圆");
    }

    @Override
    public void erase() {
        System.out.println("擦除了一个圆");
    }
}
