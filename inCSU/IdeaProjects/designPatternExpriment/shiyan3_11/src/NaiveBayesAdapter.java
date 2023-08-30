public class NaiveBayesAdapter extends DataProcessing{
    private NaiveBayes naiveBayes;

    public NaiveBayesAdapter(){
        naiveBayes = new NaiveBayes();
    }

    @Override
    public void callAlgorithm() {
        naiveBayes.algorithm();
    }
}
