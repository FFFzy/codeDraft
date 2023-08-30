public class KNNAdapter extends DataProcessing{
    private KNN knn;

    public KNNAdapter(){
        knn = new KNN();
    }

    @Override
    public void callAlgorithm() {
        knn.algorithm();
    }
}
