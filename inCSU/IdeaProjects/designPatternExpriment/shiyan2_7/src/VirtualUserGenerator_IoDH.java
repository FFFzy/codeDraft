public class VirtualUserGenerator_IoDH {
    private VirtualUserGenerator_IoDH() {
    }

    private static class HolderClass{
        private final static VirtualUserGenerator_IoDH instance = new VirtualUserGenerator_IoDH();
    }

    public static VirtualUserGenerator_IoDH getInstance(){
        return HolderClass.instance;
    }
}
