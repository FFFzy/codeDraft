public class Client {
    public static void main(String args[]){
        AbstractInfoFacade aif;
        aif = (AbstractInfoFacade)XMLUtil.getBean();
        aif.dataProcessing();
    }
}
