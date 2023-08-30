#include <iostream>
using namespace std; 
#define MAXN 250

int main(){
	int n, a[MAXN],b[MAXN],c[MAXN],i,j, max;
	cout<<"Please enter the number of students: ";
	cin>>n;
	cout<<"Please enter the height of the students: ";
	for (i = 1; i <= n; i++)
	cin>>a[i];
	
	b[1] = 1;
	for (i = 2; i <= n; i++){
	max = 0;
	for (j = i - 1; j >= 1; j--)
	if(a[j]<a[i]&&b[j]>max)
	max = b[j];
	b[i] = max + 1; 
	}
	
	c[n] = 1;
	for (i = n - 1; i > 0; i--){
	max = 0;
	for (j = i + 1; j <= n; j++)
		if (a[j] < a[i] && c[j] > max)
	     	max = c[j];
		c[i] = max + 1;
    }
    
    max = b[1] + c[1];
	for (i = 2; i <= n; i++){
	if (b[i] + c[i] > max)
		max = b[i] + c[i];
	}
	cout<<"The min number of the studengts out of line is: "<<n - max + 1;
	return 0;
}

