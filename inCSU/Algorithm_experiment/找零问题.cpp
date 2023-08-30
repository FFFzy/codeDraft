#include<iostream>
using namespace std;

int main(){
	int n;
	cout<<"Please enter the change:";
	cin>>n;
	int money[4]={25,10,5,1};
	for(int i=0;i<4;i++){
	  int a=0;
	  if(n>=money[i]){
	  a=n/money[i];
	  n=n%money[i];
	  }
	cout<<"The amount of "<<money[i]<<" is "<<a<<endl;
}
return 0;
}
