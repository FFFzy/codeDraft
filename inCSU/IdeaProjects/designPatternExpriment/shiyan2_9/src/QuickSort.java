public class QuickSort {
    public void quickSort(int a[],int begin,int end){
        if (begin >= end){
            return;
        }
        int temp = a[begin];
        int i = begin;
        int j = end;

        while (i < j){
            while (i < j && a[j] > temp) j--;
            a[i] = a[j];
            while (i < j && a[i] < temp) i++;
            a[j] = a[i];
        }
        a[i] = temp;

        quickSort(a,begin,i-1);
        quickSort(a,i+1,end);

    }
}
