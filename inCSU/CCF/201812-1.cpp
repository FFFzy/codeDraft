#include<iostream>
using namespace std;

int main(){
	int r,y,g;
	cin>>r>>y>>g;
	
	int n;
	cin>>n;
	
	int a[n][2];
	for(int i=0;i<n;i++){
		for(int j=0;j<2;j++){
			cin>>a[i][j];
		}
	}
	
	int t = 0;
	for(int i=0;i<n;i++){
		if(a[i][0] == 0 || a[i][0] == 1){
			t = t+a[i][1];
		}
		else if(a[i][0] == 2){
			t = t+a[i][1]+r;
		}
		else{
			;
		}
	}
	
	cout<<t;
	return 0;
} 
