#include<iostream>
using namespace std;

int main(){
	int n;
	cin>> n;
	int a[n],b[n],c[n],d[n];
	for(int i=0;i<n;i++){
		cin>>a[i]>>b[i];
	}
	for(int i=0;i<n;i++){
		cin>>c[i]>>d[i];
	}
	int sum = 0;
	
	for(int i=0,j=0;i<n && j<n;){
		if(a[i]>c[j]){
			if(a[i]>=d[j]){
				j++;
			}
			else if(d[j]<=b[i]){
				sum = sum+(d[j]-a[i]);
				j++;
			}
			else{
				sum = sum+(b[i]-a[i]);
				i++;
			}
		}
		else{
			if(c[j]>=b[i]){
				i++;
			}
			else if(d[j]<b[i]){
				sum = sum+(d[j]-c[j]);
				j++;
			}
			else{
				sum = sum+(b[i]-c[j]);
				i++;
			}
		}
	}
	cout<<sum;
	return 0;
}
