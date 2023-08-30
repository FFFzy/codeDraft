public class Client {
    public static void main(String args[]){
        Photograph photograph = new Photograph("猫和老鼠");
        FilterStrategy filterStrategy;
        filterStrategy = (FilterStrategy)XMLUtil.getBean();

        photograph.setFilterStrategy(filterStrategy);
        photograph.displayPhoto();
    }
}
