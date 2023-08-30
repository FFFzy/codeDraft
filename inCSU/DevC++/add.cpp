#include <iostream>
using namespace std;

int main(){
	double a = 0, x;
	int b;
	cout<<"input b"<<endl;
	cin>>b;
	while(b == 0){
		cout<<"input x"<<endl;
		cin>>x;
		a += x;
		cout<<a<<endl;
	}
	cout<<a;
}
