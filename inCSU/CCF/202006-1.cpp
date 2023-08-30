#include <iostream>
using namespace std;

struct Point{
	int x;
	int y;
};

int main(){
	int m,n;
	cin>>n>>m;
	
	Point a[n];
	Point b[n];
	int p=0;
	int q=0;
	
	for(int i=0;i<n;i++){
		int xi,yi;
		char typei;
		cin>>xi>>yi>>typei;
		if(typei == 'A'){
			a[p].x = xi;
			a[p].y = yi;
			p++;
		}
		else {
			b[q].x = xi;
			b[q].y = yi;
			q++;
		}
	}
	
	int k1,k2,k3;
	string flag[m];
	for(int i=0;i<m;i++){
		cin>>k1>>k2>>k3;
		if(k1+k2*a[0].x+k3*a[0].y>0){
			for(int j=0;j<p;j++){
				if(k1+k2*a[j].x+k3*a[j].y<=0){
					flag[i]="No";
					break;
				}
				else {
					flag[i]="Yes";
				}
			}
			for(int j=0;j<q;j++){
				if (flag[i] == "No")  break;
				else if(k1+k2*b[j].x+k3*b[j].y>=0){
					flag[i]="No";
					break;
				}
				else {
					flag[i]="Yes";
				}
			}
		}
		else if(k1+k2*a[0].x+k3*a[0].y<0){
			for(int j=0;j<p;j++){
				if(k1+k2*a[j].x+k3*a[j].y>=0){
					flag[i]="No";
					break;
				}
				else {
					flag[i]="Yes";
				}
			}
			for(int j=0;j<q;j++){
				if (flag[i]=="No")  break;
				else if(k1+k2*b[j].x+k3*b[j].y<=0){
					flag[i]="No";
					break;
				}
				else {
					flag[i]="Yes";
				}
			}
		}
		else flag[i]="No";
	}
	
	for(int i=0;i<m;i++){
		cout<<flag[i]<<endl;
	}
	
	return 0;
} 
