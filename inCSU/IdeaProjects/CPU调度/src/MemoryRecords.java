import java.util.ArrayList;
import java.util.Iterator;

public class MemoryRecords implements Iterable<MemoryItem> {
    private ArrayList<MemoryItem> memoryItems;

    public Iterator<MemoryItem> iterator() {
        return this.memoryItems.iterator();
    }

    public ArrayList<MemoryItem> getMemoryItems() {

        return this.memoryItems;
    }

    public MemoryRecords() {
        this.memoryItems = new ArrayList<MemoryItem>();
    }

    public void addItem(MemoryItem newMemoryItem) {
        this.memoryItems.add(newMemoryItem);
    }
    public void removeItem(MemoryItem momoryItem) {
        this.memoryItems.remove(momoryItem);
    }

    public MemoryItem getMemoryItem(MemoryItem item) {
        for(MemoryItem mItem : this.memoryItems) {
            if(mItem.equals(item)) {
                return mItem;
            }
        }
        return null;
    }
    public MemoryItem getMemoryItem(int base) {
        for(MemoryItem mItem : this.memoryItems) {
            if(mItem.getMemoryBase() == base) {
                return mItem;
            }
        }
        return null;
    }

    public int getNumberOfItems() {
        return this.memoryItems.size();
    }

    public String[] getItemsProperties() {
        String itemsProperties[] = new String[getNumberOfItems()];
        int i=0;
        for(Iterator iterator1 = this.memoryItems.iterator(); iterator1.hasNext(); ) {
            MemoryItem mmItem = (MemoryItem) iterator1.next();
            itemsProperties[i++] = mmItem.toString();
        }
        if(itemsProperties == null) {
            itemsProperties[0] = "      ";
        }
        return itemsProperties;
    }
}
