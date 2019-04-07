import csv
import matplotlib.pyplot as plt

if __name__ == '__main__':
    x = []
    dpop = []
    bfsdpop = []
    brcbfsdpop = []
    with open('/home/mashrur/Dropbox/Thesis/DPOP/DPOP/timeOutput.csv') as csvfile:
        reader = csv.DictReader(csvfile)

        for row in reader:
            x.append(int(row['nodeCnt']))
            dpop.append(float(row['DPOP']))
            bfsdpop.append(float(row['BfsDpop']))
            brcbfsdpop.append(float(row['BrcBfsDpop']))

    plt.plot(x, dpop, color='b', label='DPOP')
    plt.plot(x, bfsdpop, color='g', label='Bfs DPOP')
    plt.plot(x, brcbfsdpop, color='r', label='Brc Bfs DPOP')
    plt.xlabel('Number of Node')
    plt.ylabel('Performance (msec)')
    plt.title('Comparison of DPOP Variants')
    plt.legend(loc='upper left')
    # plt.savefig('node_variable_sized_domain.png')
    plt.show()