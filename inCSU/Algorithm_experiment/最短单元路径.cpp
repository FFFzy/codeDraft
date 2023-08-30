#include<iostream>
using namespace std;

Dijkstra(G，D，s) ? //Dijkstra 算法    O(V2)
{ //初始化操作?? S={s}；D[s]=0；     ?? for( all i∈ V-S )     D[i]=G[s][i]； //O(V)?     
  //扩充红点集
   for (i=1;i<V;i++) ?
   {D[k]=min {D[i];all i∈V-S};
      if(D[k]==∞)  return;
     S=S∪{k }； 
     for( all j∈V-S ) ? if(D[j]>D[k]+G[k][j])     D[j]=D[k]+G[k][j]；
    }
}
