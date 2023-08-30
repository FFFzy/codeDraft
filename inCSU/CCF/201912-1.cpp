#include<iostream>
using namespace std;

bool include7(int i){
	while(i){
		if(i%10 == 7)
			return 1;
		else
			i=i/10;
	}
	return 0;
}

int main(){
	int n;
	cin>>n;
	
	int num1=0;
	int num2=0;
	int num3=0;
	int num4=0;
	
	for(int i=1;i<=n;i++){
		if((i%7 == 0) || include7(i)){
			n++;
			if(i%4 == 1){
				num1++;
			}
			else if(i%4 == 2){
				num2++;
			}
			else if(i%4 == 3){
				num3++;
			}
			else{
				num4++;
			}
		}
	}

	cout<<num1<<endl;
	cout<<num2<<endl;
	cout<<num3<<endl;
	cout<<num4<<endl;
	
	return 0;
}
