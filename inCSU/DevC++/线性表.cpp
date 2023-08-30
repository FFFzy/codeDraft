#include<iostream>
#include<stdlib.h>
#include<time.h>
using namespace std;

typedef struct node{        //��������Ľṹ��
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
	cout<<"����������1�ĸ���ϵ����ָ��(ָ��Ϊ-1ʱֹͣ)��"<<endl;
	cin>>coe1>>exp1;
	while(exp1!=-1){             //��������1�Ķ���ʽ��ÿһ��ָ����ϵ��
		p1=new node;
		p1->coe=coe1;
		p1->exp=exp1;
		t1->next=p1;
		t1=p1;
		cin>>coe1>>exp1;
	}
	t1->next=NULL;
	cout<<"����������2�ĸ���ϵ����ָ��(ָ��Ϊ-2ʱֹͣ)��"<<endl;
	cin>>coe2>>exp2;
	while (exp2!=-2){             //��������1�Ķ���ʽ��ÿһ��ָ����ϵ��
		p2=new node;
		p2->coe=coe2;
		p2->exp=exp2;
		t2->next=p2;
		t2=p2;
		cin>>coe2>>exp2;
	}
	t2->next=NULL;
	merge(L1,L2);        //���úϲ�����
	return 0;
}

int merge(node *L1, node *L2){
	node *L3, *p3, *t3;
	L3=new node;
	t3=L3;
	while (L1->next!=NULL&&L2->next!=NULL){     //����1��2��ָ��Ϊ��ʱ����ѭ��
		if (L1->next->exp==L2->next->exp){        //����1��2���������          
			if (L1->next->coe+L2->next->coe==0){  //����1��2���������Ϊ0
				L1->next=L1->next->next;
				L2->next=L2->next->next; 
			}
			else{                            //����1��2��������Ӳ�Ϊ0
				p3=new node;
				p3->coe=L1->next->coe+L2->next->coe;  
				p3->exp=L1->next->exp;
				t3->next=p3;
				t3=p3;
				L1->next=L1->next->next;
				L2->next=L2->next->next;
			}
		}
		else if (L1->next->exp<L2->next->exp){   //����1��ָ��С������2��ָ��
			p3=new node;
			p3->coe=L1->next->coe;
			p3->exp=L1->next->exp;
			t3->next=p3;
			t3=p3;
			L1->next=L1->next->next;
		}
		else if(L1->next->exp>L2->next->exp){  //����1��ָ��>����2��ָ��
			p3=new node;
			p3->coe=L2->next->coe;
			p3->exp=L2->next->exp;
			t3->next=p3;
			t3=p3;
			L2->next=L2->next->next;
		}
	}
	while (L1->next!=NULL){               //����1δ��ȫ���
		p3=new node;
		p3->coe=L1->next->coe;
		p3->exp=L1->next->exp;
		t3->next=p3;
		t3=p3;
		L1->next=L1->next->next;
	}
	while (L2->next!=NULL){             //����2δ��ȫ���
		p3=new node;
		p3->coe=L2->next->coe;
		p3->exp=L2->next->exp;
		t3->next=p3;
		t3=p3;
		L2->next=L2->next->next;
	}
	t3->next=NULL;                  //����3����һ��ָ����Ϊ��
	cout<<"����ʽ1��2�ĺ�����"<<endl;
	
	node *e;
	e = L3->next;
	
	while (L3->next!=NULL){            //����3ָ��Ϊ��ʱ����ѭ��
		if(L3->next!=e) cout<<"+";
		cout<<L3->next->coe<<"x^"<<L3->next->exp; 
		L3->next=L3->next->next;
	}
	return 0;
}
