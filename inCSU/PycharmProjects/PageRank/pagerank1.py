# Coded By Lucky
# Time: 2021/1/4 20:21

import networkx as nx

G = nx.DiGraph()

edges = [("A", "B"), ("A", "C"),
         ("B", "A"), ("B", "C"), ("B", "D"),
         ("C", "D"),
         ("D", "C")]

for edge in edges:
    G.add_edge(edge[0], edge[1])

PRs = nx.pagerank(G, alpha=1)

print(PRs)
