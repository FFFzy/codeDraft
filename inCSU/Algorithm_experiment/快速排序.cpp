#include<iostream>
using namespace std;

int partition(int a[],int left,int right)
{   int i=left;
    int j=right;
    int s=a[i];
    while(i<j){
	while(i<j && a[j]>=s)
	    j--;
    if(i<j)
		a[i]=a[j];
    while(i<j && a[i]<=s)
		i++;
    if(i<j)
		a[j]=a[i];
    }
    a[i]=s;
    return i;
}

void quicksort(int number[], int left, int right) 
  { int q; 
    if(left < right)
	{  q=partition(number, left, right);
        quicksort(number, left, q-1);
        quicksort(number, q+1, right); 
    } 
}

int main(){
	int n;
	cout<<"Please enter the size of the array : ";
	cin>>n;
	int a[n];
	cout<<"Please enter the array data : "<<endl;
	for(int i=0;i<n;i++){
        cin>>a[i];
    }
	
	quicksort(a, 0, n-1);

	cout<<"Sorted array £º "<<endl;
	for(int i=0; i<n; i++)
	    cout<<a[i]<<" ";
	cout<<endl;
    return 0;
}
