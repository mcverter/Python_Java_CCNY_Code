import numpy as np
import matplotlib.pyplot as plt
import random

DATAFILE= "/home/mitchell/ComputerScience/479_DigitalLibraries/wei_groupproject/Problem_1/survey_results.txt"
stats = np.loadtxt(fname=DATAFILE,  dtype=np.int,  delimiter=',', usecols=[0,1])
length =  len(stats)
print length 

for n in range(length):
    boot_index = random.randint(0, length-1)
    stats=np.vstack([stats, stats[boot_index]])

means = np.mean(stats, axis=0)
stds = np.std(stats, axis=0)
print means
print stds
print "Bing:  Mean is %f.  Standard Deviation is %f" % (means[0], stds[0])
print "Google:  Mean is %f.  Standard Deviation is %f" % (means[1], stds[1])


plt.axis([0, len(stats)+2, 0, 10])
plt.xlabel("Trial #")
plt.ylabel("Result")
for index in range(len(stats)):
    plt.plot(index+1, stats[index][0], 'ro')
    plt.plot(index+1, stats[index][1], 'g*')
    if(index==0):
        plt.legend("BG")
plt.show()
