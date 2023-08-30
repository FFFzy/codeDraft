#include<iostream>
using namespace std;

void Merge(int* numbers, int start, int mid, int end) {
	int* temp = new int[end - start + 1];
	int begin1 = start;
	int begin2 = mid + 1;
	int k = 0;

	while (begin1 <= mid && begin2 <= end) {
		if (numbers[begin1] <= numbers[begin2])
			temp[k++] = numbers[begin1++];
		else
			temp[k++] = numbers[begin2++];
	}

	while (begin1 <= mid)
		temp[k++] = numbers[begin1++];

	while (begin2 <= end)
		temp[k++] = numbers[begin2++];

	for (int j = 0; j < k; ++j)
		numbers[start + j] = temp[j];

	delete[] temp;
}


void MergeSort(int* numbers, int start, int end) {
	if (numbers == NULL || start >= end)
		return;

	int mid = (start + end) / 2;

	MergeSort(numbers, start, mid);
	MergeSort(numbers, mid + 1, end);
	Merge(numbers, start, mid, end);
}

int main() {
	int n;
	cout << "Please enter the size of the array : ";
	cin >> n;
	int a[n];
	cout << "Please enter the array data : " << endl;
	for (int i = 0; i < n; i++) {
		cin >> a[i];
	}
	cout << "¹é²¢ÅÅĞòÇ°£º";
	for (int i = 0; i < n; i++)
		cout << a[i] << ' ';
	cout << endl;

	MergeSort(a, 0, n - 1);

	cout << "¹é²¢ÅÅĞòºó£º";
	for (int i = 0; i < n; i++)
		cout << a[i] << ' ';
	cout << endl;
	return 0;
}