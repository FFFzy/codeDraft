public class MemoryItem {
    private int memoryBase=0;
    private int memoryLimit=0;
    private int availableStatus=0;

    public MemoryItem(int initMemoryBase, int initMemoryLimit) {
        this.memoryBase = initMemoryBase;
        this.memoryLimit = initMemoryLimit;
    }

    public int getMemoryBase() {
        return this.memoryBase;
    }

    public int getMemoryLimit() {
        return this.memoryLimit;
    }

    public int getStatus() {
        return this.availableStatus;
    }

    public String toString() {
        return this.getMemoryBase() + "_" + this.getMemoryLimit() + "\n";
    }

}
