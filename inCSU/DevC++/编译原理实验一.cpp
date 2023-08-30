#include <iostream>
#include <fstream>
#include <cassert>
#include <string>
using namespace std;

bool isKey(string s){
    string keyArray[] = {"int","char","string","bool","float","double","float","true","false","return",
                        "if","else","while","for","default","do","public","static","switch"};
    for(int i=0;i<sizeof(keyArray);i++){
        if(keyArray[i] == s){
            return true;
        }
        else{
            return false;
        }
    }
}

bool isOperator(char ch){
    if('+'==ch || '-'==ch || '*'==ch || '/'==ch || '^'==ch || '='==ch || '<'==ch || '>'==ch || '!'==ch)
        return true;
    else
        return false;
}

bool isSeparator(char ch){
    if(','==ch || ';'==ch || '{'==ch || '}'==ch || '('==ch || ')'==ch)
        return true;
    else
        return false;
}


int main( ){
    char ch;
    string result;
    string resultArray[999];
    int resultNum=0;
    string file = "./demo1.txt";

    ifstream infile;
    infile.open(file.data());
    assert(infile.is_open());

    infile>>ch;

    while (!infile.eof()){
        if(isalpha(ch) || '_'==ch){
            result.append(1,ch);
            infile>>ch;
            if(isKey(result)){
                resultArray[resultNum++]="(1,\""+result+"\")";
                result="";
            }
            while(isalpha(ch) || isdigit(ch) || '_'==ch){
                result.append(1,ch);
                infile>>ch;
                if(isKey(result)){
                    resultArray[resultNum++]="(1,\""+result+"\")";
                    result="";
                }
            }
            if(isSeparator(ch) || isOperator(ch)){
                if(isKey(result)){
                    resultArray[resultNum++]="(1,\""+result+"\")";
                    result="";
                    continue;
                }
                else{
                    if(isdigit(result.at(0))){
                        resultArray[resultNum++]="(Error,\""+result+"\")";
                    }else{
                        resultArray[resultNum++]="(2,\""+result+"\")";
                    }
                    result="";
                    continue;
                }

            }
            else{
                result.append(1,ch);
                infile>>ch;
                while(!isSeparator(ch) && !isOperator(ch)){
                    result.append(1,ch);
                    infile>>ch;
                }
                resultArray[resultNum++]="(Error,\""+result+"\")";
                result="";
                continue;
            }
        }
        else if(isdigit(ch)){
            result.append(1,ch);
            infile>>ch;
            while(isdigit(ch)){
                result.append(1,ch);
                infile>>ch;
            }
            if(isOperator(ch) || isSeparator(ch)){
                resultArray[resultNum++]="(3,\""+result+"\")";
                result="";
                continue;
            }
            else{
                result.append(1,ch);
                infile>>ch;
                while(!isSeparator(ch) && !isOperator(ch)){
                    result.append(1,ch);
                    infile>>ch;
                }
                resultArray[resultNum++]="(Error,\""+result+"\")";
                result="";
                continue;
            }
        }
        else if(isOperator(ch)){
            result.append(1,ch);
            infile>>ch;
            if("<"==result || ">"==result || "!"==result){
                if('='==ch){
                    result.append(1,ch);
                    infile>>ch;
                }
            }
            if(isalpha(ch) || isdigit(ch) || isSeparator(ch)){
                resultArray[resultNum++]="(4,\""+result+"\")";
                result="";
                continue;
            }
            else{
                while(!isSeparator(ch) && !isalpha(ch) && !isdigit(ch)){
                    result.append(1,ch);
                    infile>>ch;
                }
                resultArray[resultNum++]="(Error,\""+result+"\")";
                result="";
                continue;
            }
        }
        else if(isSeparator(ch)){
            result.append(1,ch);
            resultArray[resultNum++]="(5,\""+result+"\")";
            result="";
            infile>>ch;
        }
        else{
            result.append(1,ch);
            resultArray[resultNum++]="(Error,\""+result+"\")";
            result="";
            infile>>ch;

        }
    }
    infile.close();

    for(int i=0;i<resultNum;i++){
        cout<<resultArray[i]<<endl;
    }

   return 0;
}
