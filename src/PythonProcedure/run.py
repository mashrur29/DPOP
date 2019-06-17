import random
import sys
from copy import deepcopy
import numpy
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

def generateCOPDensity(nodeCnt, edgeCnt, root, domainSiz, filename):
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
    g = nw.scale_free_graph(nodeCnt, edgeCnt, False)

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


def generateCOPDomain(g, nodeCnt, edgeCnt, root, domainSiz, filename):
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
    g = nw.gnm_random_graph(100, 50, False)

    for domainSiz in range(5, 35, 5):
        tmp = deepcopy(filename)
        tmp += "simulation"
        tmp += str(ind)
        tmp += ".txt"
        ind += 1
        generateCOPDomain(g, 100, 50, 1, domainSiz, tmp)

    print('Complete')

def variableNode():
    node = 30
    generateCOP(node, randInt(node+1, max_edge(node)), 1, 20)


def variableDensity():
    filename = "/home/mashrur/Dropbox/Thesis/DPOP/DPOP/ScaleFreeSimulations/VariableDensity/"
    node = 30
    ind = 1

    for density in numpy.arange(0.1, 1.0, 0.2):
        edge = (density * (node * (node - 1))) // 2
        edge = int(edge)
        tmp = deepcopy(filename)
        tmp += "simulation"
        tmp += str(ind)
        tmp += ".txt"
        ind += 1
        generateCOPDensity(node, edge, 1, 20, tmp)
    print('Complete')

def max_edge(n):
    return (n*(n-1)//2)

if __name__ == '__main__':
    #variableNode()
    #variableDomain()
    variableDensity()