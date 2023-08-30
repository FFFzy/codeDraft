public class OrderByNumber implements Observer{
    private String name;

    public OrderByNumber(String name){
        this.name = name;
    }

    @Override
    public void update() {
        System.out.println(name+"按照出现频次降序显示可编辑文本区中所出现的单词以及每个单词出现的次数.");
    }

    @Override
    public String getName() {
        return name;
    }
}
