import networkx as nw

G = nw.Graph()
G.add_edge('A', 'B', weight=4)
G.add_edge('B', 'D', weight=2)
G.add_edge('A', 'C', weight=3)
G.add_edge('C', 'D', weight=4)
res = ""
res += str(nw.shortest_path(G, 'A', 'D', weight='weight'))
f = open("input1.txt", "w+")
f.write(res)
f.close()
print(10)