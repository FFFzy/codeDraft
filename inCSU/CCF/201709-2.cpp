#include<iostream>
#include<algorithm>
using namespace std;

int main(){
	int N,K;
	cin>>N>>K;
	
	int a[K][3];
	for(int i=0;i<K;i++){
		for(int j=0;j<3;j++){
			cin>>a[i][j];
		}
	}
	
	int max = 0;
	for(int i=0;i<K;i++){
		if(max<(a[i][1]+a[i][2])){
			max = a[i][1]+a[i][2];
		}
	}
	
	int key[N];
	for(int i=0;i<N;i++){
		key[i]=i+1;
	}
	
	int back[N];
	for(int i=0;i<N;i++){
		back[i]=10000;
	}
	for(int t=1;t<=max;t++){
		int num=0;
		for(int i=0;i<K;i++){
			if( (a[i][1]+a[i][2]) == t ){
				back[num]=a[i][0];
				num++;	
			}
		}
		
		sort(back,back+N);
		num=0;
		if(back[0]!=10000){
			for(int j=0;j<N;j++){
				if((key[j] == 0) && (back[num] != 10000)){
					key[j]=back[num];
					back[num]=10000;
					num++;
				}
			}
		}
		
		for(int i=0;i<K;i++){
			if(a[i][1] == t){
				for(int j=0;j<N;j++){
					if(key[j] == a[i][0]){
						key[j]=0;
					}
				}
			}
		}
	}
	
	for(int i=0;i<N;i++){
		cout<<key[i]<<" ";
	}
	
	return 0;
}
