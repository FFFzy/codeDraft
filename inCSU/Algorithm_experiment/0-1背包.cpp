#include<iostream>
#include <iomanip>
using namespace std; 

void KnapSack(int v[],int w[],int c,int n,int m[][11]){
	for(int i=0;i<n;i++){
		for(int j=0;j<11;j++){
			if(i==0){
				if(j>=w[i])
                    m[i][j] = v[i];
                else
                    m[i][j] = 0;
			}
			else{
				if(j>=w[i]){
					if(m[i-1][j]>m[i-1][j-w[i]]+v[i])
					m[i][j]=m[i-1][j];
					else
					m[i][j]=m[i-1][j-w[i]]+v[i];
				}
				else
				m[i][j]=m[i-1][j];
			}
		}
	}
}

int main(){
	int n=5;
	int c=10;
	int w[5]={2,2,6,5,4};
	int v[5]={6,3,5,4,6};
	int m[5][11];
	
	for(int i=0; i<5; i++){
        for(int j=0; j<11; j++){
            m[i][j] = 0;
        }
    }
     
    KnapSack(v,w,c,n,m);
    
    for(int i=0; i<5; i++){
        for(int j=0; j<11; j++){
		  cout<<setw(3)<<m[i][j];
        }
    cout<<endl;
    }
    return 0;
}
