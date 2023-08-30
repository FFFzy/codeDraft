#include<iostream>
using namespace std;

int main(){
	int n;
	cin>>n;
	
	int a[n][n];
	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			cin>>a[i][j];
		}
	}
	
	int b[n*n];
	int p=0,q=0;
	for(int i=0;i<n*n;i++){
		b[i] = a[p][q];
		if(p == 0 && q%2 == 0 && q!=n-1) q++;
		else if(q == 0 && p%2 == 1 && p!= n-1) p++;
		else if(p == n-1){
			if(n%2 == 1){
				if(q%2 == 1) q++;
				else {
					p--;q++;
				}
			} 
			else {
				if(q%2 == 0) q++;
				else {
					p--;q++;
				}
			}
		}
		else if(q == n-1){
			if(n%2 == 1){
				if(p%2 == 0) p++;
				else{
					p++;q--;
				}
			} 
			else{
				if(p%2 == 1) p++;
				else{
					p++;q--;
				}
			}
		}
		else if((p+q)%2 == 0){
			p--;
			q++;
		}
		else{
			p++;
			q--;
		}
	}
	
	for(int i=0;i<n*n;i++){
		cout<<b[i]<<" ";
	}
	return 0;
}
