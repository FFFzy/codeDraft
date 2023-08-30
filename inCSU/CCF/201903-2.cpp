#include<iostream>
#include<string>
#include<stack>
using namespace std;

int main(){
	int n;
	cin>>n;
	
	string s[n];
	for(int i=0;i<n;i++){
		cin>>s[i];
	}
	
	stack<int> num;
	stack<char> opter;
	int sum[n];
	for(int i=0;i<n;i++){
		for(int j=0;j<7;j++){
			if(s[i].at(j)>'0' && s[i].at(j)<='9'){
				num.push(s[i].at(j)-'0');
			}
			else{
				if(s[i].at(j) == '+'){
					opter.push('+');
				}
				else if(s[i].at(j) == '-'){
					num.push((s[i].at(j+1)-'0')*(-1));
					opter.push('+');
					j++;
				}
				else if(s[i].at(j) == 'x'){
					int newnum = 0;
					newnum = num.top()*(s[i].at(j+1)-'0');
					num.pop();
					num.push(newnum);
					j++;
				}
				else{
					int newnum = 0;
					newnum = num.top()/(s[i].at(j+1)-'0');
					num.pop();
					num.push(newnum);
					j++;
				}
			}
		}
		
		sum[i]=num.top();
		num.pop();
		while(!opter.empty()){
			int num1=num.top();
			num.pop();
			sum[i]=sum[i]+num1;
			opter.pop();	
		}
		
	}
	
	for(int i=0;i<n;i++){
		if(sum[i] == 24){
			cout<<"Yes"<<endl;
		}
		else{
			cout<<"No"<<endl;
		}
	}
	
	return 0;
}
