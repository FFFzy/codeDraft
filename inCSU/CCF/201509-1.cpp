#include<iostream>
using namespace std;

int main(){
	int n;
	cin>>n;
	
	int num=1;
	int a[n];
	for(int i=0;i<n;i++){
		cin>>a[i];
	} 
	
	for(int i=1;i<n;i++){
		int temp = a[i];
		if(temp == a[i-1]) continue;
		else num++;
	} 
	
	cout<<num;
	return 0;
} 
