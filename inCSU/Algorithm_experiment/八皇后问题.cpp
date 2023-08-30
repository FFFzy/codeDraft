#include<iostream> 
using namespace std;

#define N 8
int count=0;

void print(int A[N][N]){
    int i,j;
    for(i=0;i<N;i++){
	for(j=0;j<N;j++)
    cout<<" "<<A[i][j];
	cout<<endl;
    }
}

int mytry(int i,int M[N],int L[2*N],int R[2*N],int A[N][N]){
    for(int j=0; j<N;j++)
     if(!M[j]&&!R[i-j+N]&&!L[i+j]){
	    A[i][j]=i+1;
        M[j]=R[i-j+N]=L[i+j]=1;   
        if(i==N-1){
		    print(A);
			cout<<endl;
			count++;}
        else mytry(i+1,M,L,R,A);
        A[i][j]=0;
        M[j]=R[i-j+N]=L[i+j]=0;
    }
   return count;
}

int main(){
	int M[N]={0};
	int L[2*N]={0};
	int R[2*N]={0}; 
	int A[N][N]={0};
	
    int n=mytry(0,M,L,R,A);
	cout<<"\n count="<<n;
	return 0;
}
