#include<iostream>
using namespace std;

bool isExist(int a,int b,int n,int x[],int y[]){
	bool flag=false;
	int num = 0;
	for(int i=0;i<n;i++){
		if((x[i]==a-1 && y[i]==b) || (x[i]==a+1 && y[i]==b) ||(x[i]==a && y[i]==b+1) ||(x[i]==a && y[i]==b-1)){
			num++;
		}
	}
	if(num == 4)
		return true;
	else return false;
}

int main(){
	int n;
	cin>>n;
	int x[n];
	int y[n];
	
	for(int i=0;i<n;i++){
		cin>>x[i]>>y[i];
	}
	
	int num0=0, num1=0, num2=0, num3=0, num4=0;
	int num=0;
	
	for(int i=0;i<n;i++){
		if(isExist(x[i],y[i],n,x,y)){
			for(int j=0;j<n;j++){
				if( x[j] == x[i]+1 && y[j] == y[i]+1 ) num++;
				if( x[j] == x[i]+1 && y[j] == y[i]-1 ) num++;
				if( x[j] == x[i]-1 && y[j] == y[i]+1 ) num++;
				if( x[j] == x[i]-1 && y[j] == y[i]-1 ) num++;	
			}
			if(num == 0) num0++;
			if(num == 1) num1++;
			if(num == 2) num2++;
			if(num == 3) num3++;
			if(num == 4) num4++;
		}
		num=0;	
	}
	
	cout<<num0<<endl;
	cout<<num1<<endl;
	cout<<num2<<endl;
	cout<<num3<<endl;
	cout<<num4<<endl;

	return 0;
} 
