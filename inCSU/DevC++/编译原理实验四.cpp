#include <iostream>
#include <cstring>
#include <cstdio>
#include <vector>
#include <stack>
#include <map>
#include <set>
#include <algorithm>
#include <string>
#include <cstdlib>
#include <cctype>
#define MAX 507
 
using namespace std;
 
class WF{
    public:
    string left;
    vector<string> right;
    WF ( const string& str ){
        left = str;
    }
    void insert ( char str[] ){
        right.push_back(str);
    }
    void print ( ){
        printf ( "%s->%s" , left.c_str() , right[0].c_str() );
        for ( int i = 1 ; i < right.size() ; i++ )
            printf ( "|%s" , right[i].c_str() );
        puts("");
    }
};
 
char relation[MAX][MAX];
vector<char> VT;
vector<WF> VN_set;
map<string,int> VN_dic;
set<char> first[MAX];
set<char> last[MAX];
int used[MAX];
int vis[MAX];
 
void dfs (  int x ){
    if ( vis[x] ) return;
    vis[x] = 1;
    string& left = VN_set[x].left;
    for ( int i = 0 ; i < VN_set[x].right.size() ; i++ ){
        string& str = VN_set[x].right[i];
        if ( isupper(str[0]) ){
            int y = VN_dic[str.substr(0,1)]-1;
            if ( str.length() > 1 && !isupper(str[1] ) )
                first[x].insert ( str[1] );
            dfs ( y );
            set<char>::iterator it = first[y].begin();
            for ( ; it!= first[y].end() ; it++ )
                first[x].insert ( *it );
        }
        else 
            first[x].insert ( str[0] );
    }
}
 
void make_first ( ){
    memset ( vis , 0 , sizeof ( vis ) );
    for ( int i = 0 ; i < VN_set.size() ; i++ )
        if ( vis[i] ) continue;
        else dfs ( i );
#define DEBUG
#ifdef DEBUG
    puts("------------FIRSTVT集-------------------");
    for ( int i = 0 ; i < VN_set.size() ; i++ ){
        printf ( "%s : " , VN_set[i].left.c_str() );
        set<char>::iterator it = first[i].begin();
        for ( ; it!= first[i].end() ; it++ )
            printf ( "%c " , *it );
        puts ("" );
    }
#endif 
}
 
void dfs1 ( int x ){
    if ( vis[x] ) return;
    vis[x] = 1;
    string& left = VN_set[x].left;
    for ( int i = 0 ; i < VN_set[x].right.size() ; i++ ){
        string& str = VN_set[x].right[i];
        int n = str.length() -1;
        if ( isupper(str[n] ) ){
            int y = VN_dic[str.substr(n,1)]-1;
            if ( str.length() > 1 && !isupper(str[n-1]) )
                last[x].insert ( str[1] );
            dfs1 ( y );
            set<char>::iterator it = last[y].begin();
            for ( ; it != last[y].end() ; it++ )
                last[x].insert ( *it );
        }
        else 
            last[x].insert ( str[n] );
    }
}
 
 
void make_last ( ){
    memset ( vis , 0 , sizeof ( vis ) );
    for ( int i = 0 ; i < VN_set.size() ; i++ )
        if ( vis[i] ) continue;
        else dfs1 ( i );
#define DEBUG
#ifdef DEBUG
    puts("--------------LASTVT集---------------------");
    for ( int i = 0 ; i < VN_set.size() ; i++ ){
        printf ( "%s : " , VN_set[i].left.c_str() );
        set<char>::iterator it = last[i].begin();
        for ( ; it!= last[i].end() ; it++ )
            printf ( "%c " , *it );
        puts ("" );
    }
#endif
}
 
