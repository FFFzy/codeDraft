public class Member extends AbstractGroup{
    private String name;

    public Member(String name){
        this.name = name;
    }

    @Override
    public void add(AbstractGroup abstractGroup) {
        System.out.println("不支持该方法.");
    }

    @Override
    public void remove(AbstractGroup abstractGroup) {
        System.out.println("不支持该方法.");
    }

    @Override
    public void share() {
        System.out.println("分享给"+name);
    }
}
