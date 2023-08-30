#include <iostream>
using namespace std;

typedef struct tTree{
    char date;
    struct tTree * rTree;
    struct tTree * lTree;
}tTree;

tTree * creatBiTree(tTree *T){    //创建二叉树
    char num;

    cin>>num;
    if(num=='$')   //输入$终止
        T=NULL;
    else{
        T = new tTree;
        T->date=num;
        T->lTree = creatBiTree(T->lTree);
        T->rTree = creatBiTree(T->rTree);
    }
    return T;
}

void preOrder(tTree * T){     //先序
    if(T!=NULL){
        cout<<T->date<<" ";
        preOrder(T->lTree);
        preOrder(T->rTree);
    }
}

void inOrder(tTree * T){    //中序
    if(T!=NULL){
        inOrder(T->lTree);
        cout<<T->date<<" ";
        inOrder(T->rTree);
    }
}

void afterOrder(tTree * T){      //后序 
    if(T!=NULL){
        afterOrder(T->lTree);
        afterOrder(T->rTree);
        cout<<T->date<<" ";
    }
}

int main(){
    tTree * T;
    cout<<"请输入树各节点数据："<<endl; 
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

