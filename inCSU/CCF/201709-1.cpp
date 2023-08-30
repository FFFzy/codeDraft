#include<iostream>
using namespace std;

int main(){
	int N;
	cin>>N;
	
	int price = 10;
	int num = 0;
	while(N){
		if(N<30){
			num = num+N/10;
			N=0;
		}
		else if (N == 30){
			num = num+4;
			N=0;
		}
		else if(N<50){
			num = num+5;
			N=0;
		}
		else{
			int i=N/50;
			N = N%50;
			num = num + 7*i;
		}
	}
	cout<<num;
	return 0;
} 
