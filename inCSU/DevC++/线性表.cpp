#include<iostream>
#include<stdlib.h>
#include<time.h>
using namespace std;

typedef struct node{        //创建链表的结构体
	int coe;
	int exp;
	struct node *next;
}node;

int merge(node *L1, node *L2);
int main(){
	node *L1, *L2, *p1, *p2, *t1, *t2;

	L1=new node;
	t1=L1;
	L2=new node;
	t2=L2;

	int coe1, coe2, exp1, exp2;
	cout<<"请输入链表1的各项系数和指数(指数为-1时停止)："<<endl;
	cin>>coe1>>exp1;
	while(exp1!=-1){             //输入链表1的多项式的每一项指数，系数
		p1=new node;
		p1->coe=coe1;
		p1->exp=exp1;
		t1->next=p1;
		t1=p1;
		cin>>coe1>>exp1;
	}
	t1->next=NULL;
	cout<<"请输入链表2的各项系数和指数(指数为-2时停止)："<<endl;
	cin>>coe2>>exp2;
	while (exp2!=-2){             //输入链表1的多项式的每一项指数，系数
		p2=new node;
		p2->coe=coe2;
		p2->exp=exp2;
		t2->next=p2;
		t2=p2;
		cin>>coe2>>exp2;
	}
	t2->next=NULL;
	merge(L1,L2);        //调用合并函数
	return 0;
}

int merge(node *L1, node *L2){
	node *L3, *p3, *t3;
	L3=new node;
	t3=L3;
	while (L1->next!=NULL&&L2->next!=NULL){     //链表1、2的指针为空时跳出循坏
		if (L1->next->exp==L2->next->exp){        //链表1、2的数据相等          
			if (L1->next->coe+L2->next->coe==0){  //链表1、2的数据相加为0
				L1->next=L1->next->next;
				L2->next=L2->next->next; 
			}
			else{                            //链表1、2的数据相加不为0
				p3=new node;
				p3->coe=L1->next->coe+L2->next->coe;  
				p3->exp=L1->next->exp;
				t3->next=p3;
				t3=p3;
				L1->next=L1->next->next;
				L2->next=L2->next->next;
			}
		}
		else if (L1->next->exp<L2->next->exp){   //链表1的指数小于链表2的指数
			p3=new node;
			p3->coe=L1->next->coe;
			p3->exp=L1->next->exp;
			t3->next=p3;
			t3=p3;
			L1->next=L1->next->next;
		}
		else if(L1->next->exp>L2->next->exp){  //链表1的指数>链表2的指数
			p3=new node;
			p3->coe=L2->next->coe;
			p3->exp=L2->next->exp;
			t3->next=p3;
			t3=p3;
			L2->next=L2->next->next;
		}
	}
	while (L1->next!=NULL){               //链表1未完全输出
		p3=new node;
		p3->coe=L1->next->coe;
		p3->exp=L1->next->exp;
		t3->next=p3;
		t3=p3;
		L1->next=L1->next->next;
	}
	while (L2->next!=NULL){             //链表2未完全输出
		p3=new node;
		p3->coe=L2->next->coe;
		p3->exp=L2->next->exp;
		t3->next=p3;
		t3=p3;
		L2->next=L2->next->next;
	}
	t3->next=NULL;                  //链表3的下一个指针域为空
	cout<<"多项式1、2的和如下"<<endl;
	
	node *e;
	e = L3->next;
	
	while (L3->next!=NULL){            //链表3指针为空时跳出循环
		if(L3->next!=e) cout<<"+";
		cout<<L3->next->coe<<"x^"<<L3->next->exp; 
		L3->next=L3->next->next;
	}
	return 0;
}
