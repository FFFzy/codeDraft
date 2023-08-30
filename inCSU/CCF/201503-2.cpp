#include<iostream>
#include<algorithm>
using namespace std;

struct number{
	int num;
	int times;
};

bool isExist(int a,int n,number b[]){
	for(int i=0;i<n;i++){
		if (b[i].num == a) return true;
	}
	return false;
}

bool cmp(number a,number b){
	return ( a.times>b.times || (a.num < b.num && a.times == b.times));
}

int main(){
	int n;
	cin>>n;
	
	int a[n];
	for(int i=0;i<n;i++){
		cin>>a[i];
	}
	
	number b[n];
	for(int i=0;i<n;i++){
		b[i].num = -1;
		b[i].times = 0;
	}
	
	for(int i=0;i<n;i++){
		if(isExist(a[i],n,b)){
			for(int j=0;j<n;j++){
				if (b[j].num == a[i]) b[j].times++;
			}
		}
		else{
			for(int j=0;j<n;j++){
				if(b[j].num == -1){
					b[j].num = a[i];
					b[j].times = 1;
					break;
				}
			}
		}
	}
	
	sort(b,b+n,cmp);
	
	for(int i=0;i<n;i++){
		if(b[i].num != -1){
			cout<<b[i].num<<" "<<b[i].times<<endl;
		}
	}
	
	return 0;
}
