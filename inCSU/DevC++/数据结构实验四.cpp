
#include <iostream>
#include <vector>
#include<cstdlib>
#include<ctime>
#define random(a,b) (rand()%(b-a+1)+a)
using namespace std;

void Swap(vector<int>& unorder_array, int a, int b) {  
	unorder_array[a] = unorder_array[a] + unorder_array[b]; 
	unorder_array[b] = unorder_array[a] - unorder_array[b];
	unorder_array[a] = unorder_array[a] - unorder_array[b];
}

void BubbleSort(vector<int>& unorder_array) {
	for (int i = 1; i < unorder_array.size(); ++i) {    
		for (int j = unorder_array.size() - 1; j >= i; --j) {
			if (unorder_array[j] < unorder_array[j - 1]) {
				Swap(unorder_array, j, j - 1);
			}
		}
	}
}

int QuickSortPartition(vector<int>& unorder_array, int left, int right) {
	int i = left;   
	int j = right;
	int temp = unorder_array[left]; 
	while (i < j) {
		while (i < j && unorder_array[j] > temp) {  
			--j;
		}
		if (i < j) {    
			unorder_array[i] = unorder_array[j];    
			++i;
		}

		while (i < j && unorder_array[i] < temp) {
			++i;
		}
		if (i < j) {
			unorder_array[j] = unorder_array[i];
			--j;
		}
	}
	unorder_array[i] = temp;    

	return i;
}

void QuickSort(vector<int>& unorder_array, int left, int right) {
	if (left < right) {
		int middle = QuickSortPartition(unorder_array, left, right); 
		QuickSort(unorder_array, left, middle - 1);
		QuickSort(unorder_array, middle + 1, right);    
	}

}
int main(){
	srand((unsigned)time(NULL));
	vector<int> unorder_array;
	int data;
	int number;
	cout<<"请输入随机生成的元素数:";
	cin>>number;
	cout<<endl;
	for (int i = 0; i < number; i++)
	unorder_array.push_back(random(1, 20000));
	while (cin.get() != '\n') {
		for (int i = 0; i < number; i++)
		unorder_array.push_back(random(1, 20000));
	}
	cout << "原始数组为: ";
	for (auto it = unorder_array.begin();it != unorder_array.end();++it) {
		cout << *it << " ";
	}
	cout << "\n";

	time_t startSecondTime, endSecendTime;
	clock_t cstartMilliSecondTime, cendMilliSecondTime;
	startSecondTime = time(NULL);
	cstartMilliSecondTime = clock();

	QuickSort(unorder_array, 0, unorder_array.size() - 1);

	cout << "快速排序为: ";
	for (int i = 0; i < unorder_array.size(); ++i) {
		cout << unorder_array[i] << " ";
	}

	endSecendTime = time(NULL);
	double interval = difftime(endSecendTime, startSecondTime);
	cendMilliSecondTime = clock();
	cout << endl;
	cout << "快速排序所用时间：" << cendMilliSecondTime - cstartMilliSecondTime<<"ms"<< endl;

	system("pause");

	startSecondTime = time(NULL);
	cstartMilliSecondTime = clock();

	BubbleSort(unorder_array);
	cout << "冒泡排序为: ";
	for (int i = 0; i < unorder_array.size(); ++i) {
		std::cout << unorder_array[i] << " ";
	}

	endSecendTime = time(NULL);
    interval = difftime(endSecendTime, startSecondTime);
	cendMilliSecondTime = clock();
	cout << endl;
	cout << "冒泡排序所用时间：" << cendMilliSecondTime - cstartMilliSecondTime<<"ms" << endl;

	system("pause");

	return 0;
}
