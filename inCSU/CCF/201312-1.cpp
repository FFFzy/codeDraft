#include<iostream>
using namespace std;

int main(){
	int n;
	cin>>n;
	
	int a[n];
	for(int i=0;i<n;i++){
		cin>>a[i];
	}
	
	int times[n]={0};
	for(int i=0;i<n;i++){
		for(int j=i;j<n;j++){
			if(a[j] == a[i]) times[i]++;
		}
	}
	
	int time = times[0];
	int num = 10001;
	for(int i=0;i<n;i++){
		if(times[i] > time){
			num = a[i];
			time = times[i];
		}
		else if(times[i] == time){
			if(a[i] < num) num = a[i];
		}
	}
	cout<<num;
	return 0;
}
