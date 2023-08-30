#include<iostream>
#include<algorithm>
#include<string>
using namespace std;

int main(){
	int n;
	cin>>n;
	
	int a[n];
	for(int i=0;i<n;i++){
		cin>>a[i];
	}
	sort(a,a+n);
	
	int max,mid,min;
	string s="";
	max=a[n-1];
	min=a[0];
	if(n%2 == 0){
		mid=(a[n/2]+a[n/2-1])/2; 
		if((a[n/2]+a[n/2-1])%2 == 1){
			s = ".5";
		}
		else{
			s = "";
		}
	}
	else{
		mid=a[(n-1)/2];
	}
	cout<<max<<" "<<mid<<s<<" "<<min;
	
	return 0;
}
