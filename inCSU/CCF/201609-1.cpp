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
	
	int minus=0,temp;
	for(int i=0;i<n-1;i++){
		temp = abs(a[i]-a[i+1]);
		if(temp>minus){
			minus = temp;
		}
	}
	cout<<minus;
	return 0;
}
