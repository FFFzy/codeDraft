public class ProcessPCB {
    //	backupBAK 后备  ready 就绪  suspend 挂起 memory内存
    private String PID;
    private int RequiredTime;
    private int Priority;
    private String Status;
    private int MwmoryBase = 0000;
    private int MemoryLimit;
//	private String PCBPointer;

    public ProcessPCB(String initpID, int initRTime, int initpriority,
                      String status, int initBase, int initLimit) {

        this.PID = initpID;
        this.RequiredTime = initRTime;
        this.Priority = initpriority;
        this.Status = status;
        this.MwmoryBase = initBase;
        this.MemoryLimit = initLimit;
    }

    public String getPID() {
        if(this.PID == null)
            return " ";
        else
            return this.PID;
    }

    public int getRequiredTime() {
        return this.RequiredTime;
    }

    public int getPriority() {
        return this.Priority;
    }

    public String getStatus() {
        if(this.Status == null)
            return " ";
        else
            return this.Status;
    }

    public int getMemoryBase() {
        return this.MwmoryBase;
    }

    public int getMemoryLimit() {
        return this.MemoryLimit;
    }

    public boolean equals(ProcessPCB  pcb) {

        if(pcb.getPID() == this.getPID())  {
            return true;
        }
        else return false;
    }

    public String toString() {
        return this.getPID() + "_" + this.getRequiredTime() + "_" + this.getPriority() + "_"
                + this.getStatus() + "_" + this.getMemoryBase() + "_" + this.getMemoryLimit() + "\n";
    }

    public void run() {
        this.RequiredTime = this.RequiredTime-1;
        this.Priority = this.Priority-1;
    }

}
