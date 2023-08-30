import java.util.ArrayList;

public class Group extends AbstractGroup{
    private ArrayList<AbstractGroup> groups = new ArrayList<AbstractGroup>();
    private String name;

    public Group(String name){
        this.name = name;
    }

    @Override
    public void add(AbstractGroup abstractGroup) {
        groups.add(abstractGroup);
    }

    @Override
    public void remove(AbstractGroup abstractGroup) {
        groups.remove(abstractGroup);
    }

    @Override
    public void share() {
        System.out.println("分享给"+name);

        for(Object obj:groups){
            ((AbstractGroup)obj).share();
        }
    }
}
