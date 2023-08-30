#include <iostream>
using namespace std;

struct svector{
	int index;
	int value;
};

int main(){
	int n,a,b;
	cin>>n>>a>>b;
	
	svector u[a];
	svector v[b];
	
	int p,q;
	for(int i=0;i<a;i++){
		cin>>p>>q;
		u[i].index=p;
		u[i].value=q;
	}
	
	for(int i=0;i<b;i++){
		cin>>p>>q;
		v[i].index=p;
		v[i].value=q;
	}
	
	long long sum=0;
	
	for(int i=0,j=0;i<a && j<b;){
		if(u[i].index == v[j].index){
			sum = sum + u[i].value * v[j].value;
			i++;
			j++;
		}
		else if(u[i].index > v[j].index){
			j++;
		}
		else{
			i++;
		}
	}
	
	cout<<sum<<endl;
	return 0;
}
