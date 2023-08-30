#include<iostream>
#include<cstring>
using namespace std;

int main(){
	int n,m;
	cin>>n>>m;
	
	int a[n][m];
	for(int i=0;i<n;i++){
		for(int j=0;j<m;j++){
			cin>>a[i][j];
		}
	}
	
	int b[n][m];
	memset(b,0,sizeof(b));
	
	for(int i=0;i<n;i++){
		for(int j=1;j<m-1;j++){
			if(a[i][j-1]==a[i][j] && a[i][j+1]==a[i][j])
				b[i][j]=b[i][j-1]=b[i][j+1]=1;
		}
	}
	
	for(int i=1;i<n-1;i++){
		for(int j=0;j<m;j++){
			if(a[i-1][j]==a[i][j] && a[i+1][j]==a[i][j])
				b[i][j]=b[i-1][j]=b[i+1][j]=1;
		}
	}
	
	for(int i=0;i<n;i++){
		for(int j=0;j<m;j++){
			if(b[i][j] == 1){
				a[i][j] = 0;
			}
		}
	}
	
	for(int i=0;i<n;i++){
		for(int j=0;j<m;j++){
			cout<<a[i][j]<<" ";
		}
		cout<<endl;
	}
	
	return 0;
}
