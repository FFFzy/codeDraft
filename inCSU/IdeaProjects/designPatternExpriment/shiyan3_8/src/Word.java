public class Word implements Observer{
    private String name;

    public Word(String name){
        this.name = name;
    }

    @Override
    public void update() {
        System.out.println(name+"显示可编辑文本区中所出现的单词.");
    }

    @Override
    public String getName() {
        return name;
    }
}
