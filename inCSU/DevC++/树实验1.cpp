#include <iostream>
using namespace std;

typedef struct tTree{
    char date;
    struct tTree * rTree;
    struct tTree * lTree;
}tTree;

tTree * creatBiTree(tTree *T){    //����������
    char num;

    cin>>num;
    if(num=='$')   //����$��ֹ
        T=NULL;
    else{
        T = new tTree;
        T->date=num;
        T->lTree = creatBiTree(T->lTree);
        T->rTree = creatBiTree(T->rTree);
    }
    return T;
}

void preOrder(tTree * T){     //����
    if(T!=NULL){
        cout<<T->date<<" ";
        preOrder(T->lTree);
        preOrder(T->rTree);
    }
}

void inOrder(tTree * T){    //����
    if(T!=NULL){
        inOrder(T->lTree);
        cout<<T->date<<" ";
        inOrder(T->rTree);
    }
}

void afterOrder(tTree * T){      //���� 
    if(T!=NULL){
        afterOrder(T->lTree);
        afterOrder(T->rTree);
        cout<<T->date<<" ";
    }
}

int main(){
    tTree * T;
    cout<<"�����������ڵ����ݣ�"<<endl; 
    T=creatBiTree(T);
    cout<<endl<<"preOrder"<<endl;
    preOrder(T);
    cout<<endl<<"inOrder"<<endl;
    inOrder(T);
    cout<<endl<<"afterOrder"<<endl;
    afterOrder(T);
    cout <<endl;
    return 0;
}

