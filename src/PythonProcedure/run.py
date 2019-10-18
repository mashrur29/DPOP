import random
import sys
from copy import deepcopy
import numpy
import networkx as nw

hardStart = 1
hardEnd = 2

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

def generateCOPDensity(g, nodeCnt, edgeCnt, root, domainSiz, filename="/home/mashrur/Dropbox/Thesis/DPOP/DPOP/inputRandomGraph.txt"):
    f = open(filename, "w+")
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

    #g = nw.gnm_random_graph(nodeCnt, edgeCnt, False)


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


def generateCOPDomain(g, nodeCnt, edgeCnt, root, domainSiz, filename="/home/mashrur/Dropbox/Thesis/DPOP/DPOP/inputRandomGraph.txt"):
    f = open(str(filename), "w+")
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

def variableDomain():
    filename = "/home/mashrur/Dropbox/Thesis/DPOP/DPOP/simulations/VariableDomain/"
    ind = 1
    nodeCnt = 50
    edgeCnt = 612
    g = nw.gnm_random_graph(nodeCnt, edgeCnt, False)
    domainSiz = 40
    generateCOPDomain(g, nodeCnt, edgeCnt, 1, domainSiz)



    print('Complete')

def variableNode():
    node = 50
    edgeCnt = int((0.5*node*(node-1))//2)
    domainSz = 10
    generateCOP(node, edgeCnt, 1, domainSz)


def variableDensity():
    filename = "/home/mashrur/Dropbox/Thesis/DPOP/DPOP/ScaleFreeSimulations/VariableDensity/"
    nodeCnt = 50
    density = 0.9
    edgeCnt = int((density*nodeCnt*(nodeCnt-1))//2)
    g = nw.gnm_random_graph(nodeCnt, edgeCnt, False)
    generateCOPDensity(g, nodeCnt, edgeCnt, 1, 10)
    print('Complete')

def max_edge(n):
    return (n*(n-1)//2)


def generateCOPRLFA(nodeCnt, edgeCnt, root, domainSiz):
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

        cost = randInt(hardStart, hardEnd)
        for i in range(domainSiz):
            for j in range(domainSiz):
                if (j > 0): f.write(" ")
                f.write(str(cost))
            f.write("\n")



    f.close()

def variableNodeRLFA():
    node = 40
    edgeCnt = int((0.5 * node * (node - 1)) // 2)
    domainSz = 10
    generateCOPRLFA(node, edgeCnt, 1, domainSz)

if __name__ == '__main__':
    #variableNode()
    #variableDomain()
    #variableDensity()
    variableNodeRLFA()