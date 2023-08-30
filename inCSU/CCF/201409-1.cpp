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
	
	int num=0;
	int temp;
	for(int i=0;i<n;i++){
		for(int j=0;j<i;j++){
			temp = a[i]-a[j];
			if(abs(temp) == 1) num++;
		}
	}
	
	cout<<num;
	return 0;
}
