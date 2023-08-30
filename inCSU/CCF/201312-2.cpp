#include<iostream>
#include<cstring>
using namespace std;

int main(){
	
	string s;
	cin>>s;
	int a[10];
	int q=1;
	for(int i=0;i<13;i++){
		if(i==1 || i==5 || i==11){
		}
		else{
			a[q] = s[i]-'0';
			q++;
		}
	}
	
	int identification = 0;
	for(int i=1;i<10;i++){
		identification += a[i]*i;
	}
	identification %= 11;

	if(identification == a[10] || (identification == 10 && s[12]=='X')) cout<<"Right";
	else {
		for(int i=0;i<13;i++){
			if(i == 12) {
				if(identification == 10) cout<<"X";
				else{
					identification = char(identification);
					cout<<identification;
				} 
			}
			else cout<<s[i];
		}
	}
	
	return 0;
}
