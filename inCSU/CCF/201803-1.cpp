#include<iostream>
using namespace std;

int main(){
	int n=1;
	int score=0;
	int num=0;
	for(int i=0;i<n;i++){
		int a;
		cin>>a;
		if(a == 0){
			break;
		}
		else if (a == 1){
			num=0;
			score += 1;
			n++;
		}
		else{
			num++;
			score += 2*num;
			n++;
		}
	}
	
	cout<<score;
	return 0;
} 
