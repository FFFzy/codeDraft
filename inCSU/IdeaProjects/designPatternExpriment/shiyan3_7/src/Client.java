public class Client {
    public static void main(String args[]){
        Menu menu = new Menu();
        MenuItem open = new MenuItem("open",new OpenCommand());
        MenuItem create = new MenuItem("create",new CreateCommand());
        MenuItem edit = new MenuItem("edit",new EditCommand());

        menu.addMenuItem(open);
        menu.addMenuItem(create);
        menu.addMenuItem(edit);

        menu.getMenuItemByName("open").click();
        menu.getMenuItemByName("create").click();
        menu.getMenuItemByName("edit").click();
    }
}