void make_table ( ){
    for ( int i = 0 ; i < MAX ; i++ )
        for ( int j = 0 ; j < MAX ; j++ )
            relation[i][j] = ' ';
    for ( int i = 0 ; i < VN_set.size() ; i++ )
        for ( int j = 0 ; j < VN_set[i].right.size() ; j++ ){
            string& str = VN_set[i].right[j];
            for ( int k = 0 ; k < str.length()-1 ; k++ ){
                if ( !isupper(str[k]) && !isupper(str[k+1]) )
                    relation[str[k]][str[k+1]] = '=';
                if ( !isupper(str[k]) && isupper(str[k+1]) ){
                    int x = VN_dic[str.substr(k+1,1)]-1;
                    set<char>::iterator it = first[x].begin();
                    for ( ; it != first[x].end() ; it++ )
                        relation[str[k]][*it] = '<';
                }
                if ( isupper(str[k]) && !isupper(str[k+1]) ){
                    int x = VN_dic[str.substr(k,1)]-1;
                    set<char>::iterator it = last[x].begin();
                    for ( ; it != last[x].end() ; it++ )
                        relation[*it][str[k+1]] = '>';
                }
                if ( k > str.length()-2 ) continue;
                if ( !isupper(str[k]) && !isupper(str[k+2]) && isupper(str[k+1]) )
                    relation[str[k]][str[k+2]] = '=';
            }
        }
#define DEBUG
#ifdef DEBUG
    for ( int i = 0 ; i < VT.size()*5 ; i++ )
        printf ("-");
    printf ( "算符优先关系表" );
    for ( int i = 0 ; i < VT.size()*5 ; i++ )
        printf ( "-" );
    puts("");
    printf ( "|%8s|" , "" );
    for ( int i = 0 ; i < VT.size() ; i++ )
        printf ( "%5c%5s" , VT[i] , "|" );
    puts ("");
    for ( int i = 0 ; i < (VT.size()+1)*10 ; i++ )
        printf ("-");
    puts("");
    for ( int i = 0 ; i < VT.size() ; i++ ){
        printf ( "|%4c%5s" , VT[i] , "|");
        for ( int j = 0 ; j < VT.size() ; j++ )
            printf ( "%5c%5s" , relation[VT[i]][VT[j]] , "|" );
        puts ("");
        for ( int i = 0 ; i < (VT.size()+1)*10 ; i++ )
            printf ("-");
        puts("");
    }
#endif
}
 
int fa[MAX];
 
int _find ( int x  ){
    return x==fa[x]?x:fa[x] = _find ( fa[x] );
}
 
bool judge ( char x , char y ){
    if ( _find ( x ) == _find ( y ) )
        return true;
    return false;
}
 
void _union ( char x , char y ){
    x = _find(x);
    y = _find(y);
    fa[x] = y;
}
 
void print ( string s1 , string s2 , string s3 , string s4 , string s5 , string s6 ){
    printf ( "%-14s|%-15s%-15s%-15s%-15s%-15s\n" , s1.c_str(), s2.c_str(), s3.c_str() ,s4.c_str(),s5.c_str() , s6.c_str() );
}
 
void init ( ){
    for ( int i = 0 ; i < MAX ; i++ )
        fa[i] = i;
    for ( int i = 0 ; i < VN_set.size() ; i++ ){
        string& left = VN_set[i].left;
        for ( int j = 0 ; j < VN_set[i].right.size() ; j++ ){
            string& str = VN_set[i].right[j];
            if ( left.length() == 1 && str.length() == 1 ){
               _union ( left[0] , str[0] );
            }
        }
    }
    print("步骤","栈","优先关系","当前符号","剩余符号","动作");
}
 
string get_stk ( vector<char>& stk ){
    string ret = "";
    for ( int i = 0 ; i < stk.size() ; i++ )
        ret += stk[i];
    return ret;
}
 
bool check ( const string& str1 , const string& str2 ){
    if ( str1.length() != str2.length() ) 
        return false;
    for ( int i = 0 ; i < str1.length() ; i++ )
        if ( isupper(str1[i]) ){
            if ( !judge(str1[i],str2[i])) return false;
        }
        else {
            if ( str1[i] != str2[i] ) return false;
        }
    return true;
}
 
