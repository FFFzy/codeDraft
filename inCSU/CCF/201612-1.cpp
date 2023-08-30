#include<iostream>
using namespace std;

int main(){
	int n;
	cin>>n;
	
	int a[n];
	for(int i=0;i<n;i++){
		cin>>a[i];
	}
	
	int num,low,high,mid;
	for(int i=0;i<n;i++){
		mid = a[i];
		num = 0;
		low = 0;
		high = 0;
		for(int j=0;j<n;j++){
			if(a[j]<mid) low++;
			else if(a[j]>mid) high++;
			else num++;
		}
		if((low == high) && (low+high+num == n)) break;
		else mid = -1;
	}
	
	cout<<mid;
	return 0;
}
