public abstract class Shape {
    protected Style style;

    public void setStyle(Style style) {
        this.style = style;
    }

    public abstract void display();
}
