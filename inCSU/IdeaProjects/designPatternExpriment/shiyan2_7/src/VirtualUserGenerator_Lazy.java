public class VirtualUserGenerator_Lazy {
    private volatile static VirtualUserGenerator_Lazy instance = null;

    private VirtualUserGenerator_Lazy(){};

    public static VirtualUserGenerator_Lazy getInstance(){
        if (instance == null){
            synchronized (VirtualUserGenerator_Lazy.class){
                if (instance == null){
                    instance = new VirtualUserGenerator_Lazy();
                }
            }
        }
        return instance;
    }
}
