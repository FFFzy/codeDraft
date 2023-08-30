#include<iostream>
#include<cmath>
using namespace std;

int main(){
	int n;
	cin>>n;
	
	int a[n];
	for(int i=0;i<n;i++){
		cin>>a[i];
	}
	
	int min=10000;
	for(int i=0;i<n;i++){
		for(int j=i+1;j<n;j++){
			int temp=0;
			temp = abs(a[i]-a[j]);
			if(min > temp){
				min = temp;
			}
		}
	}
	
	cout<<min;
	return 0;
}
