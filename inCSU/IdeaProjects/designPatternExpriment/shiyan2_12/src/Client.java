public class Client {
    public static void main(String args[]){
        Image image;
        Filter filter;
        image = (Image)XMLUtil.getBean("image");
        filter = (Filter)XMLUtil.getBean("filter");
        image.setFilter(filter);
        image.parseFile("猫和老鼠");
    }
}
