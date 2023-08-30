#include<iostream>
using namespace std;

int main(){
	int r,y,g;
	cin>>r>>y>>g;
	int totle;
	totle = r+y+g;
	
	int n;
	cin>>n;
	
	int a[n][2];
	for(int i=0;i<n;i++){
		for(int j=0;j<2;j++){
			cin>>a[i][j];
		}
	}
	
	long long sum=0;
	for(int i=0;i<n;i++){
		if(a[i][0] == 0){
			sum = sum+a[i][1];
		}
		else if(a[i][0] == 1){
			if(sum < a[i][1]){
				sum = sum+(a[i][1]-sum);
			}
			else{
				int t = (sum-a[i][1])%totle;
				if(t<g){
					sum = sum;
				}
				else{
					sum = sum +(g+y+r-t);
				}
			}
		}
		else if(a[i][0] == 2){
			if(sum < a[i][1]){
				sum = sum+(a[i][1]-sum)+r;
			}
			else{
				int t = (sum-a[i][1])%totle;
				if(t<r){
					sum = sum+(r-t);
				}
				else if(t<r+g){
					sum = sum;
				}
				else{
					sum = sum+(r+g+y-t)+r;
				}
			}
		}
		else{
			if(sum < a[i][1]){
				sum = sum;
			} 
			else{
				int t = (sum-a[i][1])%totle;
				if(t<y+r){
					sum = sum+(y+r-t);
				} 
				else{
					sum = sum;
				}
			}
		}
	}
	
	cout<<sum;
	return 0;
} 
