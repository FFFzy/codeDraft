#include <iostream>
using namespace std;

int main(){
	int flag = 1;
	do{
		double precision;
		double recall;
		cout<<"please input precision:"<<endl;
		cin>>precision;
		cout<<"please input recall:"<<endl;
		cin>>recall;
		
		double F1;
		F1 = 2*(precision * recall) / (precision + recall);
		
		cout<<F1<<endl;
		
		cout<<"please input flag:"<<endl;
		cin>>flag;
	}while(flag == 1);
	
	
	return 0;
	
}
