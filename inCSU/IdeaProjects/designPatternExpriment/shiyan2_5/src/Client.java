public class Client {
    public static void main(String args[]){
        ComponentFactory factory;
        Button bt;
        Text text;
        factory = (ComponentFactory)XMLUtil.getBean();
        bt = factory.createButton();
        text = factory.createText();
        bt.display();
        text.display();
    }
}
