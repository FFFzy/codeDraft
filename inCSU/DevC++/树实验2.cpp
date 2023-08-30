#include <iostream>
using namespace std;

typedef struct tTree{
    char date;
    struct tTree * lchild;
    struct tTree * rchild;
}tTree;

tTree * createBiTree(tTree * Tree){    //����������
    char num;

    cin>>num;
    if(num=='$')
        Tree=NULL;
    else{
        Tree = new tTree;
        Tree->date=num;
        Tree->lchild=createBiTree(Tree->lchild);
        Tree->rchild=createBiTree(Tree->rchild);
    }
    return Tree;
}

void Count(tTree * Tree){
    tTree *p;
    tTree *stack[100];
    int top = 0;
    p=Tree;

    int numOfNode, numOfOneDu, numOfTwoDu, numOfLeaves;
    numOfNode = numOfOneDu = numOfTwoDu = numOfLeaves = 0;
    char numOfMax, numOfMin;
    numOfMax = numOfMin = p->date;

    while(p || top!=0){
        while(p){
            numOfNode++;       //ͳ�ƽڵ����
            if((p->lchild!=NULL&&p->rchild==NULL)|| (p->lchild==NULL&&p->rchild!=NULL) )   //ͳ�ƶ�Ϊ1�Ľڵ�
                numOfOneDu++;
            if(p->lchild!=NULL&&p->rchild!=NULL)  //ͳ�ƶ�Ϊ2�Ľڵ�
                numOfTwoDu++;
            if(p->lchild==NULL&&p->rchild==NULL)   //ͳ��Ҷ�ӽڵ�
                numOfLeaves++;
            if( numOfMax < (p->date) )         //�Ƚϵõ����ֵ
                numOfMax = p->date;
            if( numOfMin > (p->date) )         //�Ƚϵõ���Сֵ
                numOfMin = p->date;

            if(p->rchild)
            {
                stack[top++] = p->rchild;
            }
            p=p->lchild;
        }
        if(top!=0){
            p = stack[--top];
        }
    }
    cout<<endl<<endl;
    cout<<"�������Ľڵ������"<<numOfNode<<endl;
    cout<<"�������Ķ�Ϊ1�Ľڵ������"<<numOfOneDu<<endl;
    cout<<"�������Ķ�Ϊ2�Ľڵ������"<<numOfTwoDu<<endl;
    cout<<"��������Ҷ�ӽڵ������"<<numOfLeaves<<endl;
    cout<<"������������ֵ�����ֵ��"<<numOfMax<<endl;
    cout<<"������������ֵ����Сֵ��"<<numOfMin<<endl;
}

int main(){
    cout<<"����������������ݣ�"<<endl;
    tTree *head = createBiTree(head);
    Count(head);
    cout<<endl;

    return 0;
}

