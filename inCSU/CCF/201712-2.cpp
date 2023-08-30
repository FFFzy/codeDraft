#include<iostream>
using namespace std;

int isOut(int i,int n,bool flag[]){
	while(1){
		if(flag[i-1]){
			return i;
			break;
		}
		i++;
		if(i==n+1) i=1;
	}
}

int main(){
	int n,k;
	cin>>n>>k;
	
	bool flag[n];
	for(int i=0;i<n;i++){
		flag[i]=true;
	}
	
	int outnum=0;
	int people=1;
	for(int i=1;outnum<n-1;i++){
		people = isOut(people,n,flag);
		if(i%k == 0 || i%10 == k){
			flag[people-1]=false;
			outnum++;
		}
		else{
			people++;
			if(people == n+1) people=1;
		}
	}
	
	for(int i=0;i<n;i++){
		if(flag[i])
			cout<<i+1;
	}
	
	return 0;
}
