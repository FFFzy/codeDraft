#include<iostream>
using namespace std;

struct Point{
	int x1;
	int x2;
	int y1;
	int y2;
	int level;
};

int main(){
	int N,M;
	cin>>N>>M;
	
	Point a[N];
	for(int i=0;i<N;i++){
		cin>>a[i].x1;
		cin>>a[i].y1;
		cin>>a[i].x2;
		cin>>a[i].y2;
		a[i].level = N-i;
	}

	int select[M]={0};
	int level = 20;
	for(int i=0;i<M;i++){
		int x,y;
		cin>>x>>y;
		for(int j=0;j<N;j++){
			if(x<=a[j].x2 && x>=a[j].x1 && y<=a[j].y2 && y>=a[j].y1){
				if(a[j].level < level){
					level = a[j].level;
					select[i] = j+1;
				}
			}
		}
		if(level != 20){
			int temp = select[i]-1;
			a[temp].level = 1;
			for(int k=0;k<N;k++){
				if(a[k].level < level) a[k].level++;
			}
			level = 20;
		}
	}
	
	for(int i=0;i<M;i++){
		if(select[i] != 0) cout<<select[i]<<endl;
		else cout<<"IGNORED"<<endl;
	} 
	return 0;
}
