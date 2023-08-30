#include<iostream>
#include<vector>
using namespace std;


int main(){
	int n,k,t,xl,yd,xr,yw;
	cin>>n>>k>>t>>xl>>yd>>xr>>yw;
	
	int number1=0;
	int number2=0;
	int index=0;
	int line1=0;
	int line2=0;
	
	int a[n][2*t];
	for(int i=0;i<n;i++){
		for(int j=0;j<2*t;j++){
			cin>>a[i][j];
		}	
	}
	
	for(int i=0;i<n;i++){
		for(int j=0;j<2*t;j=j+2){
			if((xl<=a[i][j])&&(a[i][j]<=xr) && (yd<=a[i][j+1])&&(a[i][j+1]<=yw)){
				if(line1==i){
					number1++;
					line1++;
				}
				index++;
				if((index>=k) && (line2==i)){
					number2++;
					index=0;
					line2++;
				}
			}
			else{
				index=0;
			}
		}
		index=0;
		if(line1==i) line1++;
		if(line2==i) line2++;
	}

	cout<<number1<<endl;
	cout<<number2<<endl;
	return 0;	
}