string reduction ( string src ){
    for ( int i = 0 ; i < VN_set.size() ; i++ )
        for ( int j = 0 ; j < VN_set[i].right.size() ; j++ )
            if ( check ( VN_set[i].right[j] , src ) )
                return VN_set[i].left;
    return "";
}
 
void move_reduction ( string src ){
    init ();
    vector<char> stk;
    int steps= 1;
    src += "#";
    stk.push_back ( '#' );
    for ( int i = 0 ; i < src.length() ; i++ ){
        char top = stk[stk.size()-1];
        for ( int j = stk.size()-1 ; j >= 0 ; j-- )
            if ( isupper(stk[j]) ) continue;
            else   {
                top = stk[j];
                break;
            }
        char ch = relation[top][src[i]];
        if ( ch == '<' || ch == '=' ){
            string temp = "";
            if ( i == src.length() - 1 )
                print ( temp+(char)(steps+48) , get_stk( stk ) , temp+ch , temp+src[i] , "" , "移进" );
            else
                print ( temp+(char)(steps+48) , get_stk( stk ) , temp+ch , temp+src[i] , src.substr(i+1,src.length()-i-1) , "移进" );
            stk.push_back ( src[i] );  
        }
        else{
            string temp ="";
            string str ="";
            int x = stk.size()-2;
            if ( i == src.length() )
                print ( temp+(char)(steps+48) , get_stk(stk) , temp+ch , temp + src[i] , "" , "归约" );
            else 
                print ( temp+(char)(steps+48) , get_stk(stk) , temp+ch , temp + src[i] , src.substr(i+1,src.length()-i-1) , "归约" );
            while ( true ){
                if ( stk.size() == 0 ) break;
                if ( !isupper(stk[x] ) &&relation[stk[x]][top] == '<' ) 
                        break;
                x--;
            }
            for ( int j = stk.size()-1 ; j > x; j-- ){
                str += stk[j];
                stk.pop_back();
            }
            str = reduction(str);
            for ( int j = 0 ; j < str.length() ; j++ )
                stk.push_back ( str[j] );
            i--;
        }  
        steps++;
    }
}
 
 
int main ( ){
    int n;
    char s[MAX];
    while ( ~scanf ( "%d" , &n ) ){
        memset ( used , 0 , sizeof ( used ) );
        for ( int i = 0 ; i < n ; i++ ){
            scanf ( "%s" , s );
            int len = strlen(s),j;
            for ( j = 0 ; j < len ; j++ )
                if ( s[j] == '-' ) 
                    break;
            s[j] = 0;
            if ( !VN_dic[s] ){
                VN_set.push_back ( WF(s) );
                VN_dic[s] = VN_set.size();
            }
            int x = VN_dic[s]-1;
            VN_set[x].insert ( s+j+2 );
            for ( int k = 0 ; k < j; k++ )
                if ( !isupper(s[k] ) ){
                    if ( used[s[k]] ) continue;
                    used[s[k]] = 1;
                    VT.push_back ( s[k] );
                }
            for ( int k = j+2 ; k < len; k++ )
                if ( !isupper(s[k] ) ){
                    if ( used[s[k]] ) continue;
                    VT.push_back ( s[k] );
                    used[s[k]] = VT.size();
                }   
        }
#define DEBUG
#ifdef DEBUG
        puts ("************VT集*******************");
        for ( int i = 0 ; i < VT.size() ; i++ )
            printf ( "%c " , VT[i] );
        puts ("");
        puts("*************产生式*****************");
        for ( int i = 0 ; i < VN_set.size() ; i++ )
            VN_set[i].print();
        puts("************************************");
#endif
        make_first();
        make_last();
        make_table();
        move_reduction("i+i");
    }
}
