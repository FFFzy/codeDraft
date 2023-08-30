import java.util.ArrayList;
import java.util.Iterator;

public class PCBRecords implements Iterable<ProcessPCB> {

    private ArrayList<ProcessPCB> PCBItems;

    public ArrayList<ProcessPCB> getPCBItems() {
        return this.PCBItems;
    }

    public PCBRecords() {
        this.PCBItems = new ArrayList<ProcessPCB>();
    }

    public void addItem(ProcessPCB PcbItem) {
        this.PCBItems.add(PcbItem);
    }

    public void removeItem(ProcessPCB PCbItem) {
        this.PCBItems.remove(PCbItem);
    }

    public ProcessPCB getItem(ProcessPCB processPCB) {
        for (ProcessPCB pCbItem : this.PCBItems) {
            if (pCbItem.equals(processPCB)) {
                return pCbItem;
            }
        }
        return null;
    }

    public ProcessPCB getItem(String pid) {
        for (ProcessPCB pcBItem : this.PCBItems) {
            if (pcBItem.getPID().equals(pid)) {
                return pcBItem;
            }
        }
        return null;
    }

    public int getNumberOfItems() {
        return this.PCBItems.size();
    }

    public String[] getItemsProperties() {
        String itemsProperties[] = new String[getNumberOfItems()];

        int i = 0;
        for(Iterator iterator1 = PCBItems.iterator(); iterator1.hasNext();)
        {
            ProcessPCB stu_Item = (ProcessPCB)iterator1.next();
            itemsProperties[i++] = stu_Item.toString();
        }
        return itemsProperties;
    }

    public Iterator<ProcessPCB> iterator() {
        return this.PCBItems.iterator();
    }
}

