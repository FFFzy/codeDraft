#include<iostream>
using namespace std;

int main(){
	int n;
	cin>>n;
	
	int sum = 0;
	int temp;
	while(n){
		temp = n%10;
		sum += temp;
		n = n/10;
	}
	
	cout<<sum;
	return 0;
}
