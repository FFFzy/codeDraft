public class DataOperationAdapter extends DataOperation{
    private QuickSort quickSort;
    private BinarySearch binarySearch;

    public DataOperationAdapter(){
        quickSort = new QuickSort();
        binarySearch = new BinarySearch();
    }

    @Override
    public void sort(int a[]) {
        quickSort.quickSort(a,0,a.length-1);
    }

    @Override
    public int search(int a[],int b) {
        return binarySearch.binarySearch(a,b);
    }
}
