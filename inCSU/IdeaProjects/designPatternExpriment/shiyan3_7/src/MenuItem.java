public class MenuItem {
    private String name;
    private Command command;

    public MenuItem(String name,Command command){
        this.name = name;
        this.command = command;
    }

    public void click(){
        System.out.println("点击菜单项："+name);
        command.execute();
    }

    public String getName(){
        return name;
    }
}
