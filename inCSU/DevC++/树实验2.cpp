#include <iostream>
using namespace std;

typedef struct tTree{
    char date;
    struct tTree * lchild;
    struct tTree * rchild;
}tTree;

tTree * createBiTree(tTree * Tree){    //创建二叉树
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
            numOfNode++;       //统计节点个数
            if((p->lchild!=NULL&&p->rchild==NULL)|| (p->lchild==NULL&&p->rchild!=NULL) )   //统计度为1的节点
                numOfOneDu++;
            if(p->lchild!=NULL&&p->rchild!=NULL)  //统计度为2的节点
                numOfTwoDu++;
            if(p->lchild==NULL&&p->rchild==NULL)   //统计叶子节点
                numOfLeaves++;
            if( numOfMax < (p->date) )         //比较得到最大值
                numOfMax = p->date;
            if( numOfMin > (p->date) )         //比较得到最小值
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
    cout<<"二叉树的节点个数："<<numOfNode<<endl;
    cout<<"二叉树的度为1的节点个数："<<numOfOneDu<<endl;
    cout<<"二叉树的度为2的节点个数："<<numOfTwoDu<<endl;
    cout<<"二叉树的叶子节点个数："<<numOfLeaves<<endl;
    cout<<"二叉树的数据值的最大值："<<numOfMax<<endl;
    cout<<"二叉树的数据值的最小值："<<numOfMin<<endl;
}

int main(){
    cout<<"请输入二叉树的数据："<<endl;
    tTree *head = createBiTree(head);
    Count(head);
    cout<<endl;

    return 0;
}

