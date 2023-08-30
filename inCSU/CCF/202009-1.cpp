#include<iostream>
#include<cmath>
using namespace std;

int main(){
	int n,x,y;
	cin>>n>>x>>y;
	int min1 = INT_MAX ;
	int min2 = INT_MAX ;
	int min3 = INT_MAX ;
	int indexOfMin1=0,indexOfMin2=0,indexOfMin3=0;

	for(int i=1;i<=n;i++){
		int xi,yi;
		cin>>xi>>yi;
		int z = pow((x-xi),2)+pow((y-yi),2);
		if (z<min1){
			min3 = min2;
			min2 = min1;
			indexOfMin3 = indexOfMin2;
			indexOfMin2 =indexOfMin1;
			
			min1 = z;
			indexOfMin1 = i;
		}
		else if(z<min2){
			min3 = min2;
			indexOfMin3 = indexOfMin2;
			
			min2 = z;
			indexOfMin2 = i;
		}
		else if(z<min3){
			min3 = z;
			indexOfMin3 = i;
		}
	}
	
	cout<<indexOfMin1<<endl;
	cout<<indexOfMin2<<endl;
	cout<<indexOfMin3<<endl;
	return 0;
}
