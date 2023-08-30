public class Client {
    public static void main(String args[]){
        DataProcessing dataProcessing;
        dataProcessing = (DataProcessing)XMLUtil.getBean();
        dataProcessing.processing();
    }
}
