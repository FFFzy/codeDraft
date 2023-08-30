#include<iostream>
using namespace std;

int main(){
	int a[15][10];
	for(int i=0;i<15;i++){
		for(int j=0;j<10;j++){
			cin>>a[i][j];
		}
	}
	
	int b[4][4];
	for(int i=0;i<4;i++){
		for(int j=0;j<4;j++){
			cin>>b[i][j];
		}
	}
	
	int n,line;
	cin>>n;
	
	for(int i=0;i<15;i++){
		for(int j=0;j<4;j++){
			if(i+j >= 15) break;
			for(int k=0;k<4;k++){
				if(b[j][k] + a[i+j][n-1+k] > 1) {
					line = i-1;
					goto out;
				}
				if((b[j][k] == 1) && (i+j == 14)){
					line = i;
					goto out;
				}
			}
		}
	}
	
	out:
		for(int j=0;j<4;j++){
			if(line+j >= 15) break;
			for(int k=0;k<4;k++){
				a[line+j][n-1+k] = b[j][k] + a[line+j][n-1+k];
			}
		}
		for(int i=0;i<15;i++){
			for(int j=0;j<10;j++){
				cout<<a[i][j]<<" ";
			}
			cout<<endl;
		}
		
	return 0;
}
