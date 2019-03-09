import csv
import matplotlib.pyplot as plt

if __name__ == '__main__':
    x = []
    dpop = []
    improvedDpop = []
    with open('/home/mashrur/Dropbox/Thesis/DPOP/DPOP/timeOutput.csv') as csvfile:
        reader = csv.DictReader(csvfile)

        for row in reader:
            x.append(int(row['nodeCnt']))
            dpop.append(float(row[' DPOP']))
            improvedDpop.append(float(row[' ImprovedDpop']))

    plt.plot(x, dpop, color='g', label='DPOP')
    plt.plot(x, improvedDpop, color='r', label='Improved DPOP')
    plt.xlabel('Number of Node')
    plt.ylabel('Performance (msec)')
    plt.title('Comparison of DPOP Variants')
    plt.legend(loc='upper left')
    # plt.savefig('node_variable_sized_domain.png')
    plt.show()