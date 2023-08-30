#include<iostream>
#include<stdlib.h>
#include<string.h>
#include<math.h>
using namespace std; 

#define Stack_Size 1000

char cmp[7][8]= {">><<<>>",">><<<>>",">>>><>>",">>>><>>","<<<<<=?",">>>>?>>","<<<<<?="};

typedef struct{                                //����һ�������ջ
    char Elem[Stack_Size];
    int top;
}Opter;

typedef struct{                                //����һ��������ջ
    double Elem[Stack_Size];
    int top;
}Opnd;

void InitOpter(Opter *S){                          //��ʼ�������ջ
    S->top=-1;
}
void InitOpnd(Opnd *S){                          //��ʼ��������ջ
    S->top=-1;
}

int PopOpter(Opter *S){                          //���������ջ
    if(S->top==-1){
        cout<<"�����ջΪ��"<<endl;
    }
    S->top--;
    return 1;
}

int PopOpnd(Opnd *S){
    if(S->top==-1){
        cout<<"�����ջΪ��"<<endl;
    }
    S->top--;
    return 1;
}

int PushOpter(Opter* S,char ch){
    if(S->top==Stack_Size-1){
        cout<<"�����ջ��"<<endl;
    }
    S->top++;
    S->Elem[S->top]=ch;
    return 1;
}

int PushOpnd(Opnd* S,double ch){
    if(S->top==Stack_Size-1){
        cout<<"�����ջ��"<<endl;
    }
    S->top++;
    S->Elem[S->top]=ch;
    return 1;
}

char GetOpter(Opter *S){                     //��ȡ�����ջ��ջ��Ԫ��
    if(S->top==-1){
        cout<<"�����ջΪ��"<<endl;
    }
    return S->Elem[S->top];
}

double GetOpnd(Opnd *S){
    if(S->top==-1){
        cout<<"������ջΪ��"<<endl;
    }
    return S->Elem[S->top];
}

double Calc(double a,double b,char opt){                  //���㺯���������������Լ�һ�������
    double T;                                             //T���ڴ�ż���ó��Ľ��
    if(opt=='+') T=a+b;
    if(opt=='-') T=a-b;
    if(opt=='*') T=a*b;
    if(opt=='/'){                                         //Ҫ��ֹ������0����
        if(fabs(b)<0.00001){
            cout<<"������0����"<<endl;
        }
        T=a/b;
    }
    cout<<"�м�������"<<a<<opt<<b<<"="<<T<<endl;
    return T;                                                 //���صõ��Ľ��
}

int change(char ch){
    switch(ch){
    case '+':	return 0;
    case '-':	return 1;
    case '*':	return 2;
    case '/':	return 3;
    case '(':	return 4;
    case ')':	return 5;
    case '#':	return 6;
    }
}

int Compare(char ch1,char ch2){
    if(cmp[change(ch1)][change(ch2)]=='?'){
        cout<<"������ʽ����"<<endl;
    }
    return cmp[change(ch1)][change(ch2)];
}

int Check(char *S,int len){                                 //��麯������Ҫ���������С��������ֵ����
    int i;
    for(i=0;i<len;i++){
        if(S[i]>='0'&&S[i]<='9')continue;
        if(S[i]=='('||S[i]==')'||S[i]=='*'||S[i]=='/'||S[i]=='+'||S[i]=='-'||S[i]=='.')continue;
        return 0;
    }
    return 1;
}

int main(){
    char a[1000],b[1000];                          //���������ַ����飬a����������ı��ʽ��b�����������

    int len;                                        //lenΪ������ʽ�ĳ���
    Opter S;
    Opnd N;
    InitOpnd(&N);
    InitOpter(&S);

    PushOpter(&S,'#');                            //���������ջ��ѹ��һ��'#'����Ϊ��ʼ�� 

    cout<<"������ʽ:"<<endl;
    scanf("%s",a);                                //������ʽ

    len=strlen(a);                                //������ı��ʽ�ĳ���
    if(Check(a,len)==0){                          //����Ƿ���������� 
        cout<<"����д��ڶ����ַ�"<<endl;
        exit(1);
    }

    int i,j=0,k=0;
    double x,y;                                     //x,y�ǴӲ�������ȡ���������������ڼ������
    a[len]='#';                                     //����β����һ��#��Ϊ������ 
    for(i=0;i<=len;i++){
        if((a[i]>='0'&&a[i]<='9')||a[i]=='.'){      //���Ϊ���֣������ִ�������b�У�
            b[k++]=a[i];                             
            j=1;
            continue;
        }
        if(j){                                           //�����ɹ��������������ַ�����������ѹ�������ջ��
            b[k]='\0';									// ��һ��'\0'ʹ���Ϊ�ַ���
            PushOpnd(&N,atof(b));                        //atof��������ʹ���ֺ�С������char��Ϊdouble
            j=0;
            k=0;                                          //k����Ϊ��һ�μ�����׼��
        }
        switch(Compare(GetOpter(&S),a[i])){             //�Ƚ������ջ��ջ�������top�������a[i]�����ȼ�
        case '<':	PushOpter(&S,a[i]);
            break;
        case'=':	PopOpter(&S);
            break;
        case'>':										//��Ϊ��>'�����������Ҫ�������㣬��ȡ������ջ�������������Ԫ��
            y=GetOpnd(&N),PopOpnd(&N);
            x=GetOpnd(&N),PopOpnd(&N);             			//Ȼ�󽫼�����ѹ�������ջ��
            PushOpnd(&N,Calc(x,y,GetOpter(&S)));             //�Ѿ��ù���������͵���
            PopOpter(&S);
            i--;
            break;
        }
    }
    double answer=GetOpnd(&N);
        cout<<"���ս��Ϊ:"<<answer<<endl;
        return 0;
}
