public class Client {
    public static void main(String args[]){
        Shape shape;
        Style style;
        shape = (Shape)XMLUtil.getBean("shape");
        style = (Style)XMLUtil.getBean("style");
        shape.setStyle(style);
        shape.display();
    }
}
