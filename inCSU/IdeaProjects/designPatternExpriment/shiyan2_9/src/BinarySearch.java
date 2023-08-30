public class BinarySearch {
    public int binarySearch(int a[],int b){
        int low = 0;
        int high = a.length-1;
        while (low <= high){
            int mid = (low+high)/2;
            if (b > a[mid]){
                low = mid+1;
            }
            else if (b < a[mid]){
                high = mid-1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }
}
