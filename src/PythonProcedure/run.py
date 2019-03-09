import random
import sys
import networkx as nw

hardStart = 1
hardEnd = 1

def randInt(l, r):
    return random.randint(l, r)


def generateCOP(nodeCnt, edgeCnt, root, domainSiz):
    f = open("/home/mashrur/Dropbox/Thesis/DPOP/DPOP/inputRandomGraph.txt", "w+")
    f.write(str(nodeCnt))
    f.write(" ")
    f.write(str(edgeCnt))
    f.write(" ")
    f.write(str(root))

    f.write("\n")
    f.write(str(1))
    f.write(" ")
    f.write(str(domainSiz))
    f.write("\n")

    g = nw.gnm_random_graph(nodeCnt, edgeCnt, False)

    for edge in g.edges:
        type = randInt(0, 1)
        f.write(str(edge[0] + 1))
        f.write(" ")
        f.write(str(edge[1] + 1))
        f.write(" ")
        f.write(str(type))
        f.write("\n")

        if type == 0:
            cost = randInt(hardStart, hardEnd)
            for i in range(domainSiz):
                for j in range(domainSiz):
                    if(j>0): f.write(" ")
                    f.write(str(cost))
                f.write("\n")
        else:
            for i in range(domainSiz):
                for j in range(domainSiz):
                    cost = randInt(1, domainSiz)
                    if(j>0): f.write(" ")
                    f.write(str(cost))
                f.write("\n")

    f.close()


if __name__ == '__main__':
    generateCOP(5, 8, 1, 6)
