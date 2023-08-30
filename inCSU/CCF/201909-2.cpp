#include <iostream>
using namespace std;

int main(){
	int N;
	cin>>N;
	
	int m,a,dropnum;
	int sum=0,T=0;
	bool flag[N]={false};
	
	for(int i=0;i<N;i++){
		cin>>m;
		for(int j=0;j<m;j++){
			cin>>a;
			if(a>0){
				if(a<sum && !flag[i]){
					dropnum++;
					flag[i]=true; 
				}
				sum=a;
			}
			else if (a<=0){
				sum=sum+a;
			}
		}
		T=T+sum;
		sum=0;
	}
	
	int E;
	for(int i=0;i<N;i++){
		if( flag[i] && flag[(i+1)%N] && flag[(i+2)%N]){
			E++;
		}
	}
	
	cout<<T<<" "<<dropnum<<" "<<E<<endl;
	
	return 0;
} 
