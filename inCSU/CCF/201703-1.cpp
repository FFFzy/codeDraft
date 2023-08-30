#include<iostream>
using namespace std;

int main(){
	int n,k;
	cin>>n>>k;
	
	int a[n];
	for(int i;i<n;i++){
		cin>>a[i];
	}
	
	int num=0;
	int sum=0;
	for(int i;i<n;i++){
		if(a[i]<k){
			sum = a[i];
			while(sum<k){
				i++;
				if(i>=n) break;
				sum = sum + a[i];
			}
			num++;
		}
		else{
			num++;
		} 
	}
	
	cout<<num;
	return 0;
}
