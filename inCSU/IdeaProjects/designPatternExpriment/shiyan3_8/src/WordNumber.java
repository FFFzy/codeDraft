public class WordNumber implements Observer{
    private String name;

    public WordNumber(String name){
        this.name = name;
    }

    @Override
    public void update() {
        System.out.println(name+"显示可编辑文本区中出现的单词总数量和字符总数量.");
    }

    @Override
    public String getName() {
        return name;
    }
}
