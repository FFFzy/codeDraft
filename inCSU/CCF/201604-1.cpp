#include<iostream>
using namespace std;

int main(){
	int n;
	cin>>n;
	
	int a[n];
	for(int i=0;i<n;i++){
		cin>>a[i];
	}
	
	int num = 0;
	for(int i=1;i<n-1;i++){
		if((a[i-1]<a[i] && a[i+1]<a[i]) || (a[i-1]>a[i] && a[i+1]>a[i])){
			num++;
		}
	}
	cout<<num;
	return 0;
} 
