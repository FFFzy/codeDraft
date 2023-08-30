#include<iostream>
using namespace std;

int main(){
	int N;
	cin>>N;
	
	int a[N];
	for(int i=0;i<N;i++){
		cin>>a[i];
	}
	
	int num = 0;
	for(int i=0;i<N;i++){
		for(int j=i+1;j<N;j++){
			if(a[j] == -a[i]) num++;
		}
	}
	
	cout<<num;
	return 0;
}
