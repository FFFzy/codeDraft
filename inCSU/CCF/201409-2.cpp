#include<iostream>
using namespace std;

int main(){
	int n;
	cin>>n;
	
	int x1,y1,x2,y2;
	int flag[101][101];
	for(int i=0;i<101;i++){
		for(int j=0;j<101;j++){
			flag[i][j] = 0;
		}
	}
	
	for(int i=0;i<n;i++){
		cin>>x1>>y1>>x2>>y2;
		for(int j=x1;j<x2;j++){
			for(int k=y1;k<y2;k++){
				flag[j][k] = 1;
			}
		}
	}
	
	int area = 0;
	for(int i=0;i<101;i++){
		for(int j=0;j<101;j++){
			if(flag[i][j]) area++;
		}
	}
	cout<<area;
	return 0;
}
