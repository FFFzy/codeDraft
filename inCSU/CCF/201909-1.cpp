#include <iostream>
#include <cmath>
using namespace std;

int main(){
	int N,M;
	cin>>N>>M;
	
	int a[N][M+1];
	for(int i=0;i<N;i++){
		for(int j=0;j<=M;j++){
			cin>>a[i][j];
		}
	}
	
	int T=0;
	for(int i=0;i<N;i++){
		for(int j=0;j<=M;j++){
			T = T + a[i][j];
		}
	}

	int sum=0,k,P=1;
	for(int i=0;i<N;i++){
		for(int j=1;j<=M;j++){
			sum = sum+a[i][j];
		}
		if(sum<P){
			P=sum;
			k=i+1;
		}
		sum=0;
	}
	
	cout<<T<<" "<<k<<" "<<abs(P)<<endl;

	return 0;
}
