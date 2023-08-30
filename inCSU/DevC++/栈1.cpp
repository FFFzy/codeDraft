#include<iostream>
#include<stdlib.h>
#include<string.h>
#include<math.h>
using namespace std; 

#define Stack_Size 1000

char cmp[7][8]= {">><<<>>",">><<<>>",">>>><>>",">>>><>>","<<<<<=?",">>>>?>>","<<<<<?="};

typedef struct{                                //定义一个运算符栈
    char Elem[Stack_Size];
    int top;
}Opter;

typedef struct{                                //定义一个操作数栈
    double Elem[Stack_Size];
    int top;
}Opnd;

void InitOpter(Opter *S){                          //初始化运算符栈
    S->top=-1;
}
void InitOpnd(Opnd *S){                          //初始化操作数栈
    S->top=-1;
}

int PopOpter(Opter *S){                          //弹出运算符栈
    if(S->top==-1){
        cout<<"运算符栈为空"<<endl;
    }
    S->top--;
    return 1;
}

int PopOpnd(Opnd *S){
    if(S->top==-1){
        cout<<"运算符栈为空"<<endl;
    }
    S->top--;
    return 1;
}

int PushOpter(Opter* S,char ch){
    if(S->top==Stack_Size-1){
        cout<<"运算符栈满"<<endl;
    }
    S->top++;
    S->Elem[S->top]=ch;
    return 1;
}

int PushOpnd(Opnd* S,double ch){
    if(S->top==Stack_Size-1){
        cout<<"运算符栈满"<<endl;
    }
    S->top++;
    S->Elem[S->top]=ch;
    return 1;
}

char GetOpter(Opter *S){                     //获取运算符栈的栈顶元素
    if(S->top==-1){
        cout<<"运算符栈为空"<<endl;
    }
    return S->Elem[S->top];
}

double GetOpnd(Opnd *S){
    if(S->top==-1){
        cout<<"操作数栈为空"<<endl;
    }
    return S->Elem[S->top];
}

double Calc(double a,double b,char opt){                  //计算函数，传入两个数以及一个运算符
    double T;                                             //T用于存放计算得出的结果
    if(opt=='+') T=a+b;
    if(opt=='-') T=a-b;
    if(opt=='*') T=a*b;
    if(opt=='/'){                                         //要防止发生除0错误
        if(fabs(b)<0.00001){
            cout<<"发生除0错误"<<endl;
        }
        T=a/b;
    }
    cout<<"中间过程输出"<<a<<opt<<b<<"="<<T<<endl;
    return T;                                                 //返回得到的结果
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
        cout<<"输入表达式错误"<<endl;
    }
    return cmp[change(ch1)][change(ch2)];
}

int Check(char *S,int len){                                 //检查函数，还要考虑输入带小数点的数字的情况
    int i;
    for(i=0;i<len;i++){
        if(S[i]>='0'&&S[i]<='9')continue;
        if(S[i]=='('||S[i]==')'||S[i]=='*'||S[i]=='/'||S[i]=='+'||S[i]=='-'||S[i]=='.')continue;
        return 0;
    }
    return 1;
}

int main(){
    char a[1000],b[1000];                          //创建两个字符数组，a用来存输入的表达式，b用来存操作数

    int len;                                        //len为输入表达式的长度
    Opter S;
    Opnd N;
    InitOpnd(&N);
    InitOpter(&S);

    PushOpter(&S,'#');                            //先在运算符栈中压入一个'#'，作为开始符 

    cout<<"输入表达式:"<<endl;
    scanf("%s",a);                                //输入表达式

    len=strlen(a);                                //求输入的表达式的长度
    if(Check(a,len)==0){                          //检查是否有输入错误 
        cout<<"输出中存在多余字符"<<endl;
        exit(1);
    }

    int i,j=0,k=0;
    double x,y;                                     //x,y是从操作数中取出的两个即将用于计算的数
    a[len]='#';                                     //数组尾部接一个#作为结束符 
    for(i=0;i<=len;i++){
        if((a[i]>='0'&&a[i]<='9')||a[i]=='.'){      //如果为数字，将数字存入数组b中，
            b[k++]=a[i];                             
            j=1;
            continue;
        }
        if(j){                                           //条件成功即遇到了运算字符，将操作数压入操作数栈中
            b[k]='\0';									// 加一个'\0'使其成为字符串
            PushOpnd(&N,atof(b));                        //atof函数可以使数字和小数点由char变为double
            j=0;
            k=0;                                          //k置零为下一次计数做准备
        }
        switch(Compare(GetOpter(&S),a[i])){             //比较运算符栈的栈顶运算符top和运算符a[i]的优先级
        case '<':	PushOpter(&S,a[i]);
            break;
        case'=':	PopOpter(&S);
            break;
        case'>':										//当为‘>'的情况，即需要进行运算，先取操作数栈中最上面的两个元素
            y=GetOpnd(&N),PopOpnd(&N);
            x=GetOpnd(&N),PopOpnd(&N);             			//然后将计算结果压入操作数栈中
            PushOpnd(&N,Calc(x,y,GetOpter(&S)));             //已经用过的运算符就弹出
            PopOpter(&S);
            i--;
            break;
        }
    }
    double answer=GetOpnd(&N);
        cout<<"最终结果为:"<<answer<<endl;
        return 0;
}
