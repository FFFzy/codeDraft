public class DecisionTreeAdapter extends DataProcessing{
    private DecisionTree decisionTree;

    public DecisionTreeAdapter(){
        decisionTree = new DecisionTree();
    }
    @Override
    public void callAlgorithm() {
        decisionTree.algorithm();
    }
}
