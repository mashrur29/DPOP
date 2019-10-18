import csv
import matplotlib.pyplot as plt
import numpy as np

def regular():
    x = []
    dpop = []
    bfsdpop = []
    brcdpop = []
    cecdpop = []

    with open('/home/mashrur/Dropbox/Thesis/DPOP/DPOP/timeOutput.csv') as csvfile:
        reader = csv.DictReader(csvfile)

        for row in reader:
            x.append(float(row['nodeCnt']))
            dpop.append(np.log2(float(row['DPOP'])))
            bfsdpop.append(np.log2(float(row['Bfs-DPOP'])))
            brcdpop.append(np.log2(float(row['Brc-DPOP'])))
            cecdpop.append(np.log2(float(row['Cec-DPOP'])))

    yerrDpop = min(0.05, np.std(np.asarray(dpop)))
    yerrBfsDpop = min(0.05, np.std(np.asarray(bfsdpop)))
    yerrBrcDpop = min(0.05, np.std(np.asarray(brcdpop)))
    yerrCecDpop = min(0.05, np.std(np.asarray(cecdpop)))

    font = {'size': 13}

    plt.rc('font', **font)

    plt.errorbar(x, dpop, yerr=yerrDpop, color='b', label='DPOP')
    plt.errorbar(x, bfsdpop, yerr=yerrBfsDpop, color='g', label='Bfs-DPOP')
    plt.errorbar(x, brcdpop, yerr=yerrBrcDpop, color='r', label='Brc-DPOP')
    plt.errorbar(x, cecdpop, yerr=yerrCecDpop, color='y', label='Cec-DPOP')
    plt.xlabel('Number of Variables')
    plt.ylabel('Runtime (msec, log scale)')
    plt.legend(loc='upper left')
    #plt.savefig('node_time_rlfa.pdf')
    plt.show()

def sideBysideBar():
    x = []
    dpop = []
    bfsdpop = []
    brcdpop = []
    cecdpop = []

    with open('/home/mashrur/Dropbox/Thesis/DPOP/DPOP/timeOutput.csv') as csvfile:
        reader = csv.DictReader(csvfile)

        for row in reader:
            x.append(float(row['nodeCnt']))
            dpop.append(np.log2(float(row['DPOP'])))
            bfsdpop.append(np.log2(float(row['Bfs-DPOP'])))
            brcdpop.append(np.log2(float(row['Brc-DPOP'])))
            cecdpop.append(np.log2(float(row['Cec-DPOP'])))

    width = np.min(0.5)
    print(width)
    font = {'size': 13}

    plt.rc('font', **font)

    fig = plt.figure()
    ax = fig.add_subplot(111)
    ax.bar(x - 2*width + (width/2), dpop, width, color='b', label='DPOP')
    ax.bar(x - width + (width/2), bfsdpop, width, color='g', label='Bfs-DPOP')
    ax.bar(x + (width/2), brcdpop, width, color='r', label='Brc-DPOP')
    ax.bar(x + width + (width/2), cecdpop, width, color='y', label='Cec-DPOP')
    ax.legend(loc='upper left')
    ax.set_xlabel('Test histogram')
    plt.savefig('node_time_rlfa.pdf')
    plt.show()

if __name__ == '__main__':
    #sideBysideBar()
    regular()
