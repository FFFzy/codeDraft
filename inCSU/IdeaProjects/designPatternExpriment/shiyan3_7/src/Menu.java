import java.util.ArrayList;

public class Menu {
    private ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();

    public void addMenuItem(MenuItem item){
        this.menuItemList.add(item);
    }

    public MenuItem getMenuItemByName(String itemName){
        for (int i=0;i<menuItemList.size();i++){
            if (menuItemList.get(i).getName().equals(itemName)){
                return menuItemList.get(i);
            }
        }
        return null;
    }
}
