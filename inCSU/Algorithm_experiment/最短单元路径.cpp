#include<iostream>
using namespace std;

Dijkstra(G��D��s) ? //Dijkstra �㷨    O(V2)
{ //��ʼ������?? S={s}��D[s]=0��     ?? for( all i�� V-S )     D[i]=G[s][i]�� //O(V)?     
  //�����㼯
   for (i=1;i<V;i++) ?
   {D[k]=min {D[i];all i��V-S};
      if(D[k]==��)  return;
     S=S��{k }�� 
     for( all j��V-S ) ? if(D[j]>D[k]+G[k][j])     D[j]=D[k]+G[k][j]��
    }
}
