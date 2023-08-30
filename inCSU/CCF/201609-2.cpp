#include<iostream>
using namespace std;

bool haveEmpty(int x,int seat[20][5]){
	for(int i=0;i<20;i++){
		int num=0;
		for(int j=0;j<5;j++){
			if(seat[i][j] == 0) num++;
		}
		if(x <= num) return true;
	}
	return false;
}

int getEmpty(int x,int seat[20][5]){
	for(int i=0;i<20;i++){
		int num=0;
		for(int j=0;j<5;j++){
			if(seat[i][j] == 0) num++;
		}
		if(x <= num) return i;
	}
}

int main(){
	int n;
	cin>>n;
	
	int a[n];
	for(int i=0;i<n;i++){
		cin>>a[i];
	}
	
	int seat[20][5]={0};
	for(int i=0;i<n;i++){
		if(haveEmpty(a[i],seat)){
			int j;
			j = getEmpty(a[i],seat);
			int p=0;
			int b[5];
			for(int k=0;k<a[i];){
				if(seat[j][p] == 0){
					seat[j][p]=1;
					b[k]=j*5+p+1;
					k++;
				}
				p++;
			}
			
			for(int k=0;k<a[i];k++){
				cout<<b[k]<<" ";
			}
			cout<<endl;
		}
		else{
			int b[5];
			int num=0;
			for(int j=0;j<20;j++){
				for(int k=0;k<5;k++){
					if(seat[j][k] == 0){
						seat[j][k]=1;
						b[num]=j*5+k+1;
						num++;
					}
				}
			}
			
			for(int k=0;k<a[i];k++){
				cout<<b[k]<<" ";
			}
			cout<<endl;
		}
	}
	return 0;
}
